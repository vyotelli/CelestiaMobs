package com.magmaguy.elitemobs.items;

import com.magmaguy.elitemobs.MetadataHandler;
import com.magmaguy.elitemobs.adventurersguild.GuildRank;
import com.magmaguy.elitemobs.api.EliteMobDeathEvent;
import com.magmaguy.elitemobs.config.AdventurersGuildConfig;
import com.magmaguy.elitemobs.config.ItemSettingsConfig;
import com.magmaguy.elitemobs.config.ProceduralItemGenerationSettingsConfig;
import com.magmaguy.elitemobs.items.customenchantments.SoulbindEnchantment;
import com.magmaguy.elitemobs.items.customitems.CustomItem;
import com.magmaguy.elitemobs.items.itemconstructor.ItemConstructor;
import com.magmaguy.elitemobs.mobconstructor.EliteMobEntity;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import static com.magmaguy.elitemobs.utils.WeightedProbability.pickWeighedProbability;

/**
 * Created by MagmaGuy on 04/06/2017.
 */
public class LootTables implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDeath(EliteMobDeathEvent event) {

        if (!event.getEliteMobEntity().getHasSpecialLoot()) return;
        if (event.getEliteMobEntity().getLevel() < 2) return;
        if (event.getEliteMobEntity().getDamagers().isEmpty()) return;

        generatePlayerLoot(event.getEliteMobEntity());

    }

    public static void generatePlayerLoot(EliteMobEntity eliteMobEntity) {
        if (eliteMobEntity.getTriggeredAntiExploit())
            return;
        for (Player player : eliteMobEntity.getDamagers().keySet()) {

            if (eliteMobEntity.getDamagers().get(player) / eliteMobEntity.getMaxHealth() < 0.1)
                return;

            new ItemLootShower(eliteMobEntity.getTier(), eliteMobEntity.getLivingEntity().getLocation(), player);

            Item item = null;

            if (AdventurersGuildConfig.guildLootLimiter) {
                double itemTier = setItemTier((int) eliteMobEntity.getTier());
                if (itemTier < 100 && itemTier > GuildRank.getRank(player) * 10) {
                    itemTier = GuildRank.getRank(player) * 10;
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(AdventurersGuildConfig.lootLimiterMessage));
                        }
                    }.runTaskLater(MetadataHandler.PLUGIN, 20 * 10);
                }
                item = generateLoot((int) Math.floor(itemTier), eliteMobEntity);
            } else
                item = generateLoot(eliteMobEntity);

            if (item != null &&
                    item.getItemStack() != null &&
                    item.getItemStack().hasItemMeta() &&
                    item.getItemStack().getItemMeta().hasDisplayName()) {
                item.setCustomName(item.getItemStack().getItemMeta().getDisplayName());
                item.setCustomNameVisible(true);
            }

            SoulbindEnchantment.addEnchantment(item, player);

            if (item == null) return;

            RareDropEffect.runEffect(item);
        }
    }

    private static boolean proceduralItemsOn = ProceduralItemGenerationSettingsConfig.doProceduralItemDrops;
    private static boolean customItemsOn = ItemSettingsConfig.doEliteMobsLoot && !CustomItem.getCustomItemStackList().isEmpty();
    private static boolean weighedItemsExist = CustomItem.getWeighedFixedItems() != null && !CustomItem.getWeighedFixedItems().isEmpty();
    private static boolean fixedItemsExist = CustomItem.getFixedItems() != null && !CustomItem.getFixedItems().isEmpty();
    private static boolean limitedItemsExist = CustomItem.getLimitedItem() != null && !CustomItem.getLimitedItem().isEmpty();
    private static boolean scalableItemsExist = CustomItem.getScalableItems() != null && !CustomItem.getScalableItems().isEmpty();

    private static Item generateLoot(EliteMobEntity eliteMobEntity) {

        int mobTier = (int) MobTierCalculator.findMobTier(eliteMobEntity);

        /*
        Add some wiggle room to avoid making obtaining loot too linear
         */
        int itemTier = (int) setItemTier(mobTier);

        return generateLoot(itemTier, eliteMobEntity);

    }

    private static Item generateLoot(int itemTier, EliteMobEntity eliteMobEntity) {

         /*
        Handle the odds of an item dropping
         */
        double baseChance = ItemSettingsConfig.flatDropRate;
        double dropChanceBonus = ItemSettingsConfig.tierIncreaseDropRate * itemTier;

        if (ThreadLocalRandom.current().nextDouble() > baseChance + dropChanceBonus)
            return null;

        HashMap<String, Double> weightedProbability = new HashMap<>();
        if (proceduralItemsOn)
            weightedProbability.put("procedural", ItemSettingsConfig.proceduralItemWeight);
        if (customItemsOn) {
            if (weighedItemsExist)
                weightedProbability.put("weighed", ItemSettingsConfig.weighedItemWeight);
            if (fixedItemsExist)
                if (CustomItem.getFixedItems().containsKey(itemTier))
                    weightedProbability.put("fixed", ItemSettingsConfig.fixedItemWeight);
            if (limitedItemsExist)
                weightedProbability.put("limited", ItemSettingsConfig.limitedItemWeight);
            if (scalableItemsExist)
                weightedProbability.put("scalable", ItemSettingsConfig.scalableItemWeight);
        }

        String selectedLootSystem = pickWeighedProbability(weightedProbability);

        switch (selectedLootSystem) {
            case "procedural":
                return dropProcedurallyGeneratedItem(itemTier, eliteMobEntity);
            case "weighed":
                return dropWeighedFixedItem(eliteMobEntity.getLivingEntity().getLocation());
            case "fixed":
                return dropFixedItem(eliteMobEntity, itemTier);
            case "limited":
                return dropLimitedItem(eliteMobEntity, itemTier);
            case "scalable":
                return dropScalableItem(eliteMobEntity, itemTier);
        }

        return null;

    }

    public static double setItemTier(int mobTier) {

        double chanceToUpgradeTier = 10 / (double) mobTier * ItemSettingsConfig.maximumLootTier;

        if (ThreadLocalRandom.current().nextDouble() * 100 < chanceToUpgradeTier)
            return mobTier + 1;


        double diceRoll = ThreadLocalRandom.current().nextDouble();

        /*
        10% of the time, give an item a tier below what the player is wearing
        40% of the time, give an item of the same tier as what the player is wearing
        50% of the time, give an item better than what the player is wearing
        If you're wondering why this isn't configurable, wonder instead why no one has noticed it isn't before you reading this
         */
        if (diceRoll < 0.10)
            mobTier -= 2;
        else if (diceRoll < 0.50)
            mobTier -= 1;

        if (mobTier < 0) mobTier = 0;

        return mobTier;

    }


    private static Item dropWeighedFixedItem(Location location) {

        double totalWeight = 0;

        for (ItemStack itemStack : CustomItem.getWeighedFixedItems().keySet())
            totalWeight += CustomItem.getWeighedFixedItems().get(itemStack);

        ItemStack generatedItemStack = null;
        double random = Math.random() * totalWeight;

        for (ItemStack itemStack : CustomItem.getWeighedFixedItems().keySet()) {
            random -= CustomItem.getWeighedFixedItems().get(itemStack);
            if (random <= 0) {
                generatedItemStack = itemStack;
                break;
            }
        }

        return location.getWorld().dropItem(location, generatedItemStack);

    }

    private static Item dropProcedurallyGeneratedItem(int tierLevel, EliteMobEntity eliteMobEntity) {

        ItemStack randomLoot = ItemConstructor.constructItem(tierLevel, eliteMobEntity);
        return eliteMobEntity.getLivingEntity().getWorld().dropItem(eliteMobEntity.getLivingEntity().getLocation(), randomLoot);

    }

    private static Item dropScalableItem(EliteMobEntity eliteMobEntity, int itemTier) {

        return eliteMobEntity.getLivingEntity().getWorld().dropItem(eliteMobEntity.getLivingEntity().getLocation(),
                ScalableItemConstructor.randomizeScalableItem(itemTier));

    }

    private static Item dropLimitedItem(EliteMobEntity eliteMobEntity, int itemTier) {

        return eliteMobEntity.getLivingEntity().getWorld().dropItem(eliteMobEntity.getLivingEntity().getLocation(),
                ScalableItemConstructor.randomizeLimitedItem(itemTier));

    }

    private static Item dropFixedItem(EliteMobEntity eliteMobEntity, int itemTier) {

        return eliteMobEntity.getLivingEntity().getWorld().dropItem(eliteMobEntity.getLivingEntity().getLocation(),
                CustomItem.getFixedItems().get(itemTier).get(ThreadLocalRandom.current().nextInt(CustomItem.getFixedItems().get(itemTier).size())).generateDefaultsItemStack());

    }

}

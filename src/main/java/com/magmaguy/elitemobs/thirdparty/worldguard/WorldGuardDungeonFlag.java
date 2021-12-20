package com.magmaguy.elitemobs.thirdparty.worldguard;

import com.magmaguy.elitemobs.EliteMobs;
import com.magmaguy.elitemobs.entitytracker.EntityTracker;
import com.magmaguy.elitemobs.mobconstructor.EliteEntity;
import com.magmaguy.elitemobs.mobconstructor.custombosses.CustomBossEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class WorldGuardDungeonFlag implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onSpawn(CreatureSpawnEvent event) {
        if (!EliteMobs.worldGuardIsEnabled) return;
        if (event.getEntity().getType().equals(EntityType.ARMOR_STAND) ||
                event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM))
            return;
        if (WorldGuardFlagChecker.checkFlag(event.getLocation(), WorldGuardCompatibility.getEliteMobsDungeonFlag())) {
            EliteEntity eliteEntity = EntityTracker.getEliteMobEntity(event.getEntity());
            if (eliteEntity instanceof CustomBossEntity)
                return;
            event.setCancelled(true);
            event.getEntity().remove();
        }
    }

}

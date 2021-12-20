package com.magmaguy.elitemobs.quests.objectives;

import com.magmaguy.elitemobs.entitytracker.EntityTracker;
import com.magmaguy.elitemobs.npcs.NPCEntity;
import com.magmaguy.elitemobs.playerdata.database.PlayerData;
import com.magmaguy.elitemobs.quests.Quest;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class DialogObjective extends Objective {

    @Getter
    private final String targetLocation;
    @Getter
    private final String npcFilename;
    @Getter
    private final String dialog;

    public DialogObjective(String npcFilename, String npcName, String targetLocation, String dialog) {
        super(1, npcName);
        this.targetLocation = targetLocation;
        this.npcFilename = npcFilename;
        this.dialog = dialog.replace("\\n", System.lineSeparator());
    }

    public boolean checkProgress(Player player, QuestObjectives questObjectives) {
        if (super.currentAmount >= super.targetAmount) return false;
        progressObjective(questObjectives);
        player.sendMessage(dialog);
        return true;
    }

    public static class DialogObjectiveEvents implements Listener {
        @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
        public void onInteract(PlayerInteractAtEntityEvent event) {
            NPCEntity npcEntity = EntityTracker.getNPCEntity(event.getRightClicked());
            if (npcEntity == null) return;
            for (Quest quest : PlayerData.getQuests(event.getPlayer().getUniqueId()))
                for (Objective objective : quest.getQuestObjectives().getObjectives())
                    if (objective instanceof DialogObjective &&
                            ((DialogObjective) objective).getNpcFilename().equals(npcEntity.getNpCsConfigFields().getFilename()))
                        if (((DialogObjective) objective).checkProgress(event.getPlayer(), quest.getQuestObjectives()))
                            event.setCancelled(true);

        }
    }

}

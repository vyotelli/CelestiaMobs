package com.magmaguy.elitemobs.commands.quests;

import com.magmaguy.elitemobs.quests.CustomQuest;
import org.bukkit.entity.Player;

public class QuestCommand {

    // /em quest join questFilename
    public static void joinQuest(String questFilename, Player player){
        CustomQuest.startQuest(questFilename, player);
    }

    // /em quest leave questFilename
    public static void leaveQuest(String questFilename, Player player){
        CustomQuest.stopPlayerQuest(player);
    }

}

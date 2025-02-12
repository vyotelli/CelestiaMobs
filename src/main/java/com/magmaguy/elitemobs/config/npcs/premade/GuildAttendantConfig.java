package com.magmaguy.elitemobs.config.npcs.premade;

import com.magmaguy.elitemobs.config.npcs.NPCsConfigFields;
import com.magmaguy.elitemobs.npcs.NPCInteractions;
import org.bukkit.entity.Villager;

import java.util.Arrays;

public class GuildAttendantConfig extends NPCsConfigFields {
    public GuildAttendantConfig() {
        super("guild_attendant",
                true,
                "Gillian",
                "<Guild Attendant>",
                Villager.Profession.LIBRARIAN,
                "em_adventurers_guild,283.5,91,229.5,179,0",
                Arrays.asList(
                        "Welcome to the\\nAdventurer's Guild!",
                        "Welcome!"),
                Arrays.asList(
                        "Check the questboard to\\nsee active quests!",
                        "You can talk to me to\\nchange your guild rank!",
                        "You can sell items to the\\nblacksmith for coins!",
                        "You can talk to the arena\\nmaster to take the arena on!",
                        "You can buy equipment from\\nthe blacksmith!",
                        "You can talk to the combat\\nmaster to check your combat level!",
                        "Unlocking new guid tiers\\nincreases your maximum health!"),
                Arrays.asList(
                        "See you soon!",
                        "Thanks for stopping by!",
                        "Happy hunting!"),
                true,
                3,
                NPCInteractions.NPCInteractionType.GUILD_GREETER);
    }
}

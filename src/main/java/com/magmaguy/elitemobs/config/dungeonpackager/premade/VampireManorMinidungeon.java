package com.magmaguy.elitemobs.config.dungeonpackager.premade;

import com.magmaguy.elitemobs.config.dungeonpackager.DungeonPackagerConfigFields;
import com.magmaguy.elitemobs.utils.DiscordLinks;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class VampireManorMinidungeon extends DungeonPackagerConfigFields {
    public VampireManorMinidungeon() {
        super("vampire_manor",
                false,
                "&cThe Vampire Manor",
                DungeonLocationType.SCHEMATIC,
                Arrays.asList("&fPrepare to spill blood!",
                        "&6Credits: MagmaGuy, 69OzCanOfBepis"),
                Arrays.asList(
                        "vampire_grunt.yml:5.0,0.0,20.0",
                        "vampire_grunt.yml:-5.0,0.0,20.0",
                        "vampire_brute.yml:17.0,0.0,29.0",
                        "vampire_brute.yml:17.0,0.0,19.0",
                        "vampire_brute.yml:35.0,0.0,61.0",
                        "vampire_grunt.yml:35.0,0.0,70.0",
                        "vampire_grunt.yml:30.0,0.0,68.0",
                        "vampire_brute.yml:16.0,14.0,77.0",
                        "vampire_brute.yml:0.0,14.0,79.0",
                        "vampire_brute.yml:-16.0,14.0,77.0",
                        "vampire_brute.yml:-31.0,12.0,72.0",
                        "vampire_grunt.yml:-31.0,12.0,83.0",
                        "vampire_grunt.yml:30.0,12.0,81.0",
                        "vampire_brute.yml:31.0,12.0,72.0",
                        "vampire_necromancer.yml:-26.0,0.0,43.0",
                        "vampire_necromancer.yml:-28.0,0.0,47.0",
                        "vampire_necromancer.yml:-23.0,12.0,39.0",
                        "vampire_necromancer.yml:-30.0,12.0,39.0",
                        "vampire_grunt.yml:-32.0,0.0,22.0",
                        "vampire_grunt.yml:-27.0,0.0,15.0",
                        "vampire_grunt.yml:-26.0,0.0,29.0",
                        "vampire_grunt.yml:-18.0,12.0,21.0",
                        "vampire_grunt.yml:-35.0,12.0,21.0",
                        "vampire_grunt.yml:4.0,-11.0,57.0",
                        "vampire_grunt.yml:12.0,-11.0,66.0",
                        "vampire_fledgling.yml:5.0,-10.0,54.0",
                        "vampire_fledgling.yml:14.0,-12.0,70.0",
                        "vampire_fledgling.yml:6.0,-11.0,65.0",
                        "vampire_fledgling.yml:6.0,-13.0,87.0",
                        "vampire_fledgling.yml:-7.0,-12.0,89.0",
                        "vampire_fledgling.yml:-20.0,-10.0,81.0",
                        "vampire_necromancer.yml:-46.0,-9.0,24.0",
                        "vampire_necromancer.yml:-40.0,-9.0,18.0",
                        "vampire_necromancer.yml:-33.0,-9.0,24.0",
                        "vampire_grunt.yml:-35.0,-9.0,28.0",
                        "vampire_grunt.yml:-44.0,-9.0,28.0",
                        "vampire_grunt.yml:-45.0,-9.0,18.0",
                        "vampire_grunt.yml:-34.0,-9.0,17.0",
                        "vampire_grunt.yml:-14.0,0.0,62.0",
                        "vampire_grunt.yml:0.0,0.0,62.0",
                        "vampire_grunt.yml:13.0,0.0,62.0",
                        "vampire_brute.yml:0.0,2.0,78.0",
                        "vampire_brute.yml:-16.0,2.0,78.0",
                        "vampire_brute.yml:16.0,2.0,78.0",
                        "vampire_grunt.yml:35.0,0.0,33.0",
                        "vampire_grunt.yml:35.0,0.0,22.0",
                        "vampire_grunt.yml:31.0,0.0,13.0",
                        "vampire_grunt.yml:30.0,0.0,46.0",
                        "vampire_grunt.yml:25.0,0.0,36.0",
                        "vampire_necromancer.yml:30.0,1.0,30.0",
                        "vampire_grunt.yml:24.0,12.0,54.0",
                        "vampire_grunt.yml:17.0,12.0,42.0",
                        "vampire_grunt.yml:17.0,12.0,28.0",
                        "vampire_grunt.yml:-11.0,6.0,31.0",
                        "vampire_grunt.yml:10.0,6.0,31.0",
                        "vampire_grunt.yml:-30.0,0.0,67.0",
                        "vampire_grunt.yml:-33.0,0.0,76.0",
                        "vampire_grunt.yml:30.0,-11.0,33.0",
                        "vampire_grunt.yml:24.0,-11.0,33.0",
                        "vampire_necromancer.yml:19.0,-13.0,22.0",
                        "vampire_necromancer.yml:36.0,-13.0,23.0",
                        "vampire_grunt.yml:31.0,-11.0,13.0",
                        "vampire_grunt.yml:23.0,-11.0,14.0",
                        "vampire_necromancer.yml:2.0,-15.0,23.0",
                        "vampire_necromancer.yml:-26.0,-10.0,54.0",
                        "vampire_necromancer.yml:-9.0,46.0,48.0",
                        "vampire_brute.yml:-10.0,46.0,62.0",
                        "vampire_brute.yml:-9.0,46.0,33.0",
                        "vampire_brute.yml:13.0,61.0,48.0",
                        "vampire_brute.yml:0.0,61.0,60.0",
                        "vampire_necromancer_dining_hall.yml:17.0,12.0,17.0",
                        "vampire_necromancer_left_bedroom.yml:32.0,13.0,65.0",
                        "vampire_necromancer_right_bedroom.yml:-32.0,13.0,65.0",
                        "vampire_necromancer_supply_room.yml:-27.0,0.0,82.0",
                        "vampire_necromancer_gargoyle_rush.yml:-40.0,-9.0,57.0",
                        "vampire_brute_gargoyle_rush_right.yml:-38.0,-9.0,57.0",
                        "vampire_brute_gargoyle_rush_left.yml:-42.0,-9.0,57.0",
                        "vampire_grunt_gargoyle_rush_right.yml:-36.0,-9.0,57.0",
                        "vampire_grunt_gargoyle_rush_left.yml:-44.0,-9.0,57.0",
                        "vampire_king.yml:-13.0,30.0,48.0",
                        "vampire_wraith.yml:-47.0,-20.0,52.0",
                        "vampire_brute.yml:-18.0,12.0,16.0",
                        "vampire_necromancer_supreme_coffin_breaker.yml:2.0,-15.0,23.0",
                        "vampire_necromancer_supreme_gargoyle_overlord.yml:58.0,-15.0,23.0",
                        "vampire_fledgling_gargoyle_overlord.yml:58.0,-15.0,16.0",
                        "vampire_fledgling_gargoyle_overlord.yml:52.0,-15.0,16.0",
                        "vampire_fledgling_gargoyle_overlord.yml:48.0,-15.0,20.0",
                        "vampire_fledgling_gargoyle_overlord.yml:48.0,-15.0,26.0",
                        "vampire_fledgling_gargoyle_overlord.yml:52.0,-15.0,30.0",
                        "vampire_fledgling_gargoyle_overlord.yml:58.0,-15.0,30.0",
                        "vampire_fledgling_gargoyle_overlord.yml:48.0,-15.0,20.0",
                        "vampire_zombie.yml:-7.0,-25.0,65.0",
                        "vampire_zombie.yml:-19.0,-25.0,68.0",
                        "vampire_zombie.yml:-14.0,-25.0,51.0",
                        "vampire_zombie.yml:-2.0,-24.0,78.0",
                        "vampire_zombie.yml:-4.0,-26.0,43.0",
                        "vampire_zombie.yml:7.0,-24.0,78.0",
                        "vampire_zombie.yml:-30.0,-18.0,44.0",
                        "vampire_zombie.yml:-31.0,-18.0,34.0",
                        "vampire_zombie.yml:-26.0,-18.0,57.0",
                        "vampire_necromancer_entrance.yml:0.0,8.0,32.0",
                        "vampire_necromancer_foresight_pool.yml:-19.0,0.0,22.0",
                        "vampire_necromancer_roof.yml:-12.0,61.0,48.0",
                        "vampire_brute.yml:0.0,61.0,35.0",
                        "vampire_brute.yml:17.0,26.0,9.0",
                        "vampire_brute.yml:-17.0,26.0,9.0",
                        "vampire_brute.yml:-39.0,26.0,31.0",
                        "vampire_brute.yml:-39.0,26.0,65.0",
                        "vampire_brute.yml:-17.0,26.0,87.0",
                        "vampire_brute.yml:17.0,26.0,87.0",
                        "vampire_brute.yml:39.0,26.0,65.0",
                        "vampire_brute.yml:39.0,26.0,31.0",
                        "vampire_necromancer_roof_north.yml:0.0,26.0,8.0",
                        "vampire_necromancer_roof_east.yml:40.0,26.0,48.0",
                        "vampire_necromancer_roof_south.yml:0.0,26.0,88.0",
                        "vampire_necromancer_roof_west.yml:-40.0,26.0,48.0"),
                Arrays.asList(
                        "vampire_manor_south.yml:16.5,11.5,13.5",
                        "vampire_manor_west.yml:-17.5,11.5,20.5",
                        "vampire_manor_east.yml:-28.5,-0.5,44.5",
                        "vampire_manor_north.yml:26.5,11.5,83.5",
                        "vampire_manor_north.yml:-27.5,11.5,83.5",
                        "vampire_manor_north.yml:-0.5,25.5,93.5",
                        "vampire_manor_north.yml:-0.5,60.5,37.5",
                        "vampire_manor_south.yml:-0.5,25.5,1.5",
                        "vampire_manor_south.yml:-0.5,60.5,57.5",
                        "vampire_manor_east.yml:-46.5,25.5,47.5",
                        "vampire_manor_west.yml:-10.5,60.5,47.5",
                        "vampire_manor_west.yml:45.5,25.5,47.5",
                        "vampire_manor_east.yml:-54.5,-19.5,54.5",
                        "vampire_manor_south.yml:-3.5,-25.5,37.5"),
                DiscordLinks.premiumMinidungeons,
                DungeonSizeCategory.MINIDUNGEON,
                null,
                "em_vampire_manor.schem",
                null,
                true,
                new Vector(67, -28, 0),
                new Vector(-57, 112, 97),
                new Vector(0, 0, 0),
                0D,
                0D,
                0,
                "Difficulty: &6Hard\n" +
                        "$bossCount bosses, from level $lowestTier to $highestTier\n" +
                        "&6A deadly manor full of blood thristy creatures!\n" +
                        "&6Are you ready to face that which haunts the night?",
                "&8[EM] &cTrespassing on dangerous grounds!!",
                "&8[EM] &cNow leaving haunted land!");
    }
}

package io.github.paulanthonyreitz.reitzmmo.ConfigFiles;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Paul on 3/23/2016.
 */
public class MonsterConfig {
    public static void Configuration()
    {
        File file = FileManager.monsterHPConfig;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().header("This config is used to set all monster related configurations");

        configuration.addDefault("General", null);
        configuration.addDefault("General.blocks-per-mob-level", 150);
        configuration.addDefault("General.nameplates-enabled", true);
        configuration.addDefault("General.apply-on-spawner-spawns", true);

        configuration.addDefault("Blaze", null);
        configuration.addDefault("Blaze.base_hp", 3);
        configuration.addDefault("Blaze.base_attack", 3);
        configuration.addDefault("Blaze.speed", 1);
        configuration.addDefault("Blaze.min_level", 1);
        configuration.addDefault("Blaze.nameplates_enabled", true);

        configuration.addDefault("Cavespider", null);
        configuration.addDefault("Cavespider.base_hp", 3);
        configuration.addDefault("Cavespider.base_attack", 3);
        configuration.addDefault("Cavespider.speed", 1);
        configuration.addDefault("Cavespider.min_level", 1);
        configuration.addDefault("Cavespider.nameplates_enabled", true);

        configuration.addDefault("Chicken", null);
        configuration.addDefault("Chicken.base_hp", 1);
        configuration.addDefault("Chicken.speed", 1);
        configuration.addDefault("Chicken.min_level", 1);
        configuration.addDefault("Chicken.nameplates_enabled", true);

        configuration.addDefault("Cow", null);
        configuration.addDefault("Cow.base_hp", 1);
        configuration.addDefault("Cow.speed", 1);
        configuration.addDefault("Cow.min_level", 1);
        configuration.addDefault("Cow.nameplates_enabled", true);

        configuration.addDefault("Creeper", null);
        configuration.addDefault("Creeper.base_attack", 4);
        configuration.addDefault("Creeper.base_hp", 7);
        configuration.addDefault("Creeper.speed", 1.2);
        configuration.addDefault("Creeper.min_level", 1);
        configuration.addDefault("Creeper.nameplates_enabled", true);

        configuration.addDefault("Enderdragon", null);
        configuration.addDefault("Enderdragon.base_hp", 5);
        configuration.addDefault("Enderdragon.base_attack", 3);
        configuration.addDefault("Enderdragon.speed", 1.2);
        configuration.addDefault("Enderdragon.min_level", 50);
        configuration.addDefault("Enderdragon.nameplates_enabled", true);

        configuration.addDefault("Enderman", null);
        configuration.addDefault("Enderman.base_hp", 4);
        configuration.addDefault("Enderman.base_attack", 3);
        configuration.addDefault("Enderman.speed", 1);
        configuration.addDefault("Enderman.min_level", 10);
        configuration.addDefault("Enderman.nameplates_enabled", true);

        configuration.addDefault("Endermite", null);
        configuration.addDefault("Endermite.base_hp", 3);
        configuration.addDefault("Endermite.base_attack", 3);
        configuration.addDefault("Endermite.speed", 1);
        configuration.addDefault("Endermite.min_level", 5);
        configuration.addDefault("Endermite.nameplates_enabled", true);

        configuration.addDefault("Ghast", null);
        configuration.addDefault("Ghast.base_hp", 4);
        configuration.addDefault("Ghast.base_attacK", 3);
        configuration.addDefault("Ghast.speed", 1);
        configuration.addDefault("Ghast.min_level", 25);
        configuration.addDefault("Ghast.nameplates_enabled", true);

        configuration.addDefault("Giant", null);
        configuration.addDefault("Giant.base_hp", 4);
        configuration.addDefault("Giant.base_attack", 3);
        configuration.addDefault("Giant.speed", 1);
        configuration.addDefault("Giant.min_level", 10);
        configuration.addDefault("Giant.nameplates_enableD", true);

        configuration.addDefault("Golem", null);
        configuration.addDefault("Golem.base_hp", 80);
        configuration.addDefault("Golem.base_attack", 10);
        configuration.addDefault("Golem.speed", 1);
        configuration.addDefault("Golem.min_leveL", 10);
        configuration.addDefault("Golem.nameplates_enabled", true);

        configuration.addDefault("Guardian", null);
        configuration.addDefault("Guardian.base_hp", 4);
        configuration.addDefault("Guardian.base_attack", 3);
        configuration.addDefault("Guardian.speed", 1);
        configuration.addDefault("Guardian.min_level", 12);
        configuration.addDefault("Guardian.nameplates_enabled", true);

        configuration.addDefault("Magmacube", null);
        configuration.addDefault("Magmacube.base_hp", 4);
        configuration.addDefault("Magmacube.base_attack", 3);
        configuration.addDefault("Magmacube.speed", 1);
        configuration.addDefault("Magmacube.min_level", 5);
        configuration.addDefault("Magmacube.nameplates_enabled", true);

        configuration.addDefault("Mushroomcow", null);
        configuration.addDefault("Mushroomcow.base_hp", 2);
        configuration.addDefault("Mushroomcow.speed", 1);
        configuration.addDefault("Mushroomcow.min_level", 1);
        configuration.addDefault("Mushroomcow.nameplates_enabled", true);

        configuration.addDefault("Pig", null);
        configuration.addDefault("Pig.base_hp", 1);
        configuration.addDefault("Pig.speed", 1);
        configuration.addDefault("Pig.min_level", 1);
        configuration.addDefault("Pig.nameplates_enabled", true);

        configuration.addDefault("Pigzombie", null);
        configuration.addDefault("Pigzombie.base_hp", 4);
        configuration.addDefault("Pigzombie.base_attack", 3);
        configuration.addDefault("Pigzombie.speed", 1);
        configuration.addDefault("Pigzombie.min_level", 8);
        configuration.addDefault("Pigzombie.nameplates_enabled", true);

        configuration.addDefault("Rabbit", null);
        configuration.addDefault("Rabbit.base_hp", 2);
        configuration.addDefault("Rabbit.speed", 1);
        configuration.addDefault("Rabbit.min_level", 1);
        configuration.addDefault("Rabbit.nameplates_enabled", true);

        configuration.addDefault("Sheep", null);
        configuration.addDefault("Sheep.base_hp", 2);
        configuration.addDefault("Sheep.speed", 1);
        configuration.addDefault("Sheep.min_level", 1);
        configuration.addDefault("Sheep.nameplates_enabled", true);

        configuration.addDefault("Fox", null);
        configuration.addDefault("Fox.base_hp", 2);
        configuration.addDefault("Fox.speed", 1.5);
        configuration.addDefault("Fox.min_level", 1);
        configuration.addDefault("Fox.nameplates_enabled", true);

        configuration.addDefault("Panda", null);
        configuration.addDefault("Panda.base_hp", 1);
        configuration.addDefault("Panda.speed", 2);
        configuration.addDefault("Panda.min_level", 1);
        configuration.addDefault("Panda.nameplates_enabled", true);

        configuration.addDefault("Silverfish", null);
        configuration.addDefault("Silverfish.base_hp", 2);
        configuration.addDefault("Silverfish.base_attack", 3);
        configuration.addDefault("Silverfish.speed", 1);
        configuration.addDefault("Silverfish.min_level", 1);
        configuration.addDefault("Silverfish.nameplates_enabled", true);

        configuration.addDefault("Skeleton", null);
        configuration.addDefault("Skeleton.base_hp", 3);
        configuration.addDefault("Skeleton.base_attack", 1);
        configuration.addDefault("Skeleton.speed", 1);
        configuration.addDefault("Skeleton.min_level", 2);
        configuration.addDefault("Skeleton.nameplates_enabled", true);

        configuration.addDefault("Slime", null);
        configuration.addDefault("Slime.base_hp", 4);
        configuration.addDefault("Slime.base_attack", 3);
        configuration.addDefault("Slime.speed", 1);
        configuration.addDefault("Slime.min_level", 3);
        configuration.addDefault("Slime.nameplates_enabled", true);

        configuration.addDefault("Snowman", null);
        configuration.addDefault("Snowman.base_hp", 4);
        configuration.addDefault("Snowman.base_attack", 4);
        configuration.addDefault("Snowman.speed", 1);
        configuration.addDefault("Snowman.min_level", 5);
        configuration.addDefault("Snowman.nameplates_enabled", true);

        configuration.addDefault("Spider", null);
        configuration.addDefault("Spider.base_hp", 3);
        configuration.addDefault("Spider.base_attack", 3);
        configuration.addDefault("Spider.speed", 1.2);
        configuration.addDefault("Spider.min_level", 2);
        configuration.addDefault("Spider.nameplates_enabled", true);

        configuration.addDefault("Squid", null);
        configuration.addDefault("Squid.base_hp", 2);
        configuration.addDefault("Squid.speed", 1);
        configuration.addDefault("Squid.min_level", 1);
        configuration.addDefault("Squid.nameplates_enabled", true);

        configuration.addDefault("Villager", null);
        configuration.addDefault("Villager.base_hp", 4);
        configuration.addDefault("Villager.speed", 1);
        configuration.addDefault("Villager.min_level", 1);
        configuration.addDefault("Villager.nameplates_enabled", true);

        configuration.addDefault("Wolf", null);
        configuration.addDefault("Wolf.base_hp", 3);
        configuration.addDefault("Wolf.base_attack", 3);
        configuration.addDefault("Wolf.speed", 1.5);
        configuration.addDefault("Wolf.min_level", 1);
        configuration.addDefault("Wolf.nameplates_enabled", true);

        configuration.addDefault("Zombie", null);
        configuration.addDefault("Zombie.base_hp", 3);
        configuration.addDefault("Zombie.base_attack", 2);
        configuration.addDefault("Zombie.speed", 1.2);
        configuration.addDefault("Zombie.min_level", 1);
        configuration.addDefault("Zombie.nameplates_enableD", true);

        configuration.addDefault("Witch", null);
        configuration.addDefault("Witch.base_hp", 4);
        configuration.addDefault("Witch.base_attack", 3);
        configuration.addDefault("Witch.speed", 1);
        configuration.addDefault("Witch.min_level", 8);
        configuration.addDefault("Witch.nameplates_enabled", true);

        configuration.addDefault("Witherskeleton", null);
        configuration.addDefault("Witherskeleton.base_hp", 6);
        configuration.addDefault("Witherskeleton.base_attack", 3);
        configuration.addDefault("Witherskeleton.speed", 1);
        configuration.addDefault("Witherskeleton.min_level", 11);
        configuration.addDefault("Witherskeleton.nameplates_enabled", true);

        configuration.addDefault("Shulker", null);
        configuration.addDefault("Shulker.base_hp", 6);
        configuration.addDefault("Shulker.base_attack", 3);
        configuration.addDefault("Shulker.speed", 1);
        configuration.addDefault("Shulker.min_level", 15);
        configuration.addDefault("Shulker.nameplates_enabled", true);

        configuration.addDefault("Pillager", null);
        configuration.addDefault("Pillager.base_hp", 5);
        configuration.addDefault("Pillager.base_attack", 4);
        configuration.addDefault("Pillager.speed", 2);
        configuration.addDefault("Pillager.min_level", 8);
        configuration.addDefault("Pillager.nameplates_enabled", true);

        configuration.addDefault("Illusioner", null);
        configuration.addDefault("Illusioner.base_hp", 5);
        configuration.addDefault("Illusioner.base_attack", 4);
        configuration.addDefault("Illusioner.speed", 1);
        configuration.addDefault("Illusioner.min_level", 7);
        configuration.addDefault("Illusioner.nameplates_enabled", true);

        configuration.addDefault("Evoker", null);
        configuration.addDefault("Evoker.base_hp", 5);
        configuration.addDefault("Evoker.base_attack", 4);
        configuration.addDefault("Evoker.speed", 1);
        configuration.addDefault("Evoker.min_level", 10);
        configuration.addDefault("Evoker.nameplates_enabled", true);

        configuration.addDefault("Ravager", null);
        configuration.addDefault("Ravager.base_hp", 5);
        configuration.addDefault("Ravager.base_attack", 5);
        configuration.addDefault("Ravager.speed", 1);
        configuration.addDefault("Ravager.min_level", 1);
        configuration.addDefault("Ravager.nameplates_enabled", true);

        configuration.addDefault("Bat", null);
        configuration.addDefault("Bat.base_hp", 3);
        configuration.addDefault("Bat.base_attack", 3);
        configuration.addDefault("Bat.speed", 1.5);
        configuration.addDefault("Bat.min_level", 1);
        configuration.addDefault("Bat.nameplates_enabled", true);

        configuration.addDefault("Drowned", null);
        configuration.addDefault("Drowned.base_hp", 3);
        configuration.addDefault("Drowned.base_attack", 2);
        configuration.addDefault("Drowned.speed", 1.2);
        configuration.addDefault("Drowned.min_level", 3);
        configuration.addDefault("Drowned.nameplates_enabled", true);

        configuration.addDefault("Husk", null);
        configuration.addDefault("Husk.base_hp", 3);
        configuration.addDefault("Husk.base_attack", 2);
        configuration.addDefault("Husk.speed", 1);
        configuration.addDefault("Husk.min_level", 3);
        configuration.addDefault("Husk.nameplates_enabled", true);

        configuration.addDefault("Zombievillager", null);
        configuration.addDefault("Zombievillager.base_hp", 3);
        configuration.addDefault("Zombievillager.base_attack", 2);
        configuration.addDefault("Zombievillager.speed", 1);
        configuration.addDefault("Zombievillager.min_level", 3);
        configuration.addDefault("Zombievillager.nameplates_enabled", true);

        configuration.addDefault("Polarbear", null);
        configuration.addDefault("Polarbear.base_hp", 4);
        configuration.addDefault("Polarbear.base_attack", 4);
        configuration.addDefault("Polarbear.speed", 1);
        configuration.addDefault("Polarbear.min_level", 3);
        configuration.addDefault("Polarbear.nameplates_enabled", true);

        configuration.addDefault("Wanderingtrader", null);
        configuration.addDefault("Wanderingtrader.base_hp", 20);
        configuration.addDefault("Wanderingtrader.base_attack", 4);
        configuration.addDefault("Wanderingtrader.speed", 1);
        configuration.addDefault("Wanderingtrader.min_level", 20);
        configuration.addDefault("Wanderingtrader.nameplates_enabled", true);

        configuration.addDefault("Donkey", null);
        configuration.addDefault("Donkey.base_hp", 2);
        configuration.addDefault("Donkey.base_attack", 3);
        configuration.addDefault("Donkey.speed", 1);
        configuration.addDefault("Donkey.min_level", 20);
        configuration.addDefault("Donkey.nameplates_enabled", true);

        configuration.addDefault("llama", null);
        configuration.addDefault("Llama.base_hp", 2);
        configuration.addDefault("Llama.base_attack", 3);
        configuration.addDefault("Llama.speed", 1);
        configuration.addDefault("Llama.min_level", 1);
        configuration.addDefault("Llama.nameplates_enabled", true);

        configuration.addDefault("Salmon", null);
        configuration.addDefault("Salmon.base_hp", 2);
        configuration.addDefault("Salmon.base_attack", 1);
        configuration.addDefault("Salmon.speed", 1);
        configuration.addDefault("Salmon.min_level", 1);
        configuration.addDefault("Salmon.nameplates_enabled", true);

        configuration.addDefault("Vex", null);
        configuration.addDefault("Vex.base_hp", 8);
        configuration.addDefault("Vex.base_attack", 3);
        configuration.addDefault("Vex.speed", 1);
        configuration.addDefault("Vex.min_level", 5);
        configuration.addDefault("Vex.nameplates_enabled", true);

        configuration.addDefault("Vindicator", null);
        configuration.addDefault("Vindicator.base_hp", 10);
        configuration.addDefault("Vindicator.base_attack", 5);
        configuration.addDefault("Vindicator.speed", 1);
        configuration.addDefault("Vindicator.min_level", 5);
        configuration.addDefault("Vindicator.nameplates_enabled", true);

        configuration.options().copyDefaults(true);

        try
        {
            configuration.save(file);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();



        }
    }
}

package com.paully104.reitzmmo.ConfigFiles;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LootConfig {

    public static void Configuration()
    {
        File file = FileManager.lootConfig;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().header("This config is used to configure the loot table of mobs by level and mob type and you can add 1 item");

        configuration.addDefault("General",null);
        configuration.addDefault("General.MobsDropAttackUpItems", true);
        configuration.addDefault("General.MobsDropAttackUpItems.Enabled", true);
        configuration.addDefault("General.MobsDropAttackUpItems.PercentChance", 25);

        configuration.addDefault("General.BonusChest",null);
        configuration.addDefault("General.BonusChest.Enabled",true);
        configuration.addDefault("General.BonusChest.PercentChance",5);
        configuration.addDefault("General.BonusChest.TimeUntilDisappear",500);


        configuration.addDefault("General.BonusChest.Items",true);

        configuration.addDefault("General.BonusChest.Items.wooden_sword",true);
        configuration.addDefault("General.BonusChest.Items.wooden_sword.Enabled",true);
        configuration.addDefault("General.BonusChest.Items.wooden_sword.PercentChance",25);

        configuration.addDefault("General.BonusChest.Items.gold_sword",true);
        configuration.addDefault("General.BonusChest.Items.gold_sword.Enabled",true);
        configuration.addDefault("General.BonusChest.Items.gold_sword.PercentChance",25);

        configuration.addDefault("General.BonusChest.Items.stone_sword",true);
        configuration.addDefault("General.BonusChest.Items.stone_sword.Enabled",true);
        configuration.addDefault("General.BonusChest.Items.stone_sword.PercentChance",25);

        configuration.addDefault("General.BonusChest.Items.iron_sword",true);
        configuration.addDefault("General.BonusChest.Items.iron_sword.Enabled",true);
        configuration.addDefault("General.BonusChest.Items.iron_sword.PercentChance",25);

        configuration.addDefault("General.BonusChest.Items.diamond_sword",true);
        configuration.addDefault("General.BonusChest.Items.diamond_sword.Enabled",true);
        configuration.addDefault("General.BonusChest.Items.diamond_sword.PercentChance",25);


        configuration.addDefault("1.ZOMBIE", 1);
        configuration.addDefault("1.ZOMBIE.chance", 10);
        configuration.addDefault("1.ZOMBIE.item", "BUCKET");

        configuration.addDefault("2.ZOMBIE", 2);
        configuration.addDefault("2.ZOMBIE.chance", 10);
        configuration.addDefault("2.ZOMBIE.item", "BREAD");









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

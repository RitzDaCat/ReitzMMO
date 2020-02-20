package io.github.paulanthonyreitz.reitzmmo.ItemData;


import io.github.paulanthonyreitz.reitzmmo.ConfigFiles.API;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class nameSpaceKey {
    public static NamespacedKey itemDamageKey = new NamespacedKey(API.plugin, "ReitzMMOItemDamageKey");

    public static NamespacedKey itemDefenseKey = new NamespacedKey(API.plugin, "ReitzMMOItemDefenseKey");

    public static NamespacedKey itemWeaponSkillKey = new NamespacedKey(API.plugin, "ReitzMMOWeaponSkillKey");

    public static NamespacedKey itemSpeedKey = new NamespacedKey(API.plugin, "ReitzMMOItemSpeedlKey");

    public static NamespacedKey monsterLevel = new NamespacedKey(API.plugin, "ReitzMMOMonsterLevel");

    public static NamespacedKey getItemDamageKey() {
        return itemDamageKey;
    }

    public static NamespacedKey getItemDefenseKey() {
        return itemDefenseKey;
    }

    public static NamespacedKey getItemWeaponSkillKey() {
        return itemWeaponSkillKey;
    }

    public static NamespacedKey getItemSpeedKey() {
        return itemSpeedKey;
    }

    public static NamespacedKey getMonsterLevelKey() {
        return monsterLevel;
    }

    public static void setItemDamageContainer(ItemStack item, int number) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(itemDamageKey, PersistentDataType.INTEGER, Integer.valueOf(number));
        meta.setLore(Arrays.asList(new String[] { ChatColor.RED + "Bonus Damage: " + number }));
        item.setItemMeta(meta);
    }

    public static void setItemDefenseContainer(ItemStack item, int number) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(itemDefenseKey, PersistentDataType.INTEGER, Integer.valueOf(number));
        meta.setLore(Arrays.asList(new String[] { ChatColor.BLUE + "Bonus Defense: " + number }));
        item.setItemMeta(meta);
    }

    public static void setSpeedContainer(ItemStack item, int number) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(itemSpeedKey, PersistentDataType.INTEGER, Integer.valueOf(number));
        meta.setLore(Arrays.asList(new String[] { ChatColor.GREEN + "Bonus Speed: " + number }));
        item.setItemMeta(meta);
    }

    public static void setMonsterLevelContainer(Entity e, int number) {
        e.getPersistentDataContainer().set(getMonsterLevelKey(), PersistentDataType.INTEGER, Integer.valueOf(number));
    }

    public static void setItemWeaponSkillContainer(ItemStack item, String skill) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(itemWeaponSkillKey, PersistentDataType.STRING, skill);
        ArrayList<Object> lore = new ArrayList(0);
        if (null != meta.getLore())
            for (Object eachLoreLine : meta.getLore()) {
                String eachString = eachLoreLine.toString();
                if (eachString.contains("Weapon"))
                    continue;
                lore.add(eachLoreLine);
            }
        lore.add(ChatColor.GOLD + "Weapon Skill: " + skill);
        System.out.println(lore.toString());

        //fix this bullshit
        List<String> strings = lore.stream()
                .map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());

        meta.setLore(strings);
        item.setItemMeta(meta);
    }

    public static int getItemDamageFromContainer(ItemStack item) {
        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();
            if (container.has(itemDamageKey, PersistentDataType.INTEGER)) {
                int foundvalue = container.get(itemDamageKey, PersistentDataType.INTEGER).intValue();
                return foundvalue;
            }
            return 0;
        }
        return 0;
    }

    public static int getItemDefenseFromContainer(ItemStack item) {
        if (null != item) {
            if (item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                if (container.has(itemDefenseKey, PersistentDataType.INTEGER)) {
                    int foundvalue = container.get(itemDefenseKey, PersistentDataType.INTEGER).intValue();
                    return foundvalue;
                }
                return 0;
            }
            return 0;
        }
        return 0;
    }

    public static int getItemSpeedFromContainer(ItemStack item) {
        if (null != item) {
            if (item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                if (container.has(itemSpeedKey, PersistentDataType.INTEGER)) {
                    int foundvalue = ((Integer)container.get(itemSpeedKey, PersistentDataType.INTEGER)).intValue();
                    return foundvalue;
                }
                return 0;
            }
            return 0;
        }
        return 0;
    }

    public static String getItemWeaponSkillFromContainer(ItemStack item) {
        if (null != item) {
            if (item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                if (container.has(itemWeaponSkillKey, PersistentDataType.STRING)) {
                    String foundvalue = container.get(itemWeaponSkillKey, PersistentDataType.STRING);
                    return foundvalue;
                }
                return "None";
            }
            return "None";
        }
        return "None";
    }

    public static int getMonsterLevelFromContainer(Entity e) {
        if (e.getPersistentDataContainer().has(getMonsterLevelKey(), PersistentDataType.INTEGER))
            return e.getPersistentDataContainer().get(getMonsterLevelKey(), PersistentDataType.INTEGER).intValue();
        return 1;
    }
}

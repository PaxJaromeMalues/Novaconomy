package us.teaminceptus.novaconomy;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.nbt.*;
import net.minecraft.world.item.ItemStack;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import us.teaminceptus.novaconomy.abstraction.Wrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Wrapper1_19_R1 implements Wrapper {

    @Override
    public int getCommandVersion() { return 2; }

    @Override
    public void sendActionbar(Player p, String message) {
        sendActionbar(p, new TextComponent(message));
    }

    @Override
    public void sendActionbar(Player p, BaseComponent component) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
    }

    @Override
    public String getNBTString(org.bukkit.inventory.ItemStack item, String key) {
        ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        CompoundTag tag = nmsitem.getOrCreateTag();
        CompoundTag novaconomy = tag.getCompound(ROOT);

        return novaconomy.getString(key);
    }

    @Override
    public org.bukkit.inventory.ItemStack setNBT(org.bukkit.inventory.ItemStack item, String key, String value) {
        ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        CompoundTag tag = nmsitem.getOrCreateTag();
        CompoundTag novaconomy = tag.getCompound(ROOT);

        novaconomy.putString(key, value);
        tag.put(ROOT, novaconomy);
        nmsitem.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsitem);
    }

    @Override
    public void openBook(Player p, org.bukkit.inventory.ItemStack book) {
        p.openBook(book);
    }

    @Override
    public org.bukkit.inventory.ItemStack getGUIBackground() {
        org.bukkit.inventory.ItemStack item = new org.bukkit.inventory.ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public org.bukkit.inventory.ItemStack createSkull(OfflinePlayer p) {
        org.bukkit.inventory.ItemStack item = new org.bukkit.inventory.ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(p);
        item.setItemMeta(meta);

        return item;
    }

    private Object getData(Tag b) {
        switch (b.getId()) {
            case 1: return ((ByteTag) b).getAsByte();
            case 2: return ((ShortTag) b).getAsShort();
            case 3: return ((IntTag) b).getAsInt();
            case 4: return ((LongTag) b).getAsLong();
            case 5: return ((FloatTag) b).getAsFloat();
            case 6: return ((DoubleTag) b).getAsDouble();
            case 7: return ((ByteArrayTag) b).getAsByteArray();
            case 8: return b.getAsString();
            case 9: {
                List<Object> l = new ArrayList<>();

                ListTag list = (ListTag) b;
                for (Tag nbtBase : list) l.add(getData(nbtBase));
                return l;
            }
            case 10: {
                CompoundTag c = (CompoundTag) b;
                Map<String, Object> map = new HashMap<>();

                c.getAllKeys().forEach(s -> map.put(s, getData(c.get(s))));
                return map;
            }
            case 11: return ((IntArrayTag) b).getAsIntArray();
            case 12: return ((LongArrayTag) b).getAsLongArray();

            default: return null;
        }
    }

    @Override
    public org.bukkit.inventory.ItemStack setNBT(org.bukkit.inventory.ItemStack item, String key, org.bukkit.inventory.ItemStack value) {
        ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        CompoundTag tag = nmsitem.getOrCreateTag();
        CompoundTag novaconomy = tag.getCompound(ROOT);

        ItemStack nmsvalue = CraftItemStack.asNMSCopy(value);
        novaconomy.put(key, nmsvalue.save(nmsvalue.getOrCreateTag()));
        tag.put(ROOT, novaconomy);
        nmsitem.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsitem);
    }

    @Override
    public org.bukkit.inventory.ItemStack getNBTItem(org.bukkit.inventory.ItemStack item, String key) {
        ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        CompoundTag tag = nmsitem.getOrCreateTag();
        CompoundTag novaconomy = tag.getCompound(ROOT);

        CompoundTag nbt = novaconomy.getCompound(key);
        return CraftItemStack.asBukkitCopy(ItemStack.of(nbt));
    }

    @Override
    public double getNBTDouble(org.bukkit.inventory.ItemStack item, String key) {
        ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        CompoundTag tag = nmsitem.getOrCreateTag();
        CompoundTag novaconomy = tag.getCompound(ROOT);

        return novaconomy.getDouble(key);
    }

    @Override
    public org.bukkit.inventory.ItemStack setNBT(org.bukkit.inventory.ItemStack item, String key, double value) {
        ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        CompoundTag tag = nmsitem.getOrCreateTag();
        CompoundTag novaconomy = tag.getCompound(ROOT);

        novaconomy.putDouble(key, value);
        tag.put(ROOT, novaconomy);
        nmsitem.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsitem);
    }

    @Override
    public org.bukkit.inventory.ItemStack setNBT(org.bukkit.inventory.ItemStack item, String key, boolean value) {
        ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        CompoundTag tag = nmsitem.hasTag() ? nmsitem.getTag() : new CompoundTag();
        CompoundTag novaconomy = tag.getCompound(ROOT);

        novaconomy.putBoolean(key, value);
        tag.put(ROOT, novaconomy);
        return CraftItemStack.asBukkitCopy(nmsitem);
    }

    @Override
    public boolean getNBTBoolean(org.bukkit.inventory.ItemStack item, String key) {
        ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        CompoundTag tag = nmsitem.getOrCreateTag();
        CompoundTag novaconomy = tag.getCompound(ROOT);

        return novaconomy.getBoolean(key);
    }

    @Override
    public org.bukkit.inventory.ItemStack normalize(org.bukkit.inventory.ItemStack item) {
        ItemStack nmsitem = CraftItemStack.asNMSCopy(item);
        CompoundTag tag = nmsitem.getOrCreateTag();

        tag.remove("id");
        tag.remove("Count");
        nmsitem.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsitem);
    }

}

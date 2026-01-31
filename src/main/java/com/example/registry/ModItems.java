package com.example.registry;

import com.example.ExampleMod;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModItems {
	public static final Item COMPRESSED_DIAMOND = new ExampleMod.EnchantedItem(new Item.Settings());
	public static final Item COMPRESSED_COPPER = new ExampleMod.EnchantedItem(new Item.Settings());
	public static final Item COMPRESSED_IRON = new ExampleMod.EnchantedItem(new Item.Settings());
	public static final Item COMPRESSED_GOLD = new ExampleMod.EnchantedItem(new Item.Settings());
	public static final Item COMPRESSED_EMERALD = new ExampleMod.EnchantedItem(new Item.Settings());
	public static final Item COMPRESSED_COAL = new ExampleMod.EnchantedItem(new Item.Settings());
	public static final Item COMPRESSED_REDSTONE = new ExampleMod.EnchantedItem(new Item.Settings());
	public static final Item COMPRESSED_LAPIS = new ExampleMod.EnchantedItem(new Item.Settings());

	private ModItems() {
	}

	public static void registerAll() {
		register("compressed_diamond", COMPRESSED_DIAMOND);
		register("compressed_copper", COMPRESSED_COPPER);
		register("compressed_iron", COMPRESSED_IRON);
		register("compressed_gold", COMPRESSED_GOLD);
		register("compressed_emerald", COMPRESSED_EMERALD);
		register("compressed_coal", COMPRESSED_COAL);
		register("compressed_redstone", COMPRESSED_REDSTONE);
		register("compressed_lapis", COMPRESSED_LAPIS);
	}

	private static void register(String path, Item item) {
		Registry.register(Registries.ITEM, Identifier.of("modid", path), item);
	}
}
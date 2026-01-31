package com.example;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.registry.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	// Custom item class with enchanted glow effect
	public static class EnchantedItem extends Item {
		public EnchantedItem(Settings settings) {
			super(settings);
		}

		@Override
		public boolean hasGlint(ItemStack stack) {
			return true; // This makes the item have an enchanted glow
		}
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		var recipePath = "data/modid/recipes/compressed_coal.json";
		var recipeResource = ExampleMod.class.getClassLoader().getResource(recipePath);
		LOGGER.info("Recipe resource {} visible on classpath: {}", recipePath, recipeResource != null);

		ModItems.registerAll();
		LOGGER.info("Registered compressed items with enchanted glow!");
	}
}
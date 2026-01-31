package com.example.datagen;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public final class CompressedResourceGenerator {
    private static final String MOD_ID = "modid";

    private record CompressedEntry(String name, String baseItem, String baseTexture) {
    }

    private static final List<CompressedEntry> ENTRIES = List.of(
            new CompressedEntry("compressed_diamond", "minecraft:diamond_block", "minecraft:item/diamond"),
            new CompressedEntry("compressed_copper", "minecraft:copper_block", "minecraft:item/copper_ingot"),
            new CompressedEntry("compressed_iron", "minecraft:iron_block", "minecraft:item/iron_ingot"),
            new CompressedEntry("compressed_gold", "minecraft:gold_block", "minecraft:item/gold_ingot"),
            new CompressedEntry("compressed_emerald", "minecraft:emerald_block", "minecraft:item/emerald"),
            new CompressedEntry("compressed_coal", "minecraft:coal_block", "minecraft:item/coal"),
            new CompressedEntry("compressed_redstone", "minecraft:redstone_block", "minecraft:item/redstone"),
            new CompressedEntry("compressed_lapis", "minecraft:lapis_block", "minecraft:item/lapis_lazuli"));

    private CompressedResourceGenerator() {
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Expected output directory path argument.");
        }

        Path resourcesRoot = Path.of(args[0]);
        for (CompressedEntry entry : ENTRIES) {
            // Renamed method to indicate it overwrites
            writeFile(modelPath(resourcesRoot, entry.name), modelJson(entry.baseTexture));
            writeFile(recipePath(resourcesRoot, entry.name), shapedRecipeJson(entry));
            writeFile(reverseRecipePath(resourcesRoot, entry.name), shapelessRecipeJson(entry));
        }
    }

    private static Path modelPath(Path root, String name) {
        return root.resolve("assets").resolve(MOD_ID).resolve("models").resolve("item").resolve(name + ".json");
    }

    private static Path recipePath(Path root, String name) {
        return root.resolve("data").resolve(MOD_ID).resolve("recipes").resolve(name + ".json");
    }

    private static Path reverseRecipePath(Path root, String name) {
        return root.resolve("data").resolve(MOD_ID).resolve("recipes").resolve(name + "_reverse.json");
    }

    private static String modelJson(String baseItem) {
        return """
                {
                  "parent": "item/generated",
                  "textures": {
                    "layer0": "%s"
                  }
                }
                """.formatted(baseItem);
    }

    private static String shapedRecipeJson(CompressedEntry entry) {
        // Added "category": "misc"
        // Changed result "item" to "id"
        return """
                {
                  "type": "minecraft:crafting_shaped",
                  "category": "misc", 
                  "pattern": [
                    "###",
                    "###",
                    "###"
                  ],
                  "key": {
                    "#": {
                      "item": "%s"
                    }
                  },
                  "result": {
                    "id": "%s:%s",
                    "count": 1
                  }
                }
                """.formatted(entry.baseItem, MOD_ID, entry.name);
    }

    private static String shapelessRecipeJson(CompressedEntry entry) {
        // Added "category": "misc"
        // Changed result "item" to "id"
        return """
                {
                  "type": "minecraft:crafting_shapeless",
                  "category": "misc",
                  "ingredients": [
                    {
                      "item": "%s:%s"
                    }
                  ],
                  "result": {
                    "id": "%s",
                    "count": 9
                  }
                }
                """.formatted(MOD_ID, entry.name, entry.baseItem);
    }

    private static void writeFile(Path path, String content) throws IOException {
        Files.createDirectories(path.getParent());
        // Writes content, creating file if missing, truncating (overwriting) if it exists
        Files.writeString(path, content, StandardCharsets.UTF_8, 
            StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
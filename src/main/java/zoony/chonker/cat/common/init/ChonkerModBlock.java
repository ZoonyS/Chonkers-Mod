package zoony.chonker.cat.common.init;

import zoony.chonker.cat.common.block.LasagnaBlock;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ChonkerModBlock {
    public static Block LASAGNA_BLOCK;
    public static BlockItem LASAGNA_BLOCK_ITEM;

    public static void init() {
        LASAGNA_BLOCK =  Registry.register(Registry.BLOCK, new Identifier("chonkers", "lasagna"), new LasagnaBlock(FabricBlockSettings.of(Material.CAKE).strength(1.0F, 1.0F).sounds(BlockSoundGroup.MOSS_CARPET)));
        LASAGNA_BLOCK_ITEM = Registry.register(Registry.ITEM, new Identifier("chonkers", "lasagna"), new BlockItem(LASAGNA_BLOCK, new Item.Settings().group(ItemGroup.FOOD)));
    }

}

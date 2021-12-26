package zoony.chonker.cat.common.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ChonkerModItem {
    public static Item CHONKER_SPAWN_EGG;
    
    public static void init() {
        CHONKER_SPAWN_EGG = Registry.register(Registry.ITEM, new Identifier("chonkers", "chonker_spawn_egg"), new SpawnEggItem(ChonkerModEntity.CHONKER, 0x0DA70B, 0x73420E, new Item.Settings().group(ItemGroup.MISC)));
    }

}

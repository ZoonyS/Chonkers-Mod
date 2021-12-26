package zoony.chonker.cat.common.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import zoony.chonker.cat.common.entity.ChonkerEntity;

public class ChonkerModEntity {
    public static EntityType<ChonkerEntity> CHONKER;

    public static void init() {
        CHONKER = Registry.register(Registry.ENTITY_TYPE, new Identifier("chonkers", "chonker"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ChonkerEntity::new).dimensions(EntityDimensions.fixed(0.5f, 2f)).build());
        FabricDefaultAttributeRegistry.register(CHONKER, ChonkerEntity.createEntityAttributes());
    }

}

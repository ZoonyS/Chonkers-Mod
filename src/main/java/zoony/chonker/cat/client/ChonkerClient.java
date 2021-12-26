package zoony.chonker.cat.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import zoony.chonker.cat.common.init.ChonkerModEntity;
import zoony.chonker.cat.client.renderer.entity.ChonkerEntityRenderer;

public class ChonkerClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ChonkerModEntity.CHONKER, ChonkerEntityRenderer::new);
        FabricModelPredicateProviderRegistry.register(new Identifier("chonkers:filled"), (itemStack, world, livingEntity, seed) -> itemStack.getOrCreateSubNbt("chonkers").getFloat("filled"));
    }
    
}

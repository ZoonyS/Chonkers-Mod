package zoony.chonker.cat.client.renderer.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import zoony.chonker.cat.client.model.entity.ChonkerEntityModel;
import zoony.chonker.cat.common.entity.ChonkerEntity;

public class ChonkerEntityRenderer extends GeoEntityRenderer<ChonkerEntity> {

    public ChonkerEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ChonkerEntityModel());
    }
    
}

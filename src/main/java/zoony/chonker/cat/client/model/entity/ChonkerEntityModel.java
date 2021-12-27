package zoony.chonker.cat.client.model.entity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import zoony.chonker.cat.common.entity.ChonkerEntity;

public class ChonkerEntityModel extends AnimatedGeoModel<ChonkerEntity> {

    @Override
    public Identifier getAnimationFileLocation(ChonkerEntity animatable) {
        return new Identifier("chonkers", "animations/chonker.animation.json");
    }

    @Override
    public Identifier getModelLocation(ChonkerEntity object) {
        return new Identifier("chonkers", "geo/chonker.geo.json");
    }

    @Override
    public Identifier getTextureLocation(ChonkerEntity object) {
        return new Identifier("chonkers", "textures/entity/chonker.png");
    }
    
}

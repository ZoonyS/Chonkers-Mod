package zoony.chonker.cat.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ChonkerEntity extends TameableEntity implements IAnimatable {
    
    public ChonkerEntity(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void registerControllers(AnimationData data) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public AnimationFactory getFactory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PassiveEntity createChild(ServerWorld var1, PassiveEntity var2) {
        // TODO Auto-generated method stub
        return null;
    }
    
}

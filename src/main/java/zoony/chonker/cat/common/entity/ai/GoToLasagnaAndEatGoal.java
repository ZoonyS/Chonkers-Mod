package zoony.chonker.cat.common.entity.ai;

import java.util.EnumSet;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import zoony.chonker.cat.common.block.LasagnaBlock;
import zoony.chonker.cat.common.entity.ChonkerEntity;

/**
 * Makes a {@link ChonkerEntity} go to a lasagna and eat.
 */
public class GoToLasagnaAndEatGoal
extends MoveToTargetPosGoal {
    private final ChonkerEntity chonker;
    private LasagnaBlock lasagnaBlock;

    public GoToLasagnaAndEatGoal(ChonkerEntity chonker, double speed, int range) {
        super(chonker, speed, range, 6);
        this.chonker = chonker;
        this.lowestY = -2;
        this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
       return super.canStart();
    }

    @Override
    public void start() {
        super.start();;
        this.chonker.playSound(SoundEvents.ENTITY_WOLF_HURT, 1, 0.2F);
    }

    @Override
    public void stop() {
        super.stop();
        this.chonker.playSound(SoundEvents.ENTITY_WOLF_DEATH, 1, 0.2F);
    }

    @Override
    protected int getInterval(PathAwareEntity mob) {
        return 40;
    }
    
    @Override
    public void tick() {
        super.tick();
        if (!this.hasReached()) {
            this.chonker.playSound(SoundEvents.ENTITY_WOLF_AMBIENT, 1, 0.2F);
        }
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return world.isAir(pos.up()) && world.getBlockState(pos).isOf(lasagnaBlock);
    }
    
}

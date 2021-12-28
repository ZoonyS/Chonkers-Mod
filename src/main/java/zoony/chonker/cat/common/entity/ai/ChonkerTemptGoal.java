package zoony.chonker.cat.common.entity.ai;

import zoony.chonker.cat.common.entity.ChonkerEntity;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.recipe.Ingredient;

public class ChonkerTemptGoal extends TemptGoal {
    @Nullable
    private PlayerEntity player;
    private final ChonkerEntity chonker;
    

    public ChonkerTemptGoal(ChonkerEntity chonker, double speed, Ingredient food, boolean canBeScared) {
        super(chonker, speed, food, canBeScared);
        this.chonker = chonker;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.player == null && this.mob.getRandom().nextInt(this.getTickCount(600)) == 0) {
            this.player = this.closestPlayer;
        } else if (this.mob.getRandom().nextInt(this.getTickCount(500)) == 0) {
            this.player = null;
        }
    }

    @Override
    public boolean canStart() {
        return super.canStart() && !this.chonker.isTamed();
    }
}
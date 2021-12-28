// package zoony.chonker.cat.common.entity.ai;

// import org.jetbrains.annotations.Nullable;

// import net.minecraft.entity.ai.goal.TemptGoal;
// import net.minecraft.entity.player.PlayerEntity;
// import net.minecraft.recipe.Ingredient;
// import zoony.chonker.cat.common.entity.ChonkerEntity;

// static class TemptGoal extends TemptGoal {
//     @Nullable
//     private PlayerEntity player;
//     private final ChonkerEntity cat;

//     public TemptGoal(ChonkerEntity cat, double speed, Ingredient food, boolean canBeScared) {
//         super();
//         this.cat = cat;
//     }

//     @Override
//     public void tick() {
//         super.tick();
//         if (this.player == null && this.mob.getRandom().nextInt(this.getTickCount(600)) == 0) {
//             this.player = this.closestPlayer;
//         } else if (this.mob.getRandom().nextInt(this.getTickCount(500)) == 0) {
//             this.player = null;
//         }
//     }

//     @Override
//     protected boolean canBeScared() {
//         if (this.player != null && this.player.equals(this.closestPlayer)) {
//             return false;
//         }
//         return super.canBeScared();
//     }

//     @Override
//     public boolean canStart() {
//         return super.canStart() && !this.cat.isTamed();
//     }
// }
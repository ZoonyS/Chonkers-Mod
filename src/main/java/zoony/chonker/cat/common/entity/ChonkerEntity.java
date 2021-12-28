package zoony.chonker.cat.common.entity;

import zoony.chonker.cat.common.entity.ai.ChonkerTemptGoal;
import zoony.chonker.cat.common.init.ChonkerModEntity;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ChonkerEntity extends TameableEntity implements IAnimatable {
    public static final double CROUCHING_SPEED = 0.5;
    public static final double NORMAL_SPEED = 0.7;
    public static final double SPRINTING_SPEED = 1;
    private static final Ingredient TAMING_INGREDIENT = Ingredient.ofItems(Items.COD, Items.SALMON);
    
    private ChonkerTemptGoal temptGoal;
    private final AnimationFactory factory = new AnimationFactory(this);

    public ChonkerEntity(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createEntityAttributes() {
        return MobEntity.createMobAttributes()
        .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D)
        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, NORMAL_SPEED)
        .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32);
    }

    protected void initGoals() {
        this.temptGoal =  new ChonkerTemptGoal(this, CROUCHING_SPEED, TAMING_INGREDIENT, false);
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, this.temptGoal);
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, NORMAL_SPEED));
    }

    //#region [TICK]

    @Override
    public void tick() {
        super.tick();
        if (this.temptGoal != null && this.temptGoal.isActive() && !this.isTamed() && this.age % 100 == 0) {
            this.playSound(SoundEvents.ENTITY_CAT_BEG_FOR_FOOD, 1.0f, 0.2f);
        }    
    }

    //#endregion

    //#region [ANIMATION]

    private <E extends IAnimatable> PlayState Predicate(AnimationEvent<E> event) {
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::Predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    //#endregion

    //#region [BREEDING]

    @Override
    public boolean canBreedWith(AnimalEntity other) {
        if (!this.isTamed()) {
            return false;
        }
        if (!(other instanceof ChonkerEntity)) {
            return false;
        }
        ChonkerEntity chonkerEntity = (ChonkerEntity)other;
        return chonkerEntity.isTamed() && super.canBreedWith(other);
    }
    
    @Override
    public ChonkerEntity createChild(ServerWorld world, PassiveEntity entity) {
        ChonkerEntity chonkerEntity = ChonkerModEntity.CHONKER.create(world);

        UUID uuid = this.getOwnerUuid();
        if (uuid != null) {
            chonkerEntity.setOwnerUuid(uuid);
            chonkerEntity.setTamed(true);
        }
        
        return chonkerEntity;
    }

    //#endregion

    //#region [SOUND EVENTS]

    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        if (this.isTamed()) {
            if (this.isInLove()) {
                return SoundEvents.ENTITY_CAT_PURR;
            }
            if (this.random.nextInt(4) == 0) {
                return SoundEvents.ENTITY_CAT_PURREOW;
            }
            return SoundEvents.ENTITY_CAT_AMBIENT;
        }
        return SoundEvents.ENTITY_GHAST_SCREAM;
    }

    public void hiss() {
        this.playSound(SoundEvents.ENTITY_TNT_PRIMED, this.getSoundVolume(), this.getSoundPitch());
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_GHAST_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_GHAST_DEATH;
    }

    @Override
    protected void eat(PlayerEntity player, Hand hand, ItemStack stack) {
        if (this.isBreedingItem(stack)) {
            this.playSound(SoundEvents.ENTITY_CAT_EAT, 1.0f, 1.0f);
        }
        super.eat(player, hand, stack);
    }

    //#endregion

    //#region [BOOLS]

    public boolean isBreedingItem(ItemStack stack) {
        return TAMING_INGREDIENT.test(stack);
    }

    //#endregion

}


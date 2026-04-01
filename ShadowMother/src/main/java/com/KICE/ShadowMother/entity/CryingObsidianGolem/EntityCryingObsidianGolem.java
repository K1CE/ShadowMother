package com.KICE.ShadowMother.entity.CryingObsidianGolem;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.world.World;

public class EntityCryingObsidianGolem extends EntityCreature{

	public EntityCryingObsidianGolem(World worldIn) {
		super(worldIn);
		this.setSize(0.6f, 0.6f);
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(2, new EntityAIWander(this, 0.3D));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.5D, true));
		//this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 1.0f));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.21D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.00D);
	}

	//@Override
	//protected SoundEvent getDeathSound(){
	//	return SoundHandler.TEST_DEATH;
	//}
	
}

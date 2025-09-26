/*     */ package com.KICE.ShadowMother.blocks;
/*     */ 
/*     */ import com.KICE.ShadowMother.init.ModBlocks;
/*     */ import com.KICE.ShadowMother.tileentity.CryingCircle;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CryingObsidian
/*     */   extends BlockBase
/*     */ {
/*  33 */   public static final PropertyBool CRYING = PropertyBool.create("crying");
/*     */   
/*     */   public static CryingCircle core;
/*     */   
/*     */   public CryingObsidian(String name) {
/*  38 */     super(name, Material.BARRIER);
/*  39 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)CRYING, Boolean.valueOf(true)));
/*  40 */     setSoundType(SoundType.METAL);
/*  41 */     setBlockUnbreakable();
/*  42 */     this.blockResistance = 1.8E7F;
/*     */   }
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  46 */     return getDefaultState().withProperty((IProperty)CRYING, Boolean.valueOf((meta == 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  54 */     return ((Boolean)state.getValue((IProperty)CRYING)).booleanValue() ? 1 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateContainer createBlockState() {
/*  59 */     return new BlockStateContainer(this, new IProperty[] { (IProperty)CRYING });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setCore(CryingCircle cryingCircle) {
/*  64 */     core = cryingCircle;
/*     */   }
/*     */   
/*     */   public static void resetCore() {
/*  68 */     core = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void observedNeighborChange(IBlockState observerState, World world, BlockPos observerPos, Block changedBlock, BlockPos changedBlockPos) {
	
/*  73 */     if (!((Boolean)world.getBlockState(observerPos).getValue((IProperty)CRYING)).booleanValue() && 
/*  74 */       core == null) {
/*  75 */       world.setBlockState(observerPos, observerState.withProperty((IProperty)CRYING, Boolean.valueOf(true)), 2);
/*  76 */       world.setBlockState(observerPos, observerState.withProperty((IProperty)CRYING, Boolean.valueOf(true)), 1);
/*  77 */       world.observedNeighborChanged(observerPos.add(1, 1, 1), changedBlock, observerPos);
/*  78 */       world.observedNeighborChanged(observerPos.add(1, 1, -1), changedBlock, observerPos);
/*  79 */       world.observedNeighborChanged(observerPos.add(-1, 1, -1), changedBlock, observerPos);
/*  80 */       world.observedNeighborChanged(observerPos.add(-1, 1, 1), changedBlock, observerPos);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  85 */     if (world.getTileEntity(changedBlockPos) != null && world.getTileEntity(changedBlockPos) instanceof CryingCircle && ((CryingCircle)world.getTileEntity(changedBlockPos)).isActive())
				core = (CryingCircle)world.getTileEntity(changedBlockPos); 
/*  86 */     if (core != null && core.isActive()) {
/*  87 */       Block testing = world.getBlockState(observerPos.up()).getBlock();
/*  88 */       if (world.getLightFromNeighbors(observerPos.up()) >= 5 || world
/*  89 */         .getLightFromNeighbors(observerPos.east()) >= 5 || world
/*  90 */         .getLightFromNeighbors(observerPos.west()) >= 5 || world
/*  91 */         .getLightFromNeighbors(observerPos.south()) >= 5 || world
/*  92 */         .getLightFromNeighbors(observerPos.north()) >= 5) {
/*     */ 
/*     */         
/*  95 */         
/*     */ 
/*     */         
/*  98 */         if (!world.isRemote) core.messageBright(); 
/*  99 */         core.depressObsidian();

				  world.setBlockState(observerPos, observerState.withProperty((IProperty)CRYING, Boolean.valueOf(true)), 2);
/*     */       }
/* 101 */       else if (testing != Blocks.AIR && testing != ModBlocks.CRYING_OBSIDIAN && testing != ModBlocks.CRYING_CATALYST) {
/*     */ 
/*     */ 
/*     */         
/* 105 */          
/* 106 */         core.depressObsidian();
/* 107 */         System.out.println(testing.getLocalizedName());
/*     */       }
/*     */       else {
/*     */         
/* 111 */         world.setBlockState(observerPos, observerState.withProperty((IProperty)CRYING, Boolean.valueOf(false)), 2);
/*     */         
/* 113 */         core.addObsidian(observerPos, observerState);
/*     */       }
/*     */     }
/*     */     
/* 117 */     world.observedNeighborChanged(observerPos.add(1, 1, 1), changedBlock, observerPos);
/* 118 */     world.observedNeighborChanged(observerPos.add(1, 1, -1), changedBlock, observerPos);
/* 119 */     world.observedNeighborChanged(observerPos.add(-1, 1, -1), changedBlock, observerPos);
/* 120 */     world.observedNeighborChanged(observerPos.add(-1, 1, 1), changedBlock, observerPos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void depress(World world, BlockPos pos, IBlockState state) {
/* 126 */     world.setBlockState(pos, state.withProperty((IProperty)CRYING, Boolean.valueOf(true)), 2);
/*     */   }
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 130 */     if (worldIn.provider.hasSkyLight()) {
/*     */       
/* 132 */       if (worldIn.getLightFor(EnumSkyBlock.SKY, pos) > 0.0F)
/*     */       {
/* 134 */         worldIn.setBlockState(pos, state.withProperty((IProperty)CRYING, Boolean.valueOf(true)), 2);
/*     */       }
/* 136 */     } else if (worldIn.getLightFor(EnumSkyBlock.SKY, pos) == 0.0F) {
/* 137 */       worldIn.setBlockState(pos, state.withProperty((IProperty)CRYING, Boolean.valueOf(false)), 2);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\blocks\CryingObsidian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.KICE.ShadowMother.blocks;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class BlockTileEntity<TE extends TileEntity>
/*    */   extends BlockBase
/*    */ {
/*    */   public BlockTileEntity(Material material, String name) {
/* 15 */     super(name, material);
/*    */   }
/*    */   
/*    */   public abstract Class<TE> getTileEntityClass();
/*    */   
/*    */   public TE getTileEntity(IBlockAccess world, BlockPos pos) {
/* 21 */     return (TE)world.getTileEntity(pos);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasTileEntity(IBlockState state) {
/* 26 */     return true;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public abstract TE createTileEntity(World paramWorld, IBlockState paramIBlockState);
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\blocks\BlockTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
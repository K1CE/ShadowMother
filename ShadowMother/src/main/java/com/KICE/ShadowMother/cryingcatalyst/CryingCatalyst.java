/*     */ package com.KICE.ShadowMother.cryingcatalyst;
/*     */ 
/*     */ import com.KICE.ShadowMother.Main;
/*     */ import com.KICE.ShadowMother.blocks.BlockBase;
/*     */ import com.KICE.ShadowMother.init.ModBlocks;
/*     */ import com.KICE.ShadowMother.tileentity.CryingCircle;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.ITileEntityProvider;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockRenderLayer;
/*     */ import net.minecraft.util.EnumBlockRenderType;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
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
/*     */ 
/*     */ public class CryingCatalyst
/*     */   extends BlockBase
/*     */   implements ITileEntityProvider
/*     */ {
/*     */   public CryingCatalyst(String name) {
/*  44 */     super(name, Material.BARRIER);
/*  45 */     setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
/*  46 */     setBlockUnbreakable();
/*  47 */     this.blockResistance = 1.8E7F;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public BlockRenderLayer getRenderLayer() {
/*  53 */     return BlockRenderLayer.TRANSLUCENT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
/*  59 */     CryingCircle circleEntity = (CryingCircle)worldIn.getTileEntity(pos);
		      System.out.println("activated catalyst");
/*  60 */     circleEntity.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
/*  61 */     if (playerIn.isAllowEdit()) {
/*  62 */       if (worldIn.isRemote) {
/*  63 */         return true;
/*     */       }
/*     */       
/*  66 */       if (circleEntity.isActive()) worldIn.notifyNeighborsOfStateChange(pos, (Block)this, true); 
/*     */     }
/*  68 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube(IBlockState iBlockState) {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube(IBlockState iBlockState) {
/*  78 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
/*  83 */     return EnumBlockRenderType.MODEL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/*  89 */     return Block.FULL_BLOCK_AABB;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/*  95 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/* 101 */     return (TileEntity)new CryingCircle();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasTileEntity(IBlockState state) {
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerModels() {
/* 111 */     Main.proxy.registerItemRenderer(Item.getItemFromBlock(ModBlocks.CRYING_CATALYST), 0, "inventory");
/*     */   }
/*     */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\cryingcatalyst\CryingCatalyst.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
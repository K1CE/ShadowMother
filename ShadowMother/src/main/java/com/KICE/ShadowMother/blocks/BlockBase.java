/*    */ package com.KICE.ShadowMother.blocks;
/*    */ 
/*    */ import com.KICE.ShadowMother.Main;
/*    */ import com.KICE.ShadowMother.init.ModBlocks;
/*    */ import com.KICE.ShadowMother.init.ModItems;
/*    */ import com.KICE.ShadowMother.util.IHasModel;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ 
/*    */ public class BlockBase
/*    */   extends Block implements IHasModel {
/*    */   public BlockBase(String name, Material material) {
/* 16 */     super(material);
/* 17 */     setUnlocalizedName(name);
/* 18 */     setRegistryName(name);
/* 19 */     setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
/*    */     
/* 21 */     ModBlocks.BLOCKS.add(this);
/* 22 */     ModItems.ITEMS.add((new ItemBlock(this)).setRegistryName(getRegistryName()));
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerModels() {
/* 27 */     Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
/*    */   }
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\blocks\BlockBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
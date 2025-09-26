/*    */ package com.KICE.ShadowMother.items;
/*    */ 
/*    */ import com.KICE.ShadowMother.Main;
/*    */ import com.KICE.ShadowMother.init.ModItems;
/*    */ import com.KICE.ShadowMother.util.IHasModel;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ 
/*    */ public class ItemBase
/*    */   extends Item
/*    */   implements IHasModel {
/*    */   public ItemBase(String name) {
/* 13 */     setUnlocalizedName(name);
/* 14 */     setRegistryName(name);
/* 15 */     setCreativeTab(CreativeTabs.MISC);
/*    */     
/* 17 */     ModItems.ITEMS.add(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerModels() {
/* 22 */     Main.proxy.registerItemRenderer(this, 0, "inventory");
/*    */   }
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\items\ItemBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
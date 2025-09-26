/*    */ package com.KICE.ShadowMother.items;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UmbralAsh
/*    */   extends ItemBase
/*    */ {
/*    */   public static final int DAMAGE_CHANCE = 160;
/*    */   Random rand;
/*    */   
/*    */   public UmbralAsh(String name) {
/* 20 */     super(name);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 29 */     this.rand = new Random();
/*    */   }
/*    */   
/*    */   public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/*    */     tooltip.add("Vague numb pain spreads through your hand");
/*    */   }
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\items\UmbralAsh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.KICE.ShadowMother.util;
/*    */ 
/*    */ import com.KICE.ShadowMother.init.ModItems;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LivingEventHandler
/*    */   implements IEventHandler
/*    */ {
/* 19 */   Random rand = new Random();
/*    */   @SubscribeEvent
/*    */   public void onLiving(LivingEvent.LivingUpdateEvent event) {
/* 22 */     if (event.getEntityLiving().getHeldItemMainhand().getCount() != 0) {
/* 23 */       EntityLivingBase entity = event.getEntityLiving();
/* 24 */       Item item = entity.getHeldItemMainhand().getItem();
/* 25 */       if (item == ModItems.UMBRAL_ASH && this.rand.nextInt(160 - entity.getHeldItemMainhand().getCount()) == 1)
/* 26 */         if (entity.isEntityUndead()) {
/* 27 */           entity.heal(1.0F);
/* 28 */           System.out.println(entity.getHealth());
/*    */         } else {
/* 30 */           entity.attackEntityFrom(DamageSource.MAGIC, 1.0F);
/* 31 */           System.out.println(entity.getHealth());
/*    */         }  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMothe\\util\LivingEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
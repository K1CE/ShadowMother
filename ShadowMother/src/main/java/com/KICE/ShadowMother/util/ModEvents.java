/*    */ package com.KICE.ShadowMother.util;
/*    */ 
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModEvents
/*    */ {
/*    */   public static StitchEventHandler stitchEvent;
/*    */   public static DeathEventHandler deathEvent;
/*    */   public static LivingEventHandler livingEvent;
/*    */   
/*    */   public static void initClient() {
/* 14 */     stitchEvent = new StitchEventHandler();
/* 15 */     MinecraftForge.EVENT_BUS.register(stitchEvent);
/* 16 */     deathEvent = new DeathEventHandler();
/* 17 */     MinecraftForge.EVENT_BUS.register(deathEvent);
/* 18 */     livingEvent = new LivingEventHandler();
/* 19 */     MinecraftForge.EVENT_BUS.register(livingEvent);
/*    */   }
/*    */   
/*    */   public static void initCommon() {}
/*    */   
/*    */   public static void RegisterEventsCommon() {}
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMothe\\util\ModEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
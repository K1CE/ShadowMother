/*    */ package com.KICE.ShadowMother;
/*    */ 
/*    */ import com.KICE.ShadowMother.proxy.CommonProxy;
/*    */ import net.minecraftforge.fml.common.Mod;
/*    */ import net.minecraftforge.fml.common.Mod.EventHandler;
/*    */ import net.minecraftforge.fml.common.Mod.Instance;
/*    */ import net.minecraftforge.fml.common.SidedProxy;
/*    */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*    */ import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
/*    */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mod(modid = "subyin", name = "Shadow Mother", version = "0.0.1", acceptedMinecraftVersions = "[1.12.2]")
/*    */ public class Main
/*    */ {
/*    */   @Instance
/*    */   public static Main instance;
/*    */   @SidedProxy(clientSide = "com.KICE.ShadowMother.proxy.ClientProxy", serverSide = "com.KICE.ShadowMother.proxy.CommonProxy")
/*    */   public static CommonProxy proxy;
/*    */   
/*    */   @EventHandler
/*    */   public static void PreInit(FMLPreInitializationEvent event) {
/* 25 */     proxy.preInit();
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public static void init(FMLInitializationEvent event) {
/* 30 */     proxy.init();
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public static void Postinit(FMLPostInitializationEvent event) {
/* 35 */     proxy.postInit();
/*    */   }
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
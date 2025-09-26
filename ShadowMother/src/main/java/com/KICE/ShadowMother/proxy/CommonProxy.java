/*    */ package com.KICE.ShadowMother.proxy;
/*    */ 
/*    */ import com.KICE.ShadowMother.cryingcatalyst.StartupCommon;
/*    */ import com.KICE.ShadowMother.packets.ParticlePacket;
/*    */ import com.KICE.ShadowMother.packets.ServerHandlerDummy;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommonProxy
/*    */ {
/*    */   protected static final int DRAIN_SMOKE = 80;
/*    */   protected static final int THUNDER = 81;
/*    */   public static SimpleNetworkWrapper simpleNetworkWrapper;
/*    */   
/*    */   public void registerItemRenderer(Item item, int meta, String id) {}
/*    */   
/*    */   public void preInit() {
/* 31 */     simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("subyin");
/* 32 */     simpleNetworkWrapper.registerMessage(ServerHandlerDummy.class, ParticlePacket.class, 80, Side.SERVER);
/*    */ 
/*    */ 
/*    */     
/* 36 */     StartupCommon.preInitCommon();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void init() {
/* 45 */     StartupCommon.initCommon();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void postInit() {
/* 53 */     StartupCommon.postInitCommon();
/*    */   }
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\proxy\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
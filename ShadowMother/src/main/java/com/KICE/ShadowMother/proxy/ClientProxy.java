/*    */ package com.KICE.ShadowMother.proxy;
/*    */ 
/*    */ import com.KICE.ShadowMother.cryingcatalyst.StartupClientOnly;
/*    */ import com.KICE.ShadowMother.packets.ParticlePacket;
/*    */ import com.KICE.ShadowMother.util.ModEvents;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraftforge.client.model.ModelLoader;
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
/*    */ public class ClientProxy
/*    */   extends CommonProxy
/*    */ {
/*    */   public void registerItemRenderer(Item item, int meta, String id) {
/* 26 */     ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void preInit() {
/* 36 */     super.preInit();
/* 37 */     ModEvents.initClient();
/* 38 */     StartupClientOnly.preInitClientOnly();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void init() {
/* 47 */     CommonProxy.simpleNetworkWrapper.registerMessage(ParticlePacket.Handler.class, ParticlePacket.class, 80, Side.CLIENT);
/*    */     
/* 49 */     super.init();
/* 50 */     StartupClientOnly.initClientOnly();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void postInit() {
/* 58 */     super.postInit();
/* 59 */     StartupClientOnly.postInitClientOnly();
/*    */   }
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\proxy\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package com.KICE.ShadowMother.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.event.TextureStitchEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ public class StitchEventHandler
/*    */   implements IEventHandler
/*    */ {
/*    */   public static TextureAtlasSprite shadowDismantlerSprite;
/* 14 */   private static ArrayList<ResourceLocation> tributeSymbols = new ArrayList<>();
/*    */   
/*    */   public static void initTributeSymbols(ResourceLocation symbol) {
/* 17 */     tributeSymbols.add(symbol);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void textureStuff(TextureStitchEvent.Pre event) {
/* 24 */     event.getMap().registerSprite(new ResourceLocation("subyin:blocks/crying_circle"));
/* 25 */     shadowDismantlerSprite = event.getMap().registerSprite(new ResourceLocation("subyin:particle/shadow_dismantler"));
/*    */     
/* 27 */     for (ResourceLocation symbol : tributeSymbols) {
/* 28 */       event.getMap().registerSprite(symbol);
/* 29 */       System.out.println(symbol.toString());
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 34 */     event.getMap().registerSprite(new ResourceLocation("subyin:particle/test/fire"));
/* 35 */     event.getMap().registerSprite(new ResourceLocation("subyin:tribute_symbol/fire"));
/*    */   }
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMothe\\util\StitchEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
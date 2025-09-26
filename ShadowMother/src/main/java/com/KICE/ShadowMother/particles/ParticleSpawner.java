/*    */ package com.KICE.ShadowMother.particles;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.particle.Particle;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ParticleSpawner
/*    */ {
/* 10 */   private static Minecraft mc = Minecraft.getMinecraft();
/* 11 */   public static final ResourceLocation DEFAULT_SYMBOL = new ResourceLocation("subyin:tribute_symbol/default_symbol");
/*    */   
/*    */   public static Particle spawnParticle(ModParticleTypes type, double par2, double par4, double par6, double par8, double par10, double par12) {
/* 14 */     return spawnParticle(type, par2, par4, par6, par8, par10, par12, DEFAULT_SYMBOL);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Particle spawnParticle(ModParticleTypes type, double par2, double par4, double par6, double par8, double par10, double par12, ResourceLocation symbol) {
/* 19 */     if (mc != null && mc.getRenderViewEntity() != null && mc.effectRenderer != null) {
/*    */       
/* 21 */       int var14 = mc.gameSettings.particleSetting;
/*    */       
/* 23 */       if (var14 == 1 && ((World)mc.world).rand.nextInt(3) == 0)
/*    */       {
/* 25 */         var14 = 2;
/*    */       }
/*    */       
/* 28 */       double var15 = (mc.getRenderViewEntity()).posX - par2;
/* 29 */       double var17 = (mc.getRenderViewEntity()).posY - par4;
/* 30 */       double var19 = (mc.getRenderViewEntity()).posZ - par6;
/* 31 */       Particle var21 = null;
/* 32 */       double var22 = 16.0D;
/*    */       
/* 34 */       if (var15 * var15 + var17 * var17 + var19 * var19 > var22 * var22)
/*    */       {
/* 36 */         return null;
/*    */       }
/* 38 */       if (var14 > 1)
/*    */       {
/* 40 */         return null;
/*    */       }
/*    */ 
/*    */       
/* 44 */       if (type == ModParticleTypes.SHADOW_DISMANTLER)
/*    */       {
/* 46 */         var21 = new ParticleShadowDismantler((World)mc.world, par2, par4, par6, par8, par10, par12);
/*    */       }
/* 48 */       if (type == ModParticleTypes.TRIBUTE_SYMBOL)
/*    */       {
/* 50 */         var21 = new ParticleTributeSymbol((World)mc.world, par2, par4, par6, par8, par10, par12, symbol);
/*    */       }
/* 52 */       if (type == ModParticleTypes.ARCANE_CRY)
/*    */       {
/* 54 */         var21 = new ParticleArcaneCrying((World)mc.world, par2, par4, par6, par8, par10, par12);
/*    */       }
/*    */       
/* 57 */       mc.effectRenderer.addEffect(var21);
/* 58 */       return var21;
/*    */     } 
/*    */     
/* 61 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\particles\ParticleSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
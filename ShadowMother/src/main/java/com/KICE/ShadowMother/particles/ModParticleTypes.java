/*    */ package com.KICE.ShadowMother.particles;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public enum ModParticleTypes
/*    */ {
/* 11 */   SHADOW_DISMANTLER("shadow dismantler", 8312, false),
/* 12 */   TRIBUTE_SYMBOL("tribute symbol", 8313, false),
/* 13 */   ARCANE_CRY("arcane cry", 8314, false);
/*    */   
/*    */   private final String particleName;
/*    */   
/*    */   private final int particleID;
/*    */   private final boolean shouldIgnoreRange;
/*    */   
/*    */   //private final int argumentCount;
		   private static int argumentCount = 3;
/*    */   private static final Map<Integer, ModParticleTypes> PARTICLES;
/*    */   private static final Map<String, ModParticleTypes> BY_NAME;
/*    */   static {
/* 21 */     PARTICLES = Maps.newHashMap();
/* 22 */     BY_NAME = Maps.newHashMap();
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 80 */     for (ModParticleTypes enumparticletypes : values()) {
/*    */       
/* 82 */       PARTICLES.put(Integer.valueOf(enumparticletypes.getParticleID()), enumparticletypes);
/* 83 */       BY_NAME.put(enumparticletypes.getParticleName(), enumparticletypes);
/*    */     } 
/*    */   }
/*    */   
/*    */   
/*    */   ModParticleTypes(String particleNameIn, int particleIDIn, boolean shouldIgnoreRangeIn) {
/*    */     this.particleName = particleNameIn;
/*    */     this.particleID = particleIDIn;
/*    */     this.shouldIgnoreRange = shouldIgnoreRangeIn;
/*    */   }
/*    */   
/*    */   public static Set<String> getParticleNames() {
/*    */     return BY_NAME.keySet();
/*    */   }
/*    */   
/*    */   public String getParticleName() {
/*    */     return this.particleName;
/*    */   }
/*    */   
/*    */   public int getParticleID() {
/*    */     return this.particleID;
/*    */   }
/*    */   
/*    */   public int getArgumentCount() {
/*    */     return this.argumentCount;
/*    */   }
/*    */   
/*    */   public boolean getShouldIgnoreRange() {
/*    */     return this.shouldIgnoreRange;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static ModParticleTypes getParticleFromId(int particleId) {
/*    */     return PARTICLES.get(Integer.valueOf(particleId));
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static ModParticleTypes getByName(String nameIn) {
/*    */     return BY_NAME.get(nameIn);
/*    */   }
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\particles\ModParticleTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
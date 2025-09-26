/*     */ package com.KICE.ShadowMother.particles;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.IParticleFactory;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParticleShadowDismantler
/*     */   extends Particle
/*     */ {
/*  26 */   private static final VertexFormat VERTEX_FORMAT = (new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F).addElement(DefaultVertexFormats.TEX_2F).addElement(DefaultVertexFormats.COLOR_4UB).addElement(DefaultVertexFormats.TEX_2S).addElement(DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);
/*  27 */   private static final ResourceLocation textureRL = new ResourceLocation("subyin:particle/shadow_dismantler");
/*  28 */   private int frame = 0;
/*     */ 
/*     */   
/*     */   protected ParticleShadowDismantler(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double par8, double par10, double par12) {
/*  32 */     this(worldIn, xCoordIn, yCoordIn, zCoordIn, 2.0F, par8, par10, par12);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ParticleShadowDismantler(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, float scale, double par8, double par10, double par12) {
/*  37 */     super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
/*     */     
/*  39 */     this.motionX *= 0.0D;
/*  40 */     this.motionY *= 0.0D;
/*  41 */     this.motionZ *= 0.0D;
/*     */     
/*  43 */     if (par8 == 0.0D)
/*     */     {
/*  45 */       par8 = 1.0D;
/*     */     }
/*  47 */     this.particleAge = 1;
/*  48 */     this.particleScale = scale;
/*  49 */     this.particleMaxAge = 40;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  57 */     setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(textureRL.toString()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderParticle(BufferBuilder worldRendererIn, Entity entity, float partialTick, float edgeLRdirectionX, float edgeUDdirectionY, float edgeLRdirectionZ, float edgeUDdirectionX, float edgeUDdirectionZ) {
/*  80 */     float dim = (this.particleTexture.getMaxU() - this.particleTexture.getMinU()) / 3.0F;
/*  81 */     float minU = this.particleTexture.getMinU() + this.particleTextureIndexX * dim;
/*  82 */     float maxU = minU + dim;
/*  83 */     float minV = this.particleTexture.getMinV() + this.particleTextureIndexY * dim;
/*  84 */     float maxV = minV + dim;
/*     */     
/*  86 */     double scale = (0.15F * this.particleScale);
/*  87 */     double scaleLR = scale;
/*  88 */     double scaleUD = scale;
/*  89 */     double x = this.prevPosX + (this.posX - this.prevPosX) * partialTick - Particle.interpPosX;
/*  90 */     double y = this.prevPosY + (this.posY - this.prevPosY) * partialTick - Particle.interpPosY;
/*  91 */     double z = this.prevPosZ + (this.posZ - this.prevPosZ) * partialTick - Particle.interpPosZ;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     int combinedBrightness = getBrightnessForRender(partialTick);
/*  97 */     int skyLightTimes16 = combinedBrightness >> 16 & 0xFFFF;
/*  98 */     int blockLightTimes16 = combinedBrightness & 0xFFFF;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     worldRendererIn.pos(x - edgeLRdirectionX * scaleLR - edgeUDdirectionX * scaleUD, y - edgeUDdirectionY * scaleUD, z - edgeLRdirectionZ * scaleLR - edgeUDdirectionZ * scaleUD)
/*     */ 
/*     */       
/* 106 */       .tex(maxU, maxV)
/* 107 */       .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
/* 108 */       .lightmap(skyLightTimes16, blockLightTimes16)
/* 109 */       .endVertex();
/* 110 */     worldRendererIn.pos(x - edgeLRdirectionX * scaleLR + edgeUDdirectionX * scaleUD, y + edgeUDdirectionY * scaleUD, z - edgeLRdirectionZ * scaleLR + edgeUDdirectionZ * scaleUD)
/*     */ 
/*     */       
/* 113 */       .tex(maxU, minV)
/* 114 */       .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
/* 115 */       .lightmap(skyLightTimes16, blockLightTimes16)
/* 116 */       .endVertex();
/* 117 */     worldRendererIn.pos(x + edgeLRdirectionX * scaleLR + edgeUDdirectionX * scaleUD, y + edgeUDdirectionY * scaleUD, z + edgeLRdirectionZ * scaleLR + edgeUDdirectionZ * scaleUD)
/*     */ 
/*     */       
/* 120 */       .tex(minU, minV)
/* 121 */       .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
/* 122 */       .lightmap(skyLightTimes16, blockLightTimes16)
/* 123 */       .endVertex();
/* 124 */     worldRendererIn.pos(x + edgeLRdirectionX * scaleLR - edgeUDdirectionX * scaleUD, y - edgeUDdirectionY * scaleUD, z + edgeLRdirectionZ * scaleLR - edgeUDdirectionZ * scaleUD)
/*     */ 
/*     */       
/* 127 */       .tex(minU, maxV)
/* 128 */       .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
/* 129 */       .lightmap(skyLightTimes16, blockLightTimes16)
/* 130 */       .endVertex();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 177 */     move(this.motionX, this.motionY, this.motionZ);
/*     */ 
/*     */     
/* 180 */     int particleTextureIndexa = this.particleAge * 9 / this.particleMaxAge;
/*     */     
/* 182 */     if (particleTextureIndexa > 0) {
/* 183 */       this.motionY = Math.pow(1.17D, this.particleAge) / 1000.0D;
/*     */     }
/* 185 */     this.particleTextureIndexX = particleTextureIndexa % 3;
/* 186 */     this.particleTextureIndexY = particleTextureIndexa / 3;
/*     */ 
/*     */     
/* 189 */     this.prevPosX = this.posX;
/* 190 */     this.prevPosY = this.posY;
/* 191 */     this.prevPosZ = this.posZ;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 197 */     this.particleAge++;
/* 198 */     if (particleTextureIndexa >= 2) this.particleAge++; 
/* 199 */     if (particleTextureIndexa >= 4) this.particleAge++;
/*     */     
/* 201 */     if (this.particleAge > this.particleMaxAge) {
/* 202 */       setExpired();
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static class Factory
/*     */     implements IParticleFactory
/*     */   {
/*     */     public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
/* 211 */       return new ParticleShadowDismantler(worldIn, xCoordIn, yCoordIn, zCoordIn, (float)xSpeedIn, (float)ySpeedIn, (float)zSpeedIn);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFXLayer() {
/* 220 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\particles\ParticleShadowDismantler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
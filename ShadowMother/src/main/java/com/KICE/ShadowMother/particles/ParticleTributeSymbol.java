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
/*     */ public class ParticleTributeSymbol
/*     */   extends Particle
/*     */ {
/*  24 */   private static final VertexFormat VERTEX_FORMAT = (new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F).addElement(DefaultVertexFormats.TEX_2F).addElement(DefaultVertexFormats.COLOR_4UB).addElement(DefaultVertexFormats.TEX_2S).addElement(DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);
/*     */   private ResourceLocation textureRL;
/*  26 */   private int frame = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   protected ParticleTributeSymbol(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double par8, double par10, double par12, ResourceLocation symbol) {
/*  31 */     super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */     
/*  35 */     if (par8 == 0.0D)
/*     */     {
/*  37 */       par8 = 1.0D;
/*     */     }
/*     */     
/*  40 */     this.particleScale = 2.0F;
/*  41 */     this.textureRL = symbol;
/*     */ 
/*     */     
/*  44 */     setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(this.textureRL.toString()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderParticle(BufferBuilder worldRendererIn, Entity entity, float partialTick, float edgeLRdirectionX, float edgeUDdirectionY, float edgeLRdirectionZ, float edgeUDdirectionX, float edgeUDdirectionZ) {
/*  55 */     double minU = this.particleTexture.getMinU();
/*  56 */     double maxU = this.particleTexture.getMaxU();
/*  57 */     double minV = this.particleTexture.getMinV();
/*  58 */     double maxV = this.particleTexture.getMaxV();
/*     */     
/*  60 */     double scale = (0.1F * this.particleScale);
/*  61 */     double scaleLR = scale;
/*  62 */     double scaleUD = scale;
/*  63 */     double x = this.prevPosX + (this.posX - this.prevPosX) * partialTick - Particle.interpPosX;
/*  64 */     double y = this.prevPosY + (this.posY - this.prevPosY) * partialTick - Particle.interpPosY;
/*  65 */     double z = this.prevPosZ + (this.posZ - this.prevPosZ) * partialTick - Particle.interpPosZ;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     int combinedBrightness = getBrightnessForRender(partialTick);
/*  71 */     int skyLightTimes16 = combinedBrightness >> 16 & 0xFFFF;
/*  72 */     int blockLightTimes16 = 240;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     worldRendererIn.pos(x - edgeLRdirectionX * scaleLR - edgeUDdirectionX * scaleUD, y - edgeUDdirectionY * scaleUD, z - edgeLRdirectionZ * scaleLR - edgeUDdirectionZ * scaleUD)
/*     */ 
/*     */       
/*  80 */       .tex(maxU, maxV)
/*  81 */       .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
/*  82 */       .lightmap(skyLightTimes16, blockLightTimes16)
/*  83 */       .endVertex();
/*  84 */     worldRendererIn.pos(x - edgeLRdirectionX * scaleLR + edgeUDdirectionX * scaleUD, y + edgeUDdirectionY * scaleUD, z - edgeLRdirectionZ * scaleLR + edgeUDdirectionZ * scaleUD)
/*     */ 
/*     */       
/*  87 */       .tex(maxU, minV)
/*  88 */       .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
/*  89 */       .lightmap(skyLightTimes16, blockLightTimes16)
/*  90 */       .endVertex();
/*  91 */     worldRendererIn.pos(x + edgeLRdirectionX * scaleLR + edgeUDdirectionX * scaleUD, y + edgeUDdirectionY * scaleUD, z + edgeLRdirectionZ * scaleLR + edgeUDdirectionZ * scaleUD)
/*     */ 
/*     */       
/*  94 */       .tex(minU, minV)
/*  95 */       .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
/*  96 */       .lightmap(skyLightTimes16, blockLightTimes16)
/*  97 */       .endVertex();
/*  98 */     worldRendererIn.pos(x + edgeLRdirectionX * scaleLR - edgeUDdirectionX * scaleUD, y - edgeUDdirectionY * scaleUD, z + edgeLRdirectionZ * scaleLR - edgeUDdirectionZ * scaleUD)
/*     */ 
/*     */       
/* 101 */       .tex(minU, maxV)
/* 102 */       .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
/* 103 */       .lightmap(skyLightTimes16, blockLightTimes16)
/* 104 */       .endVertex();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static class Factory
/*     */     implements IParticleFactory
/*     */   {
/*     */     public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
/* 120 */       return new ParticleShadowDismantler(worldIn, xCoordIn, yCoordIn, zCoordIn, (float)xSpeedIn, (float)ySpeedIn, (float)zSpeedIn);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFXLayer() {
/* 129 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\particles\ParticleTributeSymbol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
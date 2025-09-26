/*     */ package com.KICE.ShadowMother.particles;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.particle.IParticleFactory;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*     */ import net.minecraft.entity.Entity;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParticleArcaneCrying
/*     */   extends Particle
/*     */ {
/*  29 */   private static final VertexFormat VERTEX_FORMAT = (new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F).addElement(DefaultVertexFormats.TEX_2F).addElement(DefaultVertexFormats.COLOR_4UB).addElement(DefaultVertexFormats.TEX_2S).addElement(DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);
/*     */   
/*     */   private double radius;
/*     */   private double shift;
/*     */   private float spinSpeed;
/*     */   private double originX;
/*     */   private double originZ;
/*  36 */   Random field_187136_p = new Random();
/*     */   
/*     */   protected ParticleArcaneCrying(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double par8, double par10, double par12) {
/*  39 */     this(worldIn, xCoordIn, yCoordIn, zCoordIn, 2.0F, par8, par10, par12);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ParticleArcaneCrying(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, float scale, double par8, double par10, double par12) {
/*  44 */     super(worldIn, xCoordIn, yCoordIn, zCoordIn);
/*     */     
/*  46 */     this.originX = xCoordIn;
/*  47 */     this.originZ = zCoordIn;
/*     */     
/*  49 */     this.world = worldIn;
/*  50 */     this.radius = this.field_187136_p.nextDouble() * 2.5D + 0.2D;
/*  51 */     this.shift = this.field_187136_p.nextDouble() * 2.0D * Math.PI;
/*  52 */     this.prevPosX = Math.sin(this.shift) * this.radius + xCoordIn;
/*  53 */     this.prevPosZ = Math.cos(this.shift) * this.radius + zCoordIn;
/*  54 */     this.motionY = 0.02D;
/*     */ 
/*     */     
/*  57 */     setPosition(this.prevPosX, yCoordIn, this.prevPosZ);
/*  58 */     this.spinSpeed = 0.002F;
/*     */     
/*  60 */     this.particleMaxAge *= 2;
/*  61 */     this.particleMaxAge += 100;
/*  62 */     setRBGColorF(0.0F, 173.0F, 60.0F);
/*  63 */     setParticleTextureIndex((int)(Math.random() * 26.0D + 1.0D + 224.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderParticle(BufferBuilder worldRendererIn, Entity entity, float partialTick, float edgeLRdirectionX, float edgeUDdirectionY, float edgeLRdirectionZ, float edgeUDdirectionX, float edgeUDdirectionZ) {
/*  73 */     double minU = (this.particleTextureIndexX / 16.0F);
/*  74 */     double maxU = minU + 0.062437500804662704D;
/*  75 */     double minV = (this.particleTextureIndexY / 16.0F);
/*  76 */     double maxV = minV + 0.062437500804662704D;
/*     */     
/*  78 */     double scale = (0.1F * this.particleScale);
/*  79 */     double scaleLR = scale;
/*  80 */     double scaleUD = scale;
/*  81 */     double x = this.prevPosX + (this.posX - this.prevPosX) * partialTick - Particle.interpPosX;
/*  82 */     double y = this.prevPosY + (this.posY - this.prevPosY) * partialTick - Particle.interpPosY;
/*  83 */     double z = this.prevPosZ + (this.posZ - this.prevPosZ) * partialTick - Particle.interpPosZ;
/*     */     
/*  85 */     float t = (float)(((this.particleAge + partialTick) * 2.0F) * Math.PI);
/*     */     
/*  87 */     float offX = (float)(this.radius * Math.sin((t * this.spinSpeed) + this.shift));
/*  88 */     float offZ = (float)(this.radius * Math.cos((t * this.spinSpeed) + this.shift));
/*     */ 
/*     */ 
/*     */     
/*  92 */     setPosition(this.originX + offX, this.posY, this.originZ + offZ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     int combinedBrightness = getBrightnessForRender(partialTick);
/* 101 */     int skyLightTimes16 = combinedBrightness >> 16 & 0xFFFF;
/* 102 */     int blockLightTimes16 = 240;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     worldRendererIn.pos(x - edgeLRdirectionX * scaleLR - edgeUDdirectionX * scaleUD, y - edgeUDdirectionY * scaleUD, z - edgeLRdirectionZ * scaleLR - edgeUDdirectionZ * scaleUD)
/*     */ 
/*     */       
/* 110 */       .tex(maxU, maxV)
/* 111 */       .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
/* 112 */       .lightmap(skyLightTimes16, blockLightTimes16)
/* 113 */       .endVertex();
/* 114 */     worldRendererIn.pos(x - edgeLRdirectionX * scaleLR + edgeUDdirectionX * scaleUD, y + edgeUDdirectionY * scaleUD, z - edgeLRdirectionZ * scaleLR + edgeUDdirectionZ * scaleUD)
/*     */ 
/*     */       
/* 117 */       .tex(maxU, minV)
/* 118 */       .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
/* 119 */       .lightmap(skyLightTimes16, blockLightTimes16)
/* 120 */       .endVertex();
/* 121 */     worldRendererIn.pos(x + edgeLRdirectionX * scaleLR + edgeUDdirectionX * scaleUD, y + edgeUDdirectionY * scaleUD, z + edgeLRdirectionZ * scaleLR + edgeUDdirectionZ * scaleUD)
/*     */ 
/*     */       
/* 124 */       .tex(minU, minV)
/* 125 */       .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
/* 126 */       .lightmap(skyLightTimes16, blockLightTimes16)
/* 127 */       .endVertex();
/* 128 */     worldRendererIn.pos(x + edgeLRdirectionX * scaleLR - edgeUDdirectionX * scaleUD, y - edgeUDdirectionY * scaleUD, z + edgeLRdirectionZ * scaleLR - edgeUDdirectionZ * scaleUD)
/*     */ 
/*     */       
/* 131 */       .tex(minU, maxV)
/* 132 */       .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
/* 133 */       .lightmap(skyLightTimes16, blockLightTimes16)
/* 134 */       .endVertex();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 145 */     move(this.motionX, this.motionY, this.motionZ);
/*     */ 
/*     */ 
/*     */     
/* 149 */     this.motionY += 0.001D;
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
/* 162 */     this.prevPosX = this.posX;
/* 163 */     this.prevPosY = this.posY;
/* 164 */     this.prevPosZ = this.posZ;
/*     */     
/* 166 */     this.particleAge++;
/* 167 */     this.radius -= 0.004999999888241291D;
/* 168 */     this.spinSpeed += 1.0E-4F;
/*     */     
/* 170 */     if (this.particleAge > this.particleMaxAge) {
/* 171 */       setExpired();
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static class Factory
/*     */     implements IParticleFactory
/*     */   {
/*     */     public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
/* 180 */       return new ParticleArcaneCrying(worldIn, xCoordIn, yCoordIn, zCoordIn, (float)xSpeedIn, (float)ySpeedIn, (float)zSpeedIn);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFXLayer() {
/* 189 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\particles\ParticleArcaneCrying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
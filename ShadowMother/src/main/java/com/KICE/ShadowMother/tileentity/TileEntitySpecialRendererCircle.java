/*     */ package com.KICE.ShadowMother.tileentity;
/*     */ 
/*     */ import com.KICE.ShadowMother.particles.ModParticleTypes;
/*     */ import com.KICE.ShadowMother.particles.ParticleSpawner;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraft.util.text.TextComponentString;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public class TileEntitySpecialRendererCircle
/*     */   extends TileEntitySpecialRenderer<CryingCircle>
/*     */ {
/*     */   public void render(CryingCircle tileEntity, double relativeX, double relativeY, double relativeZ, float partialTicks, int blockDamageProgress, float alpha) {
	//System.out.println("cost from renderer: " + tileEntity.getCost());
/*  36 */     int tickActive = tileEntity.getActive();
/*  37 */     if (tileEntity.isActive())
/*  38 */     { if (tickActive < 250) tileEntity.addActive(1);
/*     */        }
/*     */     
/*  41 */     else if (tickActive > 30) { tileEntity.addActive(-1); }
/*     */     else
/*     */     { return; }
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
/*  57 */     double pedestalCentreOffsetX = 0.5D;
/*  58 */     double pedestalCentreOffsetY = 0.8D;
/*  59 */     double pedestalCentreOffsetZ = 0.5D;
/*     */     
/*  61 */     double gemCentreOffsetX = 0.5D;
/*  62 */     double gemCentreOffsetY = 1.400000023841858D;
/*  63 */     double gemCentreOffsetZ = 0.5D;
/*  64 */     double MAX_REV_PER_SEC = 0.02D;
/*  65 */     double revsPerSecond = 0.02D;
/*  66 */     double angularPositionInDegrees = tileEntity.getNextAngularPosition(revsPerSecond);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  73 */       GL11.glPushMatrix();
/*  74 */       GL11.glPushAttrib(8192);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  83 */       GlStateManager.translate(relativeX + gemCentreOffsetX, relativeY + gemCentreOffsetY, relativeZ + gemCentreOffsetZ);
/*  84 */       GlStateManager.rotate((float)angularPositionInDegrees, 0.0F, 1.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  89 */       Tessellator tessellator = Tessellator.getInstance();
/*  90 */       BufferBuilder bufferBuilder = tessellator.getBuffer();
/*  91 */       bindTexture(circleTexture);
/*  92 */       GlStateManager.color(1.0F, 1.0F, 1.0F, tickActive / 250.0F);
/*     */ 
/*     */       
/*  95 */       GL11.glDisable(2896);
/*  96 */       GL11.glEnable(3042);
/*  97 */       GL11.glDepthMask(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 103 */       int SKY_LIGHT_VALUE = 15;
/* 104 */       int BLOCK_LIGHT_VALUE = 0;
/* 105 */       OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 0.0F);
/*     */       
/* 107 */       bufferBuilder.begin(4, DefaultVertexFormats.POSITION_TEX);
/* 108 */       addGemVertices(bufferBuilder);
/* 109 */       tessellator.draw();
/*     */     } finally {
/* 112 */       GL11.glPopAttrib();
/* 113 */       GL11.glPopMatrix();
/*     */     }
/* 115 */     if (tileEntity.getActive() >= 250) {

	
/* 116 */       if (tileEntity.getCost() > 0) {
/* 117 */         TextComponentString textComponentString = new TextComponentString("" + tileEntity.getCost());
				
/* 118 */         Style style = (new Style()).setColor(TextFormatting.WHITE);
/* 119 */         style.setBold(Boolean.valueOf(true));
/* 120 */         textComponentString.setStyle(style);
/*     */         
/* 122 */         setLightmapDisabled(true);
/* 123 */         drawNameplate(tileEntity, textComponentString.getFormattedText(), relativeX, relativeY, relativeZ, 20);
/* 124 */         setLightmapDisabled(false);
/* 125 */       } else if (tileEntity.getTribute()[0] != -1 && tileEntity.getActive() > 100 && 
/* 126 */         tileEntity.tributeSymbol == null) {
/* 127 */         BlockPos pos = tileEntity.getPos();
/* 128 */         String locat = tileEntity.getTributeIconLocation();
/* 129 */         tileEntity.tributeSymbol = ParticleSpawner.spawnParticle(ModParticleTypes.TRIBUTE_SYMBOL, pos.getX() + 0.5D, pos.getY() + 1.4D, pos.getZ() + 0.5D, 0.0D, -1.0D, 0.0D, new ResourceLocation(locat));
/* 130 */         System.out.println(locat);
/*     */       }
/*     */     }
/*     */     
/* 134 */     if (tileEntity.tributeSymbol != null && (tileEntity.getActive() < 250 || tileEntity.getTribute()[0] == -1)) {
/* 135 */       tileEntity.tributeSymbol.setExpired();
/* 136 */       tileEntity.tributeSymbol = null;
/* 137 */       System.out.println("deleting particle");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isGlobalRenderer(CryingCircle tileEntityMBE21) {
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void addGemVertices(BufferBuilder bufferBuilder) {
/* 151 */     double[][] vertexTable = { { -2.5D, 0.0D, -2.5D, 0.0D, 0.0D }, { -2.5D, 0.0D, 2.5D, 0.0D, 1.0D }, { 2.5D, 0.0D, -2.5D, 1.0D, 0.0D }, { 2.5D, 0.0D, -2.5D, 1.0D, 0.0D }, { -2.5D, 0.0D, 2.5D, 0.0D, 1.0D }, { 2.5D, 0.0D, 2.5D, 1.0D, 1.0D } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     for (double[] vertex : vertexTable) {
/* 162 */       bufferBuilder.pos(vertex[0], vertex[1], vertex[2])
/* 163 */         .tex(vertex[3], vertex[4])
/* 164 */         .endVertex();
/*     */     }
/*     */   }
/*     */   
/* 168 */   private static final ResourceLocation circleTexture = new ResourceLocation("subyin:textures/entity/crying_circle.png");
/*     */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\tileentity\TileEntitySpecialRendererCircle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.KICE.ShadowMother.cryingcatalyst;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.block.model.IBakedModel;
/*     */ import net.minecraft.client.renderer.block.model.ItemOverrideList;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.client.renderer.color.BlockColors;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormatElement;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraftforge.client.model.pipeline.IVertexConsumer;
/*     */ import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
/*     */ import net.minecraftforge.client.model.pipeline.VertexLighterFlat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BakedTears
/*     */   implements IBakedModel
/*     */ {
/*     */   private static VertexFormat format;
/*     */   private static TextureAtlasSprite block;
/*     */   private static TextureAtlasSprite circle;
/*  38 */   public static final ModelResourceLocation variantTag = new ModelResourceLocation("block/crying_catalyst", "normal");
/*     */ 
/*     */   
/*     */   public static IBakedModel unchanged;
/*     */ 
/*     */   
/*     */   public BakedTears(VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
/*  45 */      BakedTears.format = format;
/*  46 */      block = bakedTextureGetter.apply(new ResourceLocation("subyin:blocks/crying_catalyst"));
/*  47 */      circle = bakedTextureGetter.apply(new ResourceLocation("subyin:blocks/crying_circle"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void putVertex(UnpackedBakedQuad.Builder builder, IBlockState state, Vec3d normal, double x, double y, double z, TextureAtlasSprite sprite, float u, float v, boolean hasBrightness, int brightness) {
/*  56 */     for (int e = 0; e < format.getElementCount(); e++) {
/*     */       
/*  58 */       switch (format.getElement(e).getUsage()) {
/*     */         
/*     */         case POSITION:
/*  61 */           builder.put(e, new float[] { (float)x, (float)y, (float)z });
/*     */           break;
/*     */         case COLOR:
/*  64 */           builder.put(e, new float[] { 1.0F, 1.0F, 1.0F, 1.0F });
/*     */           break;
/*     */         case UV:
/*  67 */           if (format.getElement(e).getIndex() == 1) {
/*     */             
/*  69 */             if (hasBrightness) {
/*     */               
/*  71 */               float blockLight = (brightness >> 0 & 0xF) * 32.0F / 65535.0F;
/*  72 */               float skyLight = (brightness >> 20 & 0xF) * 32.0F / 65535.0F;
/*  73 */               builder.put(e, new float[] { blockLight, skyLight });
/*     */               
/*     */               break;
/*     */             } 
/*  77 */             builder.put(e, new float[0]);
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/*  82 */           u = sprite.getInterpolatedU(u);
/*  83 */           v = sprite.getInterpolatedV(v);
/*  84 */           builder.put(e, new float[] { u, v });
/*     */           break;
/*     */         
/*     */         case NORMAL:
/*  88 */           if (hasBrightness) {
/*     */             
/*  90 */             builder.put(e, new float[] { 0.0F, 1.0F, 0.0F });
/*     */             
/*     */             break;
/*     */           } 
/*  94 */           builder.put(e, new float[] { (float)normal.x, (float)normal.y, (float)normal.z });
/*     */           break;
/*     */         
/*     */         default:
/*  98 */           builder.put(e, new float[0]);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private BakedQuad createQuad(IBlockState state, Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4, TextureAtlasSprite sprite, boolean hasBrightness, int brightness) {
/* 106 */     Vec3d normal = v1.subtract(v2).crossProduct(v3.subtract(v2));
/*     */     
/* 108 */     UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(format);
/* 109 */     builder.setTexture(sprite);
/* 110 */     putVertex(builder, state, normal, v1.x, v1.y, v1.z, sprite, 16.0F, 16.0F, hasBrightness, brightness);
/* 111 */     putVertex(builder, state, normal, v2.x, v2.y, v2.z, sprite, 16.0F, 0.0F, hasBrightness, brightness);
/* 112 */     putVertex(builder, state, normal, v3.x, v3.y, v3.z, sprite, 0.0F, 0.0F, hasBrightness, brightness);
/* 113 */     putVertex(builder, state, normal, v4.x, v4.y, v4.z, sprite, 0.0F, 16.0F, hasBrightness, brightness);
/* 114 */     return (BakedQuad)builder.build();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
/* 120 */     if (side != null)
/*     */     {
/* 122 */       return Collections.emptyList();
/*     */     }
/*     */     
/* 125 */     List<BakedQuad> quads = new ArrayList<>();
/*     */     
/* 127 */      quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(1.0D, 0.0D, 1.0D), new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(0.0D, 0.0D, 0.0D), block, true, 255));
/* 128 */      quads.add(createQuad(state, new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(1.0D, 1.0D, 0.0D), block, true, 255));
/* 129 */      quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 1.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(0.0D, 0.0D, 1.0D), block, true, 255));
/* 130 */      quads.add(createQuad(state, new Vec3d(0.0D, 0.0D, 0.0D), new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(1.0D, 0.0D, 0.0D), block, true, 255));
/* 131 */      quads.add(createQuad(state, new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(0.0D, 0.0D, 0.0D), block, true, 255));
/* 132 */      quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(1.0D, 0.0D, 1.0D), block, true, 255));
/*     */ 
/*     */ 
/*     */     
/* 136 */     quads = transformQuads(quads);
/* 137 */     return quads;
/*     */   }
/*     */   
/*     */   private static List<BakedQuad> transformQuads(List<BakedQuad> oldQuads) {
/* 141 */     List<BakedQuad> quads = new ArrayList<>(oldQuads);
/*     */     
/* 143 */     for (int i = 0; i < quads.size(); i++) {
/* 144 */       BakedQuad quad = quads.get(i);
/*     */       
/* 146 */       quads.set(i, transformQuad(quad, 0.007F));
/*     */     } 
/*     */     
/* 149 */     return quads;
/*     */   }
/*     */ 
/*     */   
/*     */   private static BakedQuad transformQuad(BakedQuad quad, final float light) {
/* 154 */     VertexFormat newFormat = getFormatWithLightMap(quad.getFormat());
/*     */     
/* 156 */     UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(newFormat);
/*     */     
/* 158 */     VertexLighterFlat trans = new VertexLighterFlat(Minecraft.getMinecraft().getBlockColors())
/*     */       {
/*     */         protected void updateLightmap(float[] normal, float[] lightmap, float x, float y, float z) {
/* 161 */           lightmap[0] = light;
/* 162 */           lightmap[1] = light;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void setQuadTint(int tint) {}
/*     */       };
/* 171 */     trans.setParent((IVertexConsumer)builder);
/*     */     
/* 173 */     quad.pipe((IVertexConsumer)trans);
/*     */     
/* 175 */     builder.setQuadTint(quad.getTintIndex());
/* 176 */     builder.setQuadOrientation(quad.getFace());
/* 177 */     builder.setTexture(quad.getSprite());
/* 178 */     builder.setApplyDiffuseLighting(false);
/*     */     
/* 180 */     return (BakedQuad)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public static VertexFormat getFormatWithLightMap(VertexFormat format) {
/* 185 */     if (format == DefaultVertexFormats.BLOCK)
/* 186 */       return DefaultVertexFormats.BLOCK; 
/* 187 */     if (format == DefaultVertexFormats.ITEM)
/* 188 */       return (new VertexFormat(DefaultVertexFormats.ITEM)).addElement(DefaultVertexFormats.TEX_2S); 
/* 189 */     if (!format.hasUvOffset(1)) {
/* 190 */       VertexFormat result = new VertexFormat(format);
/*     */       
/* 192 */       result.addElement(DefaultVertexFormats.TEX_2S);
/*     */       
/* 194 */       return result;
/*     */     } 
/*     */     
/* 197 */     return format;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAmbientOcclusion() {
/* 202 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGui3d() {
/* 207 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBuiltInRenderer() {
/* 212 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlasSprite getParticleTexture() {
/* 217 */     return block;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemOverrideList getOverrides() {
/* 222 */     return ItemOverrideList.NONE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\cryingcatalyst\BakedTears.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
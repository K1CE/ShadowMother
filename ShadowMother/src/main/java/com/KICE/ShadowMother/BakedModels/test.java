/*     */ package com.KICE.ShadowMother.BakedModels;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.block.model.IBakedModel;
/*     */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.block.model.ItemOverrideList;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormatElement;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class test
/*     */   implements IBakedModel
/*     */ {
/*     */   private static VertexFormat format;
/*     */   private static TextureAtlasSprite base;
/*     */   private static TextureAtlasSprite teste;
/*     */   
/*     */   public test(VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
/*  34 */      test.format = format;
/*  35 */      base = (TextureAtlasSprite)bakedTextureGetter.apply(new ResourceLocation("testemod:textures/blocks/test"));
/*  36 */      teste = (TextureAtlasSprite)bakedTextureGetter.apply(new ResourceLocation("testemod:textures/blocks/test"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void putVertex(UnpackedBakedQuad.Builder builder, IBlockState state, Vec3d normal, double x, double y, double z, TextureAtlasSprite sprite, float u, float v, boolean hasBrightness, int brightness) {
/*  42 */     for (int e = 0; e < format.getElementCount(); e++) {
/*     */       
/*  44 */       switch (format.getElement(e).getUsage()) {
/*     */         
/*     */         case POSITION:
/*  47 */           builder.put(e, new float[] { (float)x, (float)y, (float)z });
/*     */           break;
/*     */         case COLOR:
/*  50 */           builder.put(e, new float[] { 1.0F, 1.0F, 1.0F, 1.0F });
/*     */           break;
/*     */         case UV:
/*  53 */           if (format.getElement(e).getIndex() == 1) {
/*     */             
/*  55 */             if (hasBrightness) {
/*     */               
/*  57 */               float blockLight = (brightness >> 0 & 0xF) * 32.0F / 65535.0F;
/*  58 */               float skyLight = (brightness >> 20 & 0xF) * 32.0F / 65535.0F;
/*  59 */               builder.put(e, new float[] { blockLight, skyLight });
/*     */               
/*     */               break;
/*     */             } 
/*  63 */             builder.put(e, new float[0]);
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/*  68 */           u = sprite.getInterpolatedU(u);
/*  69 */           v = sprite.getInterpolatedV(v);
/*  70 */           builder.put(e, new float[] { u, v });
/*     */           break;
/*     */         
/*     */         case NORMAL:
/*  74 */           if (hasBrightness) {
/*     */             
/*  76 */             builder.put(e, new float[] { 0.0F, 1.0F, 0.0F });
/*     */             
/*     */             break;
/*     */           } 
/*  80 */           builder.put(e, new float[] { (float)normal.x, (float)normal.y, (float)normal.z });
/*     */           break;
/*     */         
/*     */         default:
/*  84 */           builder.put(e, new float[0]);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private BakedQuad createQuad(IBlockState state, Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4, TextureAtlasSprite sprite, boolean hasBrightness, int brightness) {
/*  92 */     Vec3d normal = v1.subtract(v2).crossProduct(v3.subtract(v2));
/*     */     
/*  94 */     UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(format);
/*  95 */     builder.setTexture(sprite);
/*  96 */     putVertex(builder, state, normal, v1.x, v1.y, v1.z, sprite, 16.0F, 16.0F, hasBrightness, brightness);
/*  97 */     putVertex(builder, state, normal, v2.x, v2.y, v2.z, sprite, 16.0F, 0.0F, hasBrightness, brightness);
/*  98 */     putVertex(builder, state, normal, v3.x, v3.y, v3.z, sprite, 0.0F, 0.0F, hasBrightness, brightness);
/*  99 */     putVertex(builder, state, normal, v4.x, v4.y, v4.z, sprite, 0.0F, 16.0F, hasBrightness, brightness);
/* 100 */     return (BakedQuad)builder.build();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
/* 106 */     if (side != null)
/*     */     {
/* 108 */       return Collections.emptyList();
/*     */     }
/*     */     
/* 111 */     List<BakedQuad> quads = new ArrayList<>();
/*     */     
/* 113 */     quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(1.0D, 0.0D, 1.0D), new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(0.0D, 0.0D, 0.0D), base, true, 255));
/* 114 */     quads.add(createQuad(state, new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(1.0D, 1.0D, 0.0D), base, true, 255));
/* 115 */     quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 1.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(0.0D, 0.0D, 1.0D), base, true, 255));
/* 116 */     quads.add(createQuad(state, new Vec3d(0.0D, 0.0D, 0.0D), new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(1.0D, 0.0D, 0.0D), base, true, 255));
/* 117 */     quads.add(createQuad(state, new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(0.0D, 0.0D, 0.0D), base, true, 255));
/* 118 */     quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(1.0D, 0.0D, 1.0D), base, true, 255));
/*     */     
/* 120 */      quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(1.0D, 0.0D, 1.0D), new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(0.0D, 0.0D, 0.0D), teste, true, 0));
/* 121 */      quads.add(createQuad(state, new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(1.0D, 1.0D, 0.0D), teste, true, 0));
/* 122 */      quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 1.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(0.0D, 0.0D, 1.0D), teste, true, 0));
/* 123 */      quads.add(createQuad(state, new Vec3d(0.0D, 0.0D, 0.0D), new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(1.0D, 0.0D, 0.0D), teste, true, 0));
/* 124 */      quads.add(createQuad(state, new Vec3d(0.0D, 0.0D, 1.0D), new Vec3d(0.0D, 1.0D, 1.0D), new Vec3d(0.0D, 1.0D, 0.0D), new Vec3d(0.0D, 0.0D, 0.0D), teste, true, 0));
/* 125 */      quads.add(createQuad(state, new Vec3d(1.0D, 0.0D, 0.0D), new Vec3d(1.0D, 1.0D, 0.0D), new Vec3d(1.0D, 1.0D, 1.0D), new Vec3d(1.0D, 0.0D, 1.0D), teste, true, 0));
/*     */     
/* 127 */     return quads;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAmbientOcclusion() {
/* 133 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isGui3d() {
/* 139 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBuiltInRenderer() {
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TextureAtlasSprite getParticleTexture() {
/* 151 */      return teste;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemCameraTransforms getItemCameraTransforms() {
/* 157 */     return ItemCameraTransforms.DEFAULT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemOverrideList getOverrides() {
/* 163 */     return ItemOverrideList.NONE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\BakedModels\test.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
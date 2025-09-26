/*    */ package com.KICE.ShadowMother.cryingcatalyst;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.client.renderer.block.model.IBakedModel;
/*    */ import net.minecraft.client.renderer.block.model.ModelRotation;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*    */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.IModel;
/*    */ import net.minecraftforge.common.model.IModelState;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CryingModel
/*    */   implements IModel
/*    */ {
/*    */   public Collection<ResourceLocation> getDependencies() {
/* 21 */     return Collections.emptyList();
/*    */   }
/*    */   
/*    */   public Collection<ResourceLocation> getTextures() {
/* 25 */     return (Collection<ResourceLocation>)ImmutableList.copyOf(new ResourceLocation[] { new ResourceLocation("subyin:blocks/crying_catalyst") });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
/* 31 */     BakedTears bakedTears = new BakedTears(format, bakedTextureGetter);
/*    */     
/* 33 */     return bakedTears;
/*    */   }
/*    */   
/*    */   public IModelState getDefaultState() {
/* 37 */     return (IModelState)ModelRotation.X0_Y0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\cryingcatalyst\CryingModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
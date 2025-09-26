/*    */ package com.KICE.ShadowMother.cryingcatalyst;
/*    */ 
/*    */ import com.KICE.ShadowMother.init.ModBlocks;
/*    */ import com.KICE.ShadowMother.tileentity.CryingCircle;
/*    */ import com.KICE.ShadowMother.tileentity.TileEntitySpecialRendererCircle;
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.ModelLoader;
/*    */ import net.minecraftforge.client.model.ModelLoaderRegistry;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.client.registry.ClientRegistry;
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
/*    */ public class StartupClientOnly
/*    */ {
/*    */   public static void preInitClientOnly() {
/* 36 */     ModelLoaderRegistry.registerLoader(new CryingModelLoader());
/*    */     
/* 38 */     MinecraftForge.EVENT_BUS.register(ModelBakeEventHandler.instance);
/*    */ 
/*    */     
/* 41 */     ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.CRYING_CATALYST), new ItemMeshDefinition()
/*    */         {
/*    */           public ModelResourceLocation getModelLocation(ItemStack stack)
/*    */           {
/* 45 */             return new ModelResourceLocation(new ResourceLocation("subyin", "sampleBlock"), "inventory");
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void initClientOnly() {
/* 53 */     ClientRegistry.bindTileEntitySpecialRenderer(CryingCircle.class, (TileEntitySpecialRenderer)new TileEntitySpecialRendererCircle());
/*    */   }
/*    */   
/*    */   public static void postInitClientOnly() {}
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\cryingcatalyst\StartupClientOnly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
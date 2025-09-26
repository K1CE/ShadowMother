/*    */ package com.KICE.ShadowMother.util.handlers;
/*    */ 
/*    */ import com.KICE.ShadowMother.init.ModBlocks;
/*    */ import com.KICE.ShadowMother.init.ModItems;
/*    */ import com.KICE.ShadowMother.tileentity.CryingCircle;
/*    */ import com.KICE.ShadowMother.util.IHasModel;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.event.ModelRegistryEvent;
/*    */ import net.minecraftforge.event.RegistryEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*    */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class RegistryHandler
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onItemRegister(RegistryEvent.Register<Item> event) {
/* 23 */     event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onBlockRegister(RegistryEvent.Register<Block> event) {
/* 28 */     event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
/* 29 */     GameRegistry.registerTileEntity(CryingCircle.class, new ResourceLocation("subyin:crying_catalyst"));
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onModelRegister(ModelRegistryEvent event) {
/* 34 */     for (Item item : ModItems.ITEMS) {
/* 35 */       if (item instanceof IHasModel) {
/* 36 */         ((IHasModel)item).registerModels();
/*    */       }
/*    */     } 
/* 39 */     for (Block block : ModBlocks.BLOCKS) {
/* 40 */       if (block instanceof IHasModel)
/* 41 */         ((IHasModel)block).registerModels(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMothe\\util\handlers\RegistryHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
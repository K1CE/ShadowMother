/*    */ package com.KICE.ShadowMother.cryingcatalyst;
/*    */ 
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.ICustomModelLoader;
/*    */ import net.minecraftforge.client.model.IModel;
/*    */ 
/*    */ 
/*    */ public class CryingModelLoader
/*    */   implements ICustomModelLoader
/*    */ {
	
	//scavenged code
	/*
	  // Stack: 1, Locals: 1
		public CryingModelLoader() {
		    0  aload_0 [this]
		    1  invokespecial java.lang.Object() [11]
		    4  return
		      Line numbers:
		        [pc: 0, line: 9]
		      Local variable table:
		        [pc: 0, pc: 5] local: this index: 0 type: com.KICE.ShadowMother.cryingcatalyst.CryingModelLoader
		}
		*/
	public CryingModelLoader () {}
/*    */   public boolean accepts(ResourceLocation modelLocation) {
/* 13 */     return modelLocation.getResourcePath().equals("crying_catalyst");
/*    */   }
/*    */ 
/*    */   
/*    */   public IModel loadModel(ResourceLocation modelLocation) {
/* 18 */     return new CryingModel();
/*    */   }
/*    */   
/*    */   
/*    */
@Override
public void onResourceManagerReload(IResourceManager resourceManager) {
	// TODO Auto-generated method stub
	
} }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\cryingcatalyst\CryingModelLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
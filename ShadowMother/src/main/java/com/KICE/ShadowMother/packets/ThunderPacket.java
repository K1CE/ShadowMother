/*    */ package com.KICE.ShadowMother.packets;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.effect.EntityLightningBolt;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThunderPacket
/*    */   implements IMessage
/*    */ {
/*    */   private boolean messageValid;
/*    */   double x;
/*    */   double y;
/*    */   double z;
/*    */   
/*    */   public ThunderPacket() {
/* 29 */     this.messageValid = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public ThunderPacket(double x, double y, double z) {
/* 34 */     this.x = x;
/* 35 */     this.y = y;
/* 36 */     this.z = z;
/*    */     
/* 38 */     this.messageValid = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buf) {
/*    */     try {
/* 46 */       this.x = buf.readDouble();
/* 47 */       this.y = buf.readDouble();
/* 48 */       this.z = buf.readDouble();
/*    */     }
/* 50 */     catch (IndexOutOfBoundsException ioe) {
/*    */       return;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buf) {
/* 59 */     if (!this.messageValid) {
/*    */       return;
/*    */     }
/* 62 */     buf.writeDouble(this.x);
/* 63 */     buf.writeDouble(this.y);
/* 64 */     buf.writeDouble(this.z);
/*    */   }
/*    */   
/* 67 */   static Random rand = new Random();
/*    */ 
/*    */   
/*    */   public static class Handler
/*    */     implements IMessageHandler<ThunderPacket, IMessage>
/*    */   {
/*    */     public IMessage onMessage(ThunderPacket message, MessageContext ctx) {
/* 74 */       if (!message.messageValid && ctx.side != Side.CLIENT)
/*    */       {
/* 76 */         return null;
/*    */       }
/*    */ 
/*    */       
/* 80 */       Minecraft minecraft = Minecraft.getMinecraft();
/* 81 */       WorldClient worldClient = minecraft.world;
/*    */       
/* 83 */       minecraft.addScheduledTask(() -> processMessage(message, worldClient));
/*    */       
/* 85 */       return null;
/*    */     }
/*    */     
/*    */     public void processMessage(ThunderPacket message, WorldClient worldClient) {
/* 89 */       worldClient.spawnEntity((Entity)new EntityLightningBolt((World)worldClient, message.x, message.y + 1.0D, message.z, false));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\packets\ThunderPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
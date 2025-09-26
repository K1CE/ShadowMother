/*     */ package com.KICE.ShadowMother.packets;
/*     */ 
/*     */ import com.KICE.ShadowMother.particles.ModParticleTypes;
/*     */ import com.KICE.ShadowMother.particles.ParticleSpawner;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*     */ import net.minecraftforge.fml.relauncher.Side;
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
/*     */ public class ParticlePacket
/*     */   implements IMessage
/*     */ {
/*     */   private boolean messageValid;
/*     */   double x;
/*     */   double y;
/*     */   double z;
/*     */   int type;
/*     */   int count;
/*     */   
/*     */   public ParticlePacket() {
/*  36 */     this.messageValid = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ParticlePacket(double x, double y, double z, int type, int count) {
/*  41 */     this.x = x;
/*  42 */     this.y = y;
/*  43 */     this.z = z;
/*  44 */     this.type = type;
/*  45 */     this.count = count;
/*     */     
/*  47 */     this.messageValid = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fromBytes(ByteBuf buf) {
/*     */     try {
/*  55 */       this.x = buf.readDouble();
/*  56 */       this.y = buf.readDouble();
/*  57 */       this.z = buf.readDouble();
/*  58 */       this.type = buf.readInt();
/*  59 */       this.count = buf.readInt();
/*     */     }
/*  61 */     catch (IndexOutOfBoundsException ioe) {
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void toBytes(ByteBuf buf) {
/*  70 */     if (!this.messageValid) {
/*     */       return;
/*     */     }
/*  73 */     buf.writeDouble(this.x);
/*  74 */     buf.writeDouble(this.y);
/*  75 */     buf.writeDouble(this.z);
/*  76 */     buf.writeInt(this.type);
/*  77 */     buf.writeInt(this.count);
/*     */   }
/*     */   
/*  80 */   static Random rand = new Random();
/*     */ 
/*     */   
/*     */   public static class Handler
/*     */     implements IMessageHandler<ParticlePacket, IMessage>
/*     */   {
/*     */     public IMessage onMessage(ParticlePacket message, MessageContext ctx) {
/*  87 */       if (!message.messageValid && ctx.side != Side.CLIENT)
/*     */       {
/*  89 */         return null;
/*     */       }
/*     */ 
/*     */       
/*  93 */       Minecraft minecraft = Minecraft.getMinecraft();
/*  94 */       WorldClient worldClient = minecraft.world;
/*     */       
/*  96 */       minecraft.addScheduledTask(() -> processMessage(message, worldClient));
/*     */       
/*  98 */       return null;
/*     */     }
/*     */     
/*     */     public void processMessage(ParticlePacket message, WorldClient worldClient) {
/* 102 */       if (message.x != 0.0D && message.y != 0.0D && message.z != 0.0D)
/* 103 */         if (message.type == 1) {
/* 104 */           for (int i = 0; i < message.count; i++) {
/* 105 */             double xmod = ParticlePacket.rand.nextFloat() * 0.4D;
/* 106 */             double ymod = ParticlePacket.rand.nextFloat() * 0.8D;
/* 107 */             double zmod = ParticlePacket.rand.nextFloat() * 0.4D;
/* 108 */             double d1 = (float)message.x + xmod - 0.2D;
/* 109 */             double d2 = (float)message.y + ymod + 0.05D;
/* 110 */             double d3 = (float)message.z + zmod - 0.2D;
/*     */             
/* 112 */             (Minecraft.getMinecraft()).world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, false, d1, d2, d3, xmod / 5.0D, ymod / 5.0D, zmod / 5.0D, new int[0]);
/*     */           }
/*     */         
/* 115 */         } else if (message.type == 2) {
/* 116 */           for (int i = 0; i < message.count; i++) {
/* 117 */             double d1 = (float)message.x + ParticlePacket.rand.nextFloat() * 0.4D - 0.2D;
/* 118 */             double d2 = (float)message.y + ParticlePacket.rand.nextFloat() * 0.8D + 0.05D;
/* 119 */             double d3 = (float)message.z + ParticlePacket.rand.nextFloat() * 0.4D - 0.2D;
/* 120 */             ParticleSpawner.spawnParticle(ModParticleTypes.SHADOW_DISMANTLER, d1, d2, d3, 0.0D, -1.0D, 0.0D);
/*     */           } 
/* 122 */         } else if (message.type == 3) {
/* 123 */           for (int i = 0; i < message.count; i++)
/*     */           {
/*     */ 
/*     */             
/* 127 */             ParticleSpawner.spawnParticle(ModParticleTypes.ARCANE_CRY, message.x, message.y, message.z, 0.0D, -1.0D, 0.0D);
/*     */           }
/*     */         }  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\packets\ParticlePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
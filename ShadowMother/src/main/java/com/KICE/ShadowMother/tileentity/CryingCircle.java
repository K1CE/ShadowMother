/*     */ package com.KICE.ShadowMother.tileentity;
/*     */ 
/*     */ import com.KICE.ShadowMother.blocks.CryingObsidian;
/*     */ import com.KICE.ShadowMother.init.ModItems;
/*     */ import com.KICE.ShadowMother.packets.ParticlePacket;
/*     */ import com.KICE.ShadowMother.proxy.CommonProxy;
/*     */ import com.KICE.ShadowMother.util.StitchEventHandler;
import com.google.common.base.Optional;

/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.effect.EntityLightningBolt;
/*     */ import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityShulker;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.network.play.server.SPacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ITickable;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraft.util.text.TextComponentString;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.world.GameType;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CryingCircle
/*     */   extends TileEntity
/*     */   implements ITickable
/*     */ {
/*     */   private boolean crying = false;
/*  73 */   private final long INVALID_TIME = 0L;
/*  74 */   private long lastTime = 0L;
/*     */   private double lastAngularPosition;
/*  76 */   private int activePower = 30;
/*     */   public static final String DEFAULT_ICON_LOCATION = "subyin:tribute_symbol/default_symbol";
/*  78 */   public Particle tributeSymbol = null;
/*     */   BlockPos position;
/*     */   StructureBoundingBox cryingBox;
/*  81 */   private Map<BlockPos, IBlockState> obsidian = new HashMap<>();
/*     */   
/*  83 */   private EntityPlayer owner = null;
/*  84 */   private int greed = 0;
/*  85 */   private int cost = 5;
/*  86 */   private int tributeID = -1;
/*  87 */   String tributeIconLocation = "subyin:tribute_symbol/default_symbol";
/*     */   

/*     */   private boolean cooldown = false;
/*  90 */   private static final Style blueStyle = (new Style()).setColor(TextFormatting.DARK_BLUE);
/*  91 */   private static final TextComponentString TOO_BRIGHT = new TextComponentString("do you fear the dark?");
/*  92 */   private static final TextComponentString NOT_OPEN = new TextComponentString("my breath is scarce");
/*  93 */   private static final TextComponentString REVIVE = new TextComponentString("welcome to our family, "); private static final int MAX_FINAL_ANIM_TIME = 190; int briefing;
/*     */   int delay;
/*     */   int consumeDelay;
/*     */   int finishDelay;
/*     */   int previousAmount;
/*     */   int[] tribute;
/*     */   boolean finishing;
/*     */   Random rand;
/*     */   int loadDelay;
/*     */   private static ArrayList<int[]> lowTributes;
/*     */   private static ArrayList<int[]> midTributes;
/*     */   private static ArrayList<int[]> highTributes;
/*     */   private static ArrayList<String> tributeResources;
/*     */   
/*     */   public void readFromNBT(NBTTagCompound nbt) {
/* 108 */     super.readFromNBT(nbt);
				if(!nbt.getBoolean("nbtWritten")) return;
System.out.println("read cost before change" + cost);
/* 109 */     this.greed = nbt.getInteger("cri1");
/* 110 */     this.cost = nbt.getInteger("cri2");
/* 111 */     this.tribute[0] = nbt.getInteger("cri3-0");
/* 112 */     this.tribute[1] = nbt.getInteger("cri3-1");
/* 113 */     this.tributeID = nbt.getInteger("cri4");
/* 114 */     this.tributeIconLocation = nbt.getString("cri5");
System.out.println("read cost after change" + cost);
/*     */   }
/*     */   
/*     */   public NBTTagCompound writeToNBT(NBTTagCompound nbt) {	
	System.out.println("writing cost" + cost);
/* 118 */     nbt.setInteger("cri1", this.greed);
/* 119 */     nbt.setInteger("cri2", this.cost);
/* 120 */     nbt.setInteger("cri3-0", this.tribute[0]);
/* 121 */     nbt.setInteger("cri3-1", this.tribute[1]);
/* 122 */     nbt.setInteger("cri4", this.tributeID);
/* 123 */     nbt.setString("cri5", this.tributeIconLocation);
				nbt.setBoolean("nbtWritten", true);
/* 124 */     return super.writeToNBT(nbt);
/*     */   }
/*
			@Override
		    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
		    {
				return false;
		    }*/
/*     */   
/*     */   public void onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	//if (worldIn.isRemote) {
	//	return;
	//}
/* 128 */     getBlockType();

				System.out.println("circle pressed");
/* 129 */     if (playerIn.isAllowEdit()) {
/* 130 */       if (isDaytime(worldIn)) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       	
/* 135 */       boolean revived = true;
/* 136 */       int i = worldIn.playerEntities.size();
/* 137 */       while (revived && i > 0) {
/* 138 */         if (((EntityPlayer)worldIn.playerEntities.get(i - 1)).isSpectator()) revived = false; 
/* 139 */         i--;
/*     */       }
				if(false) {
/* 141 */       if (revived) {
/*     */         return;
/*     */       }
				}
/* 144 */       if (worldIn.isRemote) {
/* 145 */         this.crying = !this.crying;
/*     */         return;
/*     */       }

/* 148 */       if (this.crying) {
/* 149 */         this.crying = false;
/* 150 */         depressObsidian();
/*     */         
/*     */         return;
/*     */       }
/*     */       
/* 155 */       this.position = getPos();
/* 157 */      // this.world.notifyBlockUpdate(pos, getBlockType().getDefaultState(), getBlockType().getDefaultState(), 2);
/* 158 */       this.finishDelay = 190;
/* 160 */       this.crying = true;
/* 161 */       this.owner = playerIn;
				
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onChunkUnload() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLoad() {
/* 179 */     CryingObsidian.resetCore();
/* 180 */     System.out.println("loaded crying catalyst");
/* 181 */     this.loadDelay = 30;
/*     */   }
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
/*     */   public void messageBright() {
/* 198 */     if (this.owner != null && this.crying && !this.cooldown) this.owner.sendMessage((ITextComponent)TOO_BRIGHT); 
/*     */   }
/*     */   private void messageRevive(String name) {
/* 201 */     if (this.owner != null && this.crying && !this.cooldown) this.owner.sendMessage(REVIVE.appendText(name)); 
/*     */   }
/*     */   public void messageClosed() {
/* 204 */     if (this.owner != null && this.crying && !this.cooldown) this.owner.sendMessage((ITextComponent)NOT_OPEN); 
/*     */   }
/*     */   
/*     */   public void depressObsidian() {
	/*     */ System.out.println("OBSIDIAN DEPRESSED");
/* 208 */     this.crying = false;
			  if(world.isRemote) System.out.println("client circle off");
/* 209 */     CryingObsidian.resetCore();
/* 210 */     for (Map.Entry<BlockPos, IBlockState> entry : this.obsidian.entrySet()) {
/* 211 */       CryingObsidian.depress(this.world, entry.getKey(), entry.getValue());
/*     */     }
/* 213 */     this.obsidian = new HashMap<>();
			  System.out.println("crying turned off!!");
			  loadDelay = 20;
			  //cost = 5;
			  //this.markDirty(); 
/* 215 */     //this.world.notifyBlockUpdate(this.pos, getBlockType().getDefaultState(), getBlockType().getDefaultState(), 2);
			  //this.world.markBlockForUpdate(pos.getX(),  pos.getY(), pos.getZ());


/*     */   }
/*     */ 
/*     */   
/*     */   public void addObsidian(BlockPos pos, IBlockState state) {
/* 220 */     this.obsidian.put(pos, state);
/*     */   }
/*     */   
/*     */   public int getActive() {
/* 224 */     return this.activePower;
/*     */   }
/*     */   
/*     */   public void addActive(int increment) {
/* 228 */     this.activePower += increment;
/*     */   }
/*     */   
/*     */   public boolean isActive() {
/* 232 */     return this.crying;
/*     */   }
/*     */   
/*     */   public int getCost() {
/* 236 */     return this.cost;
/*     */   }
/*     */   
/*     */   public int[] getTribute() {
/* 240 */     return this.tribute;
/*     */   }
/*     */   
/*     */   public String getTributeIconLocation() {
/* 244 */     return this.tributeIconLocation;
/*     */   }
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
/*     */   public double getNextAngularPosition(double revsPerSecond) {
/* 257 */     long timeNow = System.nanoTime();
/* 258 */     if (this.lastTime == 0L) {
/* 259 */       this.lastTime = timeNow;
/* 260 */       this.lastAngularPosition = 0.0D;
/*     */     } 
/* 262 */     double DEGREES_PER_REV = 360.0D;
/* 263 */     double NANOSECONDS_PER_SECOND = 1.0E9D;
/* 264 */     double nextAngularPosition = this.lastAngularPosition + (timeNow - this.lastTime) * revsPerSecond * 360.0D / 1.0E9D;
/* 265 */     nextAngularPosition %= 360.0D;
/* 266 */     this.lastAngularPosition = nextAngularPosition;
/* 267 */     this.lastTime = timeNow;
/* 268 */     return nextAngularPosition;
/*     */   }
/* 270 */   public CryingCircle() {
			  this.briefing = 0;
/* 271 */     this.delay = 0;
			  this.cost = 5;
/* 272 */     this.consumeDelay = 0;
/* 273 */     this.finishDelay = 190;
/* 274 */     this.previousAmount = 0;
/* 275 */     this.tribute = new int[] { -1, -1 };
/* 276 */     this.finishing = false;
/* 277 */     this.rand = new Random();
/* 278 */     this.loadDelay = 40; TOO_BRIGHT.setStyle(blueStyle); NOT_OPEN.setStyle(blueStyle);
/*     */     REVIVE.setStyle(blueStyle);
/*     */     this.position = getPos();
/*     */     int i = 2;
/* 282 */     this.cryingBox = new StructureBoundingBox(new int[] { this.pos.getX() - i, this.pos.getY() - i, this.pos.getZ() - i, this.pos.getX() + i, this.pos.getY() + i, this.pos.getZ() + i }); 
			}

			public void update() {
     			if ((getWorld()).isRemote) {
     				//System.out.println("client cost: " + cost);
     				//System.out.println("client crying: " + crying);
       				return; 
				}
			  if (this.loadDelay != -1 && this.loadDelay > 0) {
/* 283 */       this.loadDelay--;
/*     */ 
/*     */     
/*     */     }
/* 287 */     else if (this.loadDelay == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 292 */       this.blockType = getBlockType();
/* 293 */       this.world.notifyNeighborsOfStateChange(this.pos.add(0, 0, 0), this.blockType, true);
/*     */ 
/*     */       
/* 296 */       this.loadDelay = -1;
/* 297 */       this.world.setBlockState(this.pos, this.world.getBlockState(this.pos), 2);
/* 157 */       this.world.notifyBlockUpdate(pos, getBlockType().getDefaultState(), getBlockType().getDefaultState(), 2);
				System.out.println("block update catalyst, cost: " + cost);
/*     */     }
/*     */     
     		/*
			if (this.loadDelay == 0) {
				System.out.println("block update catalyst, cost: " + cost);
				this.world.notifyBlockUpdate(pos, getBlockType().getDefaultState(), getBlockType().getDefaultState(), 2);
			} else System.out.println("delay " + loadDelay);

			*/
/* 302 */     if (this.crying && (this.world.getLightFromNeighbors(this.pos.up()) > 4 || this.world.isDaytime() || this.cooldown)) {
/* 303 */       messageBright();
/* 304 */       depressObsidian();
/*     */     }
/*     */     
/* 307 */     if (this.crying) { if ( this.activePower < 85)  this.activePower++; }
/* 308 */     else if (this.activePower > 0) { this.activePower--; }
/* 309 */      if (this.activePower >= 85) {

/* 310 */       this.delay++;
/* 311 */       this.delay %= 3;
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
/* 326 */       if (this.delay == 0) {

					//	System.out.println("cost from circle: " + getCost());
/* 327 */         if (this.cost > 0) {
/* 328 */           boolean existingFlesh = false;
/* 329 */           int removed = 0;
/* 330 */           int amount = 0;
/* 331 */           for (EntityItem flesh : this.world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB((this.pos.getX() - 2), this.pos.getY(), (this.pos.getZ() - 2), (this.pos.getX() + 3), (this.pos.getY() + 4), (this.pos.getZ() + 3)))) {
/* 332 */             if (flesh.getItem().getItem().getUnlocalizedName().equals("item.rottenFlesh") && ((Entity)flesh).collidedVertically && flesh.getDistance(this.position.getX() + 0.5D, this.position.getY() + 0.2D, this.position.getZ() + 0.5D) < 3.0D) {
/* 333 */               int stackSize = flesh.getItem().getCount();
/* 334 */               amount += stackSize;
/* 335 */               existingFlesh = true;
/* 336 */               Vec3d fPos = flesh.getPositionVector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 346 */               ParticlePacket particlePacket = new ParticlePacket(fPos.x, fPos.y, fPos.z, 2, this.consumeDelay / 3 + 1);
/*     */               
/* 348 */               NetworkRegistry.TargetPoint target = new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), fPos.x, fPos.y, fPos.z, 20.0D);
/* 349 */               CommonProxy.simpleNetworkWrapper.sendToAllAround((IMessage)particlePacket, target);
/*     */ 
/*     */ 
/*     */               
/* 353 */               if (this.consumeDelay > 12) {
/* 354 */                 existingFlesh = false;
/* 355 */                 if (stackSize >= this.cost) {
/* 356 */                   removed += this.cost;
/* 357 */                   flesh.getItem().setCount(stackSize - this.cost);
/* 358 */                   this.cost = 0;
/* 359 */                   this.consumeDelay = 0;
/* 360 */                   this.world.notifyBlockUpdate(this.pos, this.blockType.getDefaultState(), this.blockType.getDefaultState(), 2);

							if(rand.nextDouble() < 0.1f * removed) spawnAsh(fPos);
/*     */                   return;
/*     */                 }
							
							
/* 363 */                 removed += stackSize;
/* 364 */                 this.cost -= stackSize;
/* 365 */                 flesh.setDead();
/* 366 */                 this.world.notifyBlockUpdate(this.pos, this.blockType.getDefaultState(), this.blockType.getDefaultState(), 2);
							if(rand.nextDouble() < 0.1f * removed) spawnAsh(fPos);

	               			
/*     */               } 
/*     */             } 
/*     */           } 
/* 370 */           if (this.previousAmount != amount) this.consumeDelay = 0; 
/* 371 */           this.previousAmount = amount;
/*     */           
/* 373 */           if (existingFlesh) this.consumeDelay++; 
/* 374 */         } else if (this.tributeID != -1 && this.tribute[0] != -1) {
/* 375 */           boolean cont = false;
/* 376 */           for (Entity tribObject : this.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB((this.pos
/*     */                 
/* 378 */                 .getX() - 2), this.pos.getY(), (this.pos.getZ() - 2), (this.pos.getX() + 3), (this.pos.getY() + 4), (this.pos.getZ() + 3)))) {
/*     */             
/* 380 */             if (this.tribute[0] > -1) {
/* 381 */               if (tribObject instanceof EntityItem) {
/* 382 */                 EntityItem tribItem = (EntityItem)tribObject;
/* 383 */                 Item tribItemItem = tribItem.getItem().getItem();
/*     */                 
/* 385 */                 if (Item.getIdFromItem(tribItemItem) == this.tribute[0] && (
/* 386 */                   tribItemItem.getHasSubtypes() ? (tribItemItem.getMetadata(tribItem.getItem()) == this.tribute[1]) : (this.tribute[1] == -1)) && ((Entity)tribItem).collidedVertically && tribItem
/* 388 */                   .getDistance(this.position.getX() + 0.5D, this.position.getY() + 0.2D, this.position.getZ() + 0.5D) < 3.0D) {
							if(consumeDelay > 12) {
								cont = true; 
							} else {
								Vec3d fPos = tribObject.getPositionVector();
								ParticlePacket particlePacket = new ParticlePacket(fPos.x, fPos.y, fPos.z, 2, this.consumeDelay / 3 + 1);
								              
								NetworkRegistry.TargetPoint target = new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), fPos.x, fPos.y, fPos.z, 20.0D);
								CommonProxy.simpleNetworkWrapper.sendToAllAround((IMessage)particlePacket, target);
								consumeDelay++;
								break;
							}
						  }
/*     */               }
/* 390 */             } else if (this.tribute[0] == -2) {
/*     */               
/* 392 */               if (tribObject.getDistance(this.position.getX() + 0.5D, this.position.getY() + 0.2D, this.position.getZ() + 0.5D) < 3.0D) {
/* 393 */                 if (this.tribute[1] == 1)
/* 394 */                 { if (tribObject instanceof net.minecraft.entity.passive.EntityAnimal && !((EntityLivingBase)tribObject).isEntityAlive()) cont = true;
/*     */                    }
/*     */                 
/* 397 */                 else if (this.tribute[1] == 2 && (
/* 398 */                   tribObject instanceof EntityPlayer || tribObject instanceof net.minecraft.entity.passive.EntityVillager) && !((EntityLivingBase)tribObject).isEntityAlive()) { cont = true; }
/*     */               
/*     */               }
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 405 */             if (cont) {
/* 406 */               Vec3d fPos = tribObject.getPositionVector();
/*     */               
/* 408 */               ParticlePacket particlePacket = new ParticlePacket(fPos.x, fPos.y, fPos.z, 1, 15);
/*     */               
/* 410 */               NetworkRegistry.TargetPoint target = new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), fPos.x + 0.5D, fPos.y + 1.0D, fPos.z + 0.5D, 20.0D);
/* 411 */               CommonProxy.simpleNetworkWrapper.sendToAllAround((IMessage)particlePacket, target);
/*     */ 
/*     */ 
/*     */               
/* 415 */               getWorld().playSound(null, tribObject.getPosition(), SoundEvents.BLOCK_SAND_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);
/* 416 */               tribObject.setDead();
/* 417 */               this.tribute[0] = -1;
/* 418 */               this.tribute[1] = -1;
/* 419 */               this.tributeID = -1;
/*     */               
/* 421 */               getWorld().spawnEntity((Entity)new EntityItem(getWorld(), fPos.x, fPos.y, fPos.z, new ItemStack(ModItems.UMBRAL_ASH)));
/* 422 */               if (this.rand.nextBoolean()) getWorld().spawnEntity((Entity)new EntityItem(getWorld(), fPos.x, fPos.y, fPos.z, new ItemStack(ModItems.UMBRAL_ASH)));
/*     */               
/* 424 */               this.world.notifyBlockUpdate(this.pos, this.blockType.getDefaultState(), this.blockType.getDefaultState(), 2); 
						break;
/*     */             } 
/*     */           } 
/*     */         } else {
/* 428 */           this.finishing = true;
/*     */         } 
/*     */       }
/*     */       
/* 432 */       if (this.finishing && this.finishDelay <= 63) {
/* 433 */         System.out.println(this.finishDelay);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 438 */         double x = getPos().getX();
/* 439 */         double y = getPos().getY() + 1.0D;
/* 440 */         double z = getPos().getZ();
/*     */         
/* 442 */         this.world.addWeatherEffect((Entity)new EntityLightningBolt(this.world, x, y, z, false));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 447 */         this.world.getWorldInfo().setCleanWeatherTime(0);
/* 448 */         this.world.getWorldInfo().setRainTime(0);
/* 449 */         this.world.getWorldInfo().setThunderTime(12000);
/* 450 */         this.world.getWorldInfo().setRaining(true);
/* 451 */         this.world.getWorldInfo().setThundering(true);
/*     */ 
/*     */         
/* 454 */         boolean revived = false;
/* 455 */         int i = this.world.playerEntities.size();
/* 456 */         while (!revived && i > 0) {
/* 457 */           EntityPlayer ply = this.world.playerEntities.get(i - 1);
/*     */           
/* 459 */           if (ply.isSpectator()) {
/* 460 */             revived = true;
/* 461 */             ply.setPosition(getPos().getX(), getPos().getY() + 4.0D, getPos().getZ());
/* 462 */             ply.setSpawnPoint(getPos(), false);
/* 463 */             ply.setGameType(GameType.SURVIVAL);
/* 464 */             messageRevive(ply.getName());
/*     */           } 
/* 466 */           i--;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 471 */         this.cooldown = true;
/* 472 */         this.tribute[0] = -1;
/* 473 */         this.tribute[1] = -1;
/* 474 */         this.tributeID = -1;
/* 475 */         this.tributeIconLocation = "subyin:tribute_symbol/default_symbol";
/* 476 */         this.greed++;
/* 477 */         int increase = this.greed + this.greed / 2;
/* 478 */         this.cost = 5 + increase;
/* 479 */         if (this.greed % 3 == 0) {
/* 480 */           int lowChance = this.greed / 6 + 3;
/* 481 */           int midChance = 0;
/* 482 */           int highChance = 0;
/*     */           
/* 484 */           if (this.greed >= 12) midChance = 30; 
/* 485 */           if (this.greed >= 21 && this.greed < 33) highChance = this.greed - 20; 
/* 486 */           if (this.greed >= 33 && this.greed < 100) highChance = (this.greed - 50) / 5 + 30; 
/* 487 */           if (this.greed > 100) {
/* 488 */             lowChance = 20;
/* 489 */             highChance = 40;
/*     */           } 
/*     */           
/* 492 */           float result = this.rand.nextFloat() * (lowChance + midChance + highChance);
/* 493 */           int category = 0;
/* 494 */           if (result <= lowChance) { category = 1; }
/* 495 */           else if (result <= midChance) { category = 2; }
/* 496 */           else if (result <= highChance) { category = 3; }
/*     */ 
/*     */           
/* 499 */           writeTribute(category);
/*     */         } 
/* 501 */         depressObsidian();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 512 */     if (this.finishing) {
/*     */       int limit;
/* 514 */       if (this.finishDelay > 63) { limit = (190 - this.finishDelay) / 3; }
/* 515 */       else { limit = this.finishDelay / 2; }
/* 516 */        ParticlePacket particlePacket = new ParticlePacket(getPos().getX() + 0.5D, getPos().getY() + 1.2D, getPos().getZ() + 0.5D, 3, (int)Math.sqrt(limit) / 2 + 1);
/*     */       
/* 518 */       NetworkRegistry.TargetPoint target = new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), getPos().getX() + 0.5D, getPos().getY() + 1.2D, getPos().getZ() + 0.5D, 20.0D);
/* 519 */       CommonProxy.simpleNetworkWrapper.sendToAllAround((IMessage)particlePacket, target);
/*     */       
/* 521 */       this.finishDelay--;
/* 522 */       if (this.finishDelay == 0) this.finishing = false;
/*     */     
/*     */     }
/* 525 */     if (this.cooldown && isDaytime(this.world)) {
/* 526 */       this.cooldown = false;
/*     */     }
			}
			private void spawnAsh(Vec3d fPos) {
				getWorld().playSound(null, new BlockPos(fPos), SoundEvents.BLOCK_SAND_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);
				getWorld().spawnEntity((Entity)new EntityItem(getWorld(), fPos.x, fPos.y, fPos.z, new ItemStack(ModItems.UMBRAL_ASH)));
				
			}
/*     */ 
/*     */   
/*     */   private boolean isDaytime(World worldIn) {
/* 531 */     long time = worldIn.getWorldTime() % 24000L;
/*     */     
/* 533 */     return (time > 500L && time < 14000L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addTribute(int ID, int tier, String iconLocation) {
/* 542 */     addTribute(ID, -1, tier, iconLocation);
/*     */   }
/*     */   
/*     */   public static void initializeTributes() {
/* 546 */     lowTributes = (ArrayList)new ArrayList<>();
/* 547 */     midTributes = (ArrayList)new ArrayList<>();
/* 548 */     highTributes = (ArrayList)new ArrayList<>();
/* 549 */     tributeResources = new ArrayList<>();
/*     */   }
/*     */   
/*     */   public static void addTribute(int ID, int meta, int tier, String iconLocation) {
/* 553 */     switch (tier) { case 1:
/* 554 */         lowTributes.add(new int[] { ID, meta }); break;
/* 555 */       case 2: midTributes.add(new int[] { ID, meta }); break;
/* 556 */       case 3: highTributes.add(new int[] { ID, meta }); break; }
/*     */     
/* 558 */     tributeResources.add(iconLocation);
/* 559 */     StitchEventHandler.initTributeSymbols(new ResourceLocation(iconLocation));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeTribute(int tier) {
/* 565 */     if (tier == 0) throw new IndexOutOfBoundsException();
/*     */     
/* 567 */     switch (tier) {
/*     */       case 1:
/* 569 */         this.tributeID = this.rand.nextInt(lowTributes.size());
/* 570 */         this.tribute[0] = ((int[])lowTributes.get(this.tributeID))[0];
/* 571 */         this.tribute[1] = ((int[])lowTributes.get(this.tributeID))[1];
/*     */         break;
/*     */       case 2:
/* 574 */         this.tributeID = this.rand.nextInt(midTributes.size());
/* 575 */         this.tribute[0] = ((int[])midTributes.get(this.tributeID))[0];
/* 576 */         this.tribute[1] = ((int[])midTributes.get(this.tributeID))[1];
/* 577 */         this.tributeID += lowTributes.size();
/*     */         break;
/*     */       case 3:
/* 580 */         this.tributeID = this.rand.nextInt(highTributes.size());
/* 581 */         this.tribute[0] = ((int[])highTributes.get(this.tributeID))[0];
/* 582 */         this.tribute[1] = ((int[])highTributes.get(this.tributeID))[1];
/* 583 */         this.tributeID += lowTributes.size();
/* 584 */         this.tributeID += midTributes.size();
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 589 */     this.tributeIconLocation = tributeResources.get(this.tributeID);
/* 590 */     System.out.println(tributeResources.get(this.tributeID));
/*     */   }
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
/*     */   @Nullable
/*     */   public SPacketUpdateTileEntity getUpdatePacket() {
/* 689 */     NBTTagCompound nbtTagCompound = new NBTTagCompound();
/* 690 */     writeToNBT(nbtTagCompound);
/* 691 */     nbtTagCompound.setBoolean("tear1", this.crying);
/* 692 */     nbtTagCompound.setInteger("tear2", this.cost);
/* 693 */     nbtTagCompound.setInteger("tear3-0", this.tribute[0]);
/* 694 */     nbtTagCompound.setInteger("tear3-1", this.tribute[1]);
/* 695 */     nbtTagCompound.setString("tear4", this.tributeIconLocation);
/*     */ 
/*     */     
/* 698 */     int metadata = getBlockMetadata();
/* 699 */     return new SPacketUpdateTileEntity(this.pos, metadata, nbtTagCompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
/* 704 */     NBTTagCompound tag = pkt.getNbtCompound();
/* 705 */     readFromNBT(tag);
/* 706 */     System.out.println("data packet with " + this.crying);
/*     */     
/* 708 */     this.crying = tag.getBoolean("tear1");
/* 709 */     this.cost = tag.getInteger("tear2");
/* 710 */     this.tribute[0] = tag.getInteger("tear3-0");
/* 711 */     this.tribute[1] = tag.getInteger("tear3-1");
/* 712 */     this.tributeIconLocation = tag.getString("tear4");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/* 722 */     AxisAlignedBB infiniteExample = INFINITE_EXTENT_AABB;
/*     */ 
/*     */     
/* 725 */     AxisAlignedBB aabb = new AxisAlignedBB(getPos().add(-2, 1, -2), getPos().add(3, 2, 3));
/* 726 */     return aabb;
/*     */   }
/*     */ }


/* Location:              C:\Users\Kouma\Desktop\Minecraft Hell\minecraft modding\shadow mother\work folder\output\output3(1.12.2).zip!\com\KICE\ShadowMother\tileentity\CryingCircle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
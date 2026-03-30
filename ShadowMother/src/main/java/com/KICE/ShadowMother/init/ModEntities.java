package com.KICE.ShadowMother.init;

import com.KICE.ShadowMother.Main;
import com.KICE.ShadowMother.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
	public static void registerEntities() {
		registerEntity("Crying Obsidian Golem", com.KICE.ShadowMother.entity.CryingObsidianGolem.EntityCryingObsidianGolem.class, Reference.CRYING_OBSIDIAN_GOLEM, 50, 2103859, 931469);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
		//did not include mod.instance
	}
	
	private static void registerNonMobEntity() {
		
	}
}

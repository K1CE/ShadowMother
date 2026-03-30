package com.KICE.ShadowMother.util.handlers;

import com.KICE.ShadowMother.entity.CryingObsidianGolem.EntityCryingObsidianGolem;
import com.KICE.ShadowMother.entity.CryingObsidianGolem.RenderCryingObsidianGolem;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityCryingObsidianGolem.class, new IRenderFactory<EntityCryingObsidianGolem>(){
			@Override
			public Render<? super EntityCryingObsidianGolem> createRenderFor(RenderManager manager){
				return new RenderCryingObsidianGolem(manager);
			}
		});
	}
}

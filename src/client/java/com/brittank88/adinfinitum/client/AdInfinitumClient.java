package com.brittank88.adinfinitum.client;

import com.brittank88.adinfinitum.AdInfinitum;
import com.brittank88.adinfinitum.client.config.AdInfinitumConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.screen.PlayerScreenHandler;

public class AdInfinitumClient implements ClientModInitializer {

    private static float tickCount = 0;

    /** @return The total count of ticks. */
    public static float getTickCount() { return tickCount; }

    @Override public void onInitializeClient() {

        // Register config.
        AutoConfig.register(AdInfinitumConfig.class, GsonConfigSerializer::new);

        // Start tracking ticks.
        ClientTickEvents.START_CLIENT_TICK.register(mc -> tickCount++);

        // Register custom sprites.
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(this::registerSprites);

        // TODO: Migrate all client-side stuff like lang to the client (client entrypoint runs after common entrypoint anyways).
    }

    private void registerSprites(SpriteAtlasTexture atlasTexture, ClientSpriteRegistryCallback.Registry registry) {

        // Halo
        registry.register(AdInfinitum.id("item/render/halo"));
        registry.register(AdInfinitum.id("item/render/halo_noise"));

        // Singularity
        registry.register(AdInfinitum.id("item/singularity/base"));
        registry.register(AdInfinitum.id("item/singularity/core"));
    }
}

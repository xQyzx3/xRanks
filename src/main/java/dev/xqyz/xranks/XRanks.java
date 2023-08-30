package dev.xqyz.xranks;

import net.minecraft.network.chat.Component;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(XRanks.MODID)
public class XRanks {
    public static final String MODID = "xranks";

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void renderName(PlayerEvent.NameFormat e) {
            e.setDisplayname(Component.literal("VIP " + e.getUsername().getString()));
        }
    }
}

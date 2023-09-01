package dev.xqyz.xranks.Events;

import dev.xqyz.xranks.Capabilities.Rank;
import dev.xqyz.xranks.Capabilities.RankProvider;
import dev.xqyz.xranks.Capabilities.XColor;
import dev.xqyz.xranks.Capabilities.XColorProvider;
import dev.xqyz.xranks.Commands.ChangeRankCommand;
import dev.xqyz.xranks.XRanks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {
    @SubscribeEvent
    public static void renderName(PlayerEvent.NameFormat event) {
        event.getEntity().getCapability(RankProvider.RANK).ifPresent(rank -> {
            event.getEntity().getCapability(XColorProvider.COLOR).ifPresent(xColor -> {
                event.setDisplayname(Component.literal(ChatFormatting.getByName(xColor.getColor()) + rank.getRank() + " " +
                        ChatFormatting.YELLOW + event.getUsername().getString()));
            });
        });
    }

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        ChangeRankCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(Rank.class);
        event.register(XColor.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(RankProvider.RANK).isPresent()) {
                event.addCapability(new ResourceLocation(XRanks.MODID, "properties"), new RankProvider());
            }
            if (!event.getObject().getCapability(XColorProvider.COLOR).isPresent()) {
                event.addCapability(new ResourceLocation(XRanks.MODID, "color"), new XColorProvider());
            }
        }
    }
}

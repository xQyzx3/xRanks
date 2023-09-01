package dev.xqyz.xranks.Commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.xqyz.xranks.Capabilities.RankProvider;
import dev.xqyz.xranks.Capabilities.XColorProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class ChangeRankCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("xranks")
                .requires(source -> source.getServer() != null && source.getServer().isSingleplayer() || source.hasPermission(2))
                .then(Commands.literal("add")
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.argument("rank", StringArgumentType.string())
                                        .then(Commands.argument("color", StringArgumentType.string())
                                                .executes(command -> changeRank(command.getSource(),
                                                        EntityArgument.getPlayer(command, "player"), StringArgumentType.getString(command, "rank"), StringArgumentType.getString(command, "color"))))
                                )
                        )
                )
        );
    }

    private static int changeRank(CommandSourceStack command, ServerPlayer player, String rank, String color) {
        if(command.getEntity() instanceof Player) {
            command.sendSuccess(() ->
                    Component.literal("Changed Rank of Player: " + player.getDisplayName().getString() + " to " + rank), false);
            player.getCapability(RankProvider.RANK).ifPresent(playerRank -> {
                playerRank.changeRank(rank);
            });
            player.getCapability(XColorProvider.COLOR).ifPresent(rankColor -> {
                rankColor.changeColor(color);
            });
            player.refreshDisplayName();
        }
        else {
            command.sendFailure(Component.literal("Player not found"));
        }
        return 1;
    }
}

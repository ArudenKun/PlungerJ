package com.aruden.plungerv2.commands.admin;

import java.util.List;

import com.aruden.plungerv2.commands.CommandContext;
import com.aruden.plungerv2.commands.ICommand;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class Kick implements ICommand{

    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel(); 
        final Message message = ctx.getMessage();
        final Member member = ctx.getMember();
        final List<String> args = ctx.getArgs();

        if (args.size() < 2 || message.getMentionedMembers().isEmpty()) {
            channel.sendMessage("Missing Arguments").queue();
            return;
        }

        final Member target = message.getMentionedMembers().get(0);

        if (!member.canInteract(target) || !member.hasPermission(Permission.KICK_MEMBERS)) {
            channel.sendMessage("You do not have permission to use this command or kick " + target).queue();
            return;
        }

        final Member selfMember = ctx.getSelfMember();

        if (!selfMember.canInteract(target) || !selfMember.hasPermission(Permission.KICK_MEMBERS)) {
            channel.sendMessage("You do not have permission to use this command").queue();
            return;
        }

        final String reason = String.join(" ", args.subList(1, args.size()));

        ctx.getGuild()
            .kick(target, reason)
            .reason(reason)
            .queue(
                (__) -> channel.sendMessage(target + "is successfully kicked").queue(),
                (error) -> channel.sendMessageFormat("Could not kick %s", error.getMessage()).queue()
            );
    }

    @Override
    public String getName() {
        return "kick";
    }

    @Override
    public String getHelp() {
        return "Kicks a member of the server\n" +
                "Usage: `!kick [@user] [reason]`";
    }
    
}

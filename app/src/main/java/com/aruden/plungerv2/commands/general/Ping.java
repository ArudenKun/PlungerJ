package com.aruden.plungerv2.commands.general;

import com.aruden.plungerv2.commands.CommandContext;
import com.aruden.plungerv2.commands.ICommand;

import net.dv8tion.jda.api.JDA;

public class Ping implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        
        JDA jda = ctx.getJDA();
        jda.getRestPing().queue(
            (ping) -> ctx.getChannel()
            .sendMessageFormat("REST Ping: %sms\nWS ping: %sms", ping, jda.getGatewayPing()).queue()
        );
        
    }

    @Override
    public String getName() {
        return "ping";
    }
    
    @Override
    public String getHelp() {
        return "Shows the ping of the bot to the discord server";
    }
}

package com.aruden.plungerv2.commands.general;

import java.util.List;

import com.aruden.plungerv2.Config;
import com.aruden.plungerv2.commands.CommandContext;
import com.aruden.plungerv2.commands.CommandHandler;
import com.aruden.plungerv2.commands.ICommand;

import net.dv8tion.jda.api.entities.TextChannel;

public class Help implements ICommand {

    private final CommandHandler handler;

    public Help(CommandHandler _handler) {
        handler = _handler;
    }

    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();

        if (args.isEmpty()) {
            
            StringBuilder builder = new StringBuilder();

            builder.append("List of commands\n");

            handler.getCommands().stream().map(ICommand::getName).forEach(
                (it) -> builder.append("`").append(Config.get("prefix")).append(it).append("`\n")
            );

            channel.sendMessage(builder.toString()).queue();
            return; 
        }

        String search = args.get(0);
        ICommand command = handler.getCommand(search);

        if (command == null) {
            channel.sendMessage("Nothing found for " + search).queue();
            return; 
        }

        channel.sendMessage(command.getHelp()).queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Shows the usage for a that Command\n" +
                "Usage: `!Help [command]`";
    }
    
    @Override
    public List<String> getAliases() {
        return List.of("commands", "cmds");
    }
}

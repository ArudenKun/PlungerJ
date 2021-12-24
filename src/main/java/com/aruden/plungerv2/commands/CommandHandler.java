package com.aruden.plungerv2.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import com.aruden.plungerv2.Config;
import com.aruden.plungerv2.commands.admin.Kick;
import com.aruden.plungerv2.commands.general.Haste;
import com.aruden.plungerv2.commands.general.Help;
import com.aruden.plungerv2.commands.general.Paste;
import com.aruden.plungerv2.commands.general.Ping;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandHandler {
    
    private final List<ICommand> commands = new ArrayList<>();

    public CommandHandler() {
        addCommand(new Ping());
        addCommand(new Help(this));
        addCommand(new Paste());
        addCommand(new Haste());
        addCommand(new Kick());
    }

    private void addCommand(ICommand cmd) {

        boolean nameFound = commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

        if (nameFound) {
            throw new IllegalArgumentException("`This Command Already Exists`");
        }

        commands.add(cmd);
    }

    @Nullable
    public ICommand getCommand(String search) {

        String searchLower = search.toLowerCase();

        for (ICommand cmd : commands) {
            if (cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)) {
                return cmd;
            }
        }
        return null;
    }

    public List<ICommand> getCommands() {
        return commands;
    }

    public void handle(GuildMessageReceivedEvent event) {
        String[] split = event.getMessage().getContentRaw()
            .replaceFirst("(?i)" + Pattern.quote(Config.get("prefix")), "")
            .split("\\s+");

        String invoke = split[0].toLowerCase();
        ICommand cmd = getCommand(invoke);

        if (cmd != null) {
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);
            cmd.handle(ctx);
        }
    }
}

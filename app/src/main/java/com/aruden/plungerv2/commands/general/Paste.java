package com.aruden.plungerv2.commands.general;

import java.util.List;

import com.aruden.plungerv2.commands.CommandContext;
import com.aruden.plungerv2.commands.ICommand;

import org.menudocs.paste.PasteClient;
import org.menudocs.paste.PasteClientBuilder;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class Paste implements ICommand {

    private final PasteClient pasteClient = new PasteClientBuilder()
        .setUserAgent("Plunger")
        .setDefaultExpiry("1d")
        .build();

    @Override
    public void handle(CommandContext ctx) {
        final List<String> args= ctx.getArgs();
        final TextChannel channel = ctx.getChannel();

        if (args.size() < 2) {
            channel.sendMessage("Missing Arguments").queue();
            return;
        }

        final String language = args.get(0);
        final String raw = ctx.getMessage().getContentRaw();
        final int index = raw.indexOf(language) + language.length();
        final String body = raw.substring(index).trim();

        pasteClient.createPaste(language, body).async(
            (id) -> pasteClient.getPaste(id).async((paste) -> {
                EmbedBuilder builder = new EmbedBuilder()
                    .setTitle("Paste " + id, paste.getPasteUrl())
                    .setDescription("```")
                    .appendDescription(paste.getLanguage().getId())
                    .appendDescription("\n")
                    .appendDescription(paste.getBody())
                    .appendDescription("```");
                channel.sendMessageEmbeds(builder.build()).queue();
            })
        );
    }

    @Override
    public String getName() {
        return "paste";
    }

    @Override
    public String getHelp() {
        return "Creates a code paste on https://paste.menudocs.org/\n" +
                "Usage: `!paste [language] [text]`";
    }
    
}

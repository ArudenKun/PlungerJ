package com.aruden.plungerv2.commands.general;

import java.util.function.Consumer;

import com.aruden.plungerv2.commands.CommandContext;
import com.aruden.plungerv2.commands.ICommand;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.duncte123.botcommons.web.ContentType;
import me.duncte123.botcommons.web.WebParserUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.entities.TextChannel;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Haste implements ICommand {

    private static final String Haste_Server = "https://hastebin.de/"; 

    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        if (ctx.getArgs().isEmpty()) {
            channel.sendMessage("Missing Arguments");
            return;
        }

        final String invoke = getName();
        final String raw = ctx.getMessage().getContentRaw();
        final int index = raw.indexOf(invoke) + invoke.length();
        final String body = raw.substring(index).trim();

        createHaste(body, (text) -> channel.sendMessage(text).queue());
    }

    private void createHaste(String text, Consumer<String> callback) {
        Request request = new Request.Builder()
            .post(RequestBody.create(null, text.getBytes()))
            .addHeader("Content-Type", ContentType.TEXT_PLAIN.getType())
            .url(Haste_Server + "documents")
            .build();
        
        WebUtils.ins.prepareRaw(request, (r) -> WebParserUtils.toJSONObject(r, new ObjectMapper())).async(
            (json) -> {
                String key = json.get("key").asText();

                callback.accept(Haste_Server + key);
            },
            (e) -> callback.accept("Error: " + e.getMessage())
        );
    }

    @Override
    public String getName() {
        return "haste";
    }

    @Override
    public String getHelp() {
        return "Creates a post on https://hastebin.de/\n" +
                "Usage: `!haste [text]`";
    }
}
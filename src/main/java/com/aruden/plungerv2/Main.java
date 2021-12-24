package com.aruden.plungerv2;

import javax.security.auth.login.LoginException;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {

    private Main() throws LoginException {

        WebUtils.setUserAgent("My Name is Plunger");
        EmbedUtils.setEmbedBuilder(
            () -> new EmbedBuilder()
            .setAuthor("Aruden")
            .setColor(0xCF2005)
            .setFooter("Plunger, Plunging The Plungers!!!")
        );

        JDABuilder.createDefault(
            Config.get("TOKEN"),
            GatewayIntent.GUILD_MEMBERS,
            GatewayIntent.GUILD_MESSAGES
        )
        .addEventListeners(new Listener())
        .setActivity(Activity.watching("The Plungers"))
        .build();
    }
    public static void main(String[] args) throws LoginException {
        new Main();
    }
}
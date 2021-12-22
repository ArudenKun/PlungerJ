package com.aruden.plungerv2;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {

    private Main() throws LoginException {

        String token = "ODg5NTE1MDU1NjE0NDAyNjgx.YUiXcw.39YU7zFzF4pIRRjsiILSokfMKl8";

        JDABuilder.createDefault(token,
            GatewayIntent.GUILD_MEMBERS,
            GatewayIntent.GUILD_MESSAGES
        )
        .addEventListeners(new Listener())
        .setActivity(Activity.watching("Plunger"))
        .build();
    }
    public static void main(String[] args) throws LoginException {
        new Main();
    }
}

package com.aruden.plungerv2.modules;

import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class CustomEmbedBuilder {
    
    public void testEmbedBuilder(TextChannel channel) {

        EmbedUtils.setEmbedBuilder(
            () -> new EmbedBuilder()
            .setFooter("TestTing")
        ); 
    }
}

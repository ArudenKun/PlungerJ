package com.aruden.plungerv2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class); 

    @Override
    public void onReady(ReadyEvent event) {
        
        LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
    }
}

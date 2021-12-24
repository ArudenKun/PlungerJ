package com.aruden.plungerv2.commands.general;

import com.aruden.plungerv2.commands.CommandContext;
import com.aruden.plungerv2.commands.ICommand;

public class Meme implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        //TODO implement the code
    }

    @Override
    public String getName() {
        return "meme";
    }

    @Override
    public String getHelp() {
        return "Shows a random meme";
    }
    
}
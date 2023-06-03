package com.Seals.scrabble.factories.command;

import com.Seals.scrabble.model.hostSide.game.GameManager;
import com.Seals.scrabble.model.hostSide.game.Player;

import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import static com.Seals.scrabble.model.hModel.getGameManager;

class NewPlayerCommand implements ICommand {

    @Override
    public String execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid player ID");
            return "0";
        }
        //I want to use the gameHandler outputstream...
        ///so this block needs replacing
        // Create a PrintWriter object for the output stream
        PrintWriter outputStream;
        try {
            OutputStream os = new OutputStream() {
                @Override
                public void write(int b) {
                    // Do nothing
                }
            };
            outputStream = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "0";
        }
        Player p = getGameManager().addPlayer(outputStream);
        if (p != null) return String.valueOf(p.getId());
        return null;
    }
}

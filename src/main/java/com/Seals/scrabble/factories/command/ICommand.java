package com.Seals.scrabble.factories.command;

import java.net.Socket;

public interface ICommand {
    String execute(Socket socket, String... args);


}

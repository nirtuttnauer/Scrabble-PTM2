package com.Seals.scrabble.model.socketUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface ClientHandler {
	void handleClient(InputStream inFromClient, OutputStream outToClient);
	void close();

	void sendMessage(String message);

	void setClientSocket(Socket client);
}

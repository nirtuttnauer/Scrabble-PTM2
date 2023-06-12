package com.Seals.scrabble.model.socketUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface ClientHandler {
	void handleClient(InputStream inFromClient, OutputStream outToClient) throws IOException;
	void close();

	void sendMessage(String message) throws IOException;

	void setClientSocket(Socket client);
}

package com.Seals.scrabble.model;

import javafx.beans.property.StringProperty;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public interface iModel {
    void setNickname(String name);

    String getNickname();

    StringProperty nicknameProperty();

    void sendRequestToHost(String query);

    void disconnectFromHost();

    void connectToHost();

    String getServerAddress();

    void setServerAddress(String serverAddress);

    int getHostPort();

    void setHostPort(int hostPort);

    Socket getGuestSocket();

    void setGuestSocket(Socket guestSocket);

    PrintWriter getOut();

    void setOut(PrintWriter out);

    Scanner getIn();

    void setIn(Scanner in);
}

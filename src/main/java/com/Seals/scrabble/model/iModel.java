package com.Seals.scrabble.model;

import javafx.beans.property.StringProperty;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public interface iModel {
    void setNickname(String name);

    String getNickname();

    StringProperty nicknameProperty();

    void sendRequestToHost(String Cmd , String[] query);

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

    BufferedReader getIn();

    void setIn(BufferedReader in);

    int getID();
}

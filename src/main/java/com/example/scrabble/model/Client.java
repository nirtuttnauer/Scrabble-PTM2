package com.example.scrabble.model;

import com.example.scrabble.server.MyServer;
import com.example.scrabble.server.maintrains.MainTrain3;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import static com.example.scrabble.server.maintrains.MainTrain3.client1;

public class Client {
    private String name;
    private boolean isActive;
    private static int port;

    public void setScore(int score) {
        this.score = score;
    }

    private int score;
    private iModel model;

    public int getScore() {
        return score;
    }

    public Client(String name, iModel model) {
        this.model=model;
        this.score=0;
        this.name = name;
        isActive=false;
        // this try/catch get the PORT from the client!
        try {
            Socket clientSocket = new Socket("localhost",0);
            port= clientSocket.getLocalPort();
            System.out.println("Client name" + this.name + "using port" +this.port);
            isActive=true; // Active to play!
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getPort() {
        return port;
    }

    public static boolean connectToServer() {
        boolean ok=true;
        Random r=new Random();
        MyServer s=new MyServer(port, new MainTrain3.ClientHandler1());
        int c = Thread.activeCount();
        s.start(); // runs in the background
        try {
            client1(port);
        }catch(Exception e) {
            System.out.println("some exception was thrown while testing your com.example.scrabble.server, cannot continue the test (-100)");
            ok=false;
        }
        s.close();

        try {Thread.sleep(2000);} catch (InterruptedException e) {}

        if (Thread.activeCount()!=c) {
            System.out.println("you have a thread open after calling close method (-100)");
            ok=false;
        }
        return ok;
    }

    public static void setModel(){
        // we need to check how to get the client mode and then to set the model
    }

}

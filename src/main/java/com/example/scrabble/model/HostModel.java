package com.example.scrabble.model;

import com.example.scrabble.server.MyServer;
import com.example.scrabble.server.managers.BookScrabbleHandler;
import com.example.scrabble.server.managers.DictionaryManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class HostModel extends Model {
    MyServer server;

    public static void runClient(int port, String query, boolean result) {
        try {
            Socket server = new Socket("localhost", port);
            PrintWriter out = new PrintWriter(server.getOutputStream());
            Scanner in = new Scanner(server.getInputStream());
            out.println(query);
            out.flush();
            String res = in.next();
            if ((result && !res.equals("true")) || (!result && !res.equals("false")))
                System.out.println("problem getting the right answer from the com.example.scrabble.server (-10)");
            in.close();
            out.close();
            server.close();
        } catch (IOException e) {
            System.out.println("your code ran into an IOException (-10)");
        }
    }

    public void startServer() {
        Random r = new Random();
        int port = 6000 + r.nextInt(1000);
        MyServer server = new MyServer(port, new BookScrabbleHandler());
        server.start();
        System.out.println("Server started on port " + port);
        closeServer();
        System.out.println("Server closed on port " + port);
    }

    public void closeServer() {
        if (server != null)
            server.close();
    }

}

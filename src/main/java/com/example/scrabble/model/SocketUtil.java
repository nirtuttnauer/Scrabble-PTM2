package com.example.scrabble.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketUtil {
    static void finallyClose(Socket client, PrintWriter out, Scanner in) {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

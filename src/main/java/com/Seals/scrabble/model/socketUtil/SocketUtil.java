package com.Seals.scrabble.model.socketUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketUtil {
    public static void finallyClose(Socket client, PrintWriter out, Scanner in) {
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

    public static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

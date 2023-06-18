package com.Seals.scrabble;

public class Settings {
    //resulution settings:
    private static int WIDTH = 820;
    private static int HEIGHT = 820;
    //server settings:
    private static int DMServerPort = 5555;

    private static int HostServerPort = 6050;
    private static String serverAdress = "localhost";
    private static String Css = "/com/Seals/scrabble/application.css";


    //methods:
    public static int getHostServerPort() {
        return HostServerPort;
    }
    public static int getWIDTH() {
        return WIDTH;
    }

    public static void setWIDTH(int WIDTH) {
        Settings.WIDTH = WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static void setHEIGHT(int HEIGHT) {
        Settings.HEIGHT = HEIGHT;
    }

    public static int getDMServerPort() {
        return DMServerPort;
    }

    public static String getServerAddress() {
        return serverAdress;
    }

    public static String getCssPath() {
        return Css;
    }
}
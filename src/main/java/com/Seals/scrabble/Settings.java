package com.Seals.scrabble;

public class Settings {
        private static int WIDTH = 720;
        private static int HEIGHT = 720;

        private static int DMServerPort = 5555;

        private static int HostServerPort = 6000;
    private static String serverAdress= "local host";

    public static int getHostServerPort() {
        return HostServerPort;
    }

//        private static int GuestPort = 5555;

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


//    public static void setDefaultPort(int defaultPort) {
//        AppSettings.defaultPort = defaultPort;
//    }
}
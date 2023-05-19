package com.Seals.scrabble;

public class Settings {
        private static int WIDTH = 720;
        private static int HEIGHT = 720;

        private static final int defaultPort = 5555;

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

    public static int getDefaultPort() {
        return defaultPort;
    }


//    public static void setDefaultPort(int defaultPort) {
//        AppSettings.defaultPort = defaultPort;
//    }
}
package com.Seals.scrabble;

public class Settings {
        private static int WIDTH = 720;
        private static int HEIGHT = 720;

        private static final int defaultDMServerPort = 5555;
        private static final int defaultHostPort = 6000;

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

    public static int getDefaultDMServerPort() {
        return defaultDMServerPort;
    }


//    public static void setDefaultPort(int defaultPort) {
//        AppSettings.defaultPort = defaultPort;
//    }
}
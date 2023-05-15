package com.example.scrabble;

public class AppSettings {
        public static int WIDTH = 720;
        public static int HEIGHT = 720;

        public static int getWIDTH() {
            return WIDTH;
        }

        public static void setWIDTH(int WIDTH) {
            AppSettings.WIDTH = WIDTH;
        }

        public static int getHEIGHT() {
            return HEIGHT;
        }

        public static void setHEIGHT(int HEIGHT) {
            AppSettings.HEIGHT = HEIGHT;
        }
    }
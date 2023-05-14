package com.example.scrabble.model;

public class StartModel implements iModel{

        // Define properties and methods representing the application's data and behavior
        // For example:
        private String welcomeMessage;

        public StartModel() {
            welcomeMessage = "";
        }

        public void setWelcomeMessage(String message) {
            welcomeMessage = message;
        }

        public String getWelcomeMessage() {
            return welcomeMessage;
        }
}

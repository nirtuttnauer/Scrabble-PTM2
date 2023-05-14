package model;

public class HostModel implements iModel{

    // Define properties and methods representing the application's data and behavior
    // For example:
    private String welcomeMessage;

    public HostModel() {
        welcomeMessage = "";
    }

    public void setWelcomeMessage(String message) {
        welcomeMessage = message;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}

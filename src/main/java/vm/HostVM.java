package vm;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.HostModel;

public class HostVM implements iVM{
    private final HostModel model;
    private final StringProperty welcomeMessage;

    public HostVM(HostModel model) {
        this.model = model;
        welcomeMessage = new SimpleStringProperty();
    }

    public StringProperty welcomeMessageProperty() {
        return welcomeMessage;
    }

    public void onHelloButtonClick() {
        model.setWelcomeMessage("Lama Omer Kaha Kaved?!");
        welcomeMessage.set(model.getWelcomeMessage());
    }

    public void onJoinButtonClick() {
        model.setWelcomeMessage("Lama");
        welcomeMessage.set(model.getWelcomeMessage());
    }
}

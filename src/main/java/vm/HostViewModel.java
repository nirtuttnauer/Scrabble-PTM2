package vm;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.HostModel;
import model.iModel;

public class HostViewModel implements iVM{
    private final HostModel model;
    private final StringProperty welcomeMessage;

    public HostViewModel(HostModel model) {
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

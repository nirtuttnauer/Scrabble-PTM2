package com.example.scrabble.vm;

import com.example.scrabble.model.iModel;
import javafx.beans.value.ObservableValue;

public interface iVM {
    ObservableValue<String> welcomeMessageProperty();

    void onHelloButtonClick();

    void onJoinButtonClick();

    void onSettingButtonClick();
}

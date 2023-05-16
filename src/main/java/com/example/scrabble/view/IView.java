package com.example.scrabble.view;

import java.io.IOException;
import java.util.Observer;

public interface IView extends Observer {

    public void onMenuButtonClick() throws IOException;

    public void onExitButtonClick() throws IOException;

}

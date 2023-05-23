package com.Seals.scrabble.facade;

import com.Seals.scrabble.viewmodel.ViewModel;

import java.util.Observer;

public interface iModelFacade extends Observer {
    void setNickname(String nickname);

    String getNickname();

    void addObserver(ViewModel viewModel);
}

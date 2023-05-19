package com.Seals.scrabble.factories;

import com.Seals.scrabble.viewmodel.ViewModel;

public class ViewModelFactory {
    public static ViewModel SingleViewModel;

    public static ViewModel getVM() {
        if (SingleViewModel == null) {
            SingleViewModel = new ViewModel();
        }
        return SingleViewModel;
    }

}
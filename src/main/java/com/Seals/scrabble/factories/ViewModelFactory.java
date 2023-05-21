package com.Seals.scrabble.factories;

import com.Seals.scrabble.model.facades.ModelFacade;
import com.Seals.scrabble.viewmodel.ViewModel;

public class ViewModelFactory {
    private static ViewModel SingleViewModel;

    public static ViewModel getVM() {
        if (SingleViewModel == null) {
            SingleViewModel = new ViewModel(new ModelFacade());
        }
        return SingleViewModel;
    }

}
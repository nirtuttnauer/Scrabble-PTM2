package com.example.scrabble.view;

import com.example.scrabble.App;
import com.example.scrabble.model.Model;
import com.example.scrabble.vm.generics.IVM;
import com.example.scrabble.vm.generics.VM;

import java.io.IOException;

public abstract class View implements IView {
    protected static VM vm = new VM();

    public static void setVm(VM vm) {
        System.out.println(vm.toString());
        View.vm = vm;
    }

    public void onMenuButtonClick() throws IOException {
        setVm(new VM());
        App.setScene("MenuView");
    }

    public void onExitButtonClick() throws IOException {
        System.exit(0);
    }


}

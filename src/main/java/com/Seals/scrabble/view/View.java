package com.Seals.scrabble.view;

import com.Seals.scrabble.Main;
import com.Seals.scrabble.vm.generics.VM;

import java.io.IOException;

public abstract class View implements IView {
    protected static VM vm = new VM();

    public static void setVm(VM vm) {
        System.out.println(vm.toString());
        View.vm = vm;
    }

    public void onMenuButtonClick() throws IOException {
        setVm(new VM());
        Main.setScene("MenuView");
    }

    public void onExitButtonClick() throws IOException {
        System.exit(0);
    }


}

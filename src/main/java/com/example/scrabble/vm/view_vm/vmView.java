package com.example.scrabble.vm.view_vm;

import com.example.scrabble.App;
import com.example.scrabble.vm.generics.VM;

import java.io.IOException;

public abstract class vmView implements vmIView {
    private static VM vm;

    public static void setVm(VM vm) {
        System.out.println(vm.toString());
        vmView.vm = vm;
    }

    public void onMenuButtonClick() throws IOException {
        App.setScene("menu-view.fxml");
    }

    public void onExitButtonClick() throws IOException {
        System.exit(0);
    }
}

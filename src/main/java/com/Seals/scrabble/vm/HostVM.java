package com.Seals.scrabble.vm;

import com.Seals.scrabble.model.HostModel;

public class HostVM extends VM {
    public HostVM() {
        super();
        super.toggleModel();
    }

    public HostVM(VM vm) {
        toggleModel();
        this.vm_nickname = vm.vm_nickname;
    }

    @Override
    public void setNickname(String nickname) {
        model.setNickname(nickname);
    }

    public void startServer() {
        ((HostModel) model).startServer();
    }

    public void closeServer() {
        ((HostModel) model).stopServer();
    }

    public void testDMServerConnection() {
        if (model instanceof HostModel) {
            ((HostModel) model).testDMServerConnection();
        } else {
            toggleModel();
            ((HostModel) model).testDMServerConnection();
        }
    }
}

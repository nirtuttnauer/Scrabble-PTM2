package com.Seals.scrabble.facade;

import com.Seals.scrabble.model.Model;
import com.Seals.scrabble.model.hModel;
import com.Seals.scrabble.model.socketUtil.MyServer;
import com.Seals.scrabble.viewmodel.UnifiedViewModel;
import com.Seals.scrabble.viewmodel.ViewModel;

public class MainFacade {

    public static void main(String[] args){
    UnifiedViewModel vm= new UnifiedViewModel(new UnifiedModelFacade(new Model()));

    // create server
        vm.connectToServerFromFacade();
        System.out.println("line 14 works!");
        // join server
        vm.joinToserverFromFacade();
        System.out.println("line 18 works!");
    }
}

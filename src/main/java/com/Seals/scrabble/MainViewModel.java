package com.Seals.scrabble;

import com.Seals.scrabble.viewmodel.ViewModel;

public class MainViewModel {
     public static void main(String[] args){
     ViewModel vm= new ViewModel();

     // create server
         vm.hostGame();
         System.out.println("line 14 works!");
         // join server
         vm.joinGame();
         System.out.println("line 18 works!");
     }
}

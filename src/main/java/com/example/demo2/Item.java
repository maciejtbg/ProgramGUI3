package com.example.demo2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class Item {


    public static Item instance;

    public Item(){
        instance = this;
    }
    public static Item getInstance(){
        return instance;
    }

//    HelloController helloController = new HelloController();
    static boolean sprawdzaczCzyZmieniono;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public CheckBox getZaznacz() {
        return zaznacz;
    }

    public void setZaznacz(CheckBox zaznacz) {
        this.zaznacz = zaznacz;
    }

    public int getCenaSell() {
        return cenaSell;
    }

    public void setCenaSell(int cenaSell) {
        this.cenaSell = cenaSell;
    }

    public int getCenaBuy() {
        return cenaBuy;
    }

    public void setCenaBuy(int cenaBuy) {
        this.cenaBuy = cenaBuy;
    }

    public String nazwa;
    public CheckBox zaznacz;
    public int cenaSell;
    public int cenaBuy;

    Item(String nazwa){
        this.nazwa = nazwa;
        this.zaznacz = new CheckBox();
//        this.zaznacz.selectedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
//                System.out.println("Wykonano zmianę!");
//                helloController.onTransferButtonClick();
//            }
//           this.zaznacz.selectedProperty().addListener(new ChangeListener<Boolean>() {
//                public void changed(ObservableValue ov,Boolean old_val, Boolean new_val) {
//                    System.out.println(new_val);
////                    helloController.onTransferButtonClick();
//                    sprawdzaczCzyZmieniono=true;
//                    System.out.println(sprawdzaczCzyZmieniono);
//
//                }
//        });
    }

}

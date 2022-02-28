package com.example.demo2;

import java.util.stream.IntStream;

public class Punkt {
    int wspX;
    int wspY;

    public Punkt() {
        this.wspX = 0;
        this.wspY = 0;
    }


    public int getWspX() {
        return wspX;
    }

    public int getWspY() {
        return wspY;
    }

    public void setWspX(int wspX) {
        this.wspX = wspX;
    }

    public void setWspY(int wspY) {
        this.wspY = wspY;
    }

    public Punkt(int wspX, int wspY) {
        this.wspX = wspX;
        this.wspY = wspY;
    }
    public Punkt wyciagnijSrodekZDwochPunktow (Punkt punkt1, Punkt punkt2){
        int wspXsr= (int) IntStream.of(punkt1.getWspX(),punkt2.getWspX()).average().getAsDouble();
        int wspYsr= (int) IntStream.of(punkt1.getWspY(),punkt2.getWspY()).average().getAsDouble();
        return new Punkt(wspXsr,wspYsr);
    }
}

package com.example.demo2;

import java.io.IOException;

public class Uruchamiacz {
    public static void uruchomProgram(String sciezka) {
        try {
            Runtime runTime = Runtime.getRuntime();
            Process process2 = runTime.exec(sciezka);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("Closing Tibia");
            //process2.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zamknijProgram() {
        try {

            Runtime.getRuntime().exec("taskkill /F /IM client.exe");

//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            //System.out.println("Closing Tibia");
            //process2.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

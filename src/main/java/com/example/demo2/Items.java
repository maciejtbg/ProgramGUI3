package com.example.demo2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Items {
    public ArrayList<String> items = new ArrayList<String>();
    FileReader plik = new FileReader("D://Items.txt");
    BufferedReader in = new BufferedReader(plik);

    public Items() throws IOException {
        String linijka;
        while ((linijka = in.readLine()) != null) {
            items.add(linijka);
        }
        in.close();

    }

}

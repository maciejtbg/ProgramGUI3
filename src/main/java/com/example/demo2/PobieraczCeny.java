package com.example.demo2;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PobieraczCeny {
    public static int zrobZdjecieCeny(boolean czySprzedaz, String nazwaItemu) throws Exception {


        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date nowDate = new Date();
        String nazwaPlikuDoRozpoznania = sdf1.format(nowDate);
        int poczatekY, koniecY;
        System.out.print("Dla " + nazwaItemu + " cena ");
        String nazwaTransakcji;

        if (czySprzedaz == true) {
            nazwaTransakcji = "sprzedazy";
            poczatekY = 191;
            koniecY = 206;
        } else {
            nazwaTransakcji = "kupna";
            poczatekY = 372;
            koniecY = 387;
        }
        System.out.print(nazwaTransakcji + " to: ");
        ScreenShotTaker screenShotTaker = new ScreenShotTaker(685, poczatekY, 767, koniecY);
        BufferedImage zdjecieDoRozpoznania = screenShotTaker.zrobZdjecieDoPamieci();

        WritableImage wr = null;
        if (zdjecieDoRozpoznania != null) {
            wr = new WritableImage(zdjecieDoRozpoznania.getWidth(), zdjecieDoRozpoznania.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < zdjecieDoRozpoznania.getWidth(); x++) {
                for (int y = 0; y < zdjecieDoRozpoznania.getHeight(); y++) {
                    pw.setArgb(x, y, zdjecieDoRozpoznania.getRGB(x, y));
                }
            }
        }

        if (czySprzedaz) {
                HelloController.getInstance().podgladImageSell.setImage(wr);
        } else {
            HelloController.getInstance().podgladImageBuy.setImage(wr);
        }
        Zwiekszacz zwiekszacz = new Zwiekszacz();
        BufferedImage zdjecieDoRozpoznaniaPowiekszone = zwiekszacz.zwiekszZdjecieWPamieci(zdjecieDoRozpoznania);
        RecognizeText recognizeText = new RecognizeText();
        String cenaString = recognizeText.RozpoznanieWPamieci(zdjecieDoRozpoznania);
        String cenaBezKropki = cenaString.replace(".", "");
        int cenaInt = 0;
        if (cenaBezKropki != "") {
            char tablica[] = new char[cenaBezKropki.length()];
            String cenaCzysta = "";

            for (int i = 0; i < tablica.length; i++) {
                tablica[i] = cenaBezKropki.charAt(i);
                if (tablica[i] == '0' ||
                        tablica[i] == '1' ||
                        tablica[i] == '2' ||
                        tablica[i] == '3' ||
                        tablica[i] == '4' ||
                        tablica[i] == '5' ||
                        tablica[i] == '6' ||
                        tablica[i] == '7' ||
                        tablica[i] == '8' ||
                        tablica[i] == '9') {
                    cenaCzysta = cenaCzysta + tablica[i];
                }
                cenaInt = Integer.parseInt(cenaCzysta);
            }
        } else cenaInt = Integer.valueOf(cenaInt);


        System.out.println(cenaInt + ".");

        return cenaInt;
    }

    public static void zapiszDaneDoPlikuTXT(String nazwaItemu, Integer cenaSell, Integer cenaBuy, String lokalizacja) throws IOException {
        File plik = new File(lokalizacja+"/" + nazwaItemu + ".txt");
        if (plik.exists() == false) {
            plik.createNewFile();
        }
        Date nowDate = new Date();
        FileWriter zapisywacz = new FileWriter(plik, true);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dataDoZapisuwPliku = sdf2.format(nowDate);
        BufferedWriter out = new BufferedWriter(zapisywacz);
        out.write(dataDoZapisuwPliku + "," + cenaSell + "," + cenaBuy + ",DATA/SELL/BUY");
        out.newLine();
        out.close();
    }

    public static void zapiszDaneDoPlikuCSV(String nazwaItemu, Integer cenaSell, Integer cenaBuy,String lokalizacja) throws IOException {
        File plik = new File(lokalizacja+"/" + nazwaItemu + ".csv");
        if (plik.exists() == false) {
            plik.createNewFile();
        }
        Date nowDate = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dataDoZapisuwPliku = sdf2.format(nowDate);
        String out = dataDoZapisuwPliku + "," + cenaSell + "," + cenaBuy + "\n";
        FileWriter pw = new FileWriter(plik, true);
        pw.write(out);
        pw.close();
    }

    public static int sprawdzMaxCenezCSV(String nazwaItemu) throws FileNotFoundException {
//        if(!new File("/Thyria/").exists()){
//            Logger.getAnonymousLogger().warning("Katalog docelowy nie istnieje! Tworzę nowy katalog!");
//            new File("/Thyria/").mkdirs();
//        }
        File plik = new File("/Thyria/" + nazwaItemu + ".csv");


        Scanner fr = new Scanner(plik);
        String ostatniaLinia = null;
        while (fr.hasNextLine()) {
            ostatniaLinia = fr.nextLine();
        }
        int numerPrzecinka = ostatniaLinia.indexOf(',');
        String prawieOstatniString = ostatniaLinia.substring(numerPrzecinka + 2);
        int numerOstatniegoPrzecinka = prawieOstatniString.indexOf(',');
        String ostatniaLiczba = ostatniaLinia.substring(ostatniaLinia.length() - numerOstatniegoPrzecinka - 1);
        int ostatniaLiczbaInt = Integer.parseInt(ostatniaLiczba);

        return ostatniaLiczbaInt;
    }
}

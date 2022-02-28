package com.example.demo2;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import org.opencv.core.*;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;


import static com.example.demo2.Wyszukiwarka.*;


public class Zbieracz {
    static Przyciskacz przyciskacz = new Przyciskacz();
    public static void ustawPostep(double postep){
        HelloController.getInstance().setProgressBar(postep);
    }
    public static void otworzMarket() throws IOException, AWTException, InterruptedException {
        Thread.sleep(500);
        boolean czyServerLogJestWylaczonyRed=Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(SERVERLOGRED);
        boolean czyServerLogJestWylaczonyGrey=Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(SERVERLOGGREY);
        if (czyServerLogJestWylaczonyRed || czyServerLogJestWylaczonyGrey){
            System.out.println("ServerLog nie jest włączony. Włączam.");
            przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(SERVERLOGRED));
        }Thread.sleep(500);
        przyciskacz.kliknijPrawym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(MARKET));

    }
    public static void otworzDepo() throws InterruptedException, IOException, AWTException {
        Punkt punktPoczatkowy = Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(BELKA);
        Punkt punktKoncowy = new Punkt(punktPoczatkowy.getWspX(), 585);
        przyciskacz.przeciagnijLewym(punktPoczatkowy, punktKoncowy);
        Thread.sleep(2000);
        przyciskacz.skierujPostacwDol();
        Thread.sleep(500);
        przyciskacz.kliknijPrawym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(DEPO));
    }
    public static void kliknijOk() throws IOException, AWTException {
        przyciskacz.kliknijPrawym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(OK));
    }
    public static void wybierzPostac() throws IOException, AWTException, InterruptedException {
        przyciskacz.enter();
        System.out.println("Oczekuje na zalogowanie postaci.");
        boolean czyZalogowaloPostac=false;
        while (!czyZalogowaloPostac) {
            Thread.sleep(500);
            czyZalogowaloPostac = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(MAPMANIPULATOR);
            System.out.print(".");
        }
        System.out.println("\nPostac zalogowana.");
        System.out.println("Oczekuje na znikniecie napisow powitalnych. 20 seknund.");
        Thread.sleep(20000);
    }
    public static void wlaczTibie() throws AWTException, IOException, InterruptedException {
        boolean czyUruchomiona = false;
        boolean czyWymagaUpdate;
        boolean czyUpdateWykonany = false;
        System.out.print("Uruchamiam Tibie.");
        Uruchamiacz.uruchomProgram("C://Users/PC/AppData/Local/Tibia/packages/Tibia/bin/client_launcher.exe");
        while (!czyUruchomiona) {
            Thread.sleep(500);
            czyWymagaUpdate = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(UPDATE);
            if (czyWymagaUpdate) {
                System.out.println("Tibia wymaga aktualizacji.");
                Thread.sleep(500);
                System.out.print("Rozpoczynam aktualizację.");
                przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(UPDATE));
                while (!czyUpdateWykonany) {
                    System.out.print(".");
                    czyUpdateWykonany = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(UPDATED);
                }
                przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(UPDATED));
            }
            czyUruchomiona = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(LOGIN);
            System.out.print(".");
        }
        System.out.println("\nTibia uruchomiona.");
        Thread.sleep(1000);

        }
        public static void maximizuj() throws InterruptedException {
            Maksymalizator maksymalizator = new Maksymalizator();
            maksymalizator.Maksymalizuj("Tibia - Bro wser");
            maksymalizator.Maksymalizuj("Tibia");

            przyciskacz.maksymalizujAktualneOkno();
            Thread.sleep(1000);
        }

    public static void zaloguj() throws Exception {
        Thread.sleep(1000);
        String haslo = HelloController.getInstance().hasloTextField.getText();
        przyciskacz.przyciskajSlowo(haslo);
        przyciskacz.enter();
//        //tu trzeba sprawdzić czy się zalogowało
        System.out.print("Oczekuje na zalogowanie konta.");
        boolean czyZalogowalo = false;
        while (!czyZalogowalo) {
            Thread.sleep(500);
            czyZalogowalo = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(SELECTCHARACTER);
            System.out.print(".");
        }
        System.out.println("\nKonto zalogowane.");

    }

    public static void start() throws Exception {



        ustawPostep(0.0);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Zaczynam...");
        Thread.sleep(2000);
        maximizuj();
        boolean czyOtworzonyMarket = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(ERASE);
        boolean czyOtworzoneDepo = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(MARKET);
        boolean czyZalogowaloPostac = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(MAPMANIPULATOR);
        boolean czyTibiaWlaczona = CzyJestUruchomiony.isRunning("client_launcher.exe");
        boolean czyDisconnected = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(DISCONNECTED);
        boolean czyWymagaAktualizacji = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(UPDATE);



        if (czyOtworzonyMarket){
            //koniec
        } else {
            if (czyOtworzoneDepo){
                otworzMarket();
            } else {
                if(czyZalogowaloPostac){
                    otworzDepo();
                    otworzMarket();
                } else {
                    if(czyTibiaWlaczona){
                        maximizuj();
                        if (czyDisconnected){
                            kliknijOk();
                            wybierzPostac();
                            otworzDepo();
                            otworzMarket();
                        } else {

                            zaloguj();
                            wybierzPostac();
                            otworzDepo();
                            otworzMarket();
                        }
                    } else {
                        wlaczTibie();
                        maximizuj();
                        zaloguj();
                        wybierzPostac();
                        otworzDepo();
                        otworzMarket();
                    }
                }
            }
        }


//        if (!czyZalogowaloPostac) {
//            boolean result = CzyJestUruchomiony.isRunning("client_launcher.exe");
//            if (result) {
//                System.out.println("Tibia jest uruchomiona.");
//            } else {
//
//                System.out.println("Uruchamiam Tibie...");
//            }
////            System.out.println("Zaczynam sprawdzac czy Tibia sie wlaczyla.");
////
////            System.out.println("Sprawdzam, czy Tibia wymaga UPDATE'u.");
////
////            System.out.print("Oczekuje na uruchomienie Tibii.");
//            boolean czyUruchomiona = false;
//            boolean czyWymagaUpdate;
//            boolean czyUpdateWykonany = false;
//            while (!czyUruchomiona) {
//                Thread.sleep(500);
//                czyWymagaUpdate = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(UPDATE);
//                if (czyWymagaUpdate) {
//                    System.out.println("Tibia wymaga aktualizacji.");
//                    Thread.sleep(500);
//                    System.out.print("Rozpoczynam aktualizację.");
//                    przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(UPDATE));
//                    while (!czyUpdateWykonany) {
//                        System.out.print(".");
//                        czyUpdateWykonany = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(UPDATED);
//                    }
//                    przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(UPDATED));
//                }
//                czyUruchomiona = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(LOGIN);
//                System.out.print(".");
//            }
//            System.out.println("\nTibia uruchomiona.");
//
//
//            Maksymalizator maksymalizator = new Maksymalizator();
//            maksymalizator.Maksymalizuj("Tibia");
//
//            przyciskacz.maksymalizujAktualneOkno();
//            Thread.sleep(1000);
//            String haslo = HelloController.getInstance().hasloTextField.getText();
//            przyciskacz.przyciskajSlowo(haslo);
//            przyciskacz.enter();
////        //tu trzeba sprawdzić czy się zalogowało
//            System.out.print("Oczekuje na zalogowanie konta.");
//            boolean czyZalogowalo = false;
//            while (!czyZalogowalo) {
//                Thread.sleep(500);
//                czyZalogowalo = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(SELECTCHARACTER);
//                System.out.print(".");
//            }
//            System.out.println("\nKonto zalogowane.");
//
//
//
//
//
//
//            przyciskacz.enter();
//            System.out.println("Oczekuje na zalogowanie postaci.");
//            while (!czyZalogowaloPostac) {
//                Thread.sleep(500);
//                czyZalogowaloPostac = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(MAPMANIPULATOR);
//                System.out.print(".");
//            }
//            System.out.println("\nPostac zalogowana.");
//            System.out.println("Oczekuje na znikniecie napisow powitalnych. 20 seknund.");
//            Thread.sleep(20000);
//        } else System.out.println("Postac jest zalogowana.");
//        Thread.sleep(1000);
//        boolean czyServerLogJestWlaczony=Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(SERVERLOGWHITE);
//        if (!czyServerLogJestWlaczony){
//            System.out.println("ServerLog nie jest włączony. Włączam.");
//            przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(SERVERLOGRED));
//        }
//        System.out.println("SerwerLog jest włączony");
//        System.out.println("Rozszerzam widok.");
//        Punkt punktPoczatkowy = Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(BELKA);
//        Punkt punktKoncowy = new Punkt(punktPoczatkowy.getWspX(), 585);
//        przyciskacz.przeciagnijLewym(punktPoczatkowy, punktKoncowy);
//
//
//        System.out.println("Przystepuje do otwierania Depo.");
//        Thread.sleep(2000);
//        przyciskacz.skierujPostacwDol();
//        Thread.sleep(500);
//        przyciskacz.kliknijPrawym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(DEPO));
//        System.out.println("Wlaczylem depo. Przystepuje do otwierania marketu.");
////        //przyciskacz.kliknijPrawym(551,317);
//        Thread.sleep(2000);
//        przyciskacz.kliknijPrawym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(MARKET));
        ArrayList<Rekord> Rekordy = new ArrayList<>();
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Thread.sleep(2000);
        boolean czyPojawiloSieOstrzezenie = false;
        String lokalizacjaTXT = HelloController.getInstance().txtFileTextField.getText();
        String lokalizacjaCSV = HelloController.getInstance().csvFileTextField.getText();
        for (int iterator = 0; iterator < HelloController.getInstance().daneDoPrzygotowanejTablicy.size(); iterator++) {
            przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(ERASE));
            Thread.sleep(100);
            przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(SEARCHBAR));
            Thread.sleep(1);
            String nazwaItemu = HelloController.getInstance().daneDoPrzygotowanejTablicy.get(iterator).getNazwa();
            String slowoDoSzukania = nazwaItemu.toLowerCase(Locale.ROOT);
            przyciskacz.przyciskajSlowo(slowoDoSzukania);
            Thread.sleep(1);
            przyciskacz.kliknijLewym(400, 300);
            Thread.sleep(500);
            int cenaSprzedazy = 0;
            cenaSprzedazy = PobieraczCeny.zrobZdjecieCeny(true, nazwaItemu);
            int cenaKupna = 0;
            cenaKupna = PobieraczCeny.zrobZdjecieCeny(false, nazwaItemu);
            Rekord rekord = new Rekord("Thyria",nazwaItemu,sdf3,cenaSprzedazy,cenaKupna);
            Rekordy.add(rekord);
            if(HelloController.getInstance().txtFileCheckBox.isSelected()){
                PobieraczCeny.zapiszDaneDoPlikuTXT(nazwaItemu, cenaSprzedazy, cenaKupna,lokalizacjaTXT);
                System.out.println("Zapisano do pliku .TXT: "+lokalizacjaTXT+"\\"+nazwaItemu+".txt");
            }
            if(HelloController.getInstance().csvFileCheckBox.isSelected()){
                PobieraczCeny.zapiszDaneDoPlikuCSV(nazwaItemu, cenaSprzedazy, cenaKupna,lokalizacjaCSV);
                System.out.println("Zapisano do pliku .CSV: "+lokalizacjaCSV+"\\"+nazwaItemu+".csv");
            }
            HelloController.getInstance().daneDoPrzygotowanejTablicy.get(iterator).setCenaSell(cenaSprzedazy);
            HelloController.getInstance().daneDoPrzygotowanejTablicy.get(iterator).setCenaBuy(cenaKupna);
            HelloController.getInstance().tabelaPrzedmiotowPrzygotowana.refresh();
            przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(ERASE));
            Thread.sleep(1);
            if (iterator%10==9){
                czyPojawiloSieOstrzezenie = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie(LOGOUTWARNING);
            }
            if (czyPojawiloSieOstrzezenie) {
                Thread.sleep(500);
                przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(CLOSE));
                Thread.sleep(500);
                przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia(MARKET));
                Thread.sleep(500);
            }
            ustawPostep((iterator+1)/HelloController.getInstance().daneDoPrzygotowanejTablicy.size());
        }
        System.out.println("Zakończono pobieranie danych.");
        if(HelloController.getInstance().sqlCheckBox.isSelected()){
            System.out.println("Wygenerowanie kompletne zapytanie bazodanowe:");
            System.out.println(Rekord.przeksztalcArrayWZapytanieBazodanowe(Rekordy));
            System.out.println("Laduje dane do bazy...");
            BazaDanych.zaladujPrzygotowaneZapytanieDoBazy(Rekord.przeksztalcArrayWZapytanieBazodanowe(Rekordy));
            System.out.println("Dane zaladowane.");
        }
        System.out.println("Koniec programu.");

//        Uruchamiacz.zamknijProgram();
//        System.exit(0);
    }
}




















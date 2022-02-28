package com.example.demo2;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.robot.Robot;
import java.util.HashMap;
import java.util.Map;

public class Przyciskacz {
    Robot robot = new Robot();
    public Map<Character, KeyCode> mapaznakow = new HashMap();


    public Przyciskacz() {
        mapaznakow.put('0', KeyCode.DIGIT0);
        mapaznakow.put('1', KeyCode.DIGIT1);
        mapaznakow.put('2', KeyCode.DIGIT2);
        mapaznakow.put('3', KeyCode.DIGIT3);
        mapaznakow.put('4', KeyCode.DIGIT4);
        mapaznakow.put('5', KeyCode.DIGIT5);
        mapaznakow.put('6', KeyCode.DIGIT6);
        mapaznakow.put('7', KeyCode.DIGIT7);
        mapaznakow.put('8', KeyCode.DIGIT8);
        mapaznakow.put('9', KeyCode.DIGIT9);
        mapaznakow.put('a', KeyCode.A);
        mapaznakow.put('b', KeyCode.B);
        mapaznakow.put('c', KeyCode.C);
        mapaznakow.put('d', KeyCode.D);
        mapaznakow.put('e', KeyCode.E);
        mapaznakow.put('f', KeyCode.F);
        mapaznakow.put('g', KeyCode.G);
        mapaznakow.put('h', KeyCode.H);
        mapaznakow.put('i', KeyCode.I);
        mapaznakow.put('j', KeyCode.J);
        mapaznakow.put('k', KeyCode.K);
        mapaznakow.put('l', KeyCode.L);
        mapaznakow.put('m', KeyCode.M);
        mapaznakow.put('n', KeyCode.N);
        mapaznakow.put('o', KeyCode.O);
        mapaznakow.put('p', KeyCode.P);
        mapaznakow.put('q', KeyCode.Q);
        mapaznakow.put('r', KeyCode.R);
        mapaznakow.put('s', KeyCode.S);
        mapaznakow.put('t', KeyCode.T);
        mapaznakow.put('u', KeyCode.U);
        mapaznakow.put('v', KeyCode.V);
        mapaznakow.put('w', KeyCode.W);
        mapaznakow.put('x', KeyCode.X);
        mapaznakow.put('y', KeyCode.Y);
        mapaznakow.put('z', KeyCode.Z);
        mapaznakow.put('@', KeyCode.AT);
        mapaznakow.put('_', KeyCode.UNDERSCORE);
        mapaznakow.put('.', KeyCode.PERIOD);
        mapaznakow.put(' ', KeyCode.SPACE);
        mapaznakow.put('\'', KeyCode.QUOTE);
//        mapaznakow.put('Enter',VK_ENTER);
//        mapaznakow.put('prawo',VK_RIGHT);
//        mapaznakow.put('lewo',VK_LEFT);
//        mapaznakow.put('gora',VK_UP);
//        mapaznakow.put('dol',VK_DOWN);

    }

    public void Enter() {
        robot.keyPress(KeyCode.ENTER);
        robot.keyRelease(KeyCode.ENTER);
    }

    public void Prawo() {
        robot.keyPress(KeyCode.RIGHT);
        robot.keyRelease(KeyCode.RIGHT);
    }

    public void Lewo() {
        robot.keyPress(KeyCode.LEFT);
        robot.keyRelease(KeyCode.LEFT);
    }

    public void Dol() {
        robot.keyPress(KeyCode.DOWN);
        robot.keyRelease(KeyCode.DOWN);
    }

    public void skierujPostacwDol() throws InterruptedException {
        robot.keyPress(KeyCode.CONTROL);
        Thread.sleep(20);
        Dol();
        robot.keyRelease(KeyCode.CONTROL);
    }

    public void przyciskajSlowo(String slowo) throws Exception {
        Robot robot = new Robot();
//        System.out.println("Czekam 2 sekundy i zaczynam pisać...");
        Thread.sleep(20);
        char[] tablicaZnakow = slowo.toCharArray();
        for (int i = 0; i < tablicaZnakow.length; i++) {
            Character tymczasowyZnak = tablicaZnakow[i];

            robot.keyPress(mapaznakow.get(tymczasowyZnak));
            Thread.sleep(50);
            robot.keyRelease(mapaznakow.get(tymczasowyZnak));
            //System.out.print(tablicaZnakow[i]+" ");

        }
//        System.out.println("Zakonczylem pisanie.");

    }

    public void maksymalizujAktualneOkno() {
        Robot robot = new Robot();
        robot.keyPress(KeyCode.WINDOWS);
        robot.keyPress(KeyCode.UP);
        robot.keyRelease(KeyCode.UP);
        robot.keyRelease(KeyCode.WINDOWS);
    }

    public void enter() {
        Robot robot = new Robot();
        robot.keyPress(KeyCode.ENTER);
        robot.keyRelease(KeyCode.ENTER);
    }

    public void kliknijPrawym(int x, int y) {
        Robot robot = new Robot();
        robot.mouseMove(x, y);
        robot.mousePress(MouseButton.SECONDARY);
        robot.mouseRelease(MouseButton.SECONDARY);
    }

    public void kliknijPrawym(Punkt punkt) {
        Robot robot = new Robot();
        robot.mouseMove(punkt.getWspX(), punkt.getWspY());
        robot.mousePress(MouseButton.SECONDARY);
        robot.mouseRelease(MouseButton.SECONDARY);
    }

    public void kliknijLewym(int x, int y) {
        Robot robot = new Robot();
        robot.mouseMove(x, y);
        robot.mousePress(MouseButton.PRIMARY);
        robot.mouseRelease(MouseButton.PRIMARY);
    }

    public void kliknijLewym(Punkt punkt) {
        robot.mouseMove(punkt.getWspX(), punkt.getWspY());
        robot.mousePress(MouseButton.PRIMARY);
        robot.mouseRelease(MouseButton.PRIMARY);
    }

    public void przeciagnijLewym(Punkt punktPocz, Punkt punktKonc) throws InterruptedException {
        Robot robot = new Robot();
        robot.mouseMove(punktPocz.getWspX(), punktPocz.getWspY());
        Thread.sleep(1000);
        robot.mousePress(MouseButton.PRIMARY);
        Thread.sleep(1000);
        for (int i = punktPocz.getWspY(); i <= punktKonc.getWspY(); i++) {
            robot.mouseMove(punktKonc.getWspX(), i);
            Thread.sleep(20);
        }
//        robot.mouseMove(punktKonc.getWspX(), punktKonc.getWspY());
        Thread.sleep(1000);
        robot.mouseRelease(MouseButton.PRIMARY);

    }
}

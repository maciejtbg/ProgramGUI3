package com.example.demo2;

import com.sun.jna.platform.win32.WinDef;

public class Maksymalizator {
    public void Maksymalizuj(String nazwaOkna) {
        WinDef.HWND hwnd = User32.instance.FindWindowA(null, nazwaOkna);
        User32.instance.ShowWindow(hwnd, 9);
        User32.instance.SetForegroundWindow(hwnd);

    }
}

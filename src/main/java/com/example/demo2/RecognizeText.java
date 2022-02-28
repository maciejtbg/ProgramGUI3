package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;


import static org.opencv.imgproc.Imgproc.THRESH_BINARY_INV;

public class RecognizeText {
    public BufferedImage Binarize(BufferedImage zdjecieDoBin) throws IOException, AWTException {
//        PokazOknoZeZdjeciem(zdjecieDoBin);
//        WrzucZdjecieDoOkna(zdjecieDoBin);
//        System.out.println("Szerokosc zdjecia: "+zdjecieDoBin.getWidth()+" Wysokosc zdjecia: "+zdjecieDoBin.getHeight());
        Mat src = Wyszukiwarka.matify(zdjecieDoBin);
        Mat dst = new Mat();
        Imgproc.threshold(src, dst, 100, 255, THRESH_BINARY_INV);
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".png", dst, mob);
        byte ba[] = mob.toArray();
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(ba));
//        PokazOknoZeZdjeciem(bi);
//        WrzucZdjecieDoOkna(bi);
        Zwiekszacz zwiekszacz = new Zwiekszacz();
        bi = zwiekszacz.zwiekszZdjecieWPamieci(bi);
//        PokazOknoZeZdjeciem(bi);
//        WrzucZdjecieDoOkna(bi);
        return bi;
    }


    public String RozpoznanieWPamieci(BufferedImage zdjecieDoRozpoznania) throws IOException, AWTException {
        Tesseract ts;
        ts = new Tesseract();
        ts.setDatapath("D://");
        ts.setLanguage("eng");
        String text;
        ts.setTessVariable("tessedit_char_whitelist", ",0123456789");
        try {
            text = ts.doOCR(Binarize(zdjecieDoRozpoznania));
        } catch (TesseractException e) {
            e.printStackTrace();
            text = "Blad rozpoznania";
        }
        return text;
    }

    public void PokazOknoZeZdjeciem(BufferedImage zdjecieDoPokazania) throws AWTException {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(zdjecieDoPokazania)));
        frame.pack();
        frame.setVisible(true);
        Robot robot = new Robot();
        robot.delay(1000);
        frame.setVisible(false);
    }




}

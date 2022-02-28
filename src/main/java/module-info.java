module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires opencv;
    requires com.sun.jna;
    requires com.sun.jna.platform;
    requires java.datatransfer;
    requires java.desktop;
    requires java.sql;
    requires tess4j;

    opens com.example.demo2 to javafx.fxml;
    exports com.example.demo2;
}
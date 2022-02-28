package com.example.demo2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable{



    public ObservableList<Item> daneDoTablicy = FXCollections.observableArrayList(
//                new Item("Armor"),
//                new Item("Legi"),
//                new Item("Buty")
    );

    public ObservableList<Item> daneDoPrzygotowanejTablicy = FXCollections.observableArrayList();

    @FXML
    public ProgressBar progressBar;



    @FXML
    private TextField source;

    @FXML
    private TextField csvSource;

    @FXML
    public LineChart lineChart;
    @FXML
    public NumberAxis osY;

    @FXML
    public Button fileChooserButton;

    @FXML
    public Button csvChooserButton;

    @FXML
    public Button collectButton;

    @FXML
    public TableColumn listaZaznaczen;

    @FXML
    public TableColumn listaPrzedmiotow;

    @FXML
    public TableView tabelaPrzedmiotow;

    @FXML
    public ImageView podgladImageSell;

    @FXML
    public ImageView podgladImageBuy;
//    public void setPodgladImage(WritableImage image){
//        podgladImage.setImage(image);
//    }

    public static HelloController instance;

    public HelloController(){
        instance = this;
    }

    public static HelloController getInstance(){
        return instance;
    }



    @FXML
    private Button transferButton;

    @FXML
    public TextField hasloTextField;

    @FXML
    public TableView tabelaPrzedmiotowPrzygotowana;
    @FXML
    private TableColumn listaPrzedmiotowDoZbierania;
    @FXML
    public TableColumn listaCenSprzedazy;
    @FXML
    public TableColumn listaCenKupna;

    @FXML
    public Label komunikator;

    @FXML
    public CheckBox txtFileCheckBox;

    @FXML
    public CheckBox sqlCheckBox;

    @FXML
    public Button txtFileChooserButton;

    @FXML
    public TextField txtFileTextField;

    public static void podaj (String text){
        HelloController.getInstance().komunikator.setText(text);
    }


    @FXML
    public void onTXTBoxAction(){
        boolean czyMaBycDisable;
        if (txtFileCheckBox.isSelected()){
            czyMaBycDisable=false;
        }else czyMaBycDisable=true;
        txtFileTextField.setDisable(czyMaBycDisable);
        txtFileChooserButton.setDisable(czyMaBycDisable);
    }



    @FXML
    public CheckBox csvFileCheckBox;
    @FXML
    public Button csvFileChooserButton;

    @FXML
    public TextField csvFileTextField;

    public void onCSVBoxAction(){
        boolean czyMaBycDisable;
        if (csvFileCheckBox.isSelected()){
            czyMaBycDisable=false;
        }else czyMaBycDisable=true;
        csvFileTextField.setDisable(czyMaBycDisable);
        csvFileChooserButton.setDisable(czyMaBycDisable);
    }

    @FXML
    protected  void onChoseTXTPathClick(ActionEvent event){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("D:/"));
        File file = directoryChooser.showDialog(null);
        if (file != null){
            txtFileTextField.setText(file.getAbsolutePath());
        }
    }

    @FXML
    protected  void onChoseCSVPathClick(ActionEvent event){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("D:/"));
        File file = directoryChooser.showDialog(null);
        if (file != null){
            csvFileTextField.setText(file.getAbsolutePath());
        }
    }


    @FXML
    public void onCollectButtonClick() throws Exception {
        if (daneDoPrzygotowanejTablicy.size()==0){
            podaj("Nie ma czego zbierać!");
        } else {
            Zbieracz.start();
        }



    }
    @FXML
    protected void onStartButtonClick() throws FileNotFoundException {
        System.out.println("Start!");


        lineChart.setAnimated(false);
//        osY.setAutoRanging(false);
//        osY = new NumberAxis("Hours worked", 20000, 50000, 1000);
        XYChart.Series series = new XYChart.Series();
        XYChart.Series seriesBUY = new XYChart.Series();
        series.setName("Cena SELL");
        seriesBUY.setName("Cena BUY");
        String delimiter = ",";
        Integer najnizszaCena = 100000;
        Integer najwyzszaCena = 0;

                    try {
                File file = new File(csvSource.getText());
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                String data;
                Integer cenaSell;
                Integer cenaBuy;


                String[] tempArr;
                while((line = br.readLine()) != null) {
                    tempArr = line.split(delimiter);
                    data = tempArr[0].substring(5,10);
                    cenaSell = Integer.parseInt(tempArr[1]);
                    cenaBuy = Integer.parseInt(tempArr[2]);
                    series.getData().add(new XYChart.Data(data, cenaSell));
                    seriesBUY.getData().add(new XYChart.Data(data, cenaBuy));
                    if (cenaBuy>najwyzszaCena){
                        najwyzszaCena=cenaBuy;
                    }
                    if (cenaSell>najwyzszaCena){
                        najwyzszaCena=cenaSell;
                    }

                    if (cenaBuy<najnizszaCena){
                        najnizszaCena=cenaBuy;
                    }
                    if (cenaSell<najnizszaCena){
                        najnizszaCena=cenaSell;
                    }

                }
                br.close();
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        lineChart.getData().add(series);
        lineChart.getData().add(seriesBUY);
        osY.setAutoRanging(false);
//        osY.setUpperBound(najwyzszaCena);
//        osY.setLowerBound(najnizszaCena);
        osY.setUpperBound(26000);
        osY.setLowerBound(22000);
        osY.setTickUnit(500);
        System.out.println(osY.getLowerBound()+" "+osY.getUpperBound());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Zaczynamy!");
        onCSVBoxAction();
        onTXTBoxAction();


    }

    public void setProgressBar(double progress){
        progressBar.setProgress(progress);
    }


    @FXML
    public void onTestButtonClick(){
        System.out.println("Jestem!");

    }
    @FXML
    protected  void onChoseButtonClick(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT Files","*.txt"));
        fileChooser.setInitialDirectory(new File("D:/"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null){
            source.setText(file.getAbsolutePath());
        }

        ArrayList<Item> arrayItemow = new ArrayList<>();
        FileReader plik = new FileReader(source.getText());
        BufferedReader in = new BufferedReader(plik);


            String linijka;
            while ((linijka = in.readLine()) != null) {
                arrayItemow.add(new Item(linijka));
            }
            in.close();



        for(Item iterator : arrayItemow) {
            daneDoTablicy.add(iterator);
        }
        listaZaznaczen.setCellValueFactory(new PropertyValueFactory<Item,String>("zaznacz"));
        listaPrzedmiotow.setCellValueFactory(new PropertyValueFactory<Item,String>("nazwa"));



        tabelaPrzedmiotow.setItems(daneDoTablicy);
        }

        @FXML
        protected void onCheckBoxToPrepare(){
        onTransferButtonClick();
        }

    @FXML
    public void onTransferButtonClick(){
        System.out.println("Przygotowuje itemy do zbierania!");
        daneDoPrzygotowanejTablicy.clear();

        System.out.println("Utowrzylem observableList");
        for(Item iterator : daneDoTablicy){
            if (iterator.getZaznacz().isSelected()==true){
                daneDoPrzygotowanejTablicy.add(iterator);
            }
        }
        System.out.println("Przeprowadzilem iteracje po tablicy prawej.");
        listaPrzedmiotowDoZbierania.setCellValueFactory(new PropertyValueFactory<Item,String>("nazwa"));
        listaCenSprzedazy.setCellValueFactory(new PropertyValueFactory<Item,String>("cenaSell"));
        listaCenKupna.setCellValueFactory(new PropertyValueFactory<Item,String>("cenaBuy"));
        tabelaPrzedmiotowPrzygotowana.setItems(daneDoPrzygotowanejTablicy);







//        System.out.println(HelloController.getInstance().daneDoPrzygotowanejTablicy.get(0).nazwa);

    }

    @FXML
    protected  void onCSVChoseButtonClick(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files","*.csv"));
        fileChooser.setInitialDirectory(new File("D:/"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null){
            csvSource.setText(file.getAbsolutePath());
        }
    }

    @FXML
    public void sprawdzCzyZmienionoListeWejsciowa() {
        if(Item.sprawdzaczCzyZmieniono==true) {
            System.out.println("Wykrylem, że zmieniono. Dokonuje aktualizacji.");
            onTransferButtonClick();
        }
    }
}
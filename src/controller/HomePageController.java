package controller;
// Java code to generate QR code

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class HomePageController {
    public Label lblPath;
    public Button btnFilePathSelect;
    public Label lblPath1;
    public TextArea txtTextFeild2;
    public Button btnReadQR;
    public Label lblPath2;
    public Button btnFilePathSelectRead;
    Stage stage;

    String data;
    String path;
    String readPath;
    String printString;
    @FXML
    private Button btnBack;

    @FXML
    private Button btnCreateQR;

    @FXML
    private Button btnFilePath;

    @FXML
    private TextArea txtTextFeild;

    @FXML
    void backAction(ActionEvent event) throws Exception {
        Controler.navigator(event,"../view/MainFormView.fxml");
    }

    @FXML
    void createQRAction(ActionEvent event) throws Exception {

        if(path!=null){
            // The data that the QR code will contain
            data = txtTextFeild.getText();
            if(!data.equals("")){

                // The path where the image will get saved
                // Encoding charset
                String charset = "UTF-8";

                Map<EncodeHintType, ErrorCorrectionLevel> hashMap
                        = new HashMap<EncodeHintType,
                        ErrorCorrectionLevel>();

                hashMap.put(EncodeHintType.ERROR_CORRECTION,
                        ErrorCorrectionLevel.L);

                // Create the QR code and save
                // in the specified folder
                // as a jpg file
                Controler.createQR(data, path, charset, hashMap, 400, 400);
                Controler.setAlertBox("Alert","QR Code Generated!!!");

            }else{
                Controler.setAlertBox("error","empty content");
            }

        }else{
            Controler.setAlertBox("error","Please set your save location");
        }




    }

    @FXML
    void filePathAction(ActionEvent event) {

        String path1= Controler.setFilePath(new Stage());
        path=path1+".png";
        lblPath.setText(path);



    }

    // read QR
    public void readQRAction(ActionEvent event) throws Exception {

        if(lblPath2!=null) {
            // The data that the QR code will contain

            // Path where the QR code is saved
            String filePath = readPath;

            // Encoding charset
            String charset = "UTF-8";

            Map<EncodeHintType, ErrorCorrectionLevel> hashMap
                    = new HashMap<EncodeHintType,
                    ErrorCorrectionLevel>();

            hashMap.put(EncodeHintType.ERROR_CORRECTION,
                    ErrorCorrectionLevel.L);

            printString= Controler.readQR(filePath, charset, hashMap);
            txtTextFeild2.setText(printString);


        }else{
            Controler.setAlertBox("Error","add your QR");

        }

    }

    public void filePathReadAction(ActionEvent event) {
        String path1= Controler.setFilePath(new Stage());
        readPath=path1;
        lblPath2.setText(readPath);
    }
}
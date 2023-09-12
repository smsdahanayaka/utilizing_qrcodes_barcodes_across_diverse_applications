package controller;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import dbControler.DBController;
import javafx.event.ActionEvent;;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modal.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import javax.imageio.ImageIO;

public class Controler {
    static String path;
    // Control Page Navigation
    public static void navigator(ActionEvent event, String url) throws Exception {
        Stage currentStage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(FXMLLoader.load(Controler.class.getResource(url))));
    }

    // Control Alert Dialog Box
    public static void setAlertBox(String title, String body) {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(body);
        alert.show();
    }

    // Check Contact number equals or not
    public static boolean checkContactYesOrNot(String txtContact) throws SQLException {
        Connection connection= DBController.getInstance().getConnection();
        Statement stm=connection.createStatement();
        ResultSet rset = stm.executeQuery("SELECT *FROM user_table WHERE userContactNumber='" + txtContact + "'");

        if(!rset.next()){
            return false;
        }return true;
    }

    // Add users to database
    public static boolean addUser(User user) throws Exception {

        Connection connection= DBController.getInstance().getConnection();
        Statement stm=connection.createStatement();
        int x= stm.executeUpdate("INSERT INTO user_table VALUES ('"+ user.getUserId()+"','"+user.getUserName()+"','"+user.getUserContactNumber()+"','"+user.getUserMail()+"','"+user.getUserPassword()+"')");
        return x>0;
    }

    public static boolean checkPassword(String contc,String pword) throws Exception {

        Connection connection=DBController.getInstance().getConnection();
        Statement stm=connection.createStatement();
        ResultSet x = stm.executeQuery("SELECT *FROM user_table WHERE userContactNumber='" + contc + "'AND userPassword='" + pword + "' ");

        return x.next();
    }

    // Function to create the QR code
    public static void createQR(String data, String path,
                                String charset, Map<?, ?> hashMap,
                                int height, int width)
            throws WriterException, IOException
    {

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height);

        BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

        // Get the file extension from the path
        String fileExtension = path.substring(path.lastIndexOf('.') + 1);

        // Write the image to the file using ImageIO
        File qrCodeFile = new File(path);
        ImageIO.write(image, fileExtension, qrCodeFile);
    }
    public static String setFilePath(Stage stage) {
        FileChooser fil_chooser = new FileChooser();
        // get the file selected
        File file = fil_chooser.showSaveDialog(stage);

        if (file != null) {
            //label.setText(file.getAbsolutePath());
            path=file.getAbsolutePath();
                return path;
        }
        return "";
    }

    //Read QR
    public static String readQR(String path, String charset,Map hashMap) throws Exception{
        BinaryBitmap binaryBitmap
                = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(
                        ImageIO.read(
                                new FileInputStream(path)))));

        Result result
                = new MultiFormatReader().decode(binaryBitmap);

        return result.getText();
    }
}

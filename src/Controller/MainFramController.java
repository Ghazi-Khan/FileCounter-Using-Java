/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Application.*;
import static Controller.FileGeneratingLog.mainLog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ghazi
 */
public class MainFramController implements Initializable {

   // FileGeneratingLog fgl=new FileGeneratingLog();
    String dire_path=null,desination_path=null;
    DirectoryChooser d1,d2;
    FileChooser fc;
    File f1,f2;
   // Main m=new Main();
    final Stage primaryStage= FileCounter.getPrimaryStage() ;
 
    @FXML
    private Button source;
    
    @FXML
    private Button destinaton;
    
    @FXML
    private Button repot;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   @FXML
   public void selectDir(ActionEvent e) throws IOException
    {
       d1 = new DirectoryChooser();
       f1 =  d1.showDialog(primaryStage);
       if(f1 == null){
            repot.setDisable(true);
            destinaton.setDisable(true);
            dire_path=null;
       }
       else{
            dire_path = f1.getAbsolutePath();
            repot.setDisable(false);
            destinaton.setDisable(false);
       }
   }
   
   @FXML    //destination file chooser
    public void selectDes(ActionEvent e) throws IOException
    {

        fc= new FileChooser();        
        fc.setInitialDirectory(f1);     
        fc.setInitialFileName("Report_"+f1.getName());
        FileChooser.ExtensionFilter filter =new FileChooser.ExtensionFilter("Text File(*.txtt)", "*.txt","*.text");
        fc.getExtensionFilters().add(filter);
        f2=fc.showSaveDialog(primaryStage);
        if(f2==null){
            desination_path=null;
        }
        else{
            desination_path=f2.getAbsolutePath();
        }
    }
    @FXML
    public void reportGenerater(ActionEvent e)throws IOException, Exception
    {
       int output= mainLog(dire_path,desination_path);
       if(output==1){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Congratulation");
        alert.setHeaderText(null);
        alert.setContentText("Report successfully generated..!");
        Rectangle2D sbound=Screen.getPrimary().getVisualBounds();
        
        alert.setX(((sbound.getWidth()-primaryStage.getWidth())/2)+45);
        alert.setY(((sbound.getHeight()-primaryStage.getHeight())/2)+120);
        alert.showAndWait();
        repot.setDisable(true);
        destinaton.setDisable(true);
            dire_path=null;
            desination_path=null;
           //reporrt generated...
       }
    }
    
   // public String getPath(){
    //return dire_path;
    //}
}

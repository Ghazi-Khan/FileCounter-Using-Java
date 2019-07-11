
package Application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Ghazi
 */
public class FileCounter extends Application {
	
	private static Stage primaryStageObj;
	
	@Override
	public void start(Stage primaryStage) {
		try {
		   
			primaryStageObj = primaryStage;
		        Parent root = FXMLLoader.load(getClass().getResource("/style/MainFram.fxml"));
                        Scene scene = new Scene(root);
                       // primaryStage.initStyle(StageStyle.UNDECORATED);
                        primaryStage.getIcons().add(new Image("/images/file_icon.png"));
                        primaryStage.setTitle("FileCounter");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
                        
                        Rectangle2D sbound=Screen.getPrimary().getVisualBounds();
                        primaryStage.setX((sbound.getWidth()-primaryStage.getWidth())/2);
                        primaryStage.setY((sbound.getHeight()-primaryStage.getHeight())/2);
                      //  primaryStage.setMaximized(true);            //FullScreen
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void main(String[] args) {
		launch(args);
	}

        public static Stage getPrimaryStage() {
	        return primaryStageObj;
	 }

}

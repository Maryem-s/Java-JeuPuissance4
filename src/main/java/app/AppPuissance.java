package app;

import app.control.Control;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppPuissance extends Application {
 
	Control control=new Control();
	@Override
    public void start(Stage stage) {     	
		
		control.puissanceControl();
		
		Scene scene = new Scene(control.getFenetre() , 640, 480);
        stage.setScene(scene);
        stage.show();		
    }
    public static void main(String[] args) {
        launch();
    }
}
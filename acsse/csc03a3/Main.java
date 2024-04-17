/**
 * 
 */
package acsse.csc03a3;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Daniel
 *
 */
public class Main extends Application {
	
    @Override
    public void start(Stage primaryStage) {
        new FXInitializer(primaryStage);
    }
    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}

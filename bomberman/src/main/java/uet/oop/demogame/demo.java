package uet.oop.demogame;

import javafx.application.Application;
import javafx.stage.Stage;
import uet.oop.PassLevelStage;

public class demo extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        PassLevelStage stage = new PassLevelStage("Score: 10000");
        stage.show();

        
    }
    
}

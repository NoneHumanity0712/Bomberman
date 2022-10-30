package uet.oop.stages;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Setting extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        StartStage startMenu = new StartStage();
        startMenu.show();

        startMenu.setOnCloseRequest(e -> Platform.exit());

        startMenu.howtoplayButton.setOnAction(e -> {
            try {
                startMenu.howtoplay();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });


        startMenu.startButton.setOnAction(e -> {
            boolean isLightMode = startMenu.theme.isState();
            boolean isMuteSound = startMenu.theme.isState();

            System.out.println("Start Game: Light Mode:" + isLightMode + ", Sound Effect: " + isMuteSound);
            startMenu.close();
        });
    }

}

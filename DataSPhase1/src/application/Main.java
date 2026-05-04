package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Screens.getMainScreen().show();
    }

    public static void main(String[] args) {
        Data.initTheDataList();
        launch(args);
    }
}
 
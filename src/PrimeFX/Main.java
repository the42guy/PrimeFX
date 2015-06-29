package PrimeFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Controller x = new Controller();
        boolean prime = x.checkIntPassOne(10814521);
        System.out.println(prime);*/
        Parent root = FXMLLoader.load(getClass().getResource("prime.fxml"));
        primaryStage.setTitle("PrimeFX");
        primaryStage.setScene(new Scene(root, 720, 575));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

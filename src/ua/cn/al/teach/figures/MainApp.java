package ua.cn.al.teach.figures;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        URL url = new File("src/ua/cn/al/teach/figures/fxml/scene.fxml").toURL();
//        Parent root = FXMLLoader.load(url);
        Parent root = FXMLLoader.load(getClass().getResource("fxml/scene.fxml"));
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/scene.fxml"));

        Scene scene = new Scene(root, 800, 600);

        stage.setScene(scene);
        stage.setMinWidth(400);
        stage.setMinHeight(200);

        stage.show();
    }


    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

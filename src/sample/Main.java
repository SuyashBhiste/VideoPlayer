package sample;

// Imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Set scene for video player
        VideoPlayer videoPlayer = new VideoPlayer("file:///D:/video.mp4");
        Scene scene = new Scene(videoPlayer, 1280, 760, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

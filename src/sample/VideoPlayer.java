package sample;

// Imports
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class VideoPlayer extends BorderPane {

    // Declaration
    Media media;
    MediaPlayer mediaPlayer;
    MediaView mediaView;
    Pane pane;
    VideoBar bar;

    public VideoPlayer(String filepath) {

        // Initialize
        media = new Media(filepath);
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);
        pane = new Pane();

        // Attach & Alginment
        pane.getChildren().add(mediaView);
        setCenter(pane);

        // Set bar position
        bar = new VideoBar(mediaPlayer);
        setBottom(bar);

        // Play
        mediaPlayer.play();
    }
}

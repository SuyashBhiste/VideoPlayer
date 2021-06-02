package sample;

// Imports
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class VideoBar extends HBox {

    // Declaration
    Slider timeSlider = new Slider();
    Slider volSlider = new Slider();

    Button button = new Button("||");
    Label volume = new Label("Volume");

    MediaPlayer player;
    private Duration duration;

    public VideoBar(MediaPlayer play) {
        player = play;

        // Alignment & Size
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 10, 5, 10));

        volSlider.setPrefWidth(70.0);
        volSlider.setMinWidth(30.0);
        volSlider.setValue(100);

        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        button.prefWidth(40.0);

        // Attach
        getChildren().add(button);
        getChildren().add(timeSlider);
        getChildren().add(volume);
        getChildren().add(volSlider);

        // Play Pause
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Status status = player.getStatus();

                if (status == Status.PLAYING){
                    if(player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())){
                        player.seek(player.getStartTime());
                        player.play();
                    }else{
                        player.pause();
                        button.setText(">");
                    }
                }
                if (status == Status.PAUSED || status == Status.STOPPED){
                    player.play();
                    button.setText("||");
                }
            }
        });

        // Video timeline
        player.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                moveSlider();
            }
        });

        // Volume adjustment
        volSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (volSlider.isPressed()){
                    player.setVolume(volSlider.getValue()/100);
                }
            }
        });

        // Seek video
        timeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (timeSlider.isPressed()){
                    duration = player.getMedia().getDuration();
                    player.seek(duration.multiply(timeSlider.getValue()/100));
                }
            }
        });
    }

    // Calc time and set timeline
    private void moveSlider(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                timeSlider.setValue((player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis())*100);
            }
        });
    }

}

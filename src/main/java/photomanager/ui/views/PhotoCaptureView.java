package photomanager.ui.views;


import java.io.File;
import java.time.LocalDateTime;

import com.drew.metadata.Metadata;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import photomanager.logic.photo.Photo;
import photomanager.logic.photo.PhotoMetadata;


public class PhotoCaptureView extends CaptureView<Photo> {

    private Photo photo;

    public PhotoCaptureView(Stage primaryStage) {
        super(primaryStage);
    }


    @Override
    public boolean showView() {
        Label dataLabel = new Label("Datei:");
        Label dataInformationLabel = new Label("Wählen Sie eine Datei aus");

        Button buttonChooseData = new Button("Wählen");
        buttonChooseData.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(this.getStage());

            if (file != null) {
                dataInformationLabel.setText(file.getAbsolutePath());
            }
        });

        this.getInputPane().add(dataLabel, 0, 1);
        this.getInputPane().add(dataInformationLabel, 1, 1);
        this.getInputPane().add(buttonChooseData, 2, 1);

        this.getStage().setTitle("Foto hinzufügen");

        if (!super.showView()) {
            System.out.println("Abbruch");
            return false;
        }

        String name = "Test";
        String dataname = dataInformationLabel.getText();

        Photo newPhoto = new Photo(name, dataname, new PhotoMetadata(1024, 1024, "Canon", "cameraModel", LocalDateTime.now()));
        this.photo = newPhoto;
        
        return true;
    }


    @Override
    public Photo getNewObject() {
        return this.photo;
    }
    
}

package photomanager.ui.views;


/**
 * @author Patrick Winter
 */


import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import photomanager.logic.photo.PhotoAlbum;


public class AlbumCaptureView extends CaptureView<PhotoAlbum> {

    private PhotoAlbum album;


    public AlbumCaptureView(Stage primaryStage) {
        super(primaryStage);
    }


    @Override
    public PhotoAlbum getNewObject() {
        return this.album;
    }


    @Override
    public boolean showView() {
        Label labelOwner = new Label("Besitzer");
        TextField textFieldOwner = new TextField();

        this.getInputPane().add(labelOwner, 0, 1);
        this.getInputPane().add(textFieldOwner, 1, 1);
        GridPane.setColumnSpan(textFieldOwner, 2);

        this.getStage().setTitle("Neues Album erstellen");
        
        if (!super.showView()) {
            System.out.println("Abbruch");
            return false;
        }

        String name = ((TextField) this.getNodeByCoordinate(0, 1)).getText();
        String owner = textFieldOwner.getText();

        this.album = new PhotoAlbum(name, owner);

        return true;
    }
    
}

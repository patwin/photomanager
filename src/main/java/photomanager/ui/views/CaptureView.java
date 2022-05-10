package photomanager.ui.views;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

/**
 * @author Patrick Winter
 */


import javafx.stage.Stage;


public abstract class CaptureView<T> extends Stage {

    private Stage stage;
    private GridPane inputPane;
    private VBox layout;
    private boolean isReadyToCreate;


    public CaptureView (Stage primaryStage) {
        this.stage = new Stage();
        this.inputPane = new GridPane();

        this.stage.initOwner(primaryStage);
        this.stage.initModality(Modality.WINDOW_MODAL);

        this.isReadyToCreate = false;
    }


    public GridPane getInputPane() {
        return this.inputPane;
    }

    public Stage getStage() {
        return this.stage;
    }


    protected Node getNodeByCoordinate (int row, int column) {
        for (Node node : this.inputPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row &&
                    GridPane.getColumnIndex(node) == column) {
                return node;
            }
        }

        return null;
    }


    public boolean showView() {
        this.layout = new VBox();
        this.layout.setPrefSize(300, 150);

        Label labelName = new Label("Name:");
        TextField textFieldName = new TextField();;

        this.inputPane.add(labelName, 0, 0);
        this.inputPane.add(textFieldName, 1, 0);
        GridPane.setColumnSpan(textFieldName, 2);
        this.inputPane.setHgap(5);
        this.inputPane.setVgap(5);

        this.layout.getChildren().add(inputPane);

        Button buttonOk = new Button("OK");
        buttonOk.setOnAction(event -> {
            this.isReadyToCreate = true;
            this.stage.close();
        });

        Button buttonCancel = new Button("Abbrechen");
        buttonCancel.setOnAction(event -> {
            this.isReadyToCreate = false;
            this.stage.close();
        });

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(buttonOk, buttonCancel);
        buttonBox.setSpacing(10);

        this.inputPane.add(buttonBox, 1, 4);

        //this.layout.getChildren().add(buttonBox);
        this.layout.setPadding(new Insets(5, 5, 5, 5));
        this.layout.setSpacing(5);

        Scene scene = new Scene(this.layout);
        this.stage.setScene(scene);
        this.stage.showAndWait();

        return this.isReadyToCreate;
    }

    public abstract T getNewObject();

}

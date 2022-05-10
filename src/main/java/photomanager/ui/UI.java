package photomanager.ui;


/**
 * @author Patrick Winter
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.geometry.Insets;

import javafx.stage.Stage;

import photomanager.logic.photo.Photo;
import photomanager.logic.photo.PhotoAlbum;
import photomanager.logic.photo.Photomanagement;
import photomanager.ui.views.AlbumCaptureView;
import photomanager.ui.views.PhotoCaptureView;



public class UI extends Application {

    private Photomanagement photoManagment;
    private PhotoAlbum currentPhotoAlbum;

    private ObservableSet<PhotoAlbum> listOfPhotoAlbums;
    private ObservableSet<Photo> listOfPhotosInAlbum;


    // ELEMENTS MENU -> DATEI
    private MenuBar menuBar;
    private Menu dataMenu;
    private MenuItem loadItem;
    private MenuItem saveItem;
    private MenuItem exportItem;
    private MenuItem exitItem;

    // ELEMENTS MENU -> Alben
    private Menu albumMenu;
    private MenuItem createNewAlbumItem;

    // ELEMENTS ALBUM OVERVIEW
    private ListView<PhotoAlbum> albumListView;

    // ELEMENTS ALBUMINFORMATION
    private Label labelAlbumName;
    private Label labelOwner; 
    private Button buttonAddPhoto;

    // ELEMENTS PHOTOGALLERY
    ScrollPane photoScrollPane = new ScrollPane();

    // ELEMENTS PHOTOINFORMATION
    private Label labelNameInformation;
    private Label labelDatanameInformation;
    private Label labelSizeInformation;
    private Label labelCameraInformation;
    private Label labelCreationDateInformation;


    /* =============== METHODES TO CREATE MENU =============== */

    // INITIALIZE MENU 
    private MenuBar initMenu() {
        this.menuBar = new MenuBar();

        menuBar.getMenus().add(this.initDataMenu());
        menuBar.getMenus().add(this.initAlbumMenu());

        return this.menuBar;
    }

    // INITIALIZE MENU DATEI
    private Menu initDataMenu() {
        this.dataMenu = new Menu("Datei");

        this.loadItem = new MenuItem("Laden");
        this.saveItem = new MenuItem("Speichern");

        this.dataMenu.getItems().addAll(this.loadItem, this.saveItem);
        this.dataMenu.getItems().add(new SeparatorMenuItem());

        this.exportItem = new MenuItem("CSV-Export");
        this.dataMenu.getItems().add(this.exportItem);
        this.dataMenu.getItems().add(new SeparatorMenuItem());

        this.exitItem = new MenuItem("Beenden");
        this.dataMenu.getItems().add(this.exitItem);

        return this.dataMenu;
    }

    // INITIALIZE MENU ALBEN
    private Menu initAlbumMenu() {
        this.albumMenu = new Menu("Alben");

        this.createNewAlbumItem = new MenuItem("Neues Album erstellen");
        this.albumMenu.getItems().add(this.createNewAlbumItem);

        return this.albumMenu;
    }


    /* =============== METHODES TO CREATE ALBUM OVERVIEW =============== */

    // INITIALIZE LISTVIEW ALBUM OVERVIEW
    private ListView<PhotoAlbum> initAlbumListView() {        
        this.albumListView = new ListView<>();

        return this.albumListView;
    }


    /* =============== METHODES TO CREATE PHOTOGALLARY =============== */

    // INITIALIZE PHOTOGALLERYPANE
    private BorderPane initPhotoGalleryPane() {
        BorderPane photoGalleryPane = new BorderPane();
        
        photoGalleryPane.setTop(this.initAlbumInformationPane());
        photoGalleryPane.setCenter(this.initPhotoScrollPane());
        photoGalleryPane.setBottom(this.initPhotoInformationPane());

        return photoGalleryPane;
    }

    // INITIALIZE ALBUMINFORMATIONPANE
    private GridPane initAlbumInformationPane() {
        GridPane albumInformationGrid = new GridPane();

        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        ColumnConstraints columnConstraints2 = new ColumnConstraints();
        ColumnConstraints columnConstraints3 = new ColumnConstraints();

        columnConstraints1.setPercentWidth(10);
        columnConstraints2.setPercentWidth(70);
        columnConstraints3.setPercentWidth(20);

        albumInformationGrid.getColumnConstraints().addAll(columnConstraints1, columnConstraints2, columnConstraints3);
        albumInformationGrid.setPadding(new Insets(5, 5, 5, 5));
        albumInformationGrid.setVgap(2);
        albumInformationGrid.setHgap(2);
        albumInformationGrid.add(this.createBoldLabel("Album:"), 0, 0);
        albumInformationGrid.add(this.createBoldLabel("Besitzer:"), 0, 1);

        this.labelAlbumName = new Label();
        this.labelOwner = new Label();
        albumInformationGrid.add(labelAlbumName, 1, 0);
        albumInformationGrid.add(labelOwner, 1, 1);

        this.buttonAddPhoto = new Button("Foto hinzufügen");
        albumInformationGrid.add(buttonAddPhoto, 2, 0);

        return albumInformationGrid;
    }

    // INITIALIZE PHOTOTILESPANE
    private ScrollPane initPhotoScrollPane() {
        this.photoScrollPane = new ScrollPane();

        return this.photoScrollPane;
    }

    // INITIALIZE PHOTOINFORMATIONPANE
    private GridPane initPhotoInformationPane() {
        GridPane photoInformationPane = new GridPane();
        photoInformationPane.setPadding(new Insets(5, 5, 5, 5));
        photoInformationPane.setVgap(2);
        photoInformationPane.setHgap(2);

        Label labelName = this.createBoldLabel("Name:");
        this.labelNameInformation = new Label();
        photoInformationPane.add(labelName, 0, 0);
        photoInformationPane.add(labelNameInformation, 1, 0);

        Label labelDataname = this.createBoldLabel("Dateiname:");
        this.labelDatanameInformation = new Label();
        photoInformationPane.add(labelDataname, 0, 1);
        photoInformationPane.add(labelDatanameInformation, 1, 1);

        Label labelSize = this.createBoldLabel("Größe:");
        this.labelSizeInformation = new Label();
        photoInformationPane.add(labelSize, 0, 2);
        photoInformationPane.add(labelSizeInformation, 1, 2);

        Label labelCamera = this.createBoldLabel("Kamera:");
        this.labelCameraInformation = new Label();
        photoInformationPane.add(labelCamera, 0, 3);
        photoInformationPane.add(labelCameraInformation, 1, 3);

        Label labelCreationDate = this.createBoldLabel("Erstellungsdatum:");
        this.labelCreationDateInformation = new Label();
        photoInformationPane.add(labelCreationDate, 0, 4);
        photoInformationPane.add(labelCreationDateInformation, 1, 4);   

        return photoInformationPane;
    }

    // CREATE GALLERY
    private TilePane showPhotoGallery(PhotoAlbum photoAlbum) {
        TilePane galleryTilePane = new TilePane();
        galleryTilePane.setPadding(new Insets(10, 10, 10, 10));
        galleryTilePane.setHgap(10);

        for (Photo photo : photoAlbum.getPhotos()) {
            try {
                ImageView imageView = this.createThumbnail(photo.getDataname(), 100);
                galleryTilePane.getChildren().addAll(imageView);

                imageView.setOnMouseClicked(event -> {
                    if (!event.getButton().equals(MouseButton.PRIMARY)) {
                        return;
                    }

                    this.labelNameInformation.setText(photo.getName());
                    this.labelDatanameInformation.setText(photo.getDataname());
                    this.labelSizeInformation.setText(photo.getMetadata().getWidth() + " x " + photo.getMetadata().getHeight());
                    this.labelCameraInformation.setText(photo.getMetadata().getCameraBrand() + " " + photo.getMetadata().getCameraModel());
                    this.labelCreationDateInformation.setText(photo.getMetadata().getDateOfCreation().toString());

                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return galleryTilePane;
    }


    /* =============== GENERAL METHODS =============== */
    private ImageView createThumbnail (String imageFile, int width) throws FileNotFoundException {
        ImageView iv = new ImageView(new Image(new FileInputStream(new File(imageFile)), width * 2, 0, true, true));

        return iv;
    }

    private Label createBoldLabel (String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.BOLD, Font.getDefault().getSize()));

        return label;
    }

    
    @Override
    public void start(Stage window) throws Exception {  
        this.photoManagment = new Photomanagement();
        this.listOfPhotoAlbums = FXCollections.observableSet(photoManagment.getPhotoAlbums());
        this.listOfPhotosInAlbum = FXCollections.observableSet();
        
        this.listOfPhotoAlbums.addListener((SetChangeListener<PhotoAlbum>) change -> {
            if (change.wasAdded()) {               
                PhotoAlbum addedAlbum = change.getElementAdded();
                this.photoManagment.addAlbum(addedAlbum);
                this.albumListView.getItems().add(addedAlbum);
            }
        });

        this.listOfPhotosInAlbum.addListener(new SetChangeListener<Photo>() {

            @Override
            public void onChanged(SetChangeListener.Change<? extends Photo> change) {
                Photo addedPhoto = change.getElementAdded();
                currentPhotoAlbum.addPhoto(addedPhoto);

                photoScrollPane.setContent(showPhotoGallery(currentPhotoAlbum));                
            }
            
        });

        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 600);

        layout.setTop(this.initMenu());
        layout.setLeft(this.initAlbumListView());
        layout.setCenter(this.initPhotoGalleryPane());

        
        /* =============== EVENTS =============== */

        // CREATE NEW PHOTO BUTTON EVENT
        buttonAddPhoto.setOnAction(event -> {
            PhotoCaptureView photoView = new PhotoCaptureView(window);
            if (photoView.showView() && currentPhotoAlbum != null) {
                Photo newPhoto = photoView.getNewObject();
                this.listOfPhotosInAlbum.add(newPhoto);
            }
        });

        // CREATE NEW ALBUM BUTTON EVENT
        createNewAlbumItem.setOnAction(event -> {
            AlbumCaptureView albumView = new AlbumCaptureView(window);
            if (albumView.showView()) {                
                PhotoAlbum newAlbum = albumView.getNewObject();
                this.listOfPhotoAlbums.add(newAlbum);   
            }
        });

        // CHOOSE ALBUM FROM LISTVIEW
        albumListView.getSelectionModel().selectedItemProperty().addListener((obs, old, current) -> {
            if (old == null) {
                labelAlbumName.setText("");
                labelOwner.setText("");
            } 

            if (current != null) {                
                labelAlbumName.setText(current.getName());
                labelOwner.setText(current.getOwner());
                currentPhotoAlbum = current;
                
                photoScrollPane.setContent(this.showPhotoGallery(currentPhotoAlbum));
            }

        });


        Scene scene = new Scene(layout);        
        window.setTitle("Photomanager");
        window.setScene(scene);
        window.show();
    }
    
}

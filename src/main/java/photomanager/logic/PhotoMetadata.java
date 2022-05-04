package photomanager.logic;

/**
 * @author Patrick Winter
 */


 import java.time.LocalDateTime;


public class PhotoMetadata {
    
    private int width;
    private int height;
    private String cameraModel;
    private String cameraBrand;
    private LocalDateTime dateOfCreation;


    public PhotoMetadata (int width, int height, String cameraBrand, String cameraModel, LocalDateTime dateOfCreation) {
        this.width = width;
        this.height = height;
        this.cameraBrand = cameraBrand;
        this.cameraModel = cameraModel;
        this.dateOfCreation = dateOfCreation;
    }


    // GETTER-METHODS
    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String getCameraModel() {
        return this.cameraModel;
    }

    public String getCameraBrand() {
        return this.cameraBrand;
    }

    public LocalDateTime getDateOfCreation() {
        return this.dateOfCreation;
    }

    // METHODS
    @Override
    public String toString() {
        return  this.width + "px * " + this.height + "px " + this.cameraBrand + " " + this.cameraModel + " " + this.dateOfCreation;
    }
}

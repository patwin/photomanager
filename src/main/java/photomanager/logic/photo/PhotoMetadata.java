package photomanager.logic.photo;

import java.io.Serializable;


/**
 * @author Patrick Winter
 */


import java.time.LocalDateTime;
import java.util.Objects;


public class PhotoMetadata implements Serializable {
    
    private int width;
    private int height;
    private String cameraModel;
    private String cameraBrand;
    private LocalDateTime dateOfCreation;

    private static final long serialVersionUID = -4145083531653430514L;


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

    @Override
    public int hashCode() {
        return Objects.hash(this.width, this.height, this.cameraBrand, this.cameraModel, this.dateOfCreation);
    }

    @Override
    public boolean equals (Object object) {
        if (!(object instanceof PhotoObject)) {
            return false;
        }

        if (this == object) {
            return true;
        }

        PhotoMetadata otherMeta = (PhotoMetadata) object;

        return this.width == otherMeta.getWidth() 
            && this.height == otherMeta.getHeight()
            && this.cameraBrand.equals(otherMeta.getCameraBrand())
            && this.cameraModel.equals(otherMeta.getCameraModel())
            && this.dateOfCreation.equals(otherMeta.getDateOfCreation());
    }
}

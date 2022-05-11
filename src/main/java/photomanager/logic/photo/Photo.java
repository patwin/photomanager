package photomanager.logic.photo;


/**
 * @author Patrick Winter
 */


import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Photo extends PhotoObject {
    
    private String dataname;
    private PhotoMetadata metadata;

    private static final long serialVersionUID = -4145083531653430515L;


    public Photo (String name, String dataname, PhotoMetadata metadata) {
        super(name);
        this.dataname = dataname;
        this.metadata = metadata;
    }

    public Photo (String name, String dataname) {
        super(name);
        this.dataname = dataname;
    }


    // GETTER-METHODS
    public String getDataname() {
        return this.dataname;
    }

    public PhotoMetadata getMetadata() {
        return this.metadata;
    }


    // SETTER_METHODS
    public void setMetadata(PhotoMetadata metadata) {
        this.metadata = metadata;
    }


    // METHODS
    public void print() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        System.out.println("\tFotoname: " + this.getName() + "\n"
                    + "\tDateiname: " + this.dataname + "\n"
                    + "\tGröße: " + this.metadata.getWidth() + " x " + this.metadata.getHeight() + "\n"
                    + "\tKamera: " + this.metadata.getCameraBrand() + " " + this.metadata.getCameraModel() + "\n"
                    + "\tErstellungsdatum: " + this.metadata.getDateOfCreation().format(formatter));
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.dataname + " " + this.metadata;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.dataname);
    }

    @Override
    public boolean equals (Object object) {
        if (!super.equals(object)) {
            return false;
        }

        Photo otherPhoto = (Photo) object;

        return this.dataname.equals(otherPhoto.getDataname());
    }
}

package photomanager.logic;

import java.time.format.DateTimeFormatter;

public class Photo {
    
    private String name;
    private String dataname;
    private PhotoMetadata metadata;


    public Photo (String name, String dataname, PhotoMetadata metadata) {
        this.name = name;
        this.dataname = dataname;
        this.metadata = metadata;
    }


    // GETTER-METHODS
    public String getName() {
        return this.name;
    }

    public String getDataname() {
        return this.dataname;
    }

    public PhotoMetadata getMetadata() {
        return this.metadata;
    }


    // METHODS
    public void print() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        System.out.println("\tFotoname: " + this.name + "\n"
                    + "\tDateiname: " + this.dataname + "\n"
                    + "\tGröße: " + this.metadata.getWidth() + " x " + this.metadata.getHeight() + "\n"
                    + "\tKamera: " + this.metadata.getCameraBrand() + " " + this.metadata.getCameraModel() + "\n"
                    + "\tErstellungsdatum: " + this.metadata.getDateOfCreation().format(formatter));
    }

    @Override
    public String toString() {
        return this.name + " " + this.dataname + " " + this.metadata;
    }
}

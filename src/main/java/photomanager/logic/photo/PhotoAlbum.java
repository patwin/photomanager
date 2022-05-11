package photomanager.logic.photo;


/**
 * @author Patrick Winter
 */


import java.util.Set;
import java.util.HashSet;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;


public class PhotoAlbum extends PhotoObject implements Comparable<PhotoAlbum> {
    
    private String owner;
    private Set<Photo> photoAlbum;

    private static final long serialVersionUID = -4145083531653430516L;

    public PhotoAlbum (String name, String owner) {
        super(name);
        this.owner = owner;
        this.photoAlbum = new HashSet<>();
    }


    // GETTER-METHODS
    public String getOwner() {
        return this.owner;
    }

    public Set<Photo> getPhotos() {
        return this.photoAlbum;
    }


    // METHODS
    public void addPhoto (Photo photo) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(new File(photo.getDataname()));
        int width = 0;
        int height = 0;
        String cameraBrand = "";
        String cameraModel = "";
        LocalDateTime dateOfCreation = null;

        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                if (tag.getTagName().equals("Image Width")) {
                    width = Integer.valueOf(tag.getDescription().substring(0, tag.getDescription().indexOf(" ")));
                }

                if (tag.getTagName().equals("Image Height")) {
                    height = Integer.valueOf(tag.getDescription().substring(0, tag.getDescription().indexOf(" ")));
                }

                if (tag.getTagName().equals("Make")) {
                    cameraBrand = tag.getDescription();
                }

                if (tag.getTagName().equals("Model")) {
                    cameraModel = tag.getDescription();
                }

                if (tag.getTagName().equals("Date/Time Original")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
                    dateOfCreation = LocalDateTime.parse(tag.getDescription(), formatter);
                }
            }            
        }
        
        PhotoMetadata photoMetadata = new PhotoMetadata(width, height, cameraBrand, cameraModel, dateOfCreation);
        photo.setMetadata(photoMetadata);
        this.photoAlbum.add(photo);
    }

    public void print() {
        System.out.println("Name: " + this.getName());
        System.out.println("Besitzer: " +this.owner);

        //List<Photo> photoAlbum = this.getPhotos();
        
        for (int i = 0; i < this.photoAlbum.size(); i++) {
            System.out.println("=== Foto " + (i + 1) + " ===");
            //photoAlbum.get(i).print();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }


    @Override
    public int compareTo(PhotoAlbum other) {
        if (this == other) {
            return 0;
        }

        return this.getName().compareTo(other.getName());
    }
}

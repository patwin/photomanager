package photomanager.logic;

/**
 * @author Patrick Winter
 */

import java.util.List;
import java.util.ArrayList;


public class PhotoAlbum {
    
    private String name;
    private String owner;
    private List<Photo> photoAlbum;


    public PhotoAlbum (String name, String owner) {
        this.name = name;
        this.owner = owner;
        this.photoAlbum = new ArrayList<>();
    }


    // GETTER-METHODS
    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }

    public List<Photo> getPhotos() {
        return this.photoAlbum;
    }


    // METHODS
    public void addPhoto (Photo photo) {
        this.photoAlbum.add(photo);
    }

    public void print() {
        System.out.println("Name: " + this.name);
        System.out.println("Besitzer: " +this.owner);
        
        for (int i = 0; i < this.photoAlbum.size(); i++) {
            System.out.println("=== Foto " + (i + 1) + " ===");
            photoAlbum.get(i).print();
        }
    }

    @Override
    public String toString() {
        return this.name + " " + this.owner + " " + this.photoAlbum;
    }

}

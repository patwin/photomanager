package photomanager.logic.photo;

/**
 * @author Patrick Winter
 */

import java.util.Set;
import java.util.HashSet;


public class PhotoAlbum extends PhotoObject implements Comparable<PhotoAlbum> {
    
    private String owner;
    private Set<Photo> photoAlbum;


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
    public void addPhoto (Photo photo) {
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

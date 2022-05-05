package photomanager.logic;

/**
 * @author Patrick Winter
 */

 
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Photomanagement {
    
    private Set<PhotoAlbum> photoAlbums;


    public Photomanagement() {
        this.photoAlbums = new HashSet<>();
    }


    // GETTER-METHODS
    public List<PhotoAlbum> getPhotoAlbums() {
        return new ArrayList<>(this.photoAlbums);
    }


    // METHODS
    public void addAlbum (PhotoAlbum album) {
        this.photoAlbums.add(album);
    }

    public void printAllAlbums() {
        this.photoAlbums.forEach(photoAlbum -> {
            photoAlbum.print();
        });
    }

    public int getNumberOfPhotoAlbums() {
        return this.photoAlbums.size();
    }

    public PhotoAlbum findPhotoAlbumWithName (String name) {
        for (PhotoAlbum photoAlbum : this.photoAlbums) {
            if (photoAlbum.getName().equals(name)) {
                return photoAlbum;
            }
        }

        return null;
    }

}

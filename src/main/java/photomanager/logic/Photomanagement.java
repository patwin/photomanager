package photomanager.logic;

/**
 * @author Patrick Winter
 */

 
import java.util.List;
import java.util.ArrayList;


public class Photomanagement {
    
    private List<PhotoAlbum> photoAlbums;


    public Photomanagement() {
        this.photoAlbums = new ArrayList<>();
    }


    // GETTER-METHODS
    public List<PhotoAlbum> getPhotoAlbums() {
        return this.photoAlbums;
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

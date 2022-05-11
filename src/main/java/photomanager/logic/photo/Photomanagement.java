package photomanager.logic.photo;


/**
 * @author Patrick Winter
 */

 
import java.util.Set;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;


public class Photomanagement {
    
    private Set<PhotoAlbum> photoAlbums;


    public Photomanagement() {
        this.photoAlbums = new HashSet<>();
    }


    // GETTER-METHODS
    public Set<PhotoAlbum> getPhotoAlbums() {
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

    public void save() {
        File file = new File("test.ser");

        try (FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(this.photoAlbums);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        File file = new File("test.ser");

        try (FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.photoAlbums = (HashSet<PhotoAlbum>) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

}

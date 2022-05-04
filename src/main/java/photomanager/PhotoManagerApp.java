package photomanager;

import java.time.LocalDateTime;

import photomanager.logic.Photo;
import photomanager.logic.PhotoAlbum;
import photomanager.logic.PhotoMetadata;
import photomanager.logic.Photomanagement;

/**
 * @author Patrick Winter
 */


public class PhotoManagerApp {
    
    public static void main (String[] args) {
        Photomanagement management = new Photomanagement();
        PhotoAlbum album = new PhotoAlbum("Sommer", "Patrick");
        Photo photo1 = new Photo("Wiese", "images/7861351302_74a45956dd_o.jpg", new PhotoMetadata(1024, 1024, "NIKON CORPORATION", 
            "NIKON D750", LocalDateTime.now()));
        album.addPhoto(photo1);
        management.addAlbum(album);

        management.printAllAlbums();
    }
}

package photomanager;

import java.time.LocalDateTime;

import photomanager.logic.photo.Photo;
import photomanager.logic.photo.PhotoAlbum;
import photomanager.logic.photo.PhotoMetadata;
import photomanager.logic.photo.Photomanagement;

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

        PhotoAlbum album1 = new PhotoAlbum("Sommer", "Patrick");
        Photo photo2 = new Photo("Wiese", "images/7861351302_74a45956dd_o.jpg", new PhotoMetadata(1024, 1024, "NIKON CORPORATION", 
            "NIKON D750", LocalDateTime.now()));
        album1.addPhoto(photo2);
        management.addAlbum(album1);

        management.printAllAlbums();
    }
}

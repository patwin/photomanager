package photomanager.logic.photo;

import java.util.Objects;

/**
 * @author Patrick Winter
 */


import java.util.UUID;


public abstract class PhotoObject {
    
    private final String id;
    private String name;


    public PhotoObject (String name) {
        this.name = name;
        this.id = String.valueOf(UUID.randomUUID());
    }


    // GETTER-METHODS
    public String getName() {
        return this.name;
    }

    public String getID() {
        return this.id;
    }


    // ABSTRACT-METHODS
    public abstract void print();


    // METHODS
    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public boolean equals (Object object) {
        if (!(object instanceof PhotoObject)) {
            return false;
        }

        if (this == object) {
            return true;
        }

        PhotoObject otherObject = (PhotoObject) object;

        return (this.id.equals(otherObject.getID()) && this.name.equals(otherObject.getName()));
    }
}

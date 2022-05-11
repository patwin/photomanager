package photomanager.logic.photo;


/**
 * @author Patrick Winter
 */


import java.io.Serializable;

import java.util.Objects;
import java.util.UUID;


public abstract class PhotoObject implements Serializable {
    
    private final String id;
    private String name;

    private static final long serialVersionUID = -4145083531653430517L;


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

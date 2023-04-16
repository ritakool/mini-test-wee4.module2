package Contact;

import java.io.Serializable;

public class Type implements Serializable {
    private String id;
    private String name;

    public Type(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public Type(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return " id='" + id + '\'' +
                ", name='" + name + '\'';
    }
}

package app.gianglong.tracuudienthoai.Objects;

/**
 * Created by Giang Long on 8/6/2016.
 */

public class FavoriteObject {
    private String id;
    private int type;

    public FavoriteObject(String id, int type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

package app.gianglong.tracuudienthoai.Objects;

/**
 * Created by Giang Long on 7/27/2016.
 */

public class CountObjectProduct  {
    private String id;
    private int count;

    public CountObjectProduct(String id, int count) {
        this.id = id;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

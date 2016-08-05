package app.gianglong.tracuudienthoai.Objects;

import java.io.Serializable;

/**
 * Created by Giang Long on 7/22/2016.
 */
@SuppressWarnings("serial")
public class ProductObject implements Serializable{
    private static final long serialVersionUID = 1L;
    String id, name, company, image, color, price, link;
    int year;
    ParameterObject parameterObject;

    public ProductObject(){

    }

    public ProductObject(String id, String name, String company, String image, int year, ParameterObject parameterObject, String color, String price, String link) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.image = image;
        this.color = color;
        this.price = price;
        this.link = link;
        this.year = year;
        this.parameterObject = parameterObject;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ParameterObject getParameterObject() {
        return parameterObject;
    }

    public void setParameterObject(ParameterObject parameterObject) {
        this.parameterObject = parameterObject;
    }
}

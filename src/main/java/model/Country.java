package model;

public class Country implements  BaseModel{
    private String name;
    private String side;

    public Country(String name, String side) {
        this.name = name;
        this.side = side;
    }

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}

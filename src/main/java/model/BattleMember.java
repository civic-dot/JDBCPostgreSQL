package model;

public class BattleMember implements  BaseModel{
    private int id;
    private String result;
    private String name;
    private String battleName;

    public BattleMember(int id, String result, String name, String battleName) {
        this.id = id;
        this.result = result;
        this.name = name;
        this.battleName = battleName;
    }

    public BattleMember() {
    }

    public BattleMember(String result, String name, String battleName) {
        this.result = result;
        this.name = name;
        this.battleName = battleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBattleName() {
        return battleName;
    }

    public void setBattleName(String battleName) {
        this.battleName = battleName;
    }
}

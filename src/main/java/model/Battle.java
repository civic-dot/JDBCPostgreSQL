package model;

import java.util.Date;

public class Battle implements  BaseModel{
    private String battleName;
    private Date battleDate;

    public Battle(String battleName, Date battleDate) {
        this.battleName = battleName;
        this.battleDate = battleDate;
    }

    public Battle() {
    }

    public String getBattleName() {
        return battleName;
    }

    public void setBattleName(String battleName) {
        this.battleName = battleName;
    }

    public Date getBattleDate() {
        return battleDate;
    }

    public void setBattleDate(Date battleDate) {
        this.battleDate = battleDate;
    }
}

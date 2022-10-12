package model;

import java.util.Date;

public class Warship implements  BaseModel{
    private String name;
    private String clas;
    private Date commissionDate;
    private Date decommissionDate;

    public Warship(String name, String clas, Date commissionDate, Date decommissionDate) {
        this.name = name;
        this.clas = clas;
        this.commissionDate = commissionDate;
        this.decommissionDate = decommissionDate;
    }

    public Warship() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public Date getCommissionDate() {
        return commissionDate;
    }

    public void setCommissionDate(Date commissionDate) {
        this.commissionDate = commissionDate;
    }

    public Date getDecommissionDate() {
        return decommissionDate;
    }

    public void setDecommissionDate(Date decommissionDate) {
        this.decommissionDate = decommissionDate;
    }
}
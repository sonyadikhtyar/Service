package com.example.service;

public class ProductsData {

    private int id;
    private String section;
    private String item;
    private int av;
    private String startp;
    private String finish;
    private String org;
    private String statusp;

    public ProductsData(int id, String section, String item, int av, String start, String finish, String org, String status) {

        this.id = id;
        this.section = section;
        this.item = item;
        this.av = av;
        this.startp = start;
        this.finish = finish;
        this.org = org;
        this.statusp = status;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getAv() {
        return av;
    }

    public void setAv(int av) {
        this.av = av;
    }

    public String getStartp() {
        return startp;
    }

    public void setStartp(String startp) {
        this.startp = startp;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getStatusp() {
        return statusp;
    }

    public void setStatusp(String statusp) {
        this.statusp = statusp;
    }
}

package edu.northeastern.cs5200.models;

public class Priviledge {
    private String priviledge;

    private Page page;
    private Website website;
    private Developer developer;

    public Priviledge() {
    }

    public Priviledge(String priviledge) {
        this.priviledge = priviledge;
    }

    public String getPriviledge() {
        return priviledge;
    }

    public void setPriviledge(String priviledge) {
        this.priviledge = priviledge;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
}

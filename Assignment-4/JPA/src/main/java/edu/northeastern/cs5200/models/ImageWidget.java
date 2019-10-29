package edu.northeastern.cs5200.models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ImageWidget extends Widget{
    @Column(name = "SRC")
    private String src;

    public ImageWidget() {
    }

    public ImageWidget(String src) {
        this.src = src;
    }

    public ImageWidget(String type, int width, int height, Topic topic, String src) {
        super(type, width, height, topic);
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}

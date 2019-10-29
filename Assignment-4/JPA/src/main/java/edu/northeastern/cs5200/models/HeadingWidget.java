package edu.northeastern.cs5200.models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class HeadingWidget extends Widget {
    @Column(name = "SIZE")
    private int size;

    public HeadingWidget() {
    }

    public HeadingWidget(int size) {
        this.size = size;
    }

    public HeadingWidget(String type, int width, int height, Topic topic, int size) {
        super(type, width, height, topic);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

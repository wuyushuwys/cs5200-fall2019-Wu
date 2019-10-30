package edu.northeastern.cs5200.fall2019.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Widgets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Widget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String wtype;
    private int width;
    private int height;
    @ManyToOne
    @JsonIgnore
    private Topic topic;

    public Widget() {
    }

    public Widget(String wtype, int width, int height, Topic topic) {
        this.wtype = wtype;
        this.width = width;
        this.height = height;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return wtype;
    }

    public void setType(String wtype) {
        this.wtype = wtype;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}

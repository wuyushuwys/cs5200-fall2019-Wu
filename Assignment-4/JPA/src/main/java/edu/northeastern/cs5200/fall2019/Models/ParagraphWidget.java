package edu.northeastern.cs5200.fall2019.Models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ParagraphWidget extends Widget {
    @Column(name = "TEXT")
    private String text;

    public ParagraphWidget() {
    }

    public ParagraphWidget(String text) {
        this.text = text;
    }

    public ParagraphWidget(String type, int width, int height, Topic topic, String text) {
        super(type, width, height, topic);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

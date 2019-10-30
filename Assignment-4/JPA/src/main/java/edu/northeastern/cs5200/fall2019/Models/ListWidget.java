package edu.northeastern.cs5200.fall2019.Models;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class ListWidget extends Widget {
    @Column(name = "ITEMS")
    @ElementCollection
    private List<String> items;
    @Column(name = "ORDERED")
    private boolean ordered;

    public ListWidget() {
    }

    public ListWidget(List<String> items, boolean ordered) {
        this.items = items;
        this.ordered = ordered;
    }

    public ListWidget(String type, int width, int height, Topic topic, List<String> items, boolean ordered) {
        super(type, width, height, topic);
        this.items = items;
        this.ordered = ordered;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }
}

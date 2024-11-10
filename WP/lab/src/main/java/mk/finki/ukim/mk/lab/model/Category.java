package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Category {
    private Long Id;
    private String category;

    public Category(String category) {
        this.Id = (long) (Math.random() * 1000);
        this.category = category;
    }

    public boolean equals(Category c) {
        return category.equals(c.category);
    }

    @Override
    public String toString() {
        return category;
    }
}

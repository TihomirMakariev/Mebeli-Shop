package bg.tihomir.mebeli.model.entity;

import bg.tihomir.mebeli.model.enums.CategoryEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bedrooms")
public class Bedroom extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private BigDecimal price;

    private String imageUrl;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryEnum category;

    public Bedroom() {
    }

    public String getName() {
        return name;
    }

    public Bedroom setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Bedroom setColor(String color) {
        this.color = color;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Bedroom setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Bedroom setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public Bedroom setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}

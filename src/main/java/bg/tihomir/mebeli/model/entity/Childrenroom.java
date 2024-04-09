package bg.tihomir.mebeli.model.entity;

import bg.tihomir.mebeli.model.enums.CategoryEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "children_rooms")
public class Childrenroom extends BaseEntity{

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

    public Childrenroom() {
    }

    public String getName() {
        return name;
    }

    public Childrenroom setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Childrenroom setColor(String color) {
        this.color = color;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Childrenroom setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Childrenroom setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public Childrenroom setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}

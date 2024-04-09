package bg.tihomir.mebeli.model.entity;

import bg.tihomir.mebeli.model.enums.CategoryEnum;
import bg.tihomir.mebeli.model.enums.ColorEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "kitchens")
public class KitchenEntity extends BaseEntity{


    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ColorEnum color;
    @Column(nullable = false)
    private BigDecimal price;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryEnum category;

    @ManyToOne
    private UserEntity author;

    public KitchenEntity() {
    }

    public String getName() {
        return name;
    }

    public KitchenEntity setName(String name) {
        this.name = name;
        return this;
    }

    public ColorEnum getColor() {
        return color;
    }

    public KitchenEntity setColor(ColorEnum color) {
        this.color = color;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public KitchenEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public KitchenEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public KitchenEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }


    public UserEntity getAuthor() {
        return author;
    }

    public KitchenEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}

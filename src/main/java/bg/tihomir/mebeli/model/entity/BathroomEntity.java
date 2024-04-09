package bg.tihomir.mebeli.model.entity;

import bg.tihomir.mebeli.model.enums.CategoryEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bathrooms")
public class BathroomEntity extends BaseEntity{

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

    public BathroomEntity() {
    }

    public String getName() {
        return name;
    }

    public BathroomEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public BathroomEntity setColor(String color) {
        this.color = color;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BathroomEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BathroomEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public BathroomEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}

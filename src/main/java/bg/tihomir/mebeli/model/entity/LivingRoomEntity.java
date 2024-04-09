package bg.tihomir.mebeli.model.entity;

import bg.tihomir.mebeli.model.enums.CategoryEnum;
import bg.tihomir.mebeli.model.enums.ColorEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "living_rooms")
public class LivingRoomEntity extends BaseEntity{

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

    public LivingRoomEntity() {
    }

    public String getName() {
        return name;
    }

    public LivingRoomEntity setName(String name) {
        this.name = name;
        return this;
    }

    public ColorEnum getColor() {
        return color;
    }

    public LivingRoomEntity setColor(ColorEnum color) {
        this.color = color;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LivingRoomEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LivingRoomEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public LivingRoomEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public LivingRoomEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    @Override
    public String toString() {
        return "LivingRoomEntity{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", category=" + category +
                ", author=" + author +
                '}';
    }
}

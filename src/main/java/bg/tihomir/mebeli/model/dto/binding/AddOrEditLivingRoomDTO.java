package bg.tihomir.mebeli.model.dto.binding;

import bg.tihomir.mebeli.model.enums.CategoryEnum;
import bg.tihomir.mebeli.model.enums.ColorEnum;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class AddOrEditLivingRoomDTO {


    private Long id;
    @Size(min = 4, max = 20, message = "The name must min 4, max 20")
    @NotNull
    private String name;
    @NotNull
    private ColorEnum color;
    @NotNull
    @DecimalMin(value = "0.00", message = "Price must be greater than or equal to 0")
    @DecimalMax(value = "10000.00", message = "Price must be less than or equal to 1000")
    private BigDecimal price;
    @NotEmpty
    private String imageUrl;
    @NotNull
    private CategoryEnum category;

    public AddOrEditLivingRoomDTO() {
    }

    public String getName() {
        return name;
    }

    public AddOrEditLivingRoomDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ColorEnum getColor() {
        return color;
    }

    public AddOrEditLivingRoomDTO setColor(ColorEnum color) {
        this.color = color;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOrEditLivingRoomDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddOrEditLivingRoomDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public AddOrEditLivingRoomDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AddOrEditLivingRoomDTO setId(Long id) {
        this.id = id;
        return this;
    }



}

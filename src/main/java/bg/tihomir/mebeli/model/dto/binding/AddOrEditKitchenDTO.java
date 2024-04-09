package bg.tihomir.mebeli.model.dto.binding;

import bg.tihomir.mebeli.model.enums.CategoryEnum;
import bg.tihomir.mebeli.model.enums.ColorEnum;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class AddOrEditKitchenDTO {


    private Long id;
    @Size(min = 4, max = 20)
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

    public AddOrEditKitchenDTO() {
    }

    public String getName() {
        return name;
    }

    public AddOrEditKitchenDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ColorEnum getColor() {
        return color;
    }

    public AddOrEditKitchenDTO setColor(ColorEnum color) {
        this.color = color;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOrEditKitchenDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddOrEditKitchenDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public AddOrEditKitchenDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AddOrEditKitchenDTO setId(Long id) {
        this.id = id;
        return this;
    }



}

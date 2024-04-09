package bg.tihomir.mebeli.model.dto.view;

import bg.tihomir.mebeli.model.entity.UserEntity;
import bg.tihomir.mebeli.model.enums.CategoryEnum;
import bg.tihomir.mebeli.model.enums.ColorEnum;

import java.math.BigDecimal;

public class KitchenViewModel {

    private Long id;
    private String name;
    private ColorEnum color;
    private BigDecimal price;
    private String imageUrl;
    private CategoryEnum category;
    private String authorFirstName;
    private String authorLastName;
    private UserEntity author;
    private boolean canDelete;

    public KitchenViewModel() {
    }

    public Long getId() {
        return id;
    }

    public KitchenViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public KitchenViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public ColorEnum getColor() {
        return color;
    }

    public KitchenViewModel setColor(ColorEnum color) {
        this.color = color;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public KitchenViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public KitchenViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public KitchenViewModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public KitchenViewModel setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public KitchenViewModel setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public KitchenViewModel setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public KitchenViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }

    public String getAuthorFullName(){
        return authorFirstName + " " + authorLastName;
    }


}

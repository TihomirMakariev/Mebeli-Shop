package bg.tihomir.mebeli.model.entity;

import bg.tihomir.mebeli.model.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "dimensions")
public class DimensionEntity extends BaseEntity{

    @Column(nullable = false)
    private Integer depth;
    @Column(nullable = false)
    private Integer height;
    @Column(nullable = false)
    private Integer width;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryEnum category;
    @OneToOne
    private LivingRoomEntity livingRoom;
    @OneToOne
    private KitchenEntity kitchen;
    @OneToOne
    private Bedroom bedroom;
    @OneToOne
    private Childrenroom childrenroom;
    @OneToOne
    private BathroomEntity bathroom;

    public DimensionEntity() {
    }

    public Integer getDepth() {
        return depth;
    }

    public DimensionEntity setDepth(Integer depth) {
        this.depth = depth;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public DimensionEntity setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public DimensionEntity setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public DimensionEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public LivingRoomEntity getLivingRoom() {
        return livingRoom;
    }

    public DimensionEntity setLivingRoom(LivingRoomEntity livingRoom) {
        this.livingRoom = livingRoom;
        return this;
    }
}

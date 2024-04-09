package bg.tihomir.mebeli.model.dto.binding;

import bg.tihomir.mebeli.model.enums.ColorEnum;

import java.math.BigDecimal;

public class SearchLivingRoomDTO {

    private ColorEnum color;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;


    public SearchLivingRoomDTO() {
    }

    public ColorEnum getColor() {
        return color;
    }

    public SearchLivingRoomDTO setColor(ColorEnum color) {
        this.color = color;
        return this;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public SearchLivingRoomDTO setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public SearchLivingRoomDTO setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public boolean isEmpty(){
        return  color == null && minPrice == null && maxPrice == null;
    }



    @Override
    public String toString() {
        return "SearchLivingRoomDTO{" +
                "color='" + color + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}


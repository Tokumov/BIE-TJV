package com.example.frontend.modeldto;

public class MenuDto {
    private long id;
    private String name;
    private Long price;
    public MenuDto(){

    }
    public MenuDto(String name, Long price){
        this.name=name;
        this.price=price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}

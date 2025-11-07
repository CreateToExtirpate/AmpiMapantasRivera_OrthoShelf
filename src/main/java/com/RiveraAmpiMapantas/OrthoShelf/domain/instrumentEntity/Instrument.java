package com.RiveraAmpiMapantas.OrthoShelf.domain.instrumentEntity;

import com.RiveraAmpiMapantas.OrthoShelf.domain.dto.InstrumentDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "instruments")
public abstract class Instrument {

    @Id
    @Column(name = "instrument_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "instr_name")
    private String name;

    @Column(columnDefinition = "DECIMAL(10,2)", name = "instr_price")
    private double price;

    @Column(name = "instr_stock_count")
    private int stock;

    @Column(columnDefinition = "TEXT", name = "instr_description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "instr_category")
    private Category category;

    public enum Category {
        BORROWABLE, CONSUMABLE
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public InstrumentDTO toDto() {
        InstrumentDTO dto = new InstrumentDTO();
        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setPrice(this.getPrice());
        dto.setStock(this.getStock());
        dto.setBorrowedStock(0);
        dto.setDescription(this.getDescription());
        dto.setCategory(this.getCategory().toString());
        dto.setType("N/A");
        dto.setSubtype("N/A");
        dto.setUsetype("N/A");
        dto.setSize("N/A");

        return dto;
    }

}

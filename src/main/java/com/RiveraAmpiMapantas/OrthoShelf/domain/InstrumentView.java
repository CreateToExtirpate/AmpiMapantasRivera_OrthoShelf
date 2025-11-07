package com.RiveraAmpiMapantas.OrthoShelf.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="all_instruments_view")
public class InstrumentView {
    
    @Id
    @Column(name="instrument_id")
    private int id;

    @Column(name = "instr_name")
    private String name;

    @Column(columnDefinition = "DECIMAL(10,2)", name = "instr_price")
    private double price;

    @Column(name = "instr_stock_count")
    private int stock;

    @Column(name = "instr_category")
    private String category;

    @Column(name = "sub_type")
    private String subType;

    @Column(name = "borrow_count")
    private String borrowCount;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(String borrowCount) {
        this.borrowCount = borrowCount;
    }
}

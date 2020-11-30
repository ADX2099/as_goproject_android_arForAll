package com.alpha.productscanner.dto;

public class Products {

    int id;
    String   imageName;
    int idbrand;
    String name;
    String description;
    double walmartprice;
    double sorianaprice;
    double superamaprice;
    double chedrauiprice;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getIdbrand() {
        return idbrand;
    }

    public void setIdbrand(int idbrand) {
        this.idbrand = idbrand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWalmartprice() {
        return walmartprice;
    }

    public void setWalmartprice(double walmartprice) {
        this.walmartprice = walmartprice;
    }

    public double getSorianaprice() {
        return sorianaprice;
    }

    public void setSorianaprice(double sorianaprice) {
        this.sorianaprice = sorianaprice;
    }

    public double getSuperamaprice() {
        return superamaprice;
    }

    public void setSuperamaprice(double superamaprice) {
        this.superamaprice = superamaprice;
    }

    public double getChedrauiprice() {
        return chedrauiprice;
    }

    public void setChedrauiprice(double chedrauiprice) {
        this.chedrauiprice = chedrauiprice;
    }
}

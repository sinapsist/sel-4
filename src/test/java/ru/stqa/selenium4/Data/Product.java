package ru.stqa.selenium4.Data;

import java.util.Date;

/**
 * Created by Tester on 3/21/2017.
 */
public class Product {

    private String productName;
    private String productCode;
    private int productId;
    private int productGroup;
    private int quantity;
    private Date validFrom;
    private Date validTo;
    private int manufactureId;
    private String shortDescription;
    private String longDescription;
    private String headTitle;
    private String metaDescription;
    private double purchasePrice;
    private double salesPrice;

    public Product(){
    }

    public Product(String productName, String productCode, int productId, int productGroup, int quantity, Date validFrom, Date validTo,
            int manufactureId, String shortDescription, String longDescription, String headTitle, String metaDescription,
            double purchasePrice, double salesPrice){
        this.productName = productName;
        this.productCode = productCode;
        this.productId = productId;
        this.productGroup = productGroup;
        this.quantity = quantity;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.manufactureId = manufactureId;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.headTitle = headTitle;
        this.metaDescription = metaDescription;
        this.purchasePrice = purchasePrice;
        this.salesPrice = salesPrice;

    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(int productGroup) {
        this.productGroup = productGroup;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public int getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(int manufactureId) {
        this.manufactureId = manufactureId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getHeadTitle() {
        return headTitle;
    }

    public void setHeadTitle(String headTitle) {
        this.headTitle = headTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

}

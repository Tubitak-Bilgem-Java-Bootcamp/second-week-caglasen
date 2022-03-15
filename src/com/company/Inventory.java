package com.company;

public class Inventory {
    private boolean foodCon;
    private String food;
    private boolean firewoodCon;
    private String firewood;
    private boolean waterCon;
    private String water;
    private boolean bonusArmorCon;
    private String bonusArmor;

    public Inventory() {
        this.foodCon = false;
        this.food = "None";
        this.firewoodCon = false;
        this.firewood = "None";
        this.waterCon = false;
        this.water = "None";
        this.bonusArmorCon = false;
        this.bonusArmor = "None";
    }

    public boolean getFoodCon() {
        return foodCon;
    }

    public void setFoodCon(boolean foodCon) {
        this.foodCon = foodCon;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public boolean getFirewoodCon() {
        return firewoodCon;
    }

    public void setFirewoodCon(boolean firewoodCon) {
        this.firewoodCon = firewoodCon;
    }

    public String getFirewood() {
        return firewood;
    }

    public void setFirewood(String firewood) {
        this.firewood = firewood;
    }

    public boolean getWaterCon() {
        return waterCon;
    }

    public void setWaterCon(boolean waterCon) {
        this.waterCon = waterCon;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public boolean getBonusArmorCon() {
        return bonusArmorCon;
    }

    public void setBonusArmorCon(boolean bonusArmorCon) {
        this.bonusArmorCon = bonusArmorCon;
    }

    public String getBonusArmor() {
        return bonusArmor;
    }

    public void setBonusArmor(String bonusArmor) {
        this.bonusArmor = bonusArmor;
    }
}
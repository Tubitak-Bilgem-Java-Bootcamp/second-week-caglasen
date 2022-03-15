package com.company;

public class Armor {
    private String name;
    private int id;
    private int shield;
    private int price;

    public Armor(String name, int id, int shield, int price) {
        this.name = name;
        this.id = id;
        this.shield = shield;
        this.price = price;
    }

    public static void showArmors() {
        Armor[] armorList = new Armor[3];

        armorList[0] = new Armor("Light", 1, 1, 15);
        armorList[1] = new Armor("Medium", 2, 3, 25);
        armorList[2] = new Armor("Heavy", 3, 5, 40);

        for (Armor armor:armorList) {
            System.out.println(armor.getName() +
                    "\tID: " + armor.getId() +
                    "\tShield: " + armor.getShield() +
                    "\tPrice" + armor.getPrice());
        }
    }

    public static Armor selectArmor(int id) {
        Armor[] armorList = new Armor[3];

        armorList[0] = new Armor("Light", 1, 1, 15);
        armorList[1] = new Armor("Medium", 2, 3, 25);
        armorList[2] = new Armor("Heavy", 3, 5, 40);

        for (Armor armor:armorList) {
            if (armor.getId() == id) {
                return armor;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
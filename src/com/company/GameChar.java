package com.company;

public abstract class GameChar {
    private String name;
    private int id;
    private int damage;
    private int health;
    private int wealth;

    public GameChar(String name, int id, int damage, int health, int wealth) {
        this.name = name;
        this.id = id;
        this.damage = damage;
        this.health = health;
        this.wealth = wealth;
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getWealth() {
        return wealth;
    }

    public void setWealth(int wealth) {
        this.wealth = wealth;
    }
}
package com.company;

import java.util.Random;

public abstract class Obstacle {
    private String name;
    private int id;
    private int damage;
    private int health;
    private int award;


    public Obstacle(String name, int id, int damage, int health, int award) {
        this.name = name;
        this.id = id;
        this.damage = damage;
        this.health = health;
        this.award = award;
    }

    public static int obstacleNumber(int id) {
        int obstacleNumber;
        Random random = new Random();
        if (id == 4) {
            obstacleNumber = random.nextInt(5) + 1;
            System.out.println("Number of Obstacle: " + obstacleNumber);
        } else {
            obstacleNumber = random.nextInt(3) + 1;
            System.out.println("Number of Obstacle: " + obstacleNumber);
        }

        return obstacleNumber;
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

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }
}
package com.company;

import java.util.Scanner;

public abstract class BattleLoc extends Location {
    Scanner input = new Scanner(System.in);

    private Obstacle obstacle;
    private String name;
    private String inventory;

    public BattleLoc(Player player, String name,Obstacle o, String inventory) {
        super(player);
        this.name = name;
        this.obstacle = o;
        this.inventory = inventory;
    }

    @Override
    public boolean onLocation() {
        if (this.getName().equals("Forest")) {
            if (this.getPlayer().getInventory().getFirewoodCon()) {
                System.out.println("You've already passed " + this.getName() + ". You can not enter here !");
                return true;
            }
        } else if (this.getName().equals("Cave")) {
            if (this.getPlayer().getInventory().getFoodCon()) {
                System.out.println("You've already passed " + this.getName() + ". You can not enter here !");
                return true;
            }
        } else if (this.getName().equals("River")) {
            if (this.getPlayer().getInventory().getWaterCon()) {
                System.out.println("You've already passed " + this.getName() + ". You can not enter here !");
                return true;
            }
        }

        System.out.println("You are in " + this.getName());
        System.out.println("Hey, be careful and sure of being well-prepared. Here is " + this.getObstacle().getName() + "'s place !");
        if (!fightOrLeaveChoice()) {
            return false;
        }
        System.out.println();
        System.out.println("You are on the main menu. You can make a choice up to where you would like to enter.");
        return true;
    }

    public boolean fightOrLeaveChoice() {
        System.out.println("Fight or Leave?\n\nPress 1 to fight or Press 0 to leave");
        int whatToDo = input.nextInt();

        switch (whatToDo) {
            case 0:
                System.out.println("You've left the " + this.getName());
                break;
            case 1:
                System.out.println("Attack time!");
                return combat();
            default:
                System.out.println( this.getPlayer().getUsername() + ", what you'll do now?");
                fightOrLeaveChoice();
                break;
        }

        return true;
    }

    public boolean combat() {
        int numObstacle = Obstacle.obstacleNumber(this.getObstacle().getId());
        System.out.println(numObstacle + " " + this.getObstacle().getName() +"s are present in the " + this.getName());

        String keyboard;
        int obstclRemDamage, playerRemDamage;
        int defaultObstclHealth = this.getObstacle().getHealth();

        if (this.getName().equals("Mine")) {
            int[] snakeDamageArr = {3, 4, 5, 6};
            int idx = (int)(Math.random() * 4);
            this.getObstacle().setDamage(snakeDamageArr[idx]);
            System.out.println("Snake Damage: " + this.getObstacle().getDamage());
        }


        int whichSideStart = (int) Math.round( Math.random()); // returns 0 or 1 randomly

        if (whichSideStart == 1) {
            System.out.println("You're starting the round.");
        } else {
            System.out.println(this.getObstacle().getName() + " is starting the round.");
        }

        boolean exitLoop = false;

        for (int i=0; i<numObstacle; i++) {
            while(0 < this.getPlayer().getHealth() && 0 < this.getObstacle().getHealth()) {
                if(whichSideStart == 1) { // player starts first
                    System.out.println("Enter A to attack, press R to run.");
                    keyboard = input.next().toUpperCase();

                    if (keyboard.equals("A")) {
                        System.out.println("You've attacked.");
                        obstclRemDamage = this.getObstacle().getHealth() - this.getPlayer().getDamage();
                        this.getObstacle().setHealth(obstclRemDamage);
                    }

                    System.out.println("If you want to run press R.");
                    keyboard = input.next().toUpperCase();
                    if (keyboard.equals("R")) {
                        exitLoop = true;
                        break;
                    }

                    if (0 < this.getObstacle().getHealth()) { // if obstacle dies
                        System.out.println(this.getObstacle().getName() + " has attacked you. It's your turn now.");
                        playerRemDamage = this.getPlayer().getHealth() - this.getObstacle().getDamage();
                        this.getPlayer().setHealth(playerRemDamage);
                    }

                } else { // obstacle starts first
                    if (0 < this.getPlayer().getHealth()) { // if player dies
                        playerRemDamage = this.getPlayer().getHealth() - this.getObstacle().getDamage();
                        this.getPlayer().setHealth(playerRemDamage);
                        System.out.println(this.getObstacle().getName() + " has attacked you. It's your turn now.");

                        System.out.println("Enter A to attack, press R to run.");
                        keyboard = input.next().toUpperCase();
                        if (keyboard.equals("A")) {
                            obstclRemDamage = this.getObstacle().getHealth() - this.getPlayer().getDamage();
                            this.getObstacle().setHealth(obstclRemDamage);
                        }
                        if (keyboard.equals("R")) {
                            exitLoop = true;
                            break;
                        }

                    }
                }

                if (this.getObstacle().getHealth() <= 0) {
                    System.out.println("Your Health: " + this.getPlayer().getHealth() + " " + this.getObstacle().getName() + " Health: 0");
                } else {
                    if (this.getPlayer().getHealth() <= 0) {
                        System.out.println("Your Health: 0 " + this.getObstacle().getName() + " Health: " + this.getObstacle().getHealth());
                    } else {
                        System.out.println("Your Health: " + this.getPlayer().getHealth() + " " + this.getObstacle().getName() + " Health: " + this.getObstacle().getHealth());
                    }

                }

            }

            if(exitLoop) {
                break;
            }

            if (this.getObstacle().getHealth() <= 0) {
                System.out.println("You've killed " + (i+1) + ". " + this.getObstacle().getName());
                setWealthLoc();
                System.out.println("Your Health: " + this.getPlayer().getHealth() + " Your Wealth: " + this.getPlayer().getWealth());
                System.out.println();
                this.getObstacle().setHealth(defaultObstclHealth);
            }
        }

        if (exitLoop) {
            return true;
        }

        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("You've died...");
            return false;
        }

        System.out.println("You've killed all " + this.getObstacle().getName() + "s");

        if (this.getName().equals("Forest")) {
            this.getPlayer().getInventory().setFirewoodCon(true);
            this.getPlayer().getInventory().setFirewood("Firewood(acquired)");

        } else if (this.getName().equals("Cave")) {
            this.getPlayer().getInventory().setFoodCon(true);
            this.getPlayer().getInventory().setFood("Food(acquired)");

        } else if (this.getName().equals("River")) {
            this.getPlayer().getInventory().setWaterCon(true);
            this.getPlayer().getInventory().setWater("Water(acquired)");
        }

        System.out.println("Character: " + this.getPlayer().getName() + "\tWeapon: " + this.getPlayer().getWeaponName() +
                "\tArmor: " + this.getPlayer().getArmorName() + "\tDamage: " + this.getPlayer().getDamage() +
                "\tHealth: " + this.getPlayer().getHealth() + "\tWealth: " + this.getPlayer().getWealth() +
                "\t\tInventory: food-" + (this.getPlayer().getInventory().getFood()) +
                " water-" + (this.getPlayer().getInventory().getWater()) + " firewood-" +
                (this.getPlayer().getInventory().getFirewood()) +
                "Bonus Armor: " + (this.getPlayer().getInventory().getBonusArmor()));


        return true;
    }

    public void setWealthLoc () {
        if (this.getObstacle().getId() == 4) {          // if the obstacle is a snake player can win some bonus items
            double chanceMajor = Math.random();
            if (chanceMajor < 0.15) {
                System.out.println("You've got a chance to get a weapon.");
                double chanceMinor = Math.random();

                String weaponList = this.getPlayer().getWeaponName() + ",";

                if (chanceMinor < 0.2) {
                    System.out.println("You've got a chance to get a rifle.");
                    this.getPlayer().setWeaponName(weaponList + "Rifle");
                    int totalDamage = this.getPlayer().getDamage() + Weapon.selectWeapon(3).getDamage();
                    this.getPlayer().setDamage(totalDamage);

                } else if (chanceMinor < 0.5) {
                    System.out.println("You've got a chance to get a sword.");
                    this.getPlayer().setWeaponName(weaponList + "Sword");
                    int totalDamage = this.getPlayer().getDamage() + Weapon.selectWeapon(2).getDamage();
                    this.getPlayer().setDamage(totalDamage);

                } else {
                    System.out.println("You've got a chance to get a pistol.");
                    this.getPlayer().setWeaponName(weaponList + "Pistol");
                    int totalDamage = this.getPlayer().getDamage() + Weapon.selectWeapon(1).getDamage();
                    this.getPlayer().setDamage(totalDamage);
                }

            } else if (chanceMajor < 0.30) {                                                // chance of ARMOR
                System.out.println("You've got a chance to get an armor.");
                double chanceMinor = Math.random();

                if (chanceMinor < 0.2) {
                    System.out.println("You've got a chance to get a light armor.");
                    this.getPlayer().setArmorName("Light");
                    int totalHealth = this.getPlayer().getHealth() + Armor.selectArmor(1).getShield();
                    this.getPlayer().setHealth(totalHealth);
                    this.getPlayer().getInventory().setBonusArmor("Acquired (Light)");
                    this.getPlayer().getInventory().setBonusArmorCon(true);

                } else if (chanceMinor < 0.5) {
                    System.out.println("You've got a chance to get a medium armor.");
                    this.getPlayer().setArmorName("Medium");
                    int totalHealth = this.getPlayer().getHealth() + Armor.selectArmor(2).getShield();
                    this.getPlayer().setHealth(totalHealth);
                    this.getPlayer().getInventory().setBonusArmor("Acquired (Medium)");
                    this.getPlayer().getInventory().setBonusArmorCon(true);

                } else {
                    System.out.println("You've got a chance to get a heavy armor.");
                    this.getPlayer().setArmorName("Heavy");
                    int totalHealth = this.getPlayer().getHealth() + Armor.selectArmor(3).getShield();
                    this.getPlayer().setHealth(totalHealth);
                    this.getPlayer().getInventory().setBonusArmor("Acquired (Heavy)");
                    this.getPlayer().getInventory().setBonusArmorCon(true);
                }

            } else if (chanceMajor < 0.55) {                                              // chance of AWARD
                System.out.println("You've got a chance to get an award.");
                double chanceMinor = Math.random();
                if (chanceMinor < 0.2) {
                    System.out.println("You've got a chance to get an award 10.");
                    int totalWealth = this.getPlayer().getWealth() + 10;
                    this.getPlayer().setWealth(totalWealth);

                } else if (chanceMinor < 0.5) {
                    System.out.println("You've got a chance to get an award 5.");
                    int totalWealth = this.getPlayer().getWealth() + 5;
                    this.getPlayer().setWealth(totalWealth);

                } else {
                    System.out.println("You've got a chance to get an award 1.");
                    int totalWealth = this.getPlayer().getWealth() + 1;
                    this.getPlayer().setWealth(totalWealth);
                }

            } else {
                System.out.println("You've won nothing :( ");
            }

        } else {
            int finalWealth = this.getPlayer().getWealth() + this.getObstacle().getAward();
            this.getPlayer().setWealth(finalWealth);
        }
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }
}
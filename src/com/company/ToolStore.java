package com.company;

import java.util.Scanner;

public class ToolStore extends NormalLoc {
    Scanner input = new Scanner(System.in);

    public ToolStore(Player player) {
        super(player);
    }

    @Override
    public boolean onLocation() {
        System.out.println();
        System.out.println("Welcome To Tool Store".toUpperCase() );
        System.out.println("Welcome  " + this.getPlayer().getUsername() + "What would you like to purchase?");
        System.out.println();
        System.out.println("---------- Weapons ------------");
        Weapon.showWeapons();
        System.out.println();
        System.out.println("---------- Armors ------------");
        Armor.showArmors();
        System.out.println();
        System.out.println("Your wealth: " + this.getPlayer().getWealth());
        System.out.println();
        selection();
        System.out.println();

        System.out.println("Character: " + this.getPlayer().getName() + "\tWeapon: " + this.getPlayer().getWeaponName() +
                "\tArmor: " + this.getPlayer().getArmorName() + "\tDamage: " + this.getPlayer().getDamage() +
                "\tHealth: " + this.getPlayer().getHealth() + "\tWealth: " + this.getPlayer().getWealth() +
                "\t\tInventory: food-" + (this.getPlayer().getInventory().getFood()) +
                " water-" + (this.getPlayer().getInventory().getWater()) +
                " firewood-" + (this.getPlayer().getInventory().getFirewood()));

        System.out.println();
        System.out.println("See you soon " + this.getPlayer().getUsername() + ". I wish you have a nice day...");

        return true;
    }

    public void selection() {
        System.out.println("You can purchase a weapon or an armor.");
        System.out.println("If you would like to purchase a weapon, please press 1.");
        System.out.println("If you would like to purchase an armor, please press 2.");
        System.out.println("If you would like to leave the tool store, please press 0.");
        System.out.print("What do you prefer? : ");
        int selectButton = input.nextInt();
        System.out.println();

        switch (selectButton) {
            case 1:
                buyWeapon();
                selection();
                break;
            case 2:
                buyArmor();
                selection();
                break;
            case 0:
                break;
            default:
                System.out.println("It is not an option  " + this.getPlayer().getUsername() + ". Try Again!");
                System.out.println();
                selection();
        }
    }

    public void buyWeapon() {
        System.out.println("If you would like to purchase a weapon, please select one of them...");
        System.out.println("If you like to quit, you can press the number 0");
        System.out.print("Your prefer: ");
        int slctWeapon = input.nextInt();
        System.out.println();
        Weapon weapon = Weapon.selectWeapon(slctWeapon);
        int newDamage = 0, rem = 0;
        String weaponList = this.getPlayer().getWeaponName() + ",";
        System.out.println(weaponList);

        switch (slctWeapon) {
            case 1:
                System.out.println("You've selected a pistol!");
                if (weapon.getPrice() > this.getPlayer().getWealth()) {
                    System.out.println("You can not afford to purchase a pistol !");
                    buyWeapon();
                } else {
                    System.out.println("You've purchased a pistol.");
                    buyWeaponSubActions(weaponList, weapon, newDamage, rem);
                }
                break;
            case 2:
                System.out.println("You've selected a sword!");
                if (weapon.getPrice() > this.getPlayer().getWealth()) {
                    System.out.println("You can not afford to purchase a sword !");
                    buyWeapon();
                } else {
                    System.out.println("You've purchased a sword.");
                    buyWeaponSubActions(weaponList, weapon, newDamage, rem);
                }
                break;
            case 3:
                System.out.println("You've selected a rifle!");
                if (weapon.getPrice() > this.getPlayer().getWealth()) {
                    System.out.println("You can not afford to purchase a rifle !");
                    buyWeapon();
                } else {
                    System.out.println("You've purchased a rifle.");
                    buyWeaponSubActions(weaponList, weapon, newDamage, rem);
                }
                break;
            case 0:
                break;
            default:
                System.out.println("This is not an option.  " + this.getPlayer().getUsername() + ". Try Again!");
                System.out.println();
                buyWeapon();
        }
    }

    public void buyWeaponSubActions(String weaponList, Weapon weapon, int newDamage, int rem){
        this.getPlayer().setWeaponName(weaponList + weapon.getName());
        newDamage = this.getPlayer().getDamage() + weapon.getDamage();
        this.getPlayer().setDamage(newDamage);
        System.out.println("Your Damage: " + this.getPlayer().getDamage());
        rem = this.getPlayer().getWealth() - weapon.getPrice();
        this.getPlayer().setWealth(rem);
        System.out.println("Your remaining  wealth: " + this.getPlayer().getWealth());
        this.getPlayer().setWeaponName(weapon.getName());
        buyWeapon();
    }

    public void buyArmorSubActions(Armor armor, int newHealth, int rem){
        this.getPlayer().setArmorName(armor.getName());
        newHealth = this.getPlayer().getHealth() + armor.getShield();
        this.getPlayer().setHealth(newHealth);
        System.out.println("Your Health: " + this.getPlayer().getHealth());
        rem = this.getPlayer().getWealth() - armor.getPrice();
        this.getPlayer().setWealth(rem);
        System.out.println("Your remaining  wealth: " + this.getPlayer().getWealth());
        buyArmor();
    }

    public void buyArmor() {
        System.out.println("If you would like to purchase a armor, please select one of them...");
        System.out.println("If you like to quit, you can press the number 0");
        System.out.print("Your prefer: ");
        int slctArmor = input.nextInt();
        System.out.println();
        Armor armor = Armor.selectArmor(slctArmor);
        int newHealth = 0, rem = 0;

        switch (slctArmor) {
            case 1:
                System.out.println("You've selected a light armor!");
                if (armor.getPrice() > this.getPlayer().getWealth()) {
                    System.out.println("You can not afford to purchase a light armor !");
                    buyArmor();
                } else {
                    System.out.println("You've purchased a light armor.");
                    buyArmorSubActions(armor, newHealth, rem);
                }
                break;
            case 2:
                System.out.println("You've selected a medium armor!");
                if (armor.getPrice() > this.getPlayer().getWealth()) {
                    System.out.println("You can not afford to purchase a sword !");
                    buyArmor();
                } else {
                    System.out.println("You've purchased a medium armor.");
                    buyArmorSubActions(armor, newHealth, rem);
                }
                break;
            case 3:
                System.out.println("You've selected a heavy armor!");
                if (armor.getPrice() > this.getPlayer().getWealth()) {
                    System.out.println("You can not afford to purchase a heavy armor !");
                    buyArmor();
                } else {
                    System.out.println("You've purchased a heavy armor.");
                    buyArmorSubActions(armor, newHealth, rem);
                }
                break;
            case 0:
                break;
            default:
                System.out.println("We don't have this kind of a armor " + this.getPlayer().getUsername() + ". Please, Try Again!");
                System.out.println();
                buyArmor();
        }
    }

}
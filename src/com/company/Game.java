package com.company;

import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome To Adventure Game");
        System.out.print("Please enter a user name: ");
        String playerName = input.nextLine();

        Player player = new Player(playerName);
        System.out.println("Welcome  " + player.getUsername());

        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};

        System.out.println("**************************************");

        for(GameChar character:charList) {
            System.out.println("Character: " + character.getName() +
                    "\tID: " + character.getId() +
                    "\tDamage: " + character.getDamage() +
                    "\tHealth: " + character.getHealth() +
                    "\tWealth: " + character.getWealth());
        }

        System.out.println("**************************************");

        System.out.print("Enter your character id: ");
        int id = input.nextInt();
        System.out.println();
        System.out.println("You've become a " + charList[id-1].getName());
        System.out.println("Your skills are here:");
        player.selectChar(id);


        while (true) {
            System.out.println();
            System.out.println("------------Places to Go-------------");
            System.out.println("1- Safe House");
            System.out.println("2- Tool Store");
            System.out.println("3- Forest");
            System.out.println("4- Cave");
            System.out.println("5- River");
            System.out.println("6- Mine");

            System.out.print("Please, select a place: ");
            int locNum = input.nextInt();
            Location loc = null;
            boolean isQuit = false;

            switch (locNum) {
                case 0:
                    isQuit = true;
                    break;
                case 1:
                    loc = new SafeHouse(player);
                    break;
                case 2:
                    loc = new ToolStore(player);
                    break;
                case 3:
                    loc = new Forest(player);
                    break;
                case 4:
                    loc = new Cave(player);
                    break;
                case 5:
                    loc = new River(player);
                    break;
                case 6:
                    loc = new Mine(player);
            }

            if (isQuit) {
                System.out.println("See you soon");
                break;
            }

            if (!loc.onLocation()) {
                System.out.println("GAME OVER");
                break;
            }

            boolean forestCon = player.getInventory().getFirewoodCon();
            boolean caveCon = player.getInventory().getFoodCon();
            boolean riverCon = player.getInventory().getWaterCon();

            if (forestCon && caveCon && riverCon) {
                System.out.println("YOU WIN !");
                break;
            }




        }
    }
}
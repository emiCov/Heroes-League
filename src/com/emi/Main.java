package com.emi;

import java.io.*;
import java.util.*;

public class Main {
    private static Map<Character, Terrain> terrainType = new HashMap<>();
    private static List<Hero> heroes = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("input.txt")));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        terrainType.put('L', Terrain.LAND);
        terrainType.put('W', Terrain.WOODS);
        terrainType.put('V', Terrain.VOLCANIC);
        terrainType.put('D', Terrain.DESERT);

        int length = scanner.nextInt();
        int height = scanner.nextInt();

        MyMap myMap = MyMap.getInstance();

        for (int i = 0; i < height; i++) {
            String line = scanner.next();
            for (int j = 0; j < length; j++) {
                myMap.addElements(i + "," + j, terrainType.get(line.charAt(j)));
            }
        }

        int noOfHeroes = scanner.nextInt();

        for (int i = 0; i < noOfHeroes; i++) {
            String type = scanner.next();
            heroes.add(HeroFactory.createHero(
                    type,
                    type + " " + (i + 1),
                    scanner.nextInt(),
                    scanner.nextInt())
            );
        }

        int noOfRounds = scanner.nextInt();

        while (Hero.getCurrentRound() <= noOfRounds) {
            String moves = scanner.next();
            for (int i = 0; i < heroes.size(); i++) {
                heroes.get(i).moveHero(moves.charAt(i));
            }

            for (Hero hero : heroes)
                hero.applyDamageOverTime();

            for (int i = 0; i < heroes.size() - 1; i++) {
                if (heroes.get(i).isDead())
                    continue;
                for (int j = i + 1; j < heroes.size(); j++) {
                    if (heroes.get(j).isDead())
                        continue;
                    if (heroes.get(i).getPositionX() == heroes.get(j).getPositionX() &&
                            heroes.get(i).getPositionY() == heroes.get(j).getPositionY()) {

                        Terrain terrain = myMap.getTerrain(heroes.get(i).getPositionX(), heroes.get(i).getPositionY());

                        heroes.get(i).defend(heroes.get(j), terrain);
                        heroes.get(j).defend(heroes.get(i), terrain);

                        if (heroes.get(i).getHitPoints() == 0) {
                            heroes.get(j).increaseXP(heroes.get(j).getLevel(), heroes.get(i).getLevel());
                            heroes.get(j).levelUp();
                        }
                        if (heroes.get(j).getHitPoints() == 0) {
                            heroes.get(i).increaseXP(heroes.get(i).getLevel(), heroes.get(j).getLevel());
                            heroes.get(i).levelUp();
                        }
                    }
                }
            }
            Hero.goToTheNextRound();
        }


        for (Hero hero : heroes) {
            if (hero.isDead())
                writer.write(hero.getName() + " dead\n");
            else
                writer.write(hero.getName() + " " + hero.getLevel() + " " +
                        hero.getXP() + " " + hero.getHitPoints() + " " +
                        hero.getPositionX() + " " + hero.getPositionY() + "\n");
        }

        writer.close();
    }
}

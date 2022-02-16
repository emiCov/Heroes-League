package com.emi;

public class HeroFactory {

    public static Hero createHero(String heroType, String name, int positionX, int positionY) {
        switch (heroType) {
            case "K": return new Knight(name, 900, positionX, positionY);
            case "P": return new Pyromancer(name, 500, positionX, positionY);
            case "W": return new Wizard(name, 400, positionX, positionY);
            case "R": return new Rogue(name, 600, positionX, positionY);
        }
        throw new IllegalArgumentException("The hero type " + heroType + " is not recognized.");
    }
}

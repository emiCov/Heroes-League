package com.emi;

public class Rogue extends Hero {
    public Rogue(String name, int hitPoints, int positionX, int positionY) {
        super(name, hitPoints, positionX, positionY);
    }

    @Override
    public void firstAbility(Knight knightHero, Terrain terrain) {

    }

    @Override
    public void firstAbility(Pyromancer pyromancerHero, Terrain terrain) {

    }

    @Override
    public void firstAbility(Wizard wizardHero, Terrain terrain) {

    }

    @Override
    public void firstAbility(Rogue rogueHero, Terrain terrain) {

    }

    @Override
    public void secondAbility(Knight knightHero, Terrain terrain) {

    }

    @Override
    public void secondAbility(Pyromancer pyromancerHero, Terrain terrain) {

    }

    @Override
    public void secondAbility(Wizard wizardHero, Terrain terrain) {

    }

    @Override
    public void secondAbility(Rogue rogueHero, Terrain terrain) {

    }

    @Override
    public void defend(Hero hero, Terrain terrain) {
        hero.firstAbility(this, terrain);
        hero.secondAbility(this, terrain);
    }
}

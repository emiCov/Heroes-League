package com.emi;

public class Pyromancer extends Hero {
    public Pyromancer(String name, int hitPoints, int positionX, int positionY) {
        super(name, hitPoints, positionX, positionY);
    }

    // Fireblast

    @Override
    public void firstAbility(Knight knightHero, Terrain terrain) {
        float damage = 350f + 50 * this.getLevel(); // apply the level modifier

        if (terrain == Terrain.VOLCANIC)
            damage *= 1.25f;                         // apply the terrain modifier

        damage *= 1.2f;                              // apply the race modifier

        knightHero.takeDamage(Math.round(damage));
    }

    @Override
    public void firstAbility(Pyromancer pyromancerHero, Terrain terrain) {
        float damage = 350f + 50 * this.getLevel(); // apply the level modifier

        if (terrain == Terrain.VOLCANIC)
            damage *= 1.25f;                         // apply the terrain modifier

        damage *= 0.9f;                              // apply the race modifier

        pyromancerHero.takeDamage(Math.round(damage));
    }

    @Override
    public void firstAbility(Wizard wizardHero, Terrain terrain) {
        float damage = 350f + 50 * this.getLevel(); // apply the level modifier
        float wizardDeflectDamage;

        if (terrain == Terrain.VOLCANIC)
            damage *= 1.25f;                         // apply the terrain modifier

        // calculate the wizard's deflect ability
        wizardDeflectDamage = damage * 1.3f * Math.min(     // 30% against pyromancer
                (35f + 2 * wizardHero.getLevel()) / 100,
                70f / 100);

        damage *= 1.05f;                             // apply the race modifier

        wizardHero.takeDamage(Math.round(damage));

        this.takeDamage(Math.round(wizardDeflectDamage));    // wizard's deflect ability
    }

    @Override
    public void firstAbility(Rogue rogueHero, Terrain terrain) {
        float damage = 350f + 50 * this.getLevel(); // apply the level modifier

        if (terrain == Terrain.VOLCANIC)
            damage *= 1.25f;                         // apply the terrain modifier

        damage *= 0.8f;                              // apply the race modifier

        rogueHero.takeDamage(Math.round(damage));
    }

    // Ignite

    @Override
    public void secondAbility(Knight knightHero, Terrain terrain) {
        float damage = 150f + 20 * this.getLevel(); // apply the level modifier

        if (terrain == Terrain.VOLCANIC)
            damage *= 1.25f;                         // apply the terrain modifier

        damage *= 1.2f;                              // apply the race modifier

        knightHero.takeDamage(Math.round(damage));
        knightHero.ignite(
                Math.round((50 + 30 * this.getLevel()) * 1.2f),
                Hero.getCurrentRound() + 2);               // ignite the opponent
    }

    @Override
    public void secondAbility(Pyromancer pyromancerHero, Terrain terrain) {
        float damage = 150f + 20 * this.getLevel(); // apply the level modifier

        if (terrain == Terrain.VOLCANIC)
            damage *= 1.25f;                         // apply the terrain modifier

        damage *= 0.9f;                              // apply the race modifier

        pyromancerHero.takeDamage(Math.round(damage));
        pyromancerHero.ignite(
                Math.round((50 + 30 * this.getLevel()) * 0.9f),
                Hero.getCurrentRound() + 2);                // ignite the opponent
    }

    @Override
    public void secondAbility(Wizard wizardHero, Terrain terrain) {
        float damage = 150f + 20 * this.getLevel(); // apply the level modifier
        float wizardDeflectDamage;

        if (terrain == Terrain.VOLCANIC)
            damage *= 1.25f;                         // apply the terrain modifier

        // calculate the wizard's deflect ability
        wizardDeflectDamage = damage * 1.3f * Math.min(     // 30% against pyromancer
                (35f + 2 * wizardHero.getLevel()) / 100,
                70f / 100);

        damage *= 1.05f;                             // apply the race modifier

        wizardHero.takeDamage(Math.round(damage));
        wizardHero.ignite(
                Math.round((50 + 30 * this.getLevel()) * 1.05f),
                Hero.getCurrentRound() + 2);               // ignite the opponent

        this.takeDamage(Math.round(wizardDeflectDamage));    // wizard's deflect ability
    }

    @Override
    public void secondAbility(Rogue rogueHero, Terrain terrain) {
        float damage = 150f + 20 * this.getLevel(); // apply the level modifier

        if (terrain == Terrain.VOLCANIC)
            damage *= 1.25f;                         // apply the terrain modifier

        damage *= 0.8f;                              // apply the race modifier

        rogueHero.takeDamage(Math.round(damage));
        rogueHero.ignite(
                Math.round((50 + 30 * this.getLevel()) * 0.8f),
                Hero.getCurrentRound() + 2);               // ignite the opponent
    }

    @Override
    public void defend(Hero hero, Terrain terrain) {
        hero.firstAbility(this, terrain);
        hero.secondAbility(this, terrain);
    }


}

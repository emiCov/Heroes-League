package com.emi;

public class Knight extends Hero {

    public Knight(String name, int hitPoints, int positionX, int positionY) {
        super(name, hitPoints, positionX, positionY);
    }

    // Execute

    @Override
    public void firstAbility(Knight knightHero, Terrain terrain) {
        float damage;

        // calculate the limit below which the opponent is instantly killed
        float hPVictimLimit = ((20f + this.getLevel()) / 100) *
                (900 + 80 * knightHero.getLevel());
        if (knightHero.getHitPoints() < hPVictimLimit)
            damage = knightHero.getHitPoints();
        else
            damage = 200f + 30 * this.getLevel();   // apply the level modifier

        if (terrain == Terrain.LAND)
            damage *= 1.15;                         // apply the terrain modifier

        knightHero.takeDamage(Math.round(damage));
    }

    @Override
    public void firstAbility(Pyromancer pyromancerHero, Terrain terrain) {
        float damage;

        // calculate the limit below which the opponent is instantly killed
        float hPVictimLimit = ((20f + this.getLevel()) / 100) *
                (500 + 50 * pyromancerHero.getLevel());
        if (pyromancerHero.getHitPoints() < hPVictimLimit)
            damage = pyromancerHero.getHitPoints();
        else
            damage = 200f + 30 * this.getLevel();   // apply the level modifier

        if (terrain == Terrain.LAND)
            damage *= 1.15;                         // apply the terrain modifier

        damage *= 1.1;                              // apply the race modifier

        pyromancerHero.takeDamage(Math.round(damage));
    }

    @Override
    public void firstAbility(Wizard wizardHero, Terrain terrain) {
        float damage;

        // calculate the limit below which the opponent is instantly killed
        float hPVictimLimit = ((20f + this.getLevel()) / 100) *
                (400 + 30 * wizardHero.getLevel());
        if (wizardHero.getHitPoints() < hPVictimLimit)
            damage = wizardHero.getHitPoints();
        else
            damage = 200f + 30 * this.getLevel();   // apply the level modifier

        if (terrain == Terrain.LAND)
            damage *= 1.15;                         // apply the terrain modifier

            damage *= 0.8;                          // apply the race modifier

        wizardHero.takeDamage(Math.round(damage));
    }

    @Override
    public void firstAbility(Rogue rogueHero, Terrain terrain) {
        float damage;

        // calculate the limit below which the opponent is instantly killed
        float hPVictimLimit = ((20f + this.getLevel()) / 100) *
                (400 + 30 * rogueHero.getLevel());
        if (rogueHero.getHitPoints() < hPVictimLimit)
            damage = rogueHero.getHitPoints();
        else
            damage = 200f + 30 * this.getLevel();  // apply the level modifier

        if (terrain == Terrain.LAND)
            damage *= 1.15;             // apply the terrain modifier

        damage *= 1.15;                 // apply the race modifier

        rogueHero.takeDamage(Math.round(damage));
    }

    // Slam

    @Override
    public void secondAbility(Knight knightHero, Terrain terrain) {
        float damage = 100f + 40 * this.getLevel(); // apply the level modifier

        if (terrain == Terrain.LAND)
            damage *= 1.15;                         // apply the terrain modifier

        damage *= 1.2;                              // apply the race modifier

        knightHero.takeDamage(Math.round(damage));
        knightHero.stun();                          // stun the enemy
    }

    @Override
    public void secondAbility(Pyromancer pyromancerHero, Terrain terrain) {
        float damage = 100f + 40 * this.getLevel(); // apply the level modifier

        if (terrain == Terrain.LAND)
            damage *= 1.15;                         // apply the terrain modifier

        damage *= 0.9;                              // apply the race modifier

        pyromancerHero.takeDamage(Math.round(damage));
        pyromancerHero.stun();                      // stun the enemy
    }

    @Override
    public void secondAbility(Wizard wizardHero, Terrain terrain) {
        float damage = 100f + 40 * this.getLevel(); // apply the level modifier

        if (terrain == Terrain.LAND)
            damage *= 1.15;                         // apply the terrain modifier

        damage *= 1.05;                             // apply the race modifier

        wizardHero.takeDamage(Math.round(damage));
        wizardHero.stun();                          // stun the enemy
    }

    @Override
    public void secondAbility(Rogue rogueHero, Terrain terrain) {
        float damage = 100f + 40 * this.getLevel(); // apply the level modifier

        if (terrain == Terrain.LAND)
            damage *= 1.15;                         // apply the terrain modifier

        damage *= 0.8;                              // apply the race modifier

        rogueHero.takeDamage(Math.round(damage));
        rogueHero.stun();                           // stun the enemy
    }

    @Override
    public void defend(Hero hero, Terrain terrain) {
        hero.firstAbility(this, terrain);
        hero.secondAbility(this, terrain);
    }

}

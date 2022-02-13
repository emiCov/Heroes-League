package com.emi;

public class Wizard extends Hero {
    public Wizard(String name, int hitPoints, int positionX, int positionY) {
        super(name, hitPoints, positionX, positionY);
    }

    // Drain

    @Override
    public void firstAbility(Knight knightHero, Terrain terrain) {
        float percent;
        float damage;

        percent = (20f + 5 * this.getLevel()) / 100;    // calculates the percent
        percent *= 1.2f;                                // apply the race modifier

        damage = percent * Math.min(
                        knightHero.getHitPoints(),
                        Math.round((0.3 * (600 + 40 * knightHero.getLevel()))));

        if (terrain == Terrain.DESERT)
            damage *= 1.1f;                             // apply the terrain modifier

        knightHero.takeDamage(Math.round(damage));
    }

    @Override
    public void firstAbility(Pyromancer pyromancerHero, Terrain terrain) {
        float percent;
        float damage;

        percent = (20f + 5 * this.getLevel()) / 100;    // calculates the percent
        percent *= 0.9f;                                // apply the race modifier

        damage = percent * Math.min(
                pyromancerHero.getHitPoints(),
                Math.round((0.3 * (600 + 40 * pyromancerHero.getLevel()))));

        if (terrain == Terrain.DESERT)
            damage *= 1.1f;                             // apply the terrain modifier

        pyromancerHero.takeDamage(Math.round(damage));
    }

    @Override
    public void firstAbility(Wizard wizardHero, Terrain terrain) {
        float percent;
        float damage;

        percent = (20f + 5 * this.getLevel()) / 100;    // calculates the percent
        percent *= 1.05f;                               // apply the race modifier

        damage = percent * Math.min(
                wizardHero.getHitPoints(),
                Math.round((0.3 * (600 + 40 * wizardHero.getLevel()))));

        if (terrain == Terrain.DESERT)
            damage *= 1.1f;                             // apply the terrain modifier

        wizardHero.takeDamage(Math.round(damage));
    }

    @Override
    public void firstAbility(Rogue rogueHero, Terrain terrain) {
        float percent;
        float damage;

        percent = (20f + 5 * this.getLevel()) / 100;    // calculates the percent
        percent *= 0.8f;                                // apply the race modifier

        damage = percent * Math.min(
                rogueHero.getHitPoints(),
                Math.round((0.3 * (600 + 40 * rogueHero.getLevel()))));

        if (terrain == Terrain.DESERT)
            damage *= 1.1f;                             // apply the terrain modifier

        rogueHero.takeDamage(Math.round(damage));
    }

    // Deflect

    @Override
    public void secondAbility(Knight knightHero, Terrain terrain) {
        // Deflect implemented in the other heroes' abilities
    }

    @Override
    public void secondAbility(Pyromancer pyromancerHero, Terrain terrain) {
        // Deflect implemented in the other heroes' abilities
    }

    @Override
    public void secondAbility(Wizard wizardHero, Terrain terrain) {
        // Deflect not applicable here
    }

    @Override
    public void secondAbility(Rogue rogueHero, Terrain terrain) {
        // Deflect implemented in the other heroes' abilities
    }

    @Override
    public void defend(Hero hero, Terrain terrain) {
        hero.firstAbility(this, terrain);
    }
}

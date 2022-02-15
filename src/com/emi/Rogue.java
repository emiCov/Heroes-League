package com.emi;

public class Rogue extends Hero {
    private byte strikeNo;

    public Rogue(String name, int hitPoints, int positionX, int positionY) {
        super(name, hitPoints, positionX, positionY);
        strikeNo = 0;
    }

    // Backstab

    @Override
    public void firstAbility(Knight knightHero, Terrain terrain) {
        float damage = 200f + 20 * this.getLevel();  // apply the level modifier

        if (terrain == Terrain.WOODS)
            damage *= 1.15f;                         // apply the terrain modifier

        damage *= 0.9f;                              // apply the race modifier

        // check for critical strike
        if (strikeNo == 3) {
            if (terrain == Terrain.WOODS) {
                damage *= 1.5f;
            }
            strikeNo = 0;
        } else
            strikeNo++;

        knightHero.takeDamage(Math.round(damage));
    }

    @Override
    public void firstAbility(Pyromancer pyromancerHero, Terrain terrain) {
        float damage = 200f + 20 * this.getLevel();  // apply the level modifier

        if (terrain == Terrain.WOODS)
            damage *= 1.15f;                         // apply the terrain modifier

        damage *= 1.25f;                             // apply the race modifier

        // check for critical strike
        if (strikeNo == 3) {
            if (terrain == Terrain.WOODS) {
                damage *= 1.5f;
            }
            strikeNo = 0;
        } else
            strikeNo++;

        pyromancerHero.takeDamage(Math.round(damage));
    }

    @Override
    public void firstAbility(Wizard wizardHero, Terrain terrain) {
        float damage = 200f + 20 * this.getLevel();  // apply the level modifier
        float wizardDeflectDamage;

        if (terrain == Terrain.WOODS)
            damage *= 1.15f;                         // apply the terrain modifier

        // check for critical strike
        if (strikeNo == 3) {
            if (terrain == Terrain.WOODS) {
                damage *= 1.5f;
            }
            strikeNo = 0;
        } else
            strikeNo++;

        // calculate the wizard's deflect ability
        wizardDeflectDamage = damage * 1.2f * Math.min(     // 20% against rogue
                (35f + 2 * wizardHero.getLevel()) / 100,
                70f / 100);

        damage *= 1.25f;                             // apply the race modifier

        wizardHero.takeDamage(Math.round(damage));

        this.takeDamage(Math.round(wizardDeflectDamage));    // wizard's deflect ability
    }

    @Override
    public void firstAbility(Rogue rogueHero, Terrain terrain) {
        float damage = 200f + 20 * this.getLevel();  // apply the level modifier

        if (terrain == Terrain.WOODS)
            damage *= 1.15f;                         // apply the terrain modifier

        damage *= 1.2f;                              // apply the race modifier

        // check for critical strike
        if (strikeNo == 3) {
            if (terrain == Terrain.WOODS) {
                damage *= 1.5f;
            }
            strikeNo = 0;
        } else
            strikeNo++;

        rogueHero.takeDamage(Math.round(damage));
    }

    // Paralysis

    @Override
    public void secondAbility(Knight knightHero, Terrain terrain) {
        byte duration;
        float damage = 40f + 10 * this.getLevel(); // apply the level modifier

        if (terrain == Terrain.WOODS) {
            damage *= 1.15f;                        // apply the terrain modifier
            duration = 6;
        } else
            duration = 3;

        damage *= 0.8f;                              // apply the race modifier

        knightHero.takeDamage(Math.round(damage));
        knightHero.paralysis(
                Math.round((40 + 10 * this.getLevel()) * 0.8f),
                duration);               // paralyse the opponent
    }

    @Override
    public void secondAbility(Pyromancer pyromancerHero, Terrain terrain) {
        byte duration;
        float damage = 40f + 10 * this.getLevel(); // apply the level modifier

        if (terrain == Terrain.WOODS) {
            damage *= 1.15f;                        // apply the terrain modifier
            duration = 6;
        } else
            duration = 3;

        damage *= 1.2f;                              // apply the race modifier

        pyromancerHero.takeDamage(Math.round(damage));
        pyromancerHero.paralysis(
                Math.round((40 + 10 * this.getLevel()) * 1.2f),
                duration);               // paralyse the opponent
    }

    @Override
    public void secondAbility(Wizard wizardHero, Terrain terrain) {
        byte duration;
        float damage = 40f + 10 * this.getLevel(); // apply the level modifier
        float wizardDeflectDamage;

        if (terrain == Terrain.WOODS) {
            damage *= 1.15f;                        // apply the terrain modifier
            duration = 6;
        } else
            duration = 3;

        // calculate the wizard's deflect ability
        wizardDeflectDamage = damage * 1.2f * Math.min(     // 20% against rogue
                (35f + 2 * wizardHero.getLevel()) / 100,
                70f / 100);

        damage *= 1.25f;                            // apply the race modifier

        wizardHero.takeDamage(Math.round(damage));
        wizardHero.paralysis(
                Math.round((40 + 10 * this.getLevel()) * 1.25f),
                duration);               // paralyse the opponent

        this.takeDamage(Math.round(wizardDeflectDamage));    // wizard's deflect ability
    }

    @Override
    public void secondAbility(Rogue rogueHero, Terrain terrain) {
        byte duration;
        float damage = 40f + 10 * this.getLevel(); // apply the level modifier

        if (terrain == Terrain.WOODS) {
            damage *= 1.15f;                        // apply the terrain modifier
            duration = 6;
        } else
            duration = 3;

        damage *= 0.9f;                             // apply the race modifier

        rogueHero.takeDamage(Math.round(damage));
        rogueHero.paralysis(
                Math.round((40 + 10 * this.getLevel()) * 0.9f),
                duration);               // paralyse the opponent
    }

    @Override
    public void defend(Hero hero, Terrain terrain) {
        hero.firstAbility(this, terrain);
        hero.secondAbility(this, terrain);
    }
}

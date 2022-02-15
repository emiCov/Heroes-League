package com.emi;

public abstract class Hero {
    private String name;
    private int hitPoints;
    private int XP;
    private int level;
    private int positionX;
    private int positionY;
    private boolean isStunned;
    private int damageOverTime;
    private byte damageOverTimeRounds;
    private static int currentRound = 1;

    public Hero(String name, int hitPoints, int positionX, int positionY) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.positionX = positionX;
        this.positionY = positionY;
        XP = 0;
        level = 0;
        isStunned = false;
        damageOverTime = 0;
        damageOverTimeRounds = 0;
    }

    public void moveHero(char direction) {
        if (this.isStunned || direction == '_')
            return;

        switch (direction) {
            case 'U' -> this.positionX--;
            case 'D' -> this.positionX++;
            case 'L' -> this.positionY--;
            case 'R' -> this.positionY++;
        }
    }

    // methods for calculating the firstAbility against different opponents
    public abstract void firstAbility(Knight knightHero, Terrain terrain);

    public abstract void firstAbility(Pyromancer pyromancerHero, Terrain terrain);

    public abstract void firstAbility(Wizard wizardHero, Terrain terrain);

    public abstract void firstAbility(Rogue rogueHero, Terrain terrain);

    // methods for calculating the secondAbility against different opponents
    public abstract void secondAbility(Knight knightHero, Terrain terrain);

    public abstract void secondAbility(Pyromancer pyromancerHero, Terrain terrain);

    public abstract void secondAbility(Wizard wizardHero, Terrain terrain);

    public abstract void secondAbility(Rogue rogueHero, Terrain terrain);

    // method for calling and applying the abilities
    public abstract void defend(Hero hero, Terrain terrain);

    // method for applying damage
    public void takeDamage(int damage) {

        if (this.hitPoints > damage)
            this.hitPoints -= damage;
        else
            this.hitPoints = 0;
    }

    // method for increasing the XP according to the formula
    public void increaseXP(int levelWinner, int levelLoser) {
        this.XP += Math.max(0, 200 - (levelWinner - levelLoser) * 40);
    }

    // method for leveling up (also setting the HP to 100%, depending on the hero's type)
    public void levelUp(Hero hero, int amountXP) {
        int newLevel;
        XP += amountXP;
        if (XP < 250)
            newLevel = 0;
        else
            newLevel = (XP - 249) / 50 + 1;

        if (newLevel > level) {
            level = newLevel;
            if (hero instanceof Knight)
                hitPoints = 900 + 80 * level;
            else if (hero instanceof Pyromancer)
                hitPoints = 500 + 50 * level;
            else if (hero instanceof Wizard)
                hitPoints = 400 + 30 * level;
            else
                hitPoints = 600 + 40 * level;
        }
    }

    public void stun() {
        isStunned = true;
        damageOverTime = 0;
        damageOverTimeRounds = 1;
    }

    public void ignite(int damageOverTime, byte damageOverTimeRounds) {
        isStunned = false;
        this.damageOverTime = damageOverTime;
        this.damageOverTimeRounds = damageOverTimeRounds;
    }

    public void paralysis(int damageOverTime, byte damageOverTimeRounds) {
        isStunned = false;
        this.damageOverTime = damageOverTime;
        this.damageOverTimeRounds = damageOverTimeRounds;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public boolean isStunned() {
        return isStunned;
    }

    public int getDamageOverTime() {
        return damageOverTime;
    }

    public int getDamageOverTimeRounds() {
        return damageOverTimeRounds;
    }

    public static int getCurrentRound() {
        return currentRound;
    }
}

package com.emi;

public abstract class Hero {
    private String name;
    private int hitPoints;
    private int XP;
    private int level;
    private int positionX;
    private int positionY;
    private boolean isStunned;
    private boolean takesDamageOverTime;
    private int damageOverTime;
    private int endDOTRound;
    private static int currentRound = 1;

    public Hero(String name, int hitPoints, int positionX, int positionY) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.positionX = positionX;
        this.positionY = positionY;
        XP = 0;
        level = 0;
        isStunned = false;
        takesDamageOverTime = false;
        damageOverTime = 0;
        endDOTRound = 0;
    }

    public void moveHero(char direction) {
        if (this.isStunned && this.endDOTRound < currentRound)
            this.isStunned = false;

        if (this.getHitPoints() == 0 || this.isStunned|| direction == '_')
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
    public void levelUp() {
        int newLevel;
//        XP += amountXP;
        if (XP < 250)
            newLevel = 0;
        else
            newLevel = (XP - 249) / 50 + 1;

        if (newLevel > level) {
            level = newLevel;
            if (this instanceof Knight)
                hitPoints = 900 + 80 * level;
            else if (this instanceof Pyromancer)
                hitPoints = 500 + 50 * level;
            else if (this instanceof Wizard)
                hitPoints = 400 + 30 * level;
            else
                hitPoints = 600 + 40 * level;
        }
    }

    public void stun() {
        isStunned = true;
        damageOverTime = 0;
        endDOTRound = 1 + Hero.getCurrentRound();
        this.takesDamageOverTime = false;
    }

    public void ignite(int damageOverTime, int endDOTRound) {
        isStunned = false;
        this.damageOverTime = damageOverTime;
        this.endDOTRound = endDOTRound;
        this.takesDamageOverTime = true;
    }

    public void paralyse(int damageOverTime, int endDOTRound) {
        isStunned = false;
        this.damageOverTime = damageOverTime;
        this.endDOTRound = endDOTRound;
        this.takesDamageOverTime = true;
    }

    public void applyDamageOverTime() {
        if (!this.takesDamageOverTime || this.getHitPoints() == 0)
            return;

        if (Hero.getCurrentRound() > this.endDOTRound) {
            this.takesDamageOverTime = false;
            return;
        }

        this.takeDamage(this.damageOverTime);
    }

    public static void goToTheNextRound() {
        currentRound++;
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

    public int getEndDOTRound() {
        return endDOTRound;
    }

    public static int getCurrentRound() {
        return currentRound;
    }

    public int getXP() {
        return XP;
    }
}

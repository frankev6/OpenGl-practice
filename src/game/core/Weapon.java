package game.core;

import engine.graphics.Entity;

public class Weapon{

    private Entity entity;
    private int damage;
    private float range;
    
    public Weapon(float range, int damage){
	this.range = range;
	this.damage= damage;
    }    
    public int getDamage() {
	return damage;
    }

    public float getRange() {
	return range;
    }

}

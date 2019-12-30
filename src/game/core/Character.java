package game.core;

import engine.graphics.Entity;
import engine.math.Vector3Df;

public class Character {

    protected Entity entity;
    protected static String meshName;

    protected int maxHealth;
    protected int health;
    protected int attack;
    protected int moveSpeed;
    protected int defense;
    protected boolean isInvincible;
    protected int attackCd;
    protected int currentCd;
    protected int level;

    protected Weapon currentWeapon;

    public Character(Vector3Df position, Vector3Df rotation) {
	entity = new Entity(meshName, position, rotation, 1);
    }

    public int getMaxHealth() {
	return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
	this.maxHealth = maxHealth;
    }

    public int getHealth() {
	return health;
    }

    public void setHealth(int health) {
	this.health = health;
    }

    public int getAttack() {
	return attack;
    }

    public void setAttack(int attack) {
	this.attack = attack;
    }

    public int getMoveSpeed() {
	return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
	this.moveSpeed = moveSpeed;
    }

    public int getDefense() {
	return defense;
    }

    public void setDefense(int defense) {
	this.defense = defense;
    }

    public boolean isInvincible() {
	return isInvincible;
    }

    public void setInvincible(boolean isInvincible) {
	this.isInvincible = isInvincible;
    }

    public int getAttackCd() {
	return attackCd;
    }

    public void setAttackCd(int attackCd) {
	this.attackCd = attackCd;
    }

    public int getCurrentCd() {
	return currentCd;
    }

    public void setCurrentCd(int currentCd) {
	this.currentCd = currentCd;
    }

    public void getHit(int damage) {
	this.health -= damage;
	// create damage text beside character showing damage done
	if (health <= 0) {
	    Die();
	}
    }

    public Entity getEntity() {
	return this.entity;
    }

    protected void setEntity(Entity entity) {
	this.entity = entity;
    }

    public void Die() {
	// respawn if player?
    }

    public void Attack(Character enemy) {
	if (enemy.defense >= attack + currentWeapon.getDamage()) {
	    enemy.getHit(1);
	    return;
	}
	enemy.getHit((attack + currentWeapon.getDamage()) - enemy.defense);
    }

}

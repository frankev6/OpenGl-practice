package game.core;

import engine.graphics.Entity;
import engine.graphics.Scene;
import engine.math.Mathf;
import engine.math.Vector3f;
import engine.utils.Collider;

public abstract class Character {// :TODO make it an interface or abstract class

    protected Entity entity;
    protected static String meshName;

    protected int maxHealth = 100;// maximum health of character
    protected int health;// health, when its 0, character dies
    protected int attack;// damage output
    protected float moveSpeed = 0.5f;// movement speed
    protected int defense;// armour against incomming attacks
    protected boolean isInvincible;// if attacking does any damage
    protected float attackCd = 0.5f;// attacks per second
    protected float currentCd;// current cooldown since last attack
    protected int level;// level
    protected Collider collider;

    protected Weapon currentWeapon;

    public Character(Scene scene, Vector3f position, Vector3f rotation) {
	entity = new Entity(scene, meshName, position, rotation, 1f);
	collider = new Collider(2, entity.getPosition());
	scene.getGameObjects().add(entity);
	health = maxHealth;
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

    public float getMoveSpeed() {
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

    public float getAttackCd() {
	return attackCd;
    }

    public void setAttackCd(int attackCd) {
	this.attackCd = attackCd;
    }

    public float getCurrentCd() {
	return currentCd;
    }

    public void setCurrentCd(int currentCd) {
	this.currentCd = currentCd;
    }

    public void takeDamage(int damage) {
	this.health -= damage;
	System.out.println("OUCH!");
	// create damage text beside character showing damage done
	if (health <= 0) {
	    die();
	    System.out.println("i is dead now");
	}
    }

    public Entity getEntity() {
	return this.entity;
    }

    protected void setEntity(Entity entity) {
	this.entity = entity;
    }

    public void die() {
	// respawn if player?

    }

    public void attack(Character enemy) {
	if (currentCd >= attackCd) {
	    if (enemy.isInvincible)
		return;
	    if (enemy.defense >= attack + currentWeapon.getDamage()) {
		enemy.takeDamage(1);
		return;
	    }
	    enemy.takeDamage((attack + currentWeapon.getDamage()) - enemy.defense);
	    currentCd = 0;

	} else {
	    currentCd += 0.01;
	}

    }

    public void checkCollision(Character other) {
	this.collider.checkCollision(other.collider);
    }

    public float checkDistance(Character other) {
	return Mathf.Distance(entity.getPosition().x, entity.getPosition().z, other.getEntity().getPosition().x,
		other.getEntity().getPosition().x);
    }

    protected void updateCollider() {
	collider.setCenter(entity.getPosition());
    }

}

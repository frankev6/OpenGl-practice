package game.core;

import java.util.concurrent.ThreadLocalRandom;

import engine.graphics.Entity;
import engine.graphics.Mesh;
import engine.graphics.Scene;
import engine.math.Mathf;
import engine.math.Vector3f;
import engine.physics.CircleCollider;

public abstract class Character extends Entity {// :TODO make it an interface or abstract class

    public enum LookDirection {
	up(180), down(0), left(-90), right(90);

	private int numVal;

	LookDirection(int numVal) {
	    this.numVal = numVal;
	}

	public int getNumVal() {
	    return numVal;
	}
    }

    protected static String meshName;
    protected LookDirection lookDirection;
    protected int maxHealth = 100;// maximum health of character
    protected int health;// health, when its 0, character dies
    protected int attack;// damage output
    protected float moveSpeed = 0.5f;// movement speed
    protected int defense;// armour against incomming attacks
    protected boolean isInvincible;// if attacking does any damage
    protected float attackCd = 0.5f;// attacks per second
    protected float currentCd;// current cooldown since last attack
    protected int level;// level
    protected CircleCollider collider;

    protected Weapon currentWeapon;

    /*
     * public Character(Scene scene, Vector3f position, Vector3f rotation) { entity
     * = new Entity(scene, meshName, position, rotation, 1f);
     * 
     * 
     * }
     */
    public Character(Scene scene, String meshName, Vector3f position, Vector3f rotation, float scale) {
	super(scene, meshName, position, rotation, scale);
	lookDirection = LookDirection.right;
	collider = new CircleCollider(1.5f, getPosition());
	scene.getGameObjects().add(this);
	scene.getCharacters().add(this);
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
	System.out.println("ouchies!");
	// create damage text beside character showing damage done
	if (health <= 0) {
	    die();
	    System.out.println("i have died oh no");
	}
    }

    public void die() {
	scale = 3;
	translate(0, -3, 0);
	mesh = new Mesh("Assets/Models/tomb" + ThreadLocalRandom.current().nextInt(1, 3 + 1) + ".obj");
	look(LookDirection.down);
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

    public CircleCollider collide() {

	for (Character c : scene.getCharacters()) {
	    if (!c.equals(this) && this.collider.checkCollision(c.collider) != null) {
		return c.collider;
	    }

	}
	return null;
    }

    public float checkDistance(Character other) {
	return Mathf.Distance(getPosition().x, getPosition().z, other.getPosition().x, other.getPosition().x);
    }

    public void look(LookDirection direction) {
	lookDirection = direction;
	this.setRotation(new Vector3f(0, lookDirection.getNumVal(), 0));
    }

}

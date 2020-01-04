package game.core;

import engine.graphics.Scene;
import engine.math.Vector3f;

public class Player extends Character {

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

    protected LookDirection lookDirection;
    protected int maxExperience = 100;
    protected int experience;

    private Vector3f respawnPoint;

    public Player(Scene scene, Vector3f position, Vector3f rotation) {
	super(scene, position, rotation);
	lookDirection = LookDirection.right;
    }

    protected void levelUp() {
	level++;
	maxExperience *= level / 4;
	experience = 0;
	maxHealth = 90 + (level * 10);
	health += (level * 10);
    }

    public void addExperience(int experience) {
	experience += experience;
	if (experience >= maxExperience) {
	    levelUp();
	}
    }

    public void DropWeapon() {
	// instantiate an entity of that weapon in the world, that you could pick back
	// up
    }

    public void Attack() {

    }

    public void Look(LookDirection direction) {
	lookDirection = direction;
	this.getEntity().setRotation(new Vector3f(0, lookDirection.getNumVal(), 0));
    }

    public void Move() {

	
	if (lookDirection.equals(LookDirection.up)) {
	    getEntity().Translate(0, 0, -getMoveSpeed());
	    return;
	}
	if (lookDirection.equals(LookDirection.down)) {
	    getEntity().Translate(0, 0, getMoveSpeed());
	    return;
	}
	if (lookDirection.equals(LookDirection.left)) {
	    getEntity().Translate(-getMoveSpeed(), 0, 0);
	    return;
	}
	if (lookDirection.equals(LookDirection.right)) {
	    getEntity().Translate(getMoveSpeed(), 0, 0);
	    return;
	}
	updateCollider();
    }

}

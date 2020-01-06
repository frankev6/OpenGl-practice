package game.core;

import engine.graphics.Scene;
import engine.math.Vector3f;
import engine.physics.CircleCollider;

public class Player extends Character {

    protected int maxExperience = 100;
    protected int experience;

    private Vector3f respawnPoint;

    public Player(Scene scene, String meshName, Vector3f position, Vector3f rotation) {
	super(scene, meshName, position, rotation, 1);
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

    public void attack() {

    }

    public void update() {

    }

    public void move() {

	/*LookDirection collisionDirection = null;
	CircleCollider collidingObject = collide();

	if (collidingObject != null) {
	    collisionDirection = lookDirection;

	} else {
	    collisionDirection = null;
	}*/

	if (lookDirection.equals(LookDirection.up)) {
	//    if (collisionDirection != lookDirection) {
		translate(0, 0, -getMoveSpeed());
	 //   } else if (collisionDirection != null){
	//	setPosition(position.x, position.y, position.z - (collider.getDistance(collidingObject) - 1));
	  //  }

	} else if (lookDirection.equals(LookDirection.down)) {
	 //   if (collisionDirection != lookDirection) {
		translate(0, 0, getMoveSpeed());
	 //   } else if (collisionDirection != null){
	//	setPosition(position.x, position.y, position.z + (collider.getDistance(collidingObject) - 1));
	  //  }
	} else if (lookDirection.equals(LookDirection.left)) {

	  //  if (collisionDirection != lookDirection) {
		translate(-getMoveSpeed(), 0, 0);
	  //  } else if(collisionDirection != null){
	//	setPosition(position.x - (collider.getDistance(collidingObject) - 1), position.y, position.z);
	  //  }
	} else if (lookDirection.equals(LookDirection.right)) {

	   // if (collisionDirection != lookDirection) {
		translate(getMoveSpeed(), 0, 0);
	   // } else if(collisionDirection != null){
	//	setPosition(position.x + (collider.getDistance(collidingObject) - 1), position.y, position.z);
	   // }
	}

    }

}

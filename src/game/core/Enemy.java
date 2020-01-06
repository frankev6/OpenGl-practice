package game.core;

import engine.graphics.Scene;
import engine.math.Mathf;
import engine.math.Vector3f;

public class Enemy extends Character {

    float distanceThreshold = 15f; // distance at which enemy will follow player
    private float proximityThreshold = 0f;// distance at which enemy will run away from player, if ranged or low health
    Player target;
    float guardDirection = 1;// 1 going positive in z axis -1 going negative

    public Enemy(Scene scene, String meshName, Vector3f position, Vector3f rotation, float scale) {
	super(scene, meshName, position, rotation, scale);
	moveSpeed = 0.3f;
	currentWeapon = new Weapon(1, 25);
    }

    private void checkPlayerPos() {

	Player player1 = getScene().magnus;
	Player player2 = getScene().gjerta;

	if (target == null) {

	    float distanceP1 = Mathf.LinearDistance(this.getPosition().x, player1.getPosition().x);
	    float distanceP2 = Mathf.LinearDistance(this.getPosition().x, player2.getPosition().x);

	    if (distanceP1 < distanceThreshold && player1.getHealth() > 0) {// player 1 close?
		target = player1;

	    }
	    if (distanceP2 < distanceThreshold && player2.getHealth() > 0) {// player 2 close?
		target = player2;
	    }
	} else {
	    if (target.getHealth() < 0) {
		target = null;
		return;
	    }
	    if (Mathf.LinearDistance(this.getPosition().x, target.getPosition().x) > distanceThreshold * 2) {
		target = null;
		return;
	    }
	}

    }

    private void move() {
	if (target != null) {
	    lookAtTarget();
	    if (!targetInRange() || tooClose()) {

		if (!targetInRange()) {
		    moveSpeed = Math.abs(moveSpeed);
		}
		if (tooClose()) {
		    moveSpeed = -moveSpeed;
		}

		// Move in x direction towards target until in range
		if (target.getPosition().x - currentWeapon.getRange() > this.getPosition().x) {
		    this.translate(this.getMoveSpeed(), 0, 0);
		}
		if (target.getPosition().x + currentWeapon.getRange() < this.getPosition().x) {
		    this.translate(-this.getMoveSpeed(), 0, 0);
		}
		// Move in z direction towards target
		if (target.getPosition().z - currentWeapon.getRange() > this.getPosition().z) {
		    this.translate(0, 0, this.getMoveSpeed());
		}
		if (target.getPosition().z + currentWeapon.getRange() < this.getPosition().z) {
		    this.translate(0, 0, -this.getMoveSpeed());
		}
	    } else {// target in range
		if (target.getHealth() > 0) {
		    attack(target);
		} else {
		    target = null;
		}
	    }
	} else {// no player nearby
	    if (this.getPosition().z > this.getScene().getWidth() / 2) {
		guardDirection = -1;
	    }
	    if (this.getPosition().z < -this.getScene().getWidth() / 2) {
		guardDirection = 1;
	    }

	    this.translate(0, 0, guardDirection * this.getMoveSpeed());
	}
    }

    /** Checks if target is in range in the x and z axis **/
    private boolean targetInRange() {

	return Mathf.LinearDistance(this.getPosition().x, target.getPosition().x) < currentWeapon.getRange()
		&& Mathf.LinearDistance(this.getPosition().x, target.getPosition().x) < currentWeapon.getRange();
    }

    public void update() {
	checkPlayerPos();
	move();
	//collide();
    }

    private boolean tooClose() {
	return Mathf.Distance(target.getPosition().x, target.getPosition().x, getPosition().x,
		getPosition().z) < proximityThreshold;

    }

    private void lookAtTarget() {
	if (target.getPosition().x > this.getPosition().x) {
	    look(LookDirection.right);
	}
	if (target.getPosition().x < this.getPosition().x) {
	    look(LookDirection.left);
	}
	// Move in z direction towards target
	if (target.getPosition().z > this.getPosition().z) {
	    look(LookDirection.down);
	}
	if (target.getPosition().z < this.getPosition().z) {
	    look(LookDirection.up);
	}

    }

}

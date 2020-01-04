package game.core;

import engine.graphics.Scene;
import engine.math.Mathf;
import engine.math.Vector3f;

public class Enemy extends Character {

    float distanceThreshold = 10f; // distance at which enemy will follow player
    
    Player target;
    float guardDirection = 1;// 1 going positive in z axis -1 going negative

    public Enemy(Scene scene,Vector3f position, Vector3f rotation) {
	super(scene, position, rotation);
	moveSpeed = 0.3f;
	currentWeapon = new Weapon(3, 25);
    }

    private void checkPlayerPos(Player player1, Player player2) {

	if (target == null) {
	    
	    float distanceP1 = Mathf.LinearDistance(this.getEntity().getPosition().x,
		    player1.getEntity().getPosition().x);
	    float distanceP2 = Mathf.LinearDistance(this.getEntity().getPosition().x,
		    player2.getEntity().getPosition().x);
	    
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
	    if (Mathf.LinearDistance(this.getEntity().getPosition().x, target.getEntity().getPosition().x) > distanceThreshold*2) {
		target = null;
		return;
	    }
	}

    }

    private void move() {
	if (target != null) {
	    moveSpeed = 0.3f;
	    if (!targetInRange()) {
		// Move in x direction towards target until in range
		if (target.getEntity().getPosition().x - currentWeapon.getRange() > this.getEntity().getPosition().x) {
		    this.getEntity().Translate(this.getMoveSpeed(), 0, 0);
		}
		if (target.getEntity().getPosition().x + currentWeapon.getRange() < this.getEntity().getPosition().x) {
		    this.getEntity().Translate(-this.getMoveSpeed(), 0, 0);
		}
		// Move in z direction towards target
		if (target.getEntity().getPosition().z - currentWeapon.getRange() > this.getEntity().getPosition().z) {
		    this.getEntity().Translate(0, 0, this.getMoveSpeed());
		}
		if (target.getEntity().getPosition().z + currentWeapon.getRange() < this.getEntity().getPosition().z) {
		    this.getEntity().Translate(0, 0, -this.getMoveSpeed());
		}
	    } else {// target in range
		if(target.getHealth() > 0) {
		attack(target);
		}else {
		    target = null;
		}
	    }
	} else {// no player nearby
	    moveSpeed = 0.15f;
	    if (this.getEntity().getPosition().z > this.getEntity().getScene().getWidth()/2) {
		guardDirection = -1;
	    }
	    if (this.getEntity().getPosition().z < -this.getEntity().getScene().getWidth()/2) {
		guardDirection = 1;
	    }

	    this.getEntity().Translate(0, 0, guardDirection * this.getMoveSpeed());
	}
    }

    /** Checks if target is in range in the x and z axis **/
    private boolean targetInRange() {

	return Mathf.LinearDistance(this.getEntity().getPosition().x,
		target.getEntity().getPosition().x) < currentWeapon.getRange()
		&& Mathf.LinearDistance(this.getEntity().getPosition().x,
			target.getEntity().getPosition().x) < currentWeapon.getRange();
    }

    public void update(Player player1, Player player2) {
	checkPlayerPos(player1, player2);
	move();
	updateCollider();
    }

}

package game.core;

import java.util.ArrayList;

import engine.graphics.Entity;
import engine.graphics.Scene;
import engine.math.Vector3f;
import engine.physics.CircleCollider;
import game.core.Character.LookDirection;

public class Projectile extends Entity {

    public enum ProjectileType {
	arrow, slingshot, ball;
    }

    private CircleCollider collider;
    private float direction;
    private float moveSpeed = 1.5f;
    private int damage;
    private float gravity = 0.01f;
    private float groundLevel = -6;

    public Projectile(Scene scene, ProjectileType type, Vector3f position, float rotation, int damage) {
	super(scene, type.name(), new Vector3f(position.x, 0, position.z), new Vector3f(0, 0, 0), 3);
	getScene().getGameObjects().add(this);
	this.direction = rotation;
	this.damage = damage;
	collider = new CircleCollider(0.5f, this.position);
    }

    public float getMoveSpeed() {
	return moveSpeed;
    }

    public void move() {

	if (getPosition().y > groundLevel) {
	    setPosition(getPosition().x, -gravity, getPosition().z);
	    setRotation(new Vector3f(-gravity, direction, 0));
	    if (direction == (LookDirection.up).getNumVal()) {
		translate(0, 0, -getMoveSpeed());
	    }
	    if (direction == (LookDirection.down).getNumVal()) {
		translate(0, 0, getMoveSpeed());
	    }
	    if (direction == (LookDirection.left).getNumVal()) {
		translate(-getMoveSpeed(), 0, 0);
	    }
	    if (direction == (LookDirection.right).getNumVal()) {
		translate(getMoveSpeed(), 0, 0);
	    }
	    updateCollider();
	    Character c = checkCollisions(getScene().getCharacters());
	    if (c != null) {
		System.out.println("projectile hit");
		doDamage(c);
	    }
	}

    }

    public void update() {

	move();
	gravity += 0.2f;
    }

    public Character checkCollisions(ArrayList<Character> other) {

	for (Character c : other) {
	    if (collider.checkCollision(c.collider) != null) {
		return c;
	    }

	}
	return null;

    }

    protected void updateCollider() {
	collider.setPosition(getPosition());
    }

    private void doDamage(Character c) {
	c.takeDamage(damage);

    }
}

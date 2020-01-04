package engine.utils;

import engine.math.Mathf;
import engine.math.Vector3f;

public class Collider {

    private Vector3f center;
    private float radius;
    private boolean isActive = true;

    /** Radius of collider, position in world **/
    public Collider(float r, Vector3f center) {
	this.radius = r;
	this.center = center;
    }

    public boolean checkCollision(Collider other) {
	boolean isColliding = false;

	if (Mathf.Distance(center.x, center.z, other.getCenter().x, other.getCenter().z) < radius + other.getRadius()) {

	    System.out.println("Collision detected");
	}

	return isColliding;
    }

    public Vector3f getCenter() {
	return center;
    }

    public void setCenter(Vector3f center) {
	this.center = center;
    }

    public float getRadius() {
	return radius;
    }

    public void setRadius(float radius) {
	this.radius = radius;
    }

    public boolean isActive() {
	return isActive;
    }

    public void setActive(boolean isActive) {
	this.isActive = isActive;
    }

}

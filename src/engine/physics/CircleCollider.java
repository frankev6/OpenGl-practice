package engine.physics;

import java.util.ArrayList;

import engine.math.Mathf;
import engine.math.Vector3f;

public class CircleCollider implements Collider {

    private Vector3f position;
    private float radius;
    private boolean isActive = true;

    /** Radius of collider, position in world **/
    public CircleCollider(float r, Vector3f position) {
	this.radius = r;
	this.position = position;
    }
    
    public float getDistance(CircleCollider o) {
	return Mathf.Distance(position.x, position.z, o.getPosition().x,
		o.getPosition().z) - (radius + o.getRadius());
    }

    public Collider checkCollision(Collider other) {

	CircleCollider o = (CircleCollider) other;

	if (other.isActive() && this.isActive && Mathf.Distance(position.x, position.z, o.getPosition().x,
		o.getPosition().z) < radius + o.getRadius()) {
	    return o;
	}

	return null;
    }

    public CircleCollider checkCollision(ArrayList<CircleCollider> colliders) {

	for (CircleCollider collider : colliders) {
	    if (checkCollision(collider) != null) {
		return collider;
	    }
	}
	return null;
    }

    public Vector3f getPosition() {
	return position;
    }

    public void setPosition(Vector3f position) {
	this.position = position;
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

    public boolean isPointInCollision(float x, float y) {

	return false;
    }

}

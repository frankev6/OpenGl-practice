package engine.physics;

import engine.math.Vector3f;

public class RectangleCollider  implements Collider {

    private float width;
    private float height;
    private float y, x;

    public RectangleCollider(Vector3f position, float width, float height) {
	y = position.y;
	x = position.x;
	this.width = width;
	this.height = height;
    }

    public Collider checkCollision(Collider other) {

	RectangleCollider o = (RectangleCollider) other;

	// Axis Aligned Bounding Box Algorithm
	if (this.x < o.x + o.width && this.x + this.width > o.x && this.y < o.y + o.height
		&& this.y + this.height > o.y) {
	    return o;
	}
	return null;
    }

    public boolean isPointInCollision(float x, float y) {

	
	if (x > this.x && x < this.x + width && y > this.y && y < this.y + height)
	    return true;

	return false;
    }

    public Vector3f getPosition() {
	return null;
    }

    public void setPosition(Vector3f position) {

    }

    public boolean isActive() {
	return false;
    }

    public void setActive(boolean isActive) {

    }

}

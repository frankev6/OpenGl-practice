package engine.graphics;

import engine.math.Vector3f;

public class Camera {

    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;
    private float minDistance = 60;

    public Camera() {
    }

    public void move(Vector3f pos1, Vector3f pos2) {
	
	float distance = (pos1.x + pos2.x) / 2.0f;
	position.z = minDistance+Math.abs(pos1.x - pos2.x);
	position.x = distance;
	
    }

    public Vector3f getPosition() {
	return position;
    }

    public float getPitch() {
	return pitch;
    }

    public float getYaw() {
	return yaw;
    }

    public float getRoll() {
	return roll;
    }

    public void setPosition(Vector3f position) {
	this.position = position;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public void Translate(float dx, float dy, float dz) {
	this.position.x += dx;
	this.position.y += dy;
	this.position.z += dz;
    }
    
    
}
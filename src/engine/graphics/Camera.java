package engine.graphics;

import engine.math.Vector3Df;

public class Camera {

    private Vector3Df position = new Vector3Df(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;

    public Camera() {
    }

    public void move() {
	/*
	 * if(Keyboard.isKeyDown(Keyboard.KEY_W)){ position.z-=0.02f; }
	 * if(Keyboard.isKeyDown(Keyboard.KEY_D)){ position.x+=0.02f; }
	 * if(Keyboard.isKeyDown(Keyboard.KEY_A)){ position.x-=0.02f; }
	 */
    }

    public Vector3Df getPosition() {
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

    public void setPosition(Vector3Df position) {
	this.position = position;
    }

}
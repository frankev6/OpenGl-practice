package engine.graphics;

import engine.math.Vector3Df;

public class Light {

    private Vector3Df position;
    private Vector3Df colour;

    public Light(Vector3Df position, Vector3Df colour) {
	this.position = position;
	this.colour = colour;
    }

    public Vector3Df getPosition() {
	return position;
    }

    public void setPosition(Vector3Df position) {
	this.position = position;
    }

    public Vector3Df getColour() {
	return colour;
    }

    public void setColour(Vector3Df colour) {
	this.colour = colour;
    }

}

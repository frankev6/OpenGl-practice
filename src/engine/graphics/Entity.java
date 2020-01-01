package engine.graphics;

import engine.math.Vector3f;

public class Entity {

    private Mesh mesh;
    private Vector3f position;
    private Vector3f rotation;
    
    private float scale;

    private boolean isVisible = true;

    public Entity(String meshName, Vector3f position, Vector3f rotation, float scale) {
	this.mesh = new Mesh("Assets/Models/" + meshName + ".obj");
	this.position = position;
	this.rotation = rotation;
	this.scale = scale;
    }

    public Mesh getMesh() {
	return mesh;
    }

    public Vector3f getPosition() {
	return position;
    }

    public float getScale() {
	return scale;
    }

    public void setMesh(Mesh model) {
	this.mesh = model;
    }

    public void setPosition(Vector3f position) {
	this.position = position;
    }

    public void setScale(float scale) {
	this.scale = scale;
    }

    public void Translate(float dx, float dy, float dz) {
	this.position.x += dx;
	this.position.y += dy;
	this.position.z += dz;
    }

    public void setRotation(Vector3f rotation) {
	this.rotation = rotation;
    }

    public Vector3f getRotation() {
	return this.rotation;
    }

    public void setPosition(float x, float y, float z) {
	this.position.x = x;
	this.position.y = y;
	this.position.z = z;
    }

    public void Destroy() {

    }

    public void setVisibility(boolean visible) {
	this.isVisible = visible;
    }

    public boolean getVisibility() {
	return this.isVisible;
    }

}

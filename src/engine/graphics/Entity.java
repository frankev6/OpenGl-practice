package engine.graphics;

import java.util.ArrayList;

import engine.math.Mathf;
import engine.math.Vector3f;

public class Entity{

    protected Scene scene;
    protected Mesh mesh;
    protected Vector3f position;
    protected Vector3f rotation;
    protected float nearEntityThreshold;
    
    protected float scale;

    protected boolean isVisible = true;

    public Entity(Scene scene, String meshName, Vector3f position, Vector3f rotation, float scale) {
	this.mesh = new Mesh("Assets/Models/" + meshName + ".obj");
	this.scene = scene;
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

    public void translate(float dx, float dy, float dz) {
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

	scene.getGameObjects().remove(this);
	
    }

    public void setVisibility(boolean visible) {
	this.isVisible = visible;
    }

    public boolean getVisibility() {
	return this.isVisible;
    }

    public Scene getScene() {
        return scene;
    }
    
    public ArrayList<Entity> getNearEntities() {
	
	ArrayList<Entity> nearEntities = new ArrayList<Entity>();
	
	for(Entity entity : scene.getGameObjects()) {
	    if(Mathf.LinearDistance(entity.getPosition().x, this.getPosition().x) < nearEntityThreshold) {
		nearEntities.add(entity);
	    }
	}
	return nearEntities;
    }
    public void update() {
	
    }
    
}

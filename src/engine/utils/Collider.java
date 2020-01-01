package engine.utils;

import engine.math.Vector3f;

public class Collider {
    
    private Vector3f center;
    
    private float height;
    private float width;
    boolean isActive = true;
    
    
    /**Width and height of collider, then position in world**/
    public Collider(float w, float h, Vector3f center) {
	height = h;
	width = w;
	this.center = center;
    }
    
    public boolean checkCollision() {
	boolean isColliding = false;
	
	//Implement A.A.B.B collision to check nearby objects,
	//then only check for real collision on the really close objects
	
	return isColliding;
    }
}

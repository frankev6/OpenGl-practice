package engine.physics;

import engine.math.Vector3f;

public interface Collider {
       
    public Collider checkCollision(Collider other);
    
    public boolean isPointInCollision(float x, float y);
    
    public Vector3f getPosition();
    
    public void setPosition(Vector3f position);
    
    public boolean isActive();

    public void setActive(boolean isActive);
    
    

}

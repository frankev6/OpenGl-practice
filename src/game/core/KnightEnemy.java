package game.core;

import engine.graphics.Scene;
import engine.math.Vector3f;

public class KnightEnemy extends Enemy {

    static {
	meshName = "knight";
    }
    
    public KnightEnemy(Scene scene, Vector3f position, Vector3f rotation) {
	super(scene, meshName, position, rotation, 1);
	
    }

}

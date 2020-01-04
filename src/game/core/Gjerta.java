package game.core;

import engine.graphics.Scene;
import engine.math.Vector3f;

public class Gjerta extends Player{

    static {
	meshName = "knight";
    }
    
    public Gjerta(Scene scene, Vector3f position, Vector3f rotation) {
	super(scene,position, rotation);
	
    }


}

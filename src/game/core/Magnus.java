package game.core;

import engine.graphics.Entity;
import engine.math.Vector3Df;

public class Magnus extends Player {

    static {
	meshName = "knight";
    }

    public Magnus(Vector3Df position, Vector3Df rotation) {

	super(position, rotation);
    }

}

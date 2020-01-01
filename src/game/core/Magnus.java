package game.core;

import engine.graphics.Entity;
import engine.math.Vector3f;

public class Magnus extends Player {

    static {
	meshName = "knight";
    }

    public Magnus(Vector3f position, Vector3f rotation) {

	super(position, rotation);
    }

}

package game.core;

import engine.graphics.Scene;
import engine.math.Vector3f;
import game.core.Projectile.ProjectileType;

public class ArcherEnemy extends Enemy {

    static {
	meshName = "knight";
    }
    
    public ArcherEnemy(Scene scene, Vector3f position, Vector3f rotation) {
	super(scene, meshName, position, rotation, 1);
	currentWeapon = new Weapon(10,5);
    }

    public void attack(Character target) {
	
	if (currentCd >= attackCd) {
	    System.out.println(lookDirection.getNumVal());
	    Projectile arrow = new Projectile(scene, ProjectileType.arrow, position, lookDirection.getNumVal(), attack+currentWeapon.getDamage());
	    currentCd = 0;

	} else {
	    currentCd += 0.01;
	}
    }

}

package game.core;

import engine.math.Vector3Df;

public class Player extends Character{
    
    

    private String LEFT_KEY;
    private String RIGHT_KEY;
    private String UP_KEY;
    private String DOWN_KEY;
    
    protected int maxExperience = 100;
    protected int experience;

    private Vector3Df respawnPoint;
    
    public Player(Vector3Df position, Vector3Df rotation) {
	super(position, rotation);
    }
    
    protected void LevelUp() {
	level++;
	maxExperience *= level/4;
	experience = 0;
	maxHealth = 90+(level * 10);
	health += (level*10);
    }
    
   

    public void AddExperience(int experience) {
	experience+= experience;
	if(experience >= maxExperience) {
	    LevelUp();
	}
    }
    
    public void DropWeapon() {
	//instantiate an entity of that weapon in the world, that you could pick back up
    }
    
    
}

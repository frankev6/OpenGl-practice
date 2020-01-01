package game.core;

import engine.math.Vector3f;

public class Player extends Character{
    
    

    protected String LEFT_KEY;
    protected String RIGHT_KEY;
    protected String UP_KEY;
    protected String DOWN_KEY;
    
    protected int maxExperience = 100;
    protected int experience;

    private Vector3f respawnPoint;
    
    public Player(Vector3f position, Vector3f rotation) {
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

    public String getLEFT_KEY() {
        return LEFT_KEY;
    }

    public void setLEFT_KEY(String lEFT_KEY) {
        LEFT_KEY = lEFT_KEY;
    }

    public String getRIGHT_KEY() {
        return RIGHT_KEY;
    }

    public void setRIGHT_KEY(String rIGHT_KEY) {
        RIGHT_KEY = rIGHT_KEY;
    }

    public String getUP_KEY() {
        return UP_KEY;
    }

    public void setUP_KEY(String uP_KEY) {
        UP_KEY = uP_KEY;
    }

    public String getDOWN_KEY() {
        return DOWN_KEY;
    }

    public void setDOWN_KEY(String dOWN_KEY) {
        DOWN_KEY = dOWN_KEY;
    }
    
    
}

/**
 * 
 */
package engine.graphics;

import java.util.ArrayList;

import engine.math.Vector3f;

/**
 * @author Frank
 *
 */
public class MenuManager {
	
		
	private MenuItem[][] MenuList;

	public MenuManager(Scene scene) {
	
		MenuList = new MenuItem[2][3];
		
		for (int i = 0; i < MenuList.length; ++i) {
	    
			for(int j = 0; j < MenuList[i].length; ++j) {
	        
				MenuList[i][j] = new MenuItem(scene,"plane",new Vector3f(i*2, j*3, -20), new Vector3f(90, 0, 0), 0.1f);
				MenuList[i][j].getMesh().setTexture(new Texture("Assets/Images/image.png"/*"menu_"+String.valueOf(i)+String.valueOf(j)*/));
				MenuList[i][j].setVisibility(false);
	        }
	     }
		
	}

	public void setVisible(boolean visible) {
		for (int i = 0; i < MenuList.length; ++i) {
			for(int j = 0; j < MenuList[i].length; ++j) {
	    		MenuList[i][j].setVisibility(visible);
	        }
	     }
		
	}
	
	public void addItemsToArray(ArrayList<Entity> itemsList) 
	{
		for (int i = 0; i < MenuList.length; ++i) {
    	    for(int j = 0; j < MenuList[i].length; ++j) {
    	    	itemsList.add(MenuList[i][j]);
    	}
		
	}
	}
	
	public void toggleVisibility() {
		for (int i = 0; i < MenuList.length; ++i) {
			for(int j = 0; j < MenuList[i].length; ++j) {
	    		MenuList[i][j].setVisibility(!MenuList[i][j].getVisibility());
	        }
	     }
		
	}
	
	/**
	 * @return the menuList
	 */
	public MenuItem[][] getMenuList() {
		return MenuList;
	}
	
	
	
	
	
	
	
	
	
	
	

}

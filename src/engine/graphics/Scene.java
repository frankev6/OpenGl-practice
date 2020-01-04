package engine.graphics;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.util.ArrayList;

import engine.math.Mathf;
import engine.math.Vector3f;
import game.core.Enemy;
import game.core.Gjerta;
import game.core.Magnus;

public class Scene {

    Window window;

    Camera cam;

    private ArrayList<Entity> GameObjects;
    private ArrayList<Entity> StaticItems;// dont check for collision or move with camera

    private float length;
    private float width = 20;

    ArrayList<Enemy> Enemies;

    MenuManager menu;
    
    Magnus magnus;
    Gjerta gjerta;
    Enemy enemyTest;
    Input input;
    Entity world = new Entity(this, "World", new Vector3f(0, -7, 5), new Vector3f(0, -90, 0), 3);
    Entity sky = new Entity(this, "plane", new Vector3f(0, 270, -500), new Vector3f(90, 180, 0), 50);

    public Scene(Window window) {

	this.window = window;
	cam = new Camera();
	// cam.setRoll(-45);
	cam.Translate(0, 10, 0);

	input = new Input(this.window);
	GameObjects = new ArrayList<Entity>();
	StaticItems = new ArrayList<Entity>();

	magnus = new Magnus(this, new Vector3f(-2, -3, 0), new Vector3f(0, 0, 0));
	gjerta = new Gjerta(this, new Vector3f(2, -3, 0), new Vector3f(0, 0, 0));
	enemyTest = new Enemy(this, new Vector3f(-15, -3, 0), new Vector3f(0, 90, 0));

	magnus.getEntity().getMesh().setTexture(new Texture("Assets/Images/BaseTexture.png"));
	gjerta.getEntity().getMesh().setTexture(new Texture("Assets/Images/BaseTexture.png"));
	enemyTest.getEntity().getMesh().setTexture(new Texture("Assets/Images/Dark-Green-Marble.jpg"));
	world.getMesh().setTexture(new Texture("Assets/Images/sky.jpeg"));
	sky.getMesh().setTexture(new Texture("Assets/Images/sky.jpeg"));

	GameObjects.add(world);

	StaticItems.add(sky);

	menu = new MenuManager(this);
	menu.addItemsToArray(StaticItems);
    }

    public void update() {

	Mathf.CalculateDeltaTime();
	
	magnus.checkCollision(gjerta);
	
	enemyTest.update(magnus, gjerta);

	input.checkInput();

	cam.move(magnus.getEntity().getPosition(), gjerta.getEntity().getPosition());

	draw();

    }

    public void draw() {

	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	for (Entity entity : GameObjects) {

	    if (entity.getVisibility()) {
		glPushMatrix();
		glTranslatef(entity.getPosition().x - cam.getPosition().x, entity.getPosition().y - cam.getPosition().y,
			entity.getPosition().z - cam.getPosition().z);
		glRotatef(entity.getRotation().x - cam.getRoll(), 1, 0, 0);
		glRotatef(entity.getRotation().y - cam.getPitch(), 0, 1, 0);
		glRotatef(entity.getRotation().z - cam.getYaw(), 0, 0, 1);
		glScalef(entity.getScale(), entity.getScale(), entity.getScale());
		entity.getMesh().draw();
		glPopMatrix();
	    }
	}
	for (Entity entity : StaticItems) {

	    if (entity.getVisibility()) {
		glPushMatrix();
		glTranslatef(entity.getPosition().x, entity.getPosition().y, entity.getPosition().z);
		glRotatef(entity.getRotation().x + cam.getRoll(), 1, 0, 0);
		glRotatef(entity.getRotation().y + cam.getPitch(), 0, 1, 0);
		glRotatef(entity.getRotation().z + cam.getYaw(), 0, 0, 1);
		glScalef(entity.getScale(), entity.getScale(), entity.getScale());
		entity.getMesh().draw();
		glPopMatrix();
	    }
	}

	glfwSwapBuffers(window.getWindowID()); // swap the color buffers

    }

    public float getLength() {
	return length;
    }

    public float getWidth() {
	return width;
    }

    public ArrayList<Entity> getGameObjects() {
	return GameObjects;
    }

    public ArrayList<Entity> getStaticItems() {
	return StaticItems;
    }

}

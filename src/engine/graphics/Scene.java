package engine.graphics;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import engine.math.Mathf;
import engine.math.Vector3f;
import game.core.Enemy;
import game.core.Gjerta;
import game.core.KnightEnemy;
import game.core.Magnus;
import game.core.Projectile;
import game.core.ArcherEnemy;
import game.core.Character;

public class Scene {

    Window window;

    Camera cam;

    private ArrayList<Entity> GameObjects;
    private ArrayList<Entity> StaticItems;// dont check for collision or move with camera
    private ArrayList<Character> Characters;

    private float length;
    private float width = 20;

    ArrayList<Enemy> Enemies;

    MenuManager menu;

    public Magnus magnus;
    public Gjerta gjerta;
    KnightEnemy enemyKnight;
    ArcherEnemy enemyArcher;
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
	Characters = new ArrayList<Character>();

	magnus = new Magnus(this, new Vector3f(-2, -3, 0), new Vector3f(0, 0, 0));
	gjerta = new Gjerta(this, new Vector3f(2, -3, 0), new Vector3f(0, 0, 0));
	enemyKnight = new KnightEnemy(this, new Vector3f(-15, -3, 0), new Vector3f(0, 90, 0));
	enemyArcher = new ArcherEnemy(this, new Vector3f(-20, -3, 0), new Vector3f(0, 90, 0));

	magnus.getMesh().setTexture(new Texture("Assets/Images/BaseTexture.png"));
	gjerta.getMesh().setTexture(new Texture("Assets/Images/BaseTexture.png"));
	enemyKnight.getMesh().setTexture(new Texture("Assets/Images/Dark-Green-Marble.jpg"));
	enemyArcher.getMesh().setTexture(new Texture("Assets/Images/Dark-Green-Marble.jpg"));
	world.getMesh().setTexture(new Texture("Assets/Images/sky.jpeg"));
	sky.getMesh().setTexture(new Texture("Assets/Images/sky.jpeg"));

	GameObjects.add(world);

	StaticItems.add(sky);

	menu = new MenuManager(this);
	menu.addItemsToArray(StaticItems);
    }

    public void update() {

	Mathf.CalculateDeltaTime();

	/*
	 * if (magnus.checkCollision(gjerta)) { System.out.println("too close buddy");
	 */
	input.checkInput();

	cam.move(magnus.getPosition(), gjerta.getPosition());

	draw();

    }

    public void draw() {

	glEnable(GL_LIGHTING);

	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	for (int i = 0; i < GameObjects.size(); i++) {

	    Entity entity = GameObjects.get(i);

	    if (entity.getVisibility()) {

		entity.update();

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
	GL11.glDisable(GL_LIGHTING);

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

    public ArrayList<Character> getCharacters() {
	return Characters;
    }

    public ArrayList<Entity> getStaticItems() {
	return StaticItems;
    }

}

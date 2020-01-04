package engine.graphics;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_TRUE;

import org.lwjgl.glfw.GLFW;

import engine.math.Mathf;
import game.core.Player;
import game.core.Player.LookDirection;

public class Input {

    private Window window;
    float distanceThreshold = 40.0f;
    float distance;
    private boolean leftButtonPressed;

    public Input(Window window) {
	this.window = window;
	createCallbacks();
    }

    private void createCallbacks() {
	
	glfwSetKeyCallback(window.getWindowID(), (window, key, scancode, action, mods) -> {
	    if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
		this.window.getScene().menu.toggleVisibility();
	    
	});

	glfwSetMouseButtonCallback(window.getWindowID(), (windowHandle, button, action, mode) -> {
	    leftButtonPressed = button == GLFW.GLFW_MOUSE_BUTTON_1 && action == GLFW.GLFW_PRESS;
	});

    }

    public void checkInput() {

	Player player1 = window.getScene().magnus;
	Player player2 = window.getScene().gjerta;

	distance = Mathf.LinearDistance(player1.getEntity().getPosition().x, player2.getEntity().getPosition().x);

	boolean isPlayer1behind = player1.getEntity().getPosition().x < player2.getEntity().getPosition().x ? true
		: false;

	// Input for Player1
	if (glfwGetKey(window.getWindowID(), GLFW_KEY_W) == GL_TRUE
		&& player1.getEntity().getPosition().z > -window.getScene().getWidth() / 2) {
	    player1.Look(LookDirection.up);
	    player1.Move();
	}
	// && player1.getEntity().getPosition().z > -terrainWidth
	if (glfwGetKey(window.getWindowID(), GLFW_KEY_S) == GL_TRUE
		&& player1.getEntity().getPosition().z < window.getScene().getWidth() / 2) {
	    player1.Look(LookDirection.down);
	    player1.Move();

	}
	if (glfwGetKey(window.getWindowID(), GLFW_KEY_A) == GL_TRUE
		&& (isPlayer1behind ? distance + player1.getMoveSpeed()
			: distance - player1.getMoveSpeed()) < distanceThreshold) {
	    player1.Look(LookDirection.left);
	    player1.Move();
	}
	if (glfwGetKey(window.getWindowID(), GLFW_KEY_D) == GL_TRUE
		&& (isPlayer1behind ? distance - player1.getMoveSpeed()
			: distance + player1.getMoveSpeed()) < distanceThreshold) {
	    player1.Look(LookDirection.right);
	    player1.Move();
	}

	if (glfwGetKey(window.getWindowID(), GLFW_KEY_Q) == GL_TRUE) {
	    player1.Attack();
	}
	if (glfwGetKey(window.getWindowID(), GLFW_KEY_E) == GL_TRUE) {

	}

	// Input for Player2
	if (glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_UP) == GL_TRUE
		&& player2.getEntity().getPosition().z > -10) {
	    player2.Look(LookDirection.up);
	    player2.Move();
	}
	if (glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_DOWN) == GL_TRUE
		&& player2.getEntity().getPosition().z < 10) {
	    player2.Look(LookDirection.down);
	    player2.Move();
	}
	if (glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_LEFT) == GL_TRUE
		&& (isPlayer1behind ? distance - player2.getMoveSpeed()
			: distance + player2.getMoveSpeed()) < distanceThreshold) {
	    player2.Look(LookDirection.left);
	    player2.Move();
	}
	if (glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_RIGHT) == GL_TRUE
		&& (isPlayer1behind ? distance + player2.getMoveSpeed()
			: distance - player2.getMoveSpeed()) < distanceThreshold) {
	    player2.Look(LookDirection.right);
	    player2.Move();
	}
	if (glfwGetKey(window.getWindowID(), GLFW_KEY_Q) == GL_TRUE) {

	}
	if (glfwGetKey(window.getWindowID(), GLFW_KEY_E) == GL_TRUE) {

	}

    }

}

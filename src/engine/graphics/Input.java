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
import game.core.Character.LookDirection;

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

	distance = Mathf.LinearDistance(player1.getPosition().x, player2.getPosition().x);

	boolean isPlayer1behind = player1.getPosition().x < player2.getPosition().x ? true : false;

	if (player1.getHealth() > 0) {
	    // Input for Player1
	    if (glfwGetKey(window.getWindowID(), GLFW_KEY_W) == GL_TRUE
		    && player1.getPosition().z > -window.getScene().getWidth() / 2) {
		player1.look(LookDirection.up);
		player1.move();
	    }
	    // && player1.getPosition().z > -terrainWidth
	    if (glfwGetKey(window.getWindowID(), GLFW_KEY_S) == GL_TRUE
		    && player1.getPosition().z < window.getScene().getWidth() / 2) {
		player1.look(LookDirection.down);
		player1.move();

	    }
	    if (glfwGetKey(window.getWindowID(), GLFW_KEY_A) == GL_TRUE
		    && (isPlayer1behind ? distance + player1.getMoveSpeed()
			    : distance - player1.getMoveSpeed()) < distanceThreshold) {
		player1.look(LookDirection.left);
		player1.move();
	    }
	    if (glfwGetKey(window.getWindowID(), GLFW_KEY_D) == GL_TRUE
		    && (isPlayer1behind ? distance - player1.getMoveSpeed()
			    : distance + player1.getMoveSpeed()) < distanceThreshold) {
		player1.look(LookDirection.right);
		player1.move();
	    }

	    if (glfwGetKey(window.getWindowID(), GLFW_KEY_Q) == GL_TRUE) {
		player1.attack();
	    }
	    if (glfwGetKey(window.getWindowID(), GLFW_KEY_E) == GL_TRUE) {

	    }
	}
	if (player2.getHealth() > 0) {
	    // Input for Player2
	    if (glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_UP) == GL_TRUE && player2.getPosition().z > -10) {
		player2.look(LookDirection.up);
		player2.move();
	    }
	    if (glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_DOWN) == GL_TRUE && player2.getPosition().z < 10) {
		player2.look(LookDirection.down);
		player2.move();
	    }
	    if (glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_LEFT) == GL_TRUE
		    && (isPlayer1behind ? distance - player2.getMoveSpeed()
			    : distance + player2.getMoveSpeed()) < distanceThreshold) {
		player2.look(LookDirection.left);
		player2.move();
	    }
	    if (glfwGetKey(window.getWindowID(), GLFW.GLFW_KEY_RIGHT) == GL_TRUE
		    && (isPlayer1behind ? distance + player2.getMoveSpeed()
			    : distance - player2.getMoveSpeed()) < distanceThreshold) {
		player2.look(LookDirection.right);
		player2.move();
	    }
	    if (glfwGetKey(window.getWindowID(), GLFW_KEY_Q) == GL_TRUE) {

	    }
	    if (glfwGetKey(window.getWindowID(), GLFW_KEY_E) == GL_TRUE) {

	    }
	}
    }

}

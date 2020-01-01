package engine.graphics;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_TRUE;

import java.sql.Time;

import org.lwjgl.glfw.GLFW;

import engine.math.Vector3f;
import game.core.Player;

public class PlayerInput {
    
    private long window;
    float distanceThreshold = 40.0f;
    float distance;
    
    public PlayerInput(long window) {
	this.window = window;
    }
    
    public void CheckInput(Player player1,Player player2) {	
	distance =  Math.abs(player1.getEntity().getPosition().x - player2.getEntity().getPosition().x);
	
	boolean isPlayer1behind = player1.getEntity().getPosition().x < player2.getEntity().getPosition().x ? true : false;
	
	// Input for Player1
	if (glfwGetKey(window, GLFW_KEY_W ) == GL_TRUE) {
	    player1.getEntity().Translate(0, 0, -player1.getMoveSpeed());
	    player1.getEntity().setRotation(new Vector3f(0,180,0));
	}
	if (glfwGetKey(window, GLFW_KEY_S) == GL_TRUE) {
	    player1.getEntity().Translate(0, 0, player1.getMoveSpeed());
	    player1.getEntity().setRotation(new Vector3f(0,0,0));
	}
	if (glfwGetKey(window, GLFW_KEY_A) == GL_TRUE && (isPlayer1behind ? distance+player1.getMoveSpeed() : distance-player1.getMoveSpeed()) < distanceThreshold) {
	    player1.getEntity().Translate(-player1.getMoveSpeed(), 0, 0);
	    player1.getEntity().setRotation(new Vector3f(0,-90,0));
	}
	if (glfwGetKey(window, GLFW_KEY_D) == GL_TRUE && (isPlayer1behind ? distance-player1.getMoveSpeed() : distance+player1.getMoveSpeed()) < distanceThreshold) {
	    player1.getEntity().Translate(player1.getMoveSpeed(), 0, 0);
	    player1.getEntity().setRotation(new Vector3f(0,90,0));
	}
	
	if (glfwGetKey(window, GLFW_KEY_Q) == GL_TRUE) {

	}
	if (glfwGetKey(window, GLFW_KEY_E) == GL_TRUE) {

	}

	
	// Input for Player2
	if (glfwGetKey(window, GLFW.GLFW_KEY_UP) == GL_TRUE) {
	    player2.getEntity().Translate(0, 0, -player2.getMoveSpeed());
	    player2.getEntity().setRotation(new Vector3f(0,180,0));
	}
	if (glfwGetKey(window, GLFW.GLFW_KEY_DOWN) == GL_TRUE) {
	    player2.getEntity().Translate(0, 0, player2.getMoveSpeed());
	    player2.getEntity().setRotation(new Vector3f(0,0,0));
	}
	if (glfwGetKey(window, GLFW.GLFW_KEY_LEFT) == GL_TRUE && (isPlayer1behind ? distance-player2.getMoveSpeed() : distance+player2.getMoveSpeed()) < distanceThreshold) {
	    player2.getEntity().Translate(-player2.getMoveSpeed(), 0, 0);
	    player2.getEntity().setRotation(new Vector3f(0,-90,0));
	}
	if (glfwGetKey(window, GLFW.GLFW_KEY_RIGHT) == GL_TRUE && (isPlayer1behind ? distance+player2.getMoveSpeed() : distance-player2.getMoveSpeed()) < distanceThreshold) {
	    player2.getEntity().Translate(player2.getMoveSpeed(), 0, 0);
	    player2.getEntity().setRotation(new Vector3f(0,90,0));
	}
	if (glfwGetKey(window, GLFW_KEY_Q) == GL_TRUE) {

	}
	if (glfwGetKey(window, GLFW_KEY_E) == GL_TRUE) {

	}
    }
}

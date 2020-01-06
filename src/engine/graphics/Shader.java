package engine.graphics;

import static org.lwjgl.glfw.GLFW.GLFW_SAMPLES;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_DIFFUSE;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_LIGHT_MODEL_AMBIENT;
import static org.lwjgl.opengl.GL11.GL_LIGHT_MODEL_LOCAL_VIEWER;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_POSITION;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SPOT_CUTOFF;
import static org.lwjgl.opengl.GL11.GL_SPOT_DIRECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_STENCIL_TEST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glFrustum;
import static org.lwjgl.opengl.GL11.glLightModelfv;
import static org.lwjgl.opengl.GL11.glLightModeli;
import static org.lwjgl.opengl.GL11.glLightf;
import static org.lwjgl.opengl.GL11.glLightfv;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Shader {

    public Shader() {
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	setPerspective((float) (Math.toRadians(40)), Window.getWindowWidth() / Window.getWindowHeight(), 0.01f, 700f);

	glEnable(GL_TEXTURE_2D); // enable texture mapping
	glEnable(GL_SMOOTH);
	glEnable(GL_DEPTH_TEST);
	glEnable(GL_STENCIL_TEST);
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	// glEnable(GL_CULL_FACE);
	// glCullFace(GL_BACK);
	glfwWindowHint(GLFW_SAMPLES, 4);

	// set up lighting
	FloatBuffer ambient = BufferUtils.createFloatBuffer(4);
	ambient.put(new float[] { 0.4f, 1f, 0.4f, 1f, });
	ambient.flip();

	FloatBuffer specular = BufferUtils.createFloatBuffer(4);
	specular.put(new float[] { 1f, 1f, 1f, 1f, });
	specular.flip();

	FloatBuffer position = BufferUtils.createFloatBuffer(4);
	position.put(new float[] { 0f, 5f, -5f, 1f, });
	position.flip();

	FloatBuffer spot_dir = BufferUtils.createFloatBuffer(4);
	spot_dir.put(new float[] { 0f, -5f, -5f, 0f, });
	spot_dir.flip();

	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);
	// Intensity of ambient light (RGBA)
	glLightModelfv(GL_LIGHT_MODEL_AMBIENT, ambient);
	// Calculation of specular reflection angles (local or infinite viewer?)
	glLightModeli(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_FALSE);
	glLightfv(GL_LIGHT0, GL_POSITION, position);
	glLightfv(GL_LIGHT0, GL_DIFFUSE, specular);
	glLightfv(GL_LIGHT0, GL_SPOT_DIRECTION, spot_dir);
	glLightf(GL_LIGHT0, GL_SPOT_CUTOFF, 1000.0f);
	// Set the clear color
	glClearColor(0.445f, 0.732f, 0.8f, 0.0f);
    }
    public static void setPerspective(float fovy, float aspect, float near, float far) {
	float bottom = -near * (float) Math.tan(fovy / 2);
	float top = -bottom;
	float left = aspect * bottom;
	float right = -left;
	glFrustum(left, right, bottom, top, near, far);
    }
}

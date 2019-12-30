package engine.graphics;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import engine.math.Vector3Df;
import game.core.Magnus;

import java.nio.*;
import java.util.ArrayList;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    // The window handle
    private long window;
    // Camera cam;
    private static final int WINDOW_WIDTH = 900;
    private static final int WINDOW_HEIGHT = 900;
    double fps = 50;

    float angle = 0;

    ArrayList<Entity> GameObjects;
    Magnus magnus;

    public void run() {
	System.out.println("LWJGL " + Version.getVersion());

	init();
	// This line is critical for LWJGL's interoperation with GLFW's
	// OpenGL context, or any context that is managed externally.
	// LWJGL detects the context that is current in the current thread,
	// creates the GLCapabilities instance and makes the OpenGL
	// bindings available for use.
	GL.createCapabilities();

	System.out.println("OpenGL version " + glGetString(GL_VERSION));

	loop();

	// Free the window callbacks and destroy the window
	glfwFreeCallbacks(window);
	glfwDestroyWindow(window);

	// Terminate GLFW and free the error callback
	glfwTerminate();
	glfwSetErrorCallback(null).free();
    }

    private void init() {
	// Setup an error callback. The default implementation
	// will print the error message in System.err.
	GLFWErrorCallback.createPrint(System.err).set();

	// Initialize GLFW. Most GLFW functions will not work before doing this.
	if (!glfwInit())
	    throw new IllegalStateException("Unable to initialize GLFW");

	// Configure GLFW
	glfwDefaultWindowHints(); // optional, the current window hints are already the default
	glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
	glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

	// Create the window
	window = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "Hello World!", NULL, NULL);
	if (window == NULL)
	    throw new RuntimeException("Failed to create the GLFW window");

	// Setup a key callback. It will be called every time a key is pressed, repeated
	// or released.
	glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
	    if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
		glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
	});

	// Get the thread stack and push a new frame
	try (MemoryStack stack = stackPush()) {
	    IntBuffer pWidth = stack.mallocInt(1); // int*
	    IntBuffer pHeight = stack.mallocInt(1); // int*

	    // Get the window size passed to glfwCreateWindow
	    glfwGetWindowSize(window, pWidth, pHeight);

	    // Get the resolution of the primary monitor
	    GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

	    // Center the window
	    glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
	} // the stack frame is popped automatically

	// Make the OpenGL context current
	glfwMakeContextCurrent(window);
	// Enable v-sync
	glfwSwapInterval(1);

	// your code to initialize the scene goes here...

	// Make the window visible
	glfwShowWindow(window);
    }

    // sets a perspective projection
    public static void setPerspective(float fovy, float aspect, float near, float far) {
	float bottom = -near * (float) Math.tan(fovy / 2);
	float top = -bottom;
	float left = aspect * bottom;
	float right = -left;
	glFrustum(left, right, bottom, top, near, far);
    }

    private void loop() {

	GameObjects = new ArrayList<Entity>();
	magnus = new Magnus(new Vector3Df(0, -3, -20), new Vector3Df(0, 0, 0));
	GameObjects.add(magnus.getEntity());

	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	setPerspective((float) (Math.toRadians(40)), WINDOW_WIDTH / WINDOW_HEIGHT, 0.01f, 100f);

	glEnable(GL_TEXTURE_2D); // enable texture mapping
	glEnable(GL_SMOOTH);
	glEnable(GL_DEPTH_TEST);

	// set up lighting
	FloatBuffer ambient = BufferUtils.createFloatBuffer(4);
	ambient.put(new float[] { 0.6f, 0.65f, 0.65f, 1f, });
	ambient.flip();

	FloatBuffer specular = BufferUtils.createFloatBuffer(4);
	specular.put(new float[] { 0.8f, 0.8f, 0.8f, 1f, });
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
	glLightf(GL_LIGHT0, GL_SPOT_CUTOFF, 45.0f);
	// Set the clear color
	glClearColor(0.4f, 0.4f, 0.4f, 0.0f);

	// Run the rendering loop until the user has attempted to close
	// the window or has pressed the ESCAPE key.
	while (!glfwWindowShouldClose(window)) {

	    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

	    input();

	    glMatrixMode(GL_MODELVIEW);
	    glLoadIdentity();

	    for (Entity entity : GameObjects) {

		if (entity.getVisibility()) {
		    glPushMatrix();
		    glTranslatef(entity.getPosition().x, entity.getPosition().y, entity.getPosition().z);
		    glRotatef(angle++, 0, 1, 0);
		    entity.getMesh().draw();
		    glPopMatrix();
		}
	    }

	    glfwSwapBuffers(window); // swap the color buffers

	    // Poll for window events. The key callback above will only be
	    // invoked during this call.
	    glfwPollEvents();
	}
    }

    private void input() {

	if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GL_TRUE) {
	    glfwSetWindowShouldClose(window, true);
	}

	// Input for Player1
	if (glfwGetKey(window, GLFW_KEY_W) == GL_TRUE) {

	}
	if (glfwGetKey(window, GLFW_KEY_S) == GL_TRUE) {

	}
	if (glfwGetKey(window, GLFW_KEY_A) == GL_TRUE) {

	}
	if (glfwGetKey(window, GLFW_KEY_D) == GL_TRUE) {

	}
	if (glfwGetKey(window, GLFW_KEY_Q) == GL_TRUE) {

	}
	if (glfwGetKey(window, GLFW_KEY_E) == GL_TRUE) {

	}
	if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GL_TRUE) {
	    glfwSetWindowShouldClose(window, true);
	}

	// Input for Player1
	if (glfwGetKey(window, GLFW_KEY_W) == GL_TRUE) {

	}
	if (glfwGetKey(window, GLFW_KEY_S) == GL_TRUE) {

	}
	if (glfwGetKey(window, GLFW_KEY_A) == GL_TRUE) {

	}
	if (glfwGetKey(window, GLFW_KEY_D) == GL_TRUE) {

	}
	if (glfwGetKey(window, GLFW_KEY_Q) == GL_TRUE) {

	}
	if (glfwGetKey(window, GLFW_KEY_E) == GL_TRUE) {

	}

    }

    public static void main(String[] args) {
	new Window().run();
    }

}
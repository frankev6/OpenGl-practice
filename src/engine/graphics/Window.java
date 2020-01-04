package engine.graphics;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.IntBuffer;

public class Window {

    // The window handle
    private long windowID;

    private static final int WINDOW_WIDTH = 900;
    private static final int WINDOW_HEIGHT = 900;
    double fps = 60;

    Shader shader;
    private Scene scene;

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
	glfwFreeCallbacks(windowID);
	glfwDestroyWindow(windowID);

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
	glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will be resizable

	// Create the window
	windowID = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "Frank's Culminating", NULL, NULL);
	if (windowID == NULL)
	    throw new RuntimeException("Failed to create the GLFW window");

	// Setup a key callback. It will be called every time a key is pressed, repeated
	// or released.

	// Get the thread stack and push a new frame
	try (MemoryStack stack = stackPush()) {
	    IntBuffer pWidth = stack.mallocInt(1); // int*
	    IntBuffer pHeight = stack.mallocInt(1); // int*

	    // Get the window size passed to glfwCreateWindow
	    glfwGetWindowSize(windowID, pWidth, pHeight);

	    // Get the resolution of the primary monitor
	    GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

	    // Center the window
	    glfwSetWindowPos(windowID, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
	} // the stack frame is popped automatically

	// Make the OpenGL context current
	glfwMakeContextCurrent(windowID);
	// Enable v-sync
	glfwSwapInterval(1);

	// your code to initialize the scene goes here...

	// Make the window visible
	glfwShowWindow(windowID);
	// glfwMaximizeWindow(window);
    }

    private void loop() {

	shader = new Shader();

	scene = new Scene(this);

	// Run the rendering loop until the user has attempted to close
	// the window or has pressed the ESCAPE key.
	while (!glfwWindowShouldClose(windowID)) {

	    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

	    scene.update();

	    // Poll for window events. The key callback above will only be
	    // invoked during this call.
	    glfwPollEvents();
	}
    }

    public static int getWindowWidth() {
	return WINDOW_WIDTH;
    }

    public static int getWindowHeight() {
	return WINDOW_HEIGHT;
    }

    public long getWindowID() {
	return windowID;
    }

    public Scene getScene() {
	return scene;
    }

    public static void main(String[] args) {
	new Window().run();
    }

}
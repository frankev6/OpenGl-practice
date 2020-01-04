package engine.graphics;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;

public class MousePicker {
	
	private double Xpos;
	private double Ypos;
	
	
	public double getX() {
		return Xpos;
	}


	public double getY() {
		return Ypos;
	}


	public MousePicker(long windowID) {
		DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
		GLFW.glfwGetCursorPos(windowID, xBuffer, yBuffer);
		this.Xpos = xBuffer.get(0);
		this.Ypos = yBuffer.get(0);
	}
	
	

}

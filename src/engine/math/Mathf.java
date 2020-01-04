package engine.math;

public class Mathf {

    // One second goes from 0.0 to 1 then resets
    private static float deltaTime = 0.0f;
    // lastTime recorded with nanoTime
    private static float lastTime;

    public static float LinearDistance(float d1, float d2) {
	return Math.abs(d1 - d2);
    }

    public static float Distance(float x1, float y1, float x2, float y2) {
	float dx = x1-x2;
	float dy = y1-y2;
	
	float distance = (float) Math.sqrt(dx*dx + dy*dy);
	
	return distance;
    }

    public static void CalculateDeltaTime() {

	long time = System.nanoTime();
	deltaTime += ((float) (time - lastTime) / 1000000000.0f);
	lastTime = time;
	if (deltaTime > 1) {
	    deltaTime = 0;
	}
    }

    public static float getDeltaTime() {
	return deltaTime;
    }

}

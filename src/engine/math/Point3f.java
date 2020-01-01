package engine.math;

public class Point3f {
    private float x;
    private float y;
    private float z;
    private float w;

    public Point3f(float x, float y, float z) {
	this.x = x;
	this.y = y;
	this.z = z;
	w = 1;
    }

    public Point3f() {
	this.x = 0;
	this.y = 0;
	this.z = 0;
	w = 1;
    }

    /**
     * Subtracts this point from another Point p
     * 
     * @param p
     * @return a vector (this.p - p)
     */
    public Vector3f sub(Point3f p) {
	return new Vector3f(x - p.getX(), y - p.getY(), z - p.getZ());
    }

    public Point3f add(Vector3f v) {
	return new Point3f(x + v.getX(), y + v.getY(), z + v.getZ());
    }

    public float getX() {
	return x;
    }

    public void setX(float x) {
	this.x = x;
    }

    public float getY() {
	return y;
    }

    public void setY(float y) {
	this.y = y;
    }

    public float getZ() {
	return z;
    }

    public void setZ(float z) {
	this.z = z;
    }

    public String toString() {
	return "[" + x + ", " + y + ", " + z + "]";
    }
}

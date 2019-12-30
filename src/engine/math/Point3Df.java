package engine.math;

public class Point3Df {
    private float x;
    private float y;
    private float z;
    private float w;

    public Point3Df(float x, float y, float z) {
	this.x = x;
	this.y = y;
	this.z = z;
	w = 1;
    }

    public Point3Df() {
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
    public Vector3Df sub(Point3Df p) {
	return new Vector3Df(x - p.getX(), y - p.getY(), z - p.getZ());
    }

    public Point3Df add(Vector3Df v) {
	return new Point3Df(x + v.getX(), y + v.getY(), z + v.getZ());
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

package engine.math;

public class Vector3f {
    public float x;
    public float y;
    public float z;
    public float w;

    public Vector3f(float x, float y, float z) {
	this.x = x;
	this.y = y;
	this.z = z;
	w = 0;
    }

    public Vector3f(float x, float y, float z, float w) {
	this.x = x;
	this.y = y;
	this.z = z;
	this.w = w;
    }

    public Vector3f add(Vector3f v) {
	return new Vector3f(x + v.getX(), y + v.getY(), z + v.getZ());
    }

    public Vector3f sub(Vector3f v) {
	return add(v.scale(-1));
    }

    /**
     * Returns the dot product of this vector with a given vector v
     * 
     * @param v
     * @return
     */
    public float dot(Vector3f v) {
	return x * v.getX() + y * v.getY() + z * v.getZ();
    }

    /**
     * Returns the cross product of this vector with a given vector v v1.y * v2.z -
     * v1.z*v2.y, v1.z * v2.x - v1.x*v2.z, v1.x * v2.y - v1.y*v2.x
     * 
     * @param v
     */
    public Vector3f cross(Vector3f v) {
	return new Vector3f(y * v.getZ() - z * v.getY(), z * v.getX() - x * v.getZ(), x * v.getY() - y * v.getX());
    }

    public Vector3f normal() {
	return scale(1 / length());
    }

    public Vector3f scale(float a) {
	return new Vector3f(a * x, a * y, a * z);
    }

    public float length() {
	return (float) Math.sqrt(x * x + y * y + z * z);
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

    public float getW() {
	return w;
    }

    public String toString() {
	return "[" + x + ", " + y + ", " + z + "]";
    }
}

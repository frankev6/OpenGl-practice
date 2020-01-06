package engine.graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import engine.math.Point3f;
import engine.math.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class Mesh {
    private ArrayList<Point3f> vertex;
    private ArrayList<Float[]> texCoords;
    private ArrayList<Vector3f> normals;

    private ArrayList<Face> faces; // indices into the vertices array list

    private Texture texture;

    public Mesh() {
	vertex = new ArrayList();
	texCoords = new ArrayList();
	normals = new ArrayList();
	faces = new ArrayList();
    }

    public void draw() {

	glEnable(GL_TEXTURE_2D);

	if (texture == null) // enable texture mapping
	    texture = new Texture("Assets/Images/BaseTexture.png");

	texture.bind();

	for (int i = 0; i < faces.size(); i++) {
	    glBegin(GL_POLYGON);
	    for (int j = 0; j < faces.get(i).getSize(); j++) {
		Float[] tex = texCoords.get(faces.get(i).getTextureIndex(j) - 1);
		glTexCoord2f(tex[0], tex[1]);
		Point3f p = vertex.get(faces.get(i).getVertexIndex(j) - 1);
		glVertex3f(p.getX(), p.getY(), p.getZ());
		Vector3f v = normals.get(faces.get(i).getNormalIndex(j) - 1);
		glNormal3f(v.getX(), v.getY(), v.getZ());
	    }
	    glEnd();
	}
    }

    public Mesh(String filename) {
	this();
	try {
	    Scanner scan = new Scanner(new File(filename));
	    while (scan.hasNextLine()) {
		String line = scan.nextLine();
		String[] lineArray = line.split(" ");
		if (lineArray[0].equals("v")) {
		    Point3f point = new Point3f(Float.parseFloat(lineArray[1]), Float.parseFloat(lineArray[2]),
			    Float.parseFloat(lineArray[3]));
		    vertex.add(point);
		} else if (lineArray[0].equals("vt")) {
		    Float[] f = new Float[2];
		    f[0] = Float.parseFloat(lineArray[1]);
		    f[1] = Float.parseFloat(lineArray[2]);
		    texCoords.add(f);
		} else if (lineArray[0].equals("vn")) {
		    Vector3f vector = new Vector3f(Float.parseFloat(lineArray[1]), Float.parseFloat(lineArray[2]),
			    Float.parseFloat(lineArray[3]));
		    normals.add(vector);
		} else if (lineArray[0].equals("f")) {
		    Face f = new Face(lineArray.length - 1);

		    for (int i = 1; i < lineArray.length; i++) {
			String[] attribString = lineArray[i].split("/");
			int[] attrib = new int[3];
			attrib[0] = Integer.parseInt(attribString[0]);
			attrib[1] = Integer.parseInt(attribString[1]);
			attrib[2] = Integer.parseInt(attribString[2]);

			f.setVertex(i - 1, attrib);
		    }
		    faces.add(f);
		} 
	    }
	    scan.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    
}

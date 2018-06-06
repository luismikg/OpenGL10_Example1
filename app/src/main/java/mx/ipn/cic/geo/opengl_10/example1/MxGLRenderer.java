package mx.ipn.cic.geo.opengl_10.example1;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class MxGLRenderer implements Renderer {

    // Triangle instance 
    private MxGLTriangle triangle;
    private MxGLTriangle triangle_2;
    // Square instance
    private MxGLSquare square;

    // Instance the Triangle and Square objects
    public MxGLRenderer() {
        this.triangle = new MxGLTriangle();
        this.triangle_2 = new MxGLTriangle();
        this.square = new MxGLSquare();
    }

    // The Surface is created/init()
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {		
        gl.glShadeModel(GL10.GL_SMOOTH);            // Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Black Background
        gl.glClearDepthf(1.0f);                  // Depth Buffer Setup
        gl.glEnable(GL10.GL_DEPTH_TEST);            // Enables Depth Testing
        gl.glDepthFunc(GL10.GL_LEQUAL);             // The Type Of Depth Testing To Do

        // Really nice perspective Calculations
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 
    }

    // Here we do our drawing
    public void onDrawFrame(GL10 gl) {
        // Clear screen and depth buffer
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	
        gl.glLoadIdentity( );           // Reset the current Modelview Matrix

        /*
         * Minor changes to the original documentation
         * 
         * Instead of drawing our objects here, we fire their own drawing methods on
         * the current instance.
         */
        gl.glTranslatef(0.0f, -0.0f, -12.0f);       // Move down 1.2 unit and into the Screen 6.0
        this.square.draw(gl);                      // Draw the square
        gl.glLoadIdentity( );

        gl.glTranslatef(0.0f, -2.5f, -12.0f);      // Move down 2.5 units
        this.triangle.draw(gl);                    // Draw the triangle
        gl.glLoadIdentity( );

        gl.glTranslatef(0.0f, 2.5f, -12.0f);         // Move up 2.5 units
        this.triangle_2.draw(gl);                    // Draw the triangle
    }

    // If the surface changes, reset the view
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if(height == 0) {                          // Prevent a divide by zero by
            height = 1;                            // Making height equal one
        }

        gl.glViewport(0, 0, width, height);        // Reset the current viewport
        gl.glMatrixMode(GL10.GL_PROJECTION);       // Select the projection Matrix
        gl.glLoadIdentity();                       // Reset the projection Matrix

        // Calculate the aspect ratio of the window
        GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);        // Select the Modelview Matrix
        gl.glLoadIdentity();                       // Reset the Modelview Matrix
    }
}
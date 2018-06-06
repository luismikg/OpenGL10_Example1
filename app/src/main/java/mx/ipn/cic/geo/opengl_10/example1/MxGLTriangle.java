package mx.ipn.cic.geo.opengl_10.example1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

// This class is an object representation of a Triangle containing
// the vertex information and drawing functionality, which is called
// by the renderer.
public class MxGLTriangle {
    
    // The buffer holding the vertices
    private FloatBuffer vertexBuffer;
    
    // The initial vertex definition
    private float vertices[] = { 
            0.0f, 1.0f, 0.0f,   //Top
            -1.0f, -1.0f, 0.0f, //Bottom Left
            1.0f, -1.0f, 0.0f   //Bottom Right
    };
    
    // The Triangle constructor. Initiate the buffers.
    public MxGLTriangle() {
        
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(this.vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        this.vertexBuffer = byteBuf.asFloatBuffer();
        this.vertexBuffer.put(this.vertices);
        this.vertexBuffer.position(0);
    }

    // The object own drawing function.
    // Called from the renderer to redraw this instance with possible changes
    // in values.
    // @param gl - The GL context
    public void draw(GL10 gl) {
        // Set the face rotation
        gl.glFrontFace(GL10.GL_CW);
        
        // Point to our vertex buffer
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.vertexBuffer);
        
        // Enable vertex buffer
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        
        // Draw the vertices as triangle strip
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, this.vertices.length / 3);
        
        // Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
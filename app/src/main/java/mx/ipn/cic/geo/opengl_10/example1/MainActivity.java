package mx.ipn.cic.geo.opengl_10.example1;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

// The initial Android Activity, setting and initiating the OpenGL ES Renderer Class
public class MainActivity extends Activity {

    // The OpenGL View
    private GLSurfaceView glSurface;

    // Initiate the OpenGL View and set our own Renderer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create an Instance with this Activity
        this.glSurface = new GLSurfaceView(this);
        // Set our own Renderer
        this.glSurface.setRenderer(new MxGLRenderer());
        // Set the GLSurface as View to this Activity
        setContentView(this.glSurface);
    }

    // Remember to resume the glSurface
    @Override
    protected void onResume() { 
        super.onResume();
        this.glSurface.onResume();
    }

    // Also pause the glSurface
    @Override
    protected void onPause() {
        super.onPause();
        glSurface.onPause();
    }
}
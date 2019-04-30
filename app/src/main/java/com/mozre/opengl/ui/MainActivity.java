package com.mozre.opengl.ui;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mozre.opengl.R;
import com.mozre.opengl.gl.CustomGLSurfaceView;
import com.mozre.opengl.gl.GLMainNative;
import com.mozre.opengl.gl.SampleRenderer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MOZRE";
    private GLSurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLSurfaceView = new CustomGLSurfaceView(this);
        setContentView(mGLSurfaceView);
        Log.d(TAG, "onCreate: " + new GLMainNative().helloworld());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }
}
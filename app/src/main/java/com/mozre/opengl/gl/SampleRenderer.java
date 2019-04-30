package com.mozre.opengl.gl;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SampleRenderer implements GLSurfaceView.Renderer {
    private int mProgramShaderId = -1;
    private TriangleGL mTriangleGL;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        if (mProgramShaderId != -1 && mTriangleGL != null) {
            mTriangleGL.drawTriangle(mProgramShaderId);
        }
    }

    public void init () {
        if (mTriangleGL == null) {
            mTriangleGL = new TriangleGL();
            mProgramShaderId = mTriangleGL.initTriangle();
        }
    }
    public void destroy () {
        if(mTriangleGL != null) {
            mTriangleGL.uninit(mProgramShaderId);
            mTriangleGL = null;
        }
    }
}

package com.mozre.opengl.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class CustomGLSurfaceView extends GLSurfaceView {
    private SampleRenderer mSampleRenderer;

    public CustomGLSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        mSampleRenderer = new SampleRenderer();
        setRenderer(mSampleRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public void onResume() {
        super.onResume();
        queueEvent(new Runnable() {
            @Override
            public void run() {
                mSampleRenderer.init();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        queueEvent(new Runnable() {
            @Override
            public void run() {
                mSampleRenderer.destroy();
            }
        });
    }
}

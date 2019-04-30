package com.mozre.opengl.gl;

import android.opengl.GLES20;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class TriangleGL {
    private static final String TAG = "TriangleGL";
    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";
    private static final float mVertices[] = {
            0.5f, 0.5f, 0.0f,  // Top Right
            0.5f, -0.5f, 0.0f,  // Bottom Right
            -0.5f, -0.5f, 0.0f  // Bottom Left
    };
    private static final float mColors[] = {
            1.0f, 1.0f, 1.0f, 1.0f
    };
    private static final int COORDS_RPE_VETEIX = 3;

    public static final int DRAW_TRIANGLE_FAIL_CODE = -1;

    public int initTriangle() {
        int verticesShader = loadShader(vertexShaderCode, GLES20.GL_VERTEX_SHADER);
        int fragmentShader = loadShader(fragmentShaderCode, GLES20.GL_FRAGMENT_SHADER);
        int programId = GLES20.glCreateProgram();
        GLES20.glAttachShader(programId, verticesShader);
        GLES20.glAttachShader(programId, fragmentShader);
        GLES20.glLinkProgram(programId);
        int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(programId, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) {
            Log.e(TAG, "drawTriangle: link gl program fail! log: " + GLES20.glGetProgramInfoLog(programId));
            return DRAW_TRIANGLE_FAIL_CODE;
        }
        GLES20.glDeleteShader(verticesShader);
        GLES20.glDeleteShader(fragmentShader);
        return programId;
    }

    public void drawTriangle(int programId) {
        GLES20.glClearColor(0f, 0f, 0f, 1f);
        ByteBuffer buffer = ByteBuffer.allocateDirect(mVertices.length * 4);
        buffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = buffer.asFloatBuffer();
        floatBuffer.put(mVertices);
        floatBuffer.position(0);
        GLES20.glViewport(0, 0, 960, 1280);
        GLES20.glUseProgram(programId);
        int position = GLES20.glGetAttribLocation(programId, "vPosition");
        GLES20.glEnableVertexAttribArray(position);
        GLES20.glVertexAttribPointer(position, COORDS_RPE_VETEIX, GLES20.GL_FLOAT, false, COORDS_RPE_VETEIX * 4, floatBuffer);
        int color = GLES20.glGetUniformLocation(programId, "vColor");
        GLES20.glUniform4fv(color, 1, mColors, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
        GLES20.glDisableVertexAttribArray(position);
    }

    private static int loadShader(String shaderSource, int sType) {
        int shaderId = GLES20.glCreateShader(sType);
        GLES20.glShaderSource(shaderId, shaderSource);
        GLES20.glCompileShader(shaderId);
        int[] compileRes = new int[1];
        GLES20.glGetShaderiv(shaderId, GLES20.GL_COMPILE_STATUS, compileRes, 0);
        if (compileRes[0] == 0) {
            Log.e(TAG, "loadShader: compile shader fail , log: " + GLES20.glGetShaderInfoLog(shaderId));
            return DRAW_TRIANGLE_FAIL_CODE;
        }
        return shaderId;
    }

    public void uninit(int mProgramShaderId) {
        GLES20.glDeleteProgram(mProgramShaderId);
    }
}

package com.mozre.opengl.gl;

public class GLMainNative {

   static {
      System.loadLibrary("native_gl");
   }

   public native String helloworld();

}

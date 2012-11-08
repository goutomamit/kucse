package sample.first.hello;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;
public class GL1Renderer implements GLSurfaceView.Renderer{

	private int w, h;

	private FloatBuffer frameVertices;

	private ByteBuffer diagIndices;
	@Override
	public void onDrawFrame(GL10 gl) {
	    gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	    gl.glVertexPointer(10, GL10.GL_FLOAT, 8, frameVertices);
	    gl.glColor4f(1f, 0f, 0f, 1f);
	    gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 5);
	    gl.glDrawElements(GL10.GL_LINES, 1, GL10.GL_UNSIGNED_BYTE, diagIndices);
	    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		w = width; h =  height;
		Log.w("Surface","its really changed");
		 gl.glVertexPointer(2, GL10.GL_FLOAT, 0, frameVertices);
		    gl.glColor4f(1f, 0f, 0f, 0.5f);
		    gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 4);
		    gl.glDrawElements(GL10.GL_LINES, 1, GL10.GL_UNSIGNED_BYTE, diagIndices);
		    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		// TODO Auto-generated method stub

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
	    w = 400; h =  200;
	  //  TRC.debug("w = " + w + ", h = " + h);
	    gl.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
	    gl.glViewport(0, 0, w, h);
	    gl.glDepthRangex(1, -1);    // TODO remove
	    gl.glMatrixMode(GL10.GL_PROJECTION);
	    gl.glLoadIdentity();
	    gl.glOrthof(0, w, 0, h, 1, -1);

	    float[] frame = {
	        10, 10,
	        w-1, 10,
	        w+100,h-100,
	        w-1, h-1,
	        10, h-1 };
	    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(frame.length * 4);
	    byteBuffer.order(ByteOrder.nativeOrder());
	    frameVertices = byteBuffer.asFloatBuffer();
	    frameVertices.put(frame);
	    frameVertices.flip();
	    frameVertices.position(0);
	    gl.glLineWidthx(10);
	    gl.glPointSize(6);
	    gl.glMatrixMode(GL10.GL_MODELVIEW);
	    gl.glLoadIdentity();
	    gl.glDisable(GL10.GL_DEPTH_TEST);
	    diagIndices = ByteBuffer.allocateDirect(2);
	    diagIndices.order(ByteOrder.nativeOrder());
	    diagIndices.put(new byte[] {0, 10});
	    diagIndices.flip();
	}

	}

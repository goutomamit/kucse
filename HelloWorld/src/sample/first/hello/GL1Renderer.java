package sample.first.hello;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageButton;
public class GL1Renderer implements GLSurfaceView.Renderer{

	private int w, h;
	public GL10 gltoDraw;
	private FloatBuffer frameVertices;
	public static Context contxt;
	private static final int START_ANIMATION =1;
	private ByteBuffer diagIndices;
	
	Handler handleAnimation = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case START_ANIMATION:
				Log.w("Animation","Started");
				break;
			}
			super.handleMessage(msg);
		}
	};
	@Override
	public void onDrawFrame(GL10 gl) {
	
	    gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//	    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//	    gl.glVertexPointer(10, GL10.GL_FLOAT, 8, frameVertices);
	    gl.glColor4f(1f, 0f, 0f, 1f);
//	    gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 5);
//	    gl.glDrawElements(GL10.GL_LINES, 1, GL10.GL_UNSIGNED_BYTE, diagIndices);
	    drawCube(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		w = width; h =  height;
		Log.w("Surface","its really changed");
	
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(4, GL10.GL_FLOAT, 0, frameVertices);
		    gl.glColor4f(1f, 0f, 0f, 0.5f);
		    gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 4);
//			drawCube(gl);
//		    gl.glDrawElements(GL10.GL_LINES, 1, GL10.GL_UNSIGNED_BYTE, diagIndices);
//		    gl.glMatrixMode(GL10.GL_TEXTURE); 
//		    gl.glTranslatef(10, 15, 0);
		    
		    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		w = 800; h =  480;
		gltoDraw = gl;
	  //  TRC.debug("w = " + w + ", h = " + h);
	    gl.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
	    gl.glViewport(0, 0, w, h);
	    gl.glDepthRangex(1, -1);    // TODO remove
	    gl.glMatrixMode(GL10.GL_PROJECTION);
	    gl.glLoadIdentity();
	    gl.glOrthof(0, w, 0, h, 1, -1);
	    
	    frameVertices = createBufferForPoints(pointsArray()).asFloatBuffer();
	    frameVertices.put(pointsArray());
	    frameVertices.flip();
	    frameVertices.position(0);
	    gl.glLineWidthx(10);
	    gl.glEnable(GL10.GL_POINT_SIZE);
	    gl.glPointSize(6);
	    gl.glMatrixMode(GL10.GL_MODELVIEW);
	    gl.glLoadIdentity();
	    gl.glDisable(GL10.GL_DEPTH_TEST);
	    diagIndices = ByteBuffer.allocateDirect(2);
	    diagIndices.order(ByteOrder.nativeOrder());
	    diagIndices.put(new byte[] {0, 10});
	    diagIndices.flip();
	}
	
	private ByteBuffer createBufferForPoints(float []points){
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(points.length * 4);
	    byteBuffer.order(ByteOrder.nativeOrder());
		return byteBuffer;
	}
	
	private float[] pointsArray(){
	
		float[] frame = {
		        10, 10,
		        w-1, 10,
		        w+100,h-100,
		        w-1, h-1,
		        10, h-1 };
		return frame;
	}
	protected FloatBuffer makeFloatBuffer(float[] arr) {
		ByteBuffer bb = ByteBuffer.allocateDirect(arr.length*4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer fb = bb.asFloatBuffer();
		fb.put(arr);
		fb.position(0);
		return fb;
		}
	
	public void drawCube(GL10 gl){
		float xrot = 0;
	    float yrot =0;
	    float mycube[] = {
	    		// FRONT
	    		-0.5f, -0.5f, 0.5f,
	    		0.5f, -0.5f, 0.5f,
	    		-0.5f, 0.5f, 0.5f,
	    		0.5f, 0.5f, 0.5f,
	    		// BACK
	    		-0.5f, -0.5f, -0.5f,
	    		-0.5f, 0.5f, -0.5f,
	    		0.5f, -0.5f, -0.5f,
	    		0.5f, 0.5f, -0.5f,
	    		// LEFT
	    		-0.5f, -0.5f, 0.5f,
	    		-0.5f, 0.5f, 0.5f,
	    		-0.5f, -0.5f, -0.5f,
	    		-0.5f, 0.5f, -0.5f,
	    		// RIGHT
	    		0.5f, -0.5f, -0.5f,
	    		0.5f, 0.5f, -0.5f,
	    		0.5f, -0.5f, 0.5f,
	    		0.5f, 0.5f, 0.5f,
	    		// TOP
	    		-0.5f, 0.5f, 0.5f,
	    		0.5f, 0.5f, 0.5f,
	    		-0.5f, 0.5f, -0.5f,
	    		0.5f, 0.5f, -0.5f,
	    		// BOTTOM
	    		-0.5f, -0.5f, 0.5f,
	    		-0.5f, -0.5f, -0.5f,
	    		0.5f, -0.5f, 0.5f,
	    		0.5f, -0.5f, -0.5f,
	    		};
	    		FloatBuffer cubeBuff;
	    		
	    		cubeBuff = makeFloatBuffer(mycube);
	    		gl.glEnable(GL10.GL_DEPTH_TEST);
	    		gl.glEnable(GL10.GL_CULL_FACE);
	    		gl.glDepthFunc(GL10.GL_LEQUAL);
	    		gl.glClearDepthf(1.0f);
	    		gl.glClear(GL10.GL_COLOR_BUFFER_BIT |
	    		GL10.GL_DEPTH_BUFFER_BIT);
	    		gl.glMatrixMode(GL10.GL_PROJECTION);
	    		gl.glLoadIdentity();
	    		gl.glViewport(0,0,w,h);
	    		GLU.gluPerspective(gl, 35.0f,
	    		((float)w)/h, 1f, 100f);
	    		gl.glMatrixMode(GL10.GL_MODELVIEW);
	    		gl.glLoadIdentity();
	    		GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1+15, 0);
	    		gl.glShadeModel(GL10.GL_SMOOTH);
	    		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeBuff);
	    		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	    		gl.glRotatef(xrot, 1, 0, 0);
	    		gl.glRotatef(yrot, 0, 1, 0);
	    		gl.glColor4f(1.0f, 0, 0, 1.0f);
	    		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
	    		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 4, 4);
	    		Message msg = new Message();
	    		msg.what = START_ANIMATION;
	    		handleAnimation.sendMessageDelayed(msg, 10000);
	    		gl.glColor4f(0, 1.0f, 0, 1.0f);
	    		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 8, 4);
	    		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 12, 4);
	    		
	    		
		    		gl.glColor4f(0, 0, 1.0f, 1.0f);
		    		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 16, 4);
		    		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 20, 4);
//		    		Bitmap bitmap = BitmapFactory.decodeResource(contxt.getResources(),R.drawable.unnamed4);
//		    		
//		    		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0,bitmap , 1);
		    		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    		
	    		xrot += 1.0f;
	    		yrot += 0.5f;
	   
	}

	}

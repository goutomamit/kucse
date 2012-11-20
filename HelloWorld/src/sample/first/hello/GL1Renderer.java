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
	public static GL10 gltoDraw;
	private FloatBuffer frameVertices;
	public Context contxt;
	private static final int START_ANIMATION =1;
	private ByteBuffer diagIndices;
	int textures[] =new int[1];
	long  time1=0;
	long  time2=System.currentTimeMillis();
	long  time3=System.currentTimeMillis();
	public float moveToX=0;
	public float moveToY = 0;
	float accelerationToX = 0.03f;
	float accrelerationToY = 0.02f;
	
	float xrot = 0;
    float yrot =0;
	private FloatBuffer vertexBuffer;   // buffer holding the vertices
	FloatBuffer textureBuffer;
	FloatBuffer cubeBuff;
	private float vertices[] = {
			-1.0f, -1.0f,  0.0f,		// V1 - bottom left
			-1.0f,  1.0f,  0.0f,		// V2 - top left
			 1.0f, -1.0f,  0.0f,		// V3 - bottom right
			 1.0f,  1.0f,  0.0f			// V4 - top right
	};
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
	
	public GL1Renderer(Context cont){
		this.contxt = cont;
		createCubePoints();
	}
	
	
	
	@Override
	public void onDrawFrame(GL10 gl) {
		// Reset the Modelview Matrix
		//gl.glLoadIdentity();

		// Drawing
		//gl.glTranslatef(0.0f, 0.0f, -5.0f);		// move 5 units INTO the screen

//	    gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//	    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//	    gl.glVertexPointer(10, GL10.GL_FLOAT, 8, frameVertices);
//	    gl.glColor4f(1f, 0f, 0f, 1f);
//	    gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 5);
//	    gl.glDrawElements(GL10.GL_LINES, 1, GL10.GL_UNSIGNED_BYTE, diagIndices);
	  //  draw(gl);
	    drawCube(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		w = width; h =  height;
		if(height == 0) { 						//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}

		Log.w("Surface","its really changed");
		gl.glViewport(0, 0, width, height); 	//Reset The Current Viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix

		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity();
//		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//		gl.glVertexPointer(4, GL10.GL_FLOAT, 0, frameVertices);
//		    gl.glColor4f(1f, 0f, 0f, 0.5f);
//		    gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 4);
////			drawCube(gl);
////		    gl.glDrawElements(GL10.GL_LINES, 1, GL10.GL_UNSIGNED_BYTE, diagIndices);
////		    gl.glMatrixMode(GL10.GL_TEXTURE); 
////		    gl.glTranslatef(10, 15, 0);
//		    
//		    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

	}


	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
	//	w = 800; h =  480;
		gltoDraw = gl;
	  //  TRC.debug("w = " + w + ", h = " + h);
//	    gl.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
//	    gl.glViewport(0, 0, w, h);
//	    gl.glDepthRangex(1, -1);    // TODO remove
//	    gl.glMatrixMode(GL10.GL_PROJECTION);
//	    gl.glLoadIdentity();
//	    gl.glOrthof(0, w, 0, h, 1, -1);
	    
//	    frameVertices = createBufferForPoints(pointsArray()).asFloatBuffer();
//	    frameVertices.put(pointsArray());
//	    frameVertices.flip();
//	    frameVertices.position(0);
//	    gl.glLineWidthx(10);
//	    gl.glEnable(GL10.GL_POINT_SIZE);
//	    gl.glPointSize(6);
//	    gl.glMatrixMode(GL10.GL_MODELVIEW);
//	    gl.glLoadIdentity();
//	    gl.glDisable(GL10.GL_DEPTH_TEST);
//	    diagIndices = ByteBuffer.allocateDirect(2);
//	    diagIndices.order(ByteOrder.nativeOrder());
//	    diagIndices.put(new byte[] {0, 10});
//	    diagIndices.flip();
	    
//	    loadGLTexture(gl, contxt);

	    			//Enable Texture Mapping ( NEW )
	    	

	}
	
	public void loadGLTexture(GL10 gl, Context context){
		gl.glEnable(GL10.GL_TEXTURE_2D);	
		Log.w("Trying to load", "image");
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuffer.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

	 // buffer holding the texture coordinates
		float texture[] = {
		// Mapping coordinates for the vertices
				0.0f, 1.0f, // top left (V2)
				0.0f, 0.0f, // bottom left (V1)
				1.0f, 1.0f, // top right (V4)
				1.0f, 0.0f // bottom right (V3)
		};
		byteBuffer = ByteBuffer.allocateDirect(texture.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuffer.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);
		
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.android);
		Log.w("height:","gh"+bitmap.getHeight());
		// generate one texture pointer
		gl.glGenTextures(1, textures, 0);
		// ...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		
		// create nearest filtered texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		//Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
//		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
//		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
		
		// Use Android GLUtils to specify a two-dimensional texture image from our bitmap 
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		// Clean up
		bitmap.recycle();
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
		gltoDraw = gl;
	    
				commonConfig(gl);
    			GLU.gluLookAt(gl, 0, 0, 5, moveToX, moveToY, 0, 0, 1+15, 0);
//	    		if((System.currentTimeMillis()-time1)>50){
//	    			
//	    			moveToX = moveToX + accelerationToX ;
//	    			moveToY = moveToY + accrelerationToY;
//	    			if(moveToY>.8f)
//	    				accrelerationToY = accrelerationToY *(-1);
//	    			if(moveToY<-.6f)
//	    				accrelerationToY = accrelerationToY *(-1);
//	    			if(moveToX >1.4f )
//	    				accelerationToX = accelerationToX *(-1);
//	    			if(moveToX <-1.6f )
//	    				accelerationToX = accelerationToX *(-1);
//	    			Log.w("Moveto","m "+moveToX);
//	    			time1=System.currentTimeMillis();
//	    		}
	    		gl.glShadeModel(GL10.GL_SMOOTH);
	    		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeBuff);
	    		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	    		gl.glRotatef(xrot, 1, 0, 0);
	    		gl.glRotatef(yrot, 0, 1, 0);
	    		gl.glColor4f(1.0f, 0, 0, 1.0f);
	    		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
//	    		if((System.currentTimeMillis()-time3)>500){

	    		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
//	    		time3 = System.currentTimeMillis();
//	    		}
//	    		Message msg = new Message();
//	    		msg.what = START_ANIMATION;
//	    		handleAnimation.sendMessageDelayed(msg, 10000);
	    		//if((System.currentTimeMillis()-time1)>1000){
	    		gl.glColor4f(0, 1.0f, 0, 1.0f);
	    		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
	    		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
	    		//time1 = System.currentTimeMillis();
	    	//	}
	    		
	    		//if((System.currentTimeMillis()-time2)>2000){
		    		gl.glColor4f(0, 0, 1.0f, 1.0f);
		    		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
		    		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
		    		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		    		time2 = System.currentTimeMillis();
	    		//}
 	    		xrot += 1.0f;
	    		yrot += .5f;
	   
	}
	
	public void draw(GL10 gl) {
		// bind the previously generated texture
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		
		// Point to our buffers
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		// Set the face rotation
		gl.glFrontFace(GL10.GL_CW);
		
		// Point to our vertex buffer
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		gl.glRotatef(xrot, 1, 0, 0);
		gl.glRotatef(yrot, 0, 1, 0);
		// Draw the vertices as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);

		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		xrot += 1.0f;
		yrot += 0.5f;
	}
	public static void setObjectPosition(float x, float y){
		if(gltoDraw!=null){
			if(400<x)
				x=x*(-1);
			
			Log.w("x y","x:"+ x + " y:" + y);
			GLU.gluLookAt(gltoDraw, 0, 0, 3, x/400, y/240, 0, 0, 1+15, 0);
		}
	}
	
	public void createCubePoints(){
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
	    	
	    		
	    		cubeBuff = makeFloatBuffer(mycube);
	}
	
	public void commonConfig(GL10 gl){
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glClearDepthf(1.0f);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT |
		GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glViewport(0,0,w,h);
		GLU.gluPerspective(gl, 45.0f,
		((float)w)/h, 1f, 100f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	}

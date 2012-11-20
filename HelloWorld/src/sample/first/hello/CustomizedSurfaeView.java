package sample.first.hello;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

public class CustomizedSurfaeView extends GLSurfaceView {
	GL1Renderer renderer;
	public CustomizedSurfaeView(Context context) {
		super(context);
		
		//setEGLContextClientVersion(2); 
		renderer = new GL1Renderer(context);
		setRenderer(renderer);
//		setEGLContextClientVersion(2);
//		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getRawX();
		float y = event.getRawY();
		
		x = 400-x;
		
		y = y-240;
//		if(x>400)
//			x = (x-400)*-1;
//		if(y>240)
//			y = (y-240)*-1;
		renderer.moveToX = x/90;
		renderer.moveToY = y/90;
		Log.w("x y","x y:" + renderer.moveToX +" "+ renderer.moveToY);
//		GL1Renderer.setObjectPosition(event.getRawX(), event.getRawY());
		return true;
	}

}

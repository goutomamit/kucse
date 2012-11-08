package sample.first.hello;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class CustomizedSurfaeView extends GLSurfaceView {

	public CustomizedSurfaeView(Context context) {
		super(context);
		//setEGLContextClientVersion(2); 
		setRenderer(new GL1Renderer());
//		setEGLContextClientVersion(2);
//		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}

}

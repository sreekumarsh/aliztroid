package com.android.aliztroid;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.Window;
import android.view.WindowManager;

import com.android.aliztroid.text.GLTextLoader;
import com.android.aliztroid.texture.TextureLibrary;
import com.android.aliztroid.ui.opengl.AliztroidRenderer;
import com.android.aliztroid.ui.scene.GameScene;
import com.android.aliztroid.ui.scene.SceneManager;
import com.android.aliztroid.utils.Config;
import com.android.aliztroid.utils.Configurations;
/**
 * The main class of the engine
 * @author Sreekumar SH
 */

public class Aliztroid {
	
	public SceneManager manager;
	static Activity  activity;
	
	public static int spriteId;
	
	
	/**
	 * 
	 * @param activity Activity
	 */
	public Aliztroid(Activity activity)
	{
		Aliztroid.activity = activity;
	}
	/**
	 * Initialize the Engine and start the openGL drawing 
	 * 
	 */
	public void Initialize()
	{
		
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE); // (NEW)
		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN); // (NEW)
		
		
		Configurations.width = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
   		Configurations.height = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();
   		Configurations.density = activity.getResources().getDisplayMetrics().density;
   		
   		
   		spriteId = 0;
   		
		//textLib = new TextureLibrary();
		GLTextLoader.load(activity, Config.FONT_FILE, Config.FONT_SIZE);
        GLSurfaceView view = new GLSurfaceView(activity);
   		view.setRenderer(new AliztroidRenderer(activity));
   		activity.setContentView(view);
   		
   		
   		
      	start();	
	}
	
	
	
	
	/**
	 * Start the game drawing and features
	 * 
	 */
	public void start()
	{
		
		
	}
	/**
	 * To add a new scene to the game and make it as the current scene.
	 * @param scene GameScene the scene to be added.
	 * @throws InterruptedException 
	 */
	public void AddScene(GameScene scene)
	{
		SceneManager.NextScene(scene, false);
	}
	
	/**
	 * get the application context
	 * @return context -Context
	 */
	public static Context getContext()
	{
		return activity.getApplicationContext();
	}
	public static void Log(String msg)
	{
		if(Configurations.debug)
	    	System.out.println(msg);
	}
}

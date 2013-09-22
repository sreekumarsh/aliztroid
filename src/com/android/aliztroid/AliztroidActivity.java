package com.android.aliztroid;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;

import com.android.aliztroid.ui.Configurations;
import com.android.aliztroid.ui.scene.GameScene;
import com.android.aliztroid.ui.scene.SceneManager;


/**
 * This is the main activity of the engine which should be extended by the main 
 * class of the game.
 * @author Sreekumar S H
 *
 */

public abstract class AliztroidActivity extends Activity{
	
	private Aliztroid alistroid;
	public Point TouchPoint;
	
	 /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        alistroid = new Aliztroid(this);
        alistroid.Initialize();
        TouchPoint = new Point();
        SetLandscape();
        onGameStart();
        
    }
    /**
     * Adds a scene to the game
     * @param scene
     */
    public void AddScene(GameScene scene)
    {
    	alistroid.AddScene(scene);
    }
    /**
     * Called when a scene is finished
     * @param scene
     */
    public static void onFinishScene(GameScene scene)
    {
    	//should be ovverriden 
    }
    
    
    /**
     * make it true to send Debug messages to logcat
     * 
     * @param debug
     */
    public void SetDebuggable(boolean debug)
	{
		Configurations.debug = debug;
	}
    /**
     * Set the orientation of view to landscape
     */
    public void SetLandscape()
    {
    	
    	Configurations.orientation = Configurations.LANDSCAPE;
    	Configurations.gameWidth = Configurations.height;
   		Configurations.gameHeight = Configurations.width;
    }
    /**
     * Set the orientation of view to potrait
     */
    public void SetPotrait()
    {
    	
    	Configurations.orientation = Configurations.POTRAIT;
    	Configurations.gameWidth = Configurations.width;
   		Configurations.gameHeight = Configurations.height;

    	
    }
    
    /**
     * 
     * extended by the game, which is called when the game starts.
     */
    public abstract void onGameStart();
    
    
    /**
     * overriden function todespatch motion events 
     * 
     */
    public boolean onTouchEvent(MotionEvent event)
    {
    	
    	
    	
    	float x,y;
    	
    	if(Configurations.orientation == Configurations.LANDSCAPE)
    	{
    		x = Configurations.gameWidth - event.getY();
    		y = event.getX();
    	}
    	else
    	{
    		x = event.getX();
    		y = event.getY();
    	}
    	
    	Aliztroid.Log("Touch event occured X:" + event.getX()+", Y:"+event.getY()+" mapped to x:"+x+", y:"+y);
    	
    	TouchPoint.x = (int) x;
    	TouchPoint.y = (int) y;
    	
		if(event.getAction() == MotionEvent.ACTION_DOWN)
		{
			SceneManager.DespatchPress(x, y);
		}
		else if(event.getAction() == MotionEvent.ACTION_UP)
		{
			SceneManager.DespatchRelease(x, y);
		}
		else if(event.getAction() == MotionEvent.ACTION_MOVE)
		{
			SceneManager.DespatchMove(x, y);
		}
    	return true;
    	
    }
    
    
}

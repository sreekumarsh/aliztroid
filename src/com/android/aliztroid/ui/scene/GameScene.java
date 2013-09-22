package com.android.aliztroid.ui.scene;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Point;

import com.android.aliztroid.Aliztroid;
import com.android.aliztroid.AliztroidActivity;
import com.android.aliztroid.ui.Configurations;
import com.android.aliztroid.ui.scene.objects.Graphic;
import com.android.aliztroid.ui.scene.objects.Sprite;
import com.android.aliztroid.ui.scene.objects.Texture;
/**
 * 
 * The scene class which contains all the objects .
 * @author Sreekumar SH
 * 
 * 
 * 
 * 
 * 
 */
public abstract class GameScene 
{
	
	
	private ArrayList<Sprite> sprites;
	public Graphic BgroundImage;
	private Thread gThread;
	private ArrayList<Sprite> listeningSprites;
	
	public Point TouchPoint;
	public String name;
	
	/**
	 * constructor for scene
	 * 
	 */
	public GameScene()
	{
		sprites = new ArrayList<Sprite>();
		TouchPoint = new Point();
		listeningSprites = new ArrayList<Sprite>();
		gThread = new Thread(){
			public void run()
			{
				while(true)
				{
					GameLoop();
				}
			}
		};
	}
	
	
	/**
	 * Loads a scene to the scenemanager
	 * 
	 */
	public void Load()
	{
		gThread.start();
	}
	
	
	/**
	 * Sets the background image for the scene.
	 * @param bgImage The Texture that is set as the background image
	 */
			
	public void SetBackGround(String bgImage){
		BgroundImage = new Graphic(0,0,Configurations.gameWidth,Configurations.gameHeight,new Texture(bgImage));
	}
	/**
	 * Adds a sprite to the scene
	 * 
	 * @param sp Sprite
	 */
	public void AddSprite(Sprite sp)
	{
		sprites.add(sp);
	}
	/**
	 * Add touch event listeners
	 * 
	 * @param sp Sprite
	 */
	public void AddListener(Sprite sp)
	{
		listeningSprites.add(sp);
	}
	
	
	/**
	 * draw all the sprites in the scene
	 * @param gl GL10 object
	 */
	public void draw(GL10 gl)
	{
		if(BgroundImage!=null)
		BgroundImage.draw(gl);
		
		for(Sprite sp : sprites)
		{
			sp.draw(gl);
		}
	}
	/**
	 * Game Loop which is active from load to unload of a scene
	 * 
	 */
	public void GameLoop()
	{
		
	}
	/**
	 * to finish the current scene
	 */
	public void Finish()
	{
		Unload();
	}
	
	/**
	 * Unloads a scene from the scenemanager 
	 */
	public void Unload()
	{
		gThread.stop();
		AliztroidActivity.onFinishScene(this);
	}
	
	
	/**
	 * Handle the Press Event
	 * 
	 * @param x
	 * @param y
	 */
	public void HandlePress(float x, float y)
	{
		
		TouchPoint.x = (int) x;
    	TouchPoint.y = (int) y;
		
		Aliztroid.Log("Press handled in scene");
		for(Sprite lsp:listeningSprites )
		{
			if(lsp.visible)
			if(lsp.HitTest(x, y))
			{
				lsp.HandlePress(x, y);
				onPress(lsp);
			}
		}
		onPress(x,y);
		
	}
	/**
	 * Handle the Press Event
	 * 
	 * @param x
	 * @param y
	 */
	public void HandleRelease(float x, float y)
	{
		
		TouchPoint.x = (int) x;
    	TouchPoint.y = (int) y;
    	
		for(Sprite lsp:listeningSprites )
		{
			
			if(lsp.visible)
			{
				if(lsp.HitTest(x, y))
				{
					lsp.HandleRelease(x, y);
					onRelease(lsp);
				}
				
				else
				{
					lsp.ReleaseOutside(x, y);
					onReleaseOutside(lsp);
				}
			}
			
			
		}
		onRelease(x,y);
		
	}
	/**
	 * Handle the Press Event
	 * 
	 * @param x
	 * @param y
	 */
	public void HandleMove(float x, float y)
	{
		TouchPoint.x = (int) x;
    	TouchPoint.y = (int) y;
    	
		for(Sprite lsp:listeningSprites )
		{
			if(lsp.visible)
				if(lsp.HitTest(x, y))
				{
					lsp.HandleMove(x, y);
					onMove(lsp);
				}
			
		}
		onMove(x,y);
	}
	
	/**
	 * called when a touch down event occurred anywhere in the scene
	 * @param x
	 * @param y
	 */
	 
	
	public void onPress(float x, float y)
	{
		
	}
	/**
	 * called when a touch up event occurred anywhere in the scene
	 * @param x
	 * @param y
	 */
	 
	public void onRelease(float x, float y)
	{
		
	}
	/**
	 * called when a touch move event occurred anywhere in the scene
	 * @param x
	 * @param y
	 */
	 
	public void onMove(float x, float y)
	{
		
	}
	
	
	public void onPress(Sprite sp)
	{
		
	}
	public void onRelease(Sprite sp)
	{
		
	}
	public void onMove(Sprite sp)
	{
		
	}
	
	public void onReleaseOutside(Sprite sp)
	{
		
	}
	
}

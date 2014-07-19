package com.android.aliztroid.ui.scene;

import javax.microedition.khronos.opengles.GL10;


/**
 * Manages the transition through different scenes.
 * @author Sreekumar SH
 * 
 * 
 * 
 */

public class SceneManager {
	
	private static GameScene currentScene;
	
	
	
	/**
	 * Navigates from the current scene to the next scene.
	 * @param scene The next scene type GameScene
	 * @param fade Boolean that determines whether to add fade effect
	 * @throws InterruptedException 
	 */
	public static void NextScene(GameScene scene,boolean fade)
	{
		if(currentScene!=null && currentScene.isAlive())
		{
			try {
				currentScene.finishScene();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		currentScene = scene;
		currentScene.Load();
	}
	/**
	 * 
	 * to draw the current scene
	 * @param gl
	 */
	public static void draw(GL10 gl)
	{
		if(currentScene!=null)
		currentScene.draw(gl);
	}
	
	/**
	 * Despatch the on Press event
	 * @param x coordinate
	 * @param y coordinate
	 */
	public static void DespatchPress(float x, float y)
	{
		if(currentScene!=null)
		{
			currentScene.HandlePress(x, y);
		}
	}
	
	/**
	 * Despatch the on Release event
	 * @param x coordinate
	 * @param y coordinate
	 */
	public static void DespatchRelease(float x, float y)
	{
		if(currentScene!=null)
		{
			currentScene.HandleRelease(x, y);
		}
	}
	/**
	 * Despatch the on Move event
	 * @param x coordinate
	 * @param y coordinate
	 */
	public static void DespatchMove(float x, float y)
	{
		if(currentScene!=null)
		{
			currentScene.HandleMove(x, y);
		}
	}
	
}

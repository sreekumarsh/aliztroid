package com.android.aliztroid.ui.scene;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Point;

import com.android.aliztroid.Aliztroid;
import com.android.aliztroid.listeners.OnSceneChangedListener;
import com.android.aliztroid.text.GLTextBox;
import com.android.aliztroid.texture.Texture;
import com.android.aliztroid.ui.Graphic;
import com.android.aliztroid.ui.Group;
import com.android.aliztroid.ui.Sprite;
import com.android.aliztroid.utils.Configurations;

/**
 * 
 * The scene class which contains all the objects.
 * 
 * @author Sreekumar SH
 * 
 * 
 * 
 * 
 * 
 */
public abstract class GameScene {

	private ArrayList<com.android.aliztroid.ui.Object> objects;
	public Graphic BgroundImage;
	private Thread gThread;
	private ArrayList<com.android.aliztroid.ui.Object> listeningSprites;
	private boolean isShowing;
	public Point TouchPoint;
	public String name;
	private OnSceneChangedListener sceneListener;

	/**
	 * constructor for scene
	 * 
	 */
	public GameScene(OnSceneChangedListener onSceneChanged) {
		sceneListener = onSceneChanged;
		objects = new ArrayList<com.android.aliztroid.ui.Object>();
		TouchPoint = new Point();
		listeningSprites = new ArrayList<com.android.aliztroid.ui.Object>();
		gThread = new Thread() {
			public void run() {
				while (isShowing) {
					GameLoop();
				}
			}
		};
	}

	public GameScene(OnSceneChangedListener onSceneChanged, boolean thread) {
		sceneListener = onSceneChanged;
		objects = new ArrayList<com.android.aliztroid.ui.Object>();
		TouchPoint = new Point();
		listeningSprites = new ArrayList<com.android.aliztroid.ui.Object>();
		if (thread) {
			gThread = new Thread() {
				public void run() {
					while (isShowing) {
						GameLoop();
					}
				}
			};
		}

	}

	public boolean isAlive() {
		return isShowing;
	}

	/**
	 * Loads a scene to the scenemanager
	 * 
	 */
	public void Load() {
		isShowing = true;
		if (gThread != null) {
			gThread.start();
		}

	}

	/**
	 * Sets the background image for the scene.
	 * 
	 * @param bgImage
	 *            The Texture that is set as the background image
	 */

	public void SetBackGround(Texture bgImage) {
		BgroundImage = new Graphic(0, 0, Configurations.gameWidth,
				Configurations.gameHeight, bgImage);
	}

	/**
	 * Sets the background image for the scene.
	 * 
	 * @param bgImage
	 *            The Texture that is set as the background image
	 */

	public void SetBackGround(String bgImage) {
		BgroundImage = new Graphic(0, 0, Configurations.gameWidth,
				Configurations.gameHeight, new Texture(bgImage));
	}

	/**
	 * Adds a sprite to the scene
	 * 
	 * @param sp
	 *            Sprite
	 */
	public void add(com.android.aliztroid.ui.Object obj) {
		objects.add(obj);
	}
	
	/**
	 * Removes a sprite from the scene
	 * 
	 * @param sp
	 *            Sprite
	 */
	public void remove(com.android.aliztroid.ui.Object obj) {
		objects.remove(obj);
	}
	

	/**
	 * Add touch event listeners
	 * 
	 * @param sp
	 *            Sprite
	 */
	public void AddListener(com.android.aliztroid.ui.Object obj) {
		listeningSprites.add(obj);
	}


	/**
	 * draw all the sprites in the scene
	 * 
	 * @param gl
	 *            GL10 object
	 */
	public void draw(GL10 gl) {
		if (BgroundImage != null)
			BgroundImage.draw(gl);

		for (Object obj : objects) {
			if (obj instanceof Sprite) {
				((Sprite) obj).draw(gl);
			}
			if (obj instanceof Group) {
				((Group) obj).draw(gl);
			}
			if (obj instanceof GLTextBox) {
				((GLTextBox) obj).draw(gl);
			}
		}
	}

	/**
	 * Game Loop which is active from load to unload of a scene
	 * 
	 */
	public void GameLoop() {

	}

	/**
	 * to finish the current scene
	 * 
	 * @throws InterruptedException
	 */
	public void finishScene() throws InterruptedException {
		isShowing = false;
		unload();
	}

	/**
	 * Unloads a scene from the scenemanager
	 * 
	 * @throws InterruptedException
	 */
	private void unload() throws InterruptedException {
		if (gThread != null) {
			gThread.join();
		}
		sceneListener.onSceneFinished(this);
	}

	/**
	 * Handle the Press Event
	 * 
	 * @param x
	 * @param y
	 */
	public void HandlePress(float x, float y) {
		TouchPoint.x = (int) x;
		TouchPoint.y = (int) y;

		Aliztroid.Log("Press handled in scene");
		for (com.android.aliztroid.ui.Object obj : listeningSprites) {
			if (obj.visible)
				if (obj.HitTest(x, y)) {
					onPress(obj);
				}
		}
		onPress(x, y);

	}

	/**
	 * Handle the Press Event
	 * 
	 * @param x
	 * @param y
	 */
	public void HandleRelease(float x, float y) {

		TouchPoint.x = (int) x;
		TouchPoint.y = (int) y;

		for (com.android.aliztroid.ui.Object obj : listeningSprites) {

			if (obj.visible) {
				if (obj.HitTest(x, y)) {
					onRelease(obj);
				} else {
					onReleaseOutside(obj);
				}
			}
		}
		onRelease(x, y);
	}

	/**
	 * Handle the Press Event
	 * 
	 * @param x
	 * @param y
	 */
	public void HandleMove(float x, float y) {
		TouchPoint.x = (int) x;
		TouchPoint.y = (int) y;

		for (com.android.aliztroid.ui.Object obj : listeningSprites) {
			if (obj.visible)
				if (obj.HitTest(x, y)) {
					onMove(obj);
				}

		}
		onMove(x, y);
	}

	/**
	 * called when a touch down event occurred anywhere in the scene
	 * 
	 * @param x
	 * @param y
	 */

	public void onPress(float x, float y) {

	}

	/**
	 * called when a touch up event occurred anywhere in the scene
	 * 
	 * @param x
	 * @param y
	 */

	public void onRelease(float x, float y) {

	}

	/**
	 * called when a touch move event occurred anywhere in the scene
	 * 
	 * @param x
	 * @param y
	 */

	public void onMove(float x, float y) {

	}

	public void onPress(com.android.aliztroid.ui.Object sp) {

	}

	public void onRelease(com.android.aliztroid.ui.Object sp) {

	}

	public void onMove(com.android.aliztroid.ui.Object sp) {

	}

	public void onReleaseOutside(com.android.aliztroid.ui.Object sp) {

	}

}

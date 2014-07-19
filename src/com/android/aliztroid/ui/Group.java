package com.android.aliztroid.ui;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import com.android.aliztroid.Aliztroid;
import com.android.aliztroid.listeners.onClickListener;
import com.android.aliztroid.text.GLTextBox;

public class Group extends Object{
	
	private ArrayList<Object> sprites;
	public String name;
	private onClickListener clickListener;
	
	public Group(float x, float y, float width, float height){
		super(x,y,width,height);
		sprites = new ArrayList<Object>();
	}
	
	/**
	 * draw all the sprites in the scene
	 * @param gl GL10 object
	 */
	public void draw(GL10 gl)
	{
		for(Object obj : sprites)
		{
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
	 * Adds a sprite to the scene
	 * 
	 * @param sp
	 *            Sprite
	 */
	public void add(com.android.aliztroid.ui.Object obj) {
		sprites.add(obj);
	}
	
	/**
	 * Removes a sprite from the scene
	 * 
	 * @param sp
	 *            Sprite
	 */
	public void remove(com.android.aliztroid.ui.Object obj) {
		sprites.remove(obj);
	}
	
	public void addSprite(com.android.aliztroid.ui.Object spr){
		spr.x = this.x + spr.x;
		spr.y = this.y + spr.y;
		sprites.add(spr);
	}
	/**
	 * Handle the Press Event
	 * 
	 * @param x
	 * @param y
	 */
	public void HandlePress(float x, float y)
	{
		
		Aliztroid.Log("Press handled in scene");
		for(com.android.aliztroid.ui.Object lsp:sprites )
		{
			if(lsp.visible)
			if(lsp.HitTest(x, y))
			{
				if(clickListener != null){
					clickListener.onPress(this);
				}
			}
		}
		
	}
	/**
	 * Handle the Press Event
	 * 
	 * @param x
	 * @param y
	 */
	public void HandleRelease(float x, float y)
	{
		
    	
		for(com.android.aliztroid.ui.Object lsp:sprites )
		{
			
			if(lsp.visible)
			{
				if(lsp.HitTest(x, y))
				{
					if(clickListener != null){
						clickListener.onRelease(this);
						clickListener.onClick(this);
					}
				}
				
			}
		}
	}
	
}

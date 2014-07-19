package com.android.aliztroid.text;

import javax.microedition.khronos.opengles.GL10;

import com.android.aliztroid.texture.Texture;
import com.android.aliztroid.ui.Sprite;

public class GLChar {

	private Sprite text;
	public GLChar(float x, float y, char c) {
		Texture texture = GLTextLoader.getTexture();
		text = new Sprite(x, y, 1,
				96, 96, texture);
		text.setHeight(GLTextLoader.getCellHeight());
		text.setWidth(GLTextLoader.getCellWidth());
		//text = new Sprite(x, y, texture);
		
		
		text.isPlaying = false;
		text.currentFrame = (int)c -31;
	}
	public void draw(GL10 gl) {
		text.draw(gl);
	}
	public void setAlpha(int alpha){
		text.alpha = alpha;
	}
	
	public void setRGB(String col){
		text.setRGB(col);
	}
	public float getWidth(){
		return text.getWidth();
	}
	public float getHeight(){
		return text.getHeight();
	}
	public void setX(float x){
		text.x = x;
	}
	public void setY(float y){
		text.y = y;
	}
	
	public float getX(){
		return text.x;
	}
	public float getY(){
		return text.y;
	}
}

package com.android.aliztroid.ui.scene.objects;

public class Graphic extends Sprite{

	public Graphic(float x, float y, float width, float height, Texture texture) {
		super(x, y, width, height, texture);
		// TODO Auto-generated constructor stub
	}
	public Graphic(float x, float y,int rows,int coloumns,int totalFrames, Texture texture) {
		super(x, y,rows,coloumns,totalFrames, texture);
	}
	
	
	public Graphic(float x, float y, Texture texture) {
		super(x, y, texture);
	}
	@Override
	public void onMove(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPress(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRelease(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ReleaseOutside(float x, float y) {
		// TODO Auto-generated method stub
		
	}

}

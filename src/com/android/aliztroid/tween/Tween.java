package com.android.aliztroid.tween;

import com.android.aliztroid.ui.Sprite;


/**
 * Tween the sprites 
 * @author Sreekumar SH
 *
 */
public class Tween 
{
	
	
	public static final int SCALE = 1;
	public static final int TRANSLATE = 2;
	public static final int ALPHA = 3;
	
	private Sprite sprite;
	private int method;
	private float destination;
	private float xarg;
	private float yarg;
	
	public Tween(int through, float destination,Sprite sprite)
	{
		this.sprite = sprite;
		method = through;
		this.destination = destination;
		
		
		
	}
	public Tween(int through,float xarg,float yarg,Sprite sprite)
	{
		this.sprite = sprite;
		method = through;
		this.xarg = xarg;
		this.yarg = yarg;
		
		
		
	}
}

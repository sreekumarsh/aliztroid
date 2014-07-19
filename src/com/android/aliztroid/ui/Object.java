package com.android.aliztroid.ui;


public abstract class Object {
	
	public boolean visible;
	
	/** x position	 */
	public float x;
	
	/** y position */
	public float y;
	
	/** width 	 */
	private float width;
	
	/** height 	 */
	private float height;
	
	public void setHeight(float h){
		height = h;
	}
	
	public void setWidth(float w){
		width = w;
	}
	
	public Object(float x,float y,float width,float height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	
	/**
	 * Get the x position
	 * @return x float
	 */
	public float getX()
	{
		return this.x;
	}
	/**
	 * Get the y position
	 * @return y float
	 */
	public float getY()
	{
		return this.y;
	}
	
	/**
	 * Get the width
	 * @return width float
	 */
	public float getWidth()
	{
		return this.width;
	}
	/**
	 * Get the height
	 * @return height float
	 */
	public float getHeight()
	{
		return this.height;
	}
	/**
	 * Check if a point is inside an object
	 * @param x - x coordinate of the point
	 * @param y - y coordinate of the point
	 * @return true if its inside false otherwise 
	 */
	public boolean HitTest(float x, float y)
	{
		return ( x>this.x && x<this.x+this.width ) && ( y>this.y && y< this.y+this.height );
	}
	
	
}

package com.android.aliztroid.ui.scene.objects;


public abstract class Object {
	
	/** x position	 */
	public float x;
	
	/** y position */
	public float y;
	
	/** width 	 */
	public float width;
	
	/** height 	 */
	public float height;
	
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

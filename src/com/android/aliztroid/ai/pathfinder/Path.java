package com.android.aliztroid.ai.pathfinder;

public class Path {
	private int x;
	private int y;
	private boolean isTrapped = false;
	private boolean isReached = false;
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the isFound
	 */
	public boolean isTrapped() {
		return isTrapped;
	}
	/**
	 * @param isFound the isFound to set
	 */
	public void setTrapped(boolean isFound) {
		this.isTrapped = isFound;
	}
	/**
	 * @return the isReached
	 */
	public boolean isReached() {
		return isReached;
	}
	/**
	 * @param isReached the isReached to set
	 */
	public void setReached(boolean isReached) {
		this.isReached = isReached;
	}
	
}

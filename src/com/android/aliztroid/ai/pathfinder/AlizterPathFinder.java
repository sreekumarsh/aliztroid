package com.android.aliztroid.ai.pathfinder;

import android.graphics.Point;

public class AlizterPathFinder {

	private int[][] pathArray;
	private int rows;
	private int cols;
	private final int MAX_COST = 1000;
	
	private int[][] boardArray;
	
	public AlizterPathFinder(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		this.pathArray = new int[rows][cols];
	}
	
	public Path getNextPath(int r, int c, int[][] board){
		this.boardArray = board;
		int[] arr = new int[6]; // 6 for hexagonal arrangement
		for(int i=0;i<6;i++){
			clearPath();
			arr[i] = getCost(c,r,getNeighbour(i, c, r).x, getNeighbour(i, c, r).y);
		}
		int pos = getLowestPosition(arr);
		
		Point nextPos = getNeighbour(pos, c, r);
		Path nextPath = new Path();
		nextPath.setX(nextPos.x);
		nextPath.setY(nextPos.y);
		nextPath.setTrapped(arr[pos] >= MAX_COST);
		nextPath.setReached(arr[pos] == 0);
		return nextPath;
	}
	private void clearPath(){
		for(int i=0; i < rows; i++){
			for(int j=0; j < cols; j++){
				pathArray[i][j] = 0;
			}
		}
	}
	private int getCost(int fromC, int fromR, int c, int r){
		
		if(boardArray[r][c] > 0){
			return MAX_COST;
		}
		
		if(c <= 0 || c >= cols-1 || r <= 0 || r >= rows-1){
			return 0;
		}
		
		
		if(pathArray[r][c] == 1){
			return MAX_COST;
		}
		pathArray[r][c] = 1;
		int[] arr = new int[6];
		for(int i=0;i<6;i++){
			arr[i] = getCost(c,r,getNeighbour(i, c, r).x, getNeighbour(i, c, r).y)  + 1;
		}
		pathArray[fromR][fromC] = 0;
		return getLowest(arr);
		
	}
	private Point getNeighbour(int pos, int x, int y){
		int newX = 0, newY = 0;
		
		switch(pos){
		case 0:
			newY = y;
			newX = x - 1;
			break;
		case 1:
			newX = x - (y+1)%2;
			newY = y - 1;
			break;
		case 2:
			newX = x + (y)%2;
			newY = y - 1;
			break;
		case 3:
			newY = y;
			newX = x + 1;
			break;
		case 4:
			newY = y + 1;
			newX = x + (y)%2;
			break;
		case 5:
			newX = x - (y+1)%2;
			newY = y + 1;
			break;
		}
		
		return new Point(newX, newY);
	}
	
	private int getLowest(int[] arr){
		int low = Integer.MAX_VALUE;
		for(int i = 0; i<arr.length; i++){
			if(low > arr[i]){
				low = arr[i];
			}
		}
		return low;
	}
	private int getLowestPosition(int[] arr){
		int low = Integer.MAX_VALUE;
		int pos = 0;
		for(int i = 0; i<arr.length; i++){
			if(low > arr[i]){
				low = arr[i];
				pos = i;
			}else if(low == arr[i] && Math.random()*10 %2 == 0){
				low = arr[i];
				pos = i;
			}
		}
		return pos;
	}
}

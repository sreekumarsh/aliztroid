package com.android.aliztroid.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.android.aliztroid.texture.Texture;

public class GLTextLoader{


	private static final int FONT_PAD_X = 0;
	private static final int FONT_PAD_Y = 0;
	private static int colCnt;
	private static int rowCnt;
	private static int cellWidth;
	private static int cellHeight;
	private static Texture texture;
	

	public static void load(Context context, String font, int size) {
		Typeface tf = Typeface.createFromAsset(context.getAssets(), font);
		Paint paint = new Paint(); // Create Android Paint Instance
		paint.setAntiAlias(true); // Enable Anti Alias
		paint.setTextSize(size); // Set Text Size
		paint.setColor(0xffffffff); // Set ARGB (White, Opaque)
		paint.setTypeface(tf); // Set Typeface

		Paint.FontMetrics fm = paint.getFontMetrics(); // Get Font Metrics
		float fontHeight = (float) Math.ceil(Math.abs(fm.bottom)
				+ Math.abs(fm.top));
		float fontDescent = (float) Math.ceil(Math.abs(fm.descent));

		float[] charWidths = new float[96];
		char[] s = new char[2]; // Create Character Array
		float charWidthMax = 0;
		float charHeight = 0; // Reset Character Width/Height Maximums
		float[] w = new float[2]; // Working Width Value
		int cnt = 0;
		for (char i = 32; i < 127; i++) {
			// String c = Character.toString ((char) i);;
			s[0] = i; // Set Character
			paint.getTextWidths(s, 0, 1, w); // Get Character Bounds
			charWidths[cnt] = w[0]; // Get Width
			if (charWidths[cnt] > charWidthMax)
				charWidthMax = charWidths[cnt];
			cnt++;
		}
		charHeight = fontHeight;
		cellWidth = (int) charWidthMax + (2 * FONT_PAD_X); // Set Cell Width
		cellHeight = (int) charHeight + (2 * FONT_PAD_Y); // Set Cell Height
		int textureSize = cellWidth * 96;
		colCnt = 96; // Calculate Number of Columns
		setRowCnt(1); // Calculate
		Bitmap bitmap = Bitmap.createBitmap(textureSize, cellHeight,
				Bitmap.Config.ALPHA_8); // Create Bitmap
		Canvas canvas = new Canvas(bitmap); // Create Canvas for Rendering to
		bitmap.eraseColor(0x00000000); // Set Transparent Background (ARGB)
		// render each of the characters to the canvas (ie. build the font map)
		float x = 0; // Set Start Position (X)
		float y = (cellHeight - 1) - fontDescent - FONT_PAD_Y; // Set Start
																// Position (Y)
		for (char c = 32; c <= 126; c++) { // FOR Each Character
			s[0] = c; // Set Character to Draw
			canvas.drawText(s, 0, 1, x , y, paint); // Draw Character
			x += cellWidth; // Move to Next Character
		}
		setTexture(new Texture(bitmap));
	}


	/**
	 * @return the rowCnt
	 */
	public static int getRowCnt() {
		return rowCnt;
	}
	/**
	 * @return the colCnt
	 */
	public static int getColCnt() {
		return colCnt;
	}
	
	public static int getCellWidth() {
		return cellWidth;
	}
	
	public static int getCellHeight() {
		return cellHeight;
	}

	/**
	 * @param rowCnt the rowCnt to set
	 */
	public static void setRowCnt(int rowCnt) {
		GLTextLoader.rowCnt = rowCnt;
	}


	/**
	 * @return the texture
	 */
	public static Texture getTexture() {
		return texture;
	}


	/**
	 * @param texture the texture to set
	 */
	public static void setTexture(Texture texture) {
		GLTextLoader.texture = texture;
	}
}

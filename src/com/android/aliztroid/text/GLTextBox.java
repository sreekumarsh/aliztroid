package com.android.aliztroid.text;

import javax.microedition.khronos.opengles.GL10;

import com.android.aliztroid.ui.Object;

public class GLTextBox extends Object {

	private String text;
	private String color = "#FFFFFF";
	private GLChar[] string;

	public GLTextBox(float x, float y, float width, float height) {
		super(x, y, width, height);

	}

	public void setText(String text) {
		this.text = text;
		if (text != null) {
			string = new GLChar[text.length()];
			for (int i = 0; i < text.length(); i++) {
				string[i] = new GLChar(
						this.x + i * GLTextLoader.getCellWidth(), this.y,
						text.charAt(i));
				// string[i].setRGB(color);
			}
		}

	}

	public void setRGB(String col) {
		color = col;
		for (GLChar ch : string) {
			ch.setRGB(color);
		}
	}

	public String getText(String text) {
		return this.text;
	}

	public void draw(GL10 gl) {
		for (int i = 0; i < string.length; i++) {
			string[i].setX(this.x + i * GLTextLoader.getCellWidth());
			string[i].setY(y);
			string[i].draw(gl);
		}
	}
}

package com.android.aliztroid.texture;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.android.aliztroid.Aliztroid;

/**
 * 
 * @author Sreekumar SH
 * 
 */

public class Texture {

	private int mTextureId = -1;

	public boolean mShouldLoadTexture;
	// The bitmap we want to load as a texture.
	private Bitmap mBitmap;
	private float width;
	private float height;
	public static String Folder = "textures";

	public Texture(String file) {
		AssetManager mngr = Aliztroid.getContext().getAssets();
		InputStream is = null;
		try {

			is = mngr.open(Folder + "/" + file);
		} catch (final IOException e) {
			e.printStackTrace();
			return;
		}

		BitmapFactory.Options mBitmapOptions = new BitmapFactory.Options();
		mBitmapOptions.inScaled = true;

		mBitmap = BitmapFactory.decodeStream(is, null, mBitmapOptions);
		setWidth(mBitmap.getWidth());
		setHeight(mBitmap.getHeight());

		TextureLibrary.insert(this);
	}

	public Texture(Bitmap bitmap) {
		this.mBitmap = bitmap;
		setWidth(mBitmap.getWidth());
		setHeight(mBitmap.getHeight());
		TextureLibrary.insert(this);
	}

	public Bitmap getBitmap() {
		return mBitmap;
	}

	/**
	 * Set the bitmap to load into a texture.
	 * 
	 * @param bitmap
	 *            The bitmap Image
	 */
	public void loadBitmap(Bitmap bitmap) {
		this.mBitmap = bitmap;
	}

	/**
	 * get the texture ID
	 * 
	 * @return mTextureId - Texture Id
	 */
	public int getId() {
		return mTextureId;
	}

	/**
	 * Loads the texture.
	 * 
	 * @param gl
	 */
	public void loadGLTexture(GL10 gl) {
		if (mBitmap == null) {
			System.out.println("Texture not loaded yet");
			return;
		}
		// Generate one texture pointer...
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);
		mTextureId = textures[0];

		// ...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);

		// Create Nearest Filtered Texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);

		// Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
				GL10.GL_CLAMP_TO_EDGE);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
				GL10.GL_REPEAT);

		// Use the Android GLUtils to specify a two-dimensional texture image
		// from our bitmap
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0); // Unbind Texture

		// release the bitmap
		mBitmap.recycle(); // Release the Bitmap

	}

	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

}

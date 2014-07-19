package com.android.aliztroid.texture;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;


/**
 * 
 * @author Sreekumar SH
 *
 */

public class TextureLibrary 
{
	private static ArrayList<Texture> textures = new ArrayList<Texture>();
	
	
	public TextureLibrary()
	{
		textures = new ArrayList<Texture>();
	}
	
	
	/**
	 * insert a texture to the texture library
	 * @param texture Texture - the texture to be inserted
	 */
	public static void insert(Texture texture)
	{
		textures.add(texture);
	}
	/**
	 * Load all the textures
	 * @param gl
	 */
	public static void LoadTextures(GL10 gl)
	{
		
		for(Texture tex:textures)
		{
			tex.loadGLTexture(gl);
		}
	}
	
}

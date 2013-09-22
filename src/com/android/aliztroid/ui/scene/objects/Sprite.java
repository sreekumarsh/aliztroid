package com.android.aliztroid.ui.scene.objects;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.android.aliztroid.Aliztroid;
import com.android.aliztroid.ui.Configurations;

/**
 * The Sprite class
 * 
 * @author Sreekumar SH
 *
 */


public abstract class Sprite extends Object
{
	
	
	public int currentFrame;
	public int totalFrames;
	public int rows;
	public int coloumns;
	
	
	/** visibility */
	public boolean visible;
	
	public boolean isPlaying;
	
	/**  name of the sprite to identify
	 * 
	 */
	public String name;
	
	/**unique id of each sprite */
	public int id;
	
	
	/** texture  	 */
	private Texture texture;
	
	private int origin = 0;
	
	/** Alpha determines the transparency of the sprite takes values from 0-100 default is 100*/
	public float alpha;
	
	short[] indices = { 0, 1, 2,0,3, 2 };

	// Our vertex buffer.
	FloatBuffer vertexBuffer;

	// Our index buffer.
	ShortBuffer indexBuffer;
	FloatBuffer mTextureBuffer;
	float[] textureCoordinates;
	

	
	/**
	 * Create new Sprite
	 * @param x x position
	 * @param y y position
	 * @param width width of the Sprite
	 * @param height heigt of the Sprite
	 * @param texture texture of the Sprite
	 */
	public Sprite(float x,float y,float width,float height, Texture texture)
	{
		
		super(x,y,width,height);
		this.texture = texture;
		Initialize();
		
	}
	
	
	public Sprite(float x,float y, Texture texture)
	{
		
		super(x,y,texture.width,texture.height);
		this.texture = texture;
		Initialize();
		
	}
	
	public Sprite(float x,float y,int rows, int coloumns,int totalFrames, Texture texture)
	{
		super(x,y,texture.width/coloumns,texture.height/rows);
		
		Initialize();
		
		this.rows = rows;
		this.coloumns = coloumns;
		
		if(totalFrames>0)
			isPlaying = true;
		
		this.texture = texture;
		this.totalFrames = totalFrames;
		
	}
	
	/**
	 * Initialize the values 
	 */
	private void Initialize()
	{
		this.id = Aliztroid.spriteId++;
		currentFrame = 1;
		isPlaying = false;
		this.visible = true;
		this.alpha = 100;
		this.rows = 1;
		this.coloumns = 1;
	}
	
	public void play()
	{
		isPlaying = true;
	}
	public void stop()
	{
		isPlaying = false;
	}
	
	
	
	
	/**
	 * 
	 * @param frame
	 */
	public void GotoFrame(int frame){
		if(frame<=totalFrames)
		currentFrame = frame;
	}
	
	
	
	/**
	 * To draw the Sprite to the screen
	 */
	public void draw(GL10 gl)
	{
		if(!visible)
		{
			return;
		}
		if(isPlaying)
		{
			currentFrame++;
			if(currentFrame>totalFrames)
				currentFrame = 1;
		}
		
		
		
		textureCoordinates = Alignment.getTextureCordinates(this);
		 ByteBuffer byteBuf = ByteBuffer
        .allocateDirect(textureCoordinates.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		mTextureBuffer = byteBuf.asFloatBuffer();
		mTextureBuffer.put(textureCoordinates);
		mTextureBuffer.position(0);
		
		float vertices[] = Alignment.getVertices(this, Alignment.LEFT_TOP);
		// The order we like to connect them.
		
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

		// short is 2 bytes, therefore we multiply the number if
		// vertices with 2.
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indexBuffer = ibb.asShortBuffer();
		indexBuffer.put(indices);
		indexBuffer.position(0);
		if(texture!=null)
		if (texture.getId() != -1 && mTextureBuffer != null) {
			gl.glEnable(GL10.GL_TEXTURE_2D);
			// Enable the texture state
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	 
			// Point to our buffers
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
			gl.glBindTexture(GL10.GL_TEXTURE_2D, texture.getId());
		}
		
		gl.glColor4f(1.0f, 1.0f, 1.0f, alpha/100); 
		
		// Counter-clockwise winding.
		gl.glFrontFace(GL10.GL_CCW); // OpenGL docs
		// Enable face culling.
		gl.glEnable(GL10.GL_CULL_FACE); // OpenGL docs
		// What faces to remove with the face culling.
		gl.glCullFace(GL10.GL_BACK); // OpenGL docs

		// Enabled the vertices buffer for writing and to be used during
		// rendering.
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);// OpenGL docs.
		// Specifies the location and data format of an array of vertex
		// coordinates to use when rendering.
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, // OpenGL docs
                                 vertexBuffer);

		gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, indices.length,// OpenGL docs
				  GL10.GL_UNSIGNED_SHORT, indexBuffer);

		// Disable the vertices buffer.
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY); 
		
		if (texture.getId() != -1 && mTextureBuffer != null) {
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		}
		// ... end new part.

		// Disable face culling.
		gl.glDisable(GL10.GL_CULL_FACE);
	}
	/**
	 * set the texture of the Sprite
	 * @param texture Texture
	 */
	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}
	
	
	/**
	 * Set the name of the sprite
	 * @param name - String
	 */
	
	public void SetName(String name)
	{
		this.name = name;
	}
	
	
	
	/**
	 * Handle the Press Event
	 * 
	 * @param x
	 * @param y
	 */
	public void HandlePress(float x, float y)
	{
		onPress(x,y);
	}
	/**
	 * Handle the Release Event
	 * 
	 * @param x
	 * @param y
	 */
	public void HandleRelease(float x, float y)
	{
		
		onRelease(x,y);
	}
	
	
	
	/**
	 * Handle the Move Event
	 * 
	 * @param x
	 * @param y
	 */
	public void HandleMove(float x, float y)
	{
		
		onMove(x,y);
	}
	/**
	 * Check if a point is inside an object
	 * @param x - x coordinate of the point
	 * @param y - y coordinate of the point
	 * @return true if its inside false otherwise 
	 */
	public boolean HitTest(float x, float y)
	{
		return super.HitTest(x, y);
	}
	
	public void onPress(float x, float y)
	{
		
	}
	public void onRelease(float x, float y)
	{
		
	}
	public void onMove(float x, float y)
	{
		
	}
	public void ReleaseOutside(float x, float y)
	{
		
	}
}

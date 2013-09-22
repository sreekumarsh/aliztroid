package com.android.aliztroid.ui.scene.objects;


/**
 * Button class , handles touch events and the ontouched events 
 * 
 * @author Sreekumar SH
 *
 */
public class Button extends Sprite{

	private Texture overImage,idleImage;
	/**
	 * new button 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param texture - texture when the button is not pressed
	 * @param textureOnOver - texture when the button is pressed
	 */
	public Button(float x, float y, float width, float height, Texture texture, Texture textureOnOver) {
		super(x, y, width, height, texture);
		overImage = textureOnOver;
		idleImage = texture;
	}
	public Button(float x, float y, Texture texture, Texture textureOnOver) {
		super(x, y, texture);
		overImage = textureOnOver;
		idleImage = texture;
	}
	public Button(float x, float y, Texture texture) {
		super(x, y, 1,2,2,texture);
	}
	

	@Override
	public void onPress(float x, float y) {
		// TODO Auto-generated method stub
		if(overImage!=null)
		setTexture(overImage);
		else if(totalFrames == 2)
		{
			GotoFrame(2);
		}
	}

	@Override
	public void onRelease(float x, float y) {
		// TODO Auto-generated method stub
		if(overImage!=null)
		setTexture(idleImage);
		else if(totalFrames == 2)
		{
			GotoFrame(1);
		}
	}

	@Override
	public void ReleaseOutside(float x, float y) {
		// TODO Auto-generated method stub
		if(overImage!=null)
		setTexture(idleImage);
		else if(totalFrames == 2)
		{
			GotoFrame(1);
		}
	}

}

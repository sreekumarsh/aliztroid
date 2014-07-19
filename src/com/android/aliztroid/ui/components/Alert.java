package com.android.aliztroid.ui.components;

import com.android.aliztroid.text.GLTextBox;
import com.android.aliztroid.texture.Textures;
import com.android.aliztroid.ui.Group;
import com.android.aliztroid.ui.scene.GameScene;
import com.android.aliztroid.utils.Configurations;

public class Alert extends Group{

	private String message;
	private AlizterButton positive;
	private AlizterButton negative;
	private GameScene contextScene;
	private GLTextBox msgBox;
	private boolean isShowing;
	public Alert(GameScene scene, String msg) {
		super(Configurations.width * .1f, Configurations.height * .3f , Configurations.width * .75f, Configurations.height * .2f);
		// TODO Auto-generated constructor stub
		contextScene = scene;
		message = msg;
		msgBox = new GLTextBox(10, 10, getWidth() * .7f, getHeight() * .5f);
		msgBox.setText(message);
		addSprite(msgBox);
		positive = new AlizterButton(10, getHeight() * .7f, Textures.btn_purple);
		positive.setHeight(getHeight()*.2f);
		positive.setWidth(getWidth()*.2f);
		addSprite(positive);
		negative = new AlizterButton(getWidth() * .7f, getHeight() * .7f, Textures.btn_purple);
		negative.setHeight(getHeight()*.2f);
		negative.setWidth(getWidth()*.2f);
		addSprite(negative);
		isShowing = false;
	}
	
	
	public void show(){
		if(!isShowing){
			isShowing = true;
			contextScene.add(this);
		}
	}
	
	public void dismiss(){
		if(isShowing){
			contextScene.remove(this);
		}
	}
	
	
	/**
	 * @return the isShowing
	 */
	public boolean isShowing() {
		return isShowing;
	}
	/**
	 * @param isShowing the isShowing to set
	 */
	public void setShowing(boolean isShowing) {
		this.isShowing = isShowing;
	}
	
	

}

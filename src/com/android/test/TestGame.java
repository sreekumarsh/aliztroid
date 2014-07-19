package com.android.test;

import com.android.aliztroid.listeners.OnSceneChangedListener;
import com.android.aliztroid.texture.Texture;
import com.android.aliztroid.ui.Graphic;
import com.android.aliztroid.ui.Sprite;
import com.android.aliztroid.ui.components.AlizterButton;
import com.android.aliztroid.ui.scene.GameScene;

public class TestGame extends GameScene{

	AlizterButton spr;  // just a sprite 
	Graphic explode;
	public TestGame(OnSceneChangedListener listener)
	{
		super(listener);
		Texture text = new Texture("debates.png");
		Texture texo = new Texture("debates_over.png");
		spr = new AlizterButton(10,10,100,80,text,texo);
		spr.SetName("debate");
		SetBackGround("bgm.png");
		explode = new Graphic(100,100,2,6,12,new Texture("explosion.png"));
		explode.SetName("explode");
		add(explode);  //add a sprite to the screen so that its drawn
		add(spr);
		AddListener(explode);  // to listen for the touch events
		
		AddListener(spr);
	}
	
	public void onPress(Sprite spr)
	{
		
		
		System.out.println("pressed "+spr.name);
		if(spr.name.equals("debate"))
		spr.alpha = 50;
		if(spr.name.equals("explode"))
		{
			spr.isPlaying = !spr.isPlaying;
		}
		
		
	}
	public void GameLoop()
	{
		spr.x+= .00001;
		spr.y+= .00001;
	}
	
	
}

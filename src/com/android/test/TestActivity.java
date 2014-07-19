package com.android.test;

import com.android.aliztroid.AliztroidActivity;
import com.android.aliztroid.ui.scene.GameScene;

public class TestActivity extends AliztroidActivity {
    /** Called when the activity is first created. */
    
   
	@Override
	public void onGameStart() {
		// TODO Auto-generated method stub
		//SetPotrait();
		SetDebuggable(true);
		TestGame scene = new TestGame(this);
	    AddScene(scene);// add a new scene for the game.
	}

	@Override
	public void onSceneChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSceneFinished(GameScene scene) {
		// TODO Auto-generated method stub
		
	}
}
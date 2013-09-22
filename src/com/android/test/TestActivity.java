package com.android.test;

import com.android.aliztroid.AliztroidActivity;

public class TestActivity extends AliztroidActivity {
    /** Called when the activity is first created. */
    
   
	@Override
	public void onGameStart() {
		// TODO Auto-generated method stub
		//SetPotrait();
		SetDebuggable(true);
		TestGame scene = new TestGame();
	    AddScene(scene);// add a new scene for the game.
	}
}
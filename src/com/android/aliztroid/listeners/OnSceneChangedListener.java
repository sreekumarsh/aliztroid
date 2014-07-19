package com.android.aliztroid.listeners;

import com.android.aliztroid.ui.scene.GameScene;

public interface OnSceneChangedListener {
	
	public abstract void onSceneChanged();
	
	public abstract void onSceneFinished(GameScene scene);

}

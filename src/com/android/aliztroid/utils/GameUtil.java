package com.android.aliztroid.utils;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

public class GameUtil {
	
	public static String loadJSONFromAsset(String name, Context context) {
	    String json = null;
	    try {

	        InputStream is = context.getAssets().open(name + ".json");

	        int size = is.available();

	        byte[] buffer = new byte[size];

	        is.read(buffer);

	        is.close();

	        json = new String(buffer, "UTF-8");


	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	    return json;

	}

}

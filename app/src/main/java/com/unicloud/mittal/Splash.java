package com.unicloud.mittal;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {

	MediaPlayer backgroundMusic;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
//		backgroundMusic = MediaPlayer.create(Splash.this, R.raw.welcome);
//		backgroundMusic.start();
		Thread timer = new Thread() {
			public void run() {
				try{
					sleep(3000);	//3000 milli seconds = 3 seconds
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent loginActivity = new Intent(Splash.this, loginWithGooglePlus.class);
					startActivity(loginActivity);
				}
				
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		backgroundMusic.release();
		finish();
	}


}

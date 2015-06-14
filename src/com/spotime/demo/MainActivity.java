package com.spotime.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.spotime.demo.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.os.Environment;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	final int N_IMAGES = 13;
	
	int image;
	private ViewPager pager = null;

	//PagerAdapter adapters[] = new PagerAdapter[14];
	//Drawable backgrounds[] = new Drawable[14];

    SharedPreferences prefs = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.spotime.demo.R.layout.activity_main);

        prefs = getSharedPreferences("ui.immersion", MODE_PRIVATE);
		// first launch, first image
		if (prefs.getBoolean("firstrun", true)) {
			image = 0;
            prefs.edit().putInt("image", image).commit();
            prefs.edit().putBoolean("firstrun", false).commit();
        } else {
        	image = prefs.getInt("image", 0 );
        	image = (image +1)%N_IMAGES;
        }
		
		//new CreateAdapters().execute();

		ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(
				getSupportFragmentManager(), image);
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(pagerAdapter);
		pager.setCurrentItem(1);
		//adapters[1] = pagerAdapter;

		pager.setBackground( getBackgroundDrawable(image) );
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) { }

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) { }
			
			@Override
			public void onPageSelected(int arg0) {
				if (arg0 == 2) {
					image = (image+1)%N_IMAGES;
					prefs.edit().putInt("image", image).commit();
					PagerAdapter pa = new ScreenSlidePagerAdapter(getSupportFragmentManager(),image);
				
					pager.setBackground( getBackgroundDrawable(image) );
					pager.setAdapter(pa);
					pager.setCurrentItem(1);
				}
			}
		});

		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
						| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
						| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
						| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
						| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		
		Window window = this.getWindow();
		window.addFlags(LayoutParams.FLAG_DISMISS_KEYGUARD);
		window.addFlags(LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		window.addFlags(LayoutParams.FLAG_TURN_SCREEN_ON);
		
		
	}
/*
	private class CreateAdapters extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			for (int i = 2; i < 14; i++) {
				ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(
						getSupportFragmentManager(), i);

				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 4;
				// options.inJustDecodeBounds = true;

				InputStream is = null;
				try {
					is = getAssets().open("images/" + i + ".jpg");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Bitmap myBitmap = BitmapFactory.decodeStream(is, null, options);
				Drawable d = new BitmapDrawable(Resources.getSystem(), myBitmap);
				backgrounds[i] = (d);

				adapters[i] = pagerAdapter;
			}
			return null;
		}
	}*/

	private Drawable getBackgroundDrawable(int i) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 2;
		//options.inJustDecodeBounds = true;
		
		Log.v("MY", "now id = "+i);
		InputStream is = null;
		try {
			is = getAssets().open("images/immagine" + i + ".jpg");
		} catch (IOException e1) {
			Log.v("MY", "exception");
			e1.printStackTrace();
		}
		Bitmap myBitmap = BitmapFactory.decodeStream(is,null,options);
		Drawable d = new BitmapDrawable(Resources.getSystem(), myBitmap);
		return d;
	}
	/**
	 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects,
	 * in sequence.
	 */

	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

		List<Fragment> fragments = new ArrayList<Fragment>();

		public ScreenSlidePagerAdapter(FragmentManager fm, int i) {
			super(fm);

			fragments.add(new FragmentLogos(i));
			fragments.add(new FragmentImage(i, 1));
			fragments.add(new FragmentImage(i, 2));
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}
	}

}

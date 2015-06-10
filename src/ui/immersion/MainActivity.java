package ui.immersion;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.authorwjf.immersiveui.R;

import android.content.Context;
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
import android.os.Environment;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	int image = 1;
	private ViewPager pager = null;

	PagerAdapter adapters[] = new PagerAdapter[14];
	Drawable backgrounds[] = new Drawable[14];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new CreateAdapters().execute();

		ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(
				getSupportFragmentManager(), image);
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(pagerAdapter);
		pager.setCurrentItem(1);
		adapters[1] = pagerAdapter;
		
		Drawable d = null;
		try {
			d = Drawable.createFromStream(
					getAssets().open("images/" + image + ".jpg"), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pager.setBackground(d);
		backgrounds[1]=d;
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageSelected(int arg0) {
				if (arg0 == 0) {
					Log.v("MY", "ora ora ora");
					setNewAdapter();
					pager.setBackground(backgrounds[image]);
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

	}

	private class CreateAdapters extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			for (int i = 2; i < 14; i++) {
				ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(
						getSupportFragmentManager(), i);

				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 4;
				//options.inJustDecodeBounds = true;
					
				InputStream is = null;
				try {
					is = getAssets().open("images/" + i + ".jpg");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Log.v("MY", " "+i);
				Bitmap myBitmap = BitmapFactory.decodeStream(is,null,options);
				Drawable d = new BitmapDrawable(Resources.getSystem(), myBitmap);
				backgrounds[i]=(d);
				
				adapters[i]=pagerAdapter;
			}
			return null;
		}
	}

	public void setNewAdapter() {
		image++;
		if( image == 14 ) image = 1;
		// pagerAdapter = new
		// ScreenSlidePagerAdapter(getSupportFragmentManager(),image);
		pager.setAdapter(adapters[image]);
		pager.setCurrentItem(1);
	}

	/**
	 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects,
	 * in sequence.
	 */
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

		List<Fragment> fragments = new ArrayList<Fragment>();

		public ScreenSlidePagerAdapter(FragmentManager fm, int i) {
			super(fm);

			fragments.add(new FragmentImage(i, 0));
			fragments.add(new FragmentImage(i, 1));
			fragments.add(new FragmentLogos(i));
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

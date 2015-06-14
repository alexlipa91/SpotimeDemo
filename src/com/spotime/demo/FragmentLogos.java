package com.spotime.demo;

import java.io.IOException;
import java.io.InputStream;

import com.spotime.demo.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class FragmentLogos extends Fragment {

	int image;

	public FragmentLogos(int i) {
		image = i;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// fragment not when container null
		if (container == null) {
			return null;
		}
		// inflate view from layout
		View view = (RelativeLayout) inflater.inflate(R.layout.frag_logos,
				container, false);

		view.setBackground(getLogoDrawable(image));

		return view;
	}
	

	private Drawable getLogoDrawable(int i) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 2;
		//options.inJustDecodeBounds = true;

		InputStream is = null;
		try {
			is = this.getActivity().getAssets().open("images/logo" + i + ".png");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Bitmap myBitmap = BitmapFactory.decodeStream(is,null,options);
		Drawable d = new BitmapDrawable(Resources.getSystem(), myBitmap);
		return d;
	}
}
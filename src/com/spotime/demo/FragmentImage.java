package com.spotime.demo;

import java.io.IOException;

import com.spotime.demo.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;

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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;

public class FragmentImage extends Fragment {

	int pos;

	public FragmentImage(int i, int pos) {
		this.pos = pos;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// fragment not when container null
		if (container == null) {
			return null;
		}
		// inflate view from layout
		View view = null;
		if (pos == 2) {
			view = (LinearLayout) inflater.inflate(R.layout.next, container,false);
		} else {
			view = (SlidingUpPanelLayout) inflater.inflate(R.layout.current, container,false);
			
			SlidingUpPanelLayout panel = (SlidingUpPanelLayout) view.findViewById(R.id.sliding_layout);
			panel.setPanelSlideListener( new PanelSlideListener() {

				@Override
				public void onPanelSlide(View panel, float slideOffset) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onPanelCollapsed(View panel) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onPanelExpanded(View panel) {
					FragmentImage.this.getActivity().finish();
				}

				@Override
				public void onPanelAnchored(View panel) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onPanelHidden(View panel) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		/*
		 * Drawable d = null; try { d =
		 * Drawable.createFromStream(this.getActivity().getAssets().open(
		 * "images/1.jpg"), null); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * view.setBackground(d);
		 */
		return view;
	}
}
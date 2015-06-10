package ui.immersion;

import java.io.IOException;

import com.authorwjf.immersiveui.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class FragmentImage extends Fragment {

	int image;
	int pos;

	public FragmentImage(int i, int pos) {
		image = i;
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
		if( pos == 0 ) {
			view = (LinearLayout) inflater.inflate(R.layout.page0, container,
				false);
		}else {
			view = (LinearLayout) inflater.inflate(R.layout.page1, container,
					false);
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
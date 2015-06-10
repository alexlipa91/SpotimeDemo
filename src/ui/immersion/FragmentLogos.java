package ui.immersion;

import java.io.IOException;
import java.io.InputStream;

import com.authorwjf.immersiveui.R;

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
		View view = (LinearLayout) inflater.inflate(R.layout.page, container,
				false);

		InputStream is = null;
		try {
			is = this.getActivity().getAssets()
					.open("images/" + image + ".png");
		} catch (IOException e1) {
			Log.v("MY", "Not found");
			e1.printStackTrace();
		}

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;

		Bitmap myBitmap = BitmapFactory.decodeStream(is, null, options);
		Drawable d = new BitmapDrawable(Resources.getSystem(), myBitmap);

		view.setBackground(d);

		return view;
	}
}
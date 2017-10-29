package wuxc.wisdomparty.Adapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Adapter.RewardsAdapter.Callback;
import wuxc.wisdomparty.Cache.VolunteerCache;
import wuxc.wisdomparty.Internet.ImageLoader;
import wuxc.wisdomparty.Internet.ImageLoader.ImageCallback;
import wuxc.wisdomparty.Internet.URLcontainer;
import wuxc.wisdomparty.Internet.getcha;
import wuxc.wisdomparty.Model.VolunteerModel;
import wuxc.wisdomparty.add.ImageLoader600;;

public class VolunteerAdapter extends ArrayAdapter<VolunteerModel> implements OnClickListener {
	private ListView listView;
	private ImageLoader ImageLoader;
	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;
	public ImageLoader600 imageLoader;
	private Callback mCallback;

	public VolunteerAdapter(Activity activity, List<VolunteerModel> imageAndTexts, ListView listView,
			Callback callback) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		this.thisactivity = activity;
		imageLoader = new ImageLoader600(activity.getApplicationContext());
		mCallback = callback;
		ImageLoader = new ImageLoader();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mCallback.click(v);
	}

	public interface Callback {
		public void click(View v);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();

		// Inflate the views from XML
		View rowView = convertView;
		VolunteerCache viewCache;

		LayoutInflater inflater = activity.getLayoutInflater();
		rowView = inflater.inflate(R.layout.item_volunteer, null);
		viewCache = new VolunteerCache(rowView);
		rowView.setTag(viewCache);

		VolunteerModel imageAndText = getItem(position);

		// Load the image and set it on the ImageView
		String imageUrl = imageAndText.getBackGround();
		ImageView imageView = viewCache.getImageBackGround();
		if (!(imageAndText.getBackGround().equals("") || imageAndText.getBackGround() == null)) {

			viewCache.getImageBackGround().setTag(imageAndText.getBackGround());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip + URLcontainer.GetFile + imageAndText.getBackGround(),
						activity, viewCache.getImageBackGround(), R.drawable.knbz);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
		} else {
			imageView.setImageResource(R.drawable.knbz);
		}
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);
		TextView TextTitle = viewCache.getTextTitle();
		TextTitle.setText("" + imageAndText.getTitle());
		TextView TextDetail = viewCache.getTextDetail();
		if (imageAndText.getSummary().equals("") || imageAndText.getSummary().equals("null")
				|| imageAndText.getSummary().equals("null")) {
			TextDetail.setText(getcha.gethan(imageAndText.getDetail()));
		} else {
			TextDetail.setText(imageAndText.getSummary());
		}

		RelativeLayout Half = viewCache.getRelaHalf();
		RelativeLayout OneOfTen = viewCache.getRelaOneOfTen();
		screenwidth = thisactivity.getWindow().getWindowManager().getDefaultDisplay().getWidth();
		int height = (int) (screenwidth / 1.7);
		LinearLayout.LayoutParams layoutParams1 = (android.widget.LinearLayout.LayoutParams) Half.getLayoutParams();
		layoutParams1.height = height;
		Half.setLayoutParams(layoutParams1);
		height = screenwidth / 10;
		RelativeLayout.LayoutParams layoutParams = (android.widget.RelativeLayout.LayoutParams) OneOfTen
				.getLayoutParams();
		layoutParams.height = height;
		OneOfTen.setLayoutParams(layoutParams);
		return rowView;
	}

	public Bitmap getBitmapByPath(String fileName) {
		// String myJpgPath =
		// Environment.getExternalStorageDirectory()+"pepper/" + fileName;
		BitmapFactory.Options options = new BitmapFactory.Options();
		// options.inSampleSize = 12;
		Bitmap bm = BitmapFactory.decodeFile(fileName, options);
		return bm;
	}

	private String getBitName(String imageUrl) {
		// TODO Auto-generated method stub
		String[] temp = imageUrl.split("");
		String result = "";
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].equals("/") || temp[i].equals(".")) {
				temp[i] = "";
			}
			result = result + temp[i];
		}
		return result;
	}

	public void saveMyBitmap(String bitName, Bitmap mBitmap) throws IOException {
		String path = Environment.getExternalStorageDirectory() + "/chat/";
		String myJpgPath = Environment.getExternalStorageDirectory() + "/chat/" + bitName + ".png";
		File tmp = new File(path);
		if (!tmp.exists()) {
			tmp.mkdir();
		}
		File f = new File(myJpgPath);
		f.createNewFile();
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		try {
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Bitmap cutBmp(Bitmap bmp) {
		Bitmap result;
		int w = bmp.getWidth();// 输入长方形宽
		int h = bmp.getHeight();// 输入长方形高
		int nw;// 输出正方形宽
		result = Bitmap.createBitmap(bmp, 15 * w / 100, 15 * h / 100, 7 * w / 10, 7 * h / 10);
		// }
		return result;
	}
}

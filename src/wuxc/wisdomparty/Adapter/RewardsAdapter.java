package wuxc.wisdomparty.Adapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.alipay.android.phone.mrpc.core.r;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Cache.RewardsCache;
import wuxc.wisdomparty.Internet.ImageLoader;
import wuxc.wisdomparty.Internet.URLcontainer;
import wuxc.wisdomparty.Internet.getcha;
import wuxc.wisdomparty.Model.RewardsModel;
import wuxc.wisdomparty.add.ImageLoader600;;

public class RewardsAdapter extends ArrayAdapter<RewardsModel> {
	private ListView listView;
	private ImageLoader ImageLoader;
	private String imageurl = "";
	private int screenwidth = 0;
	private float scale = 0;
	private float scalepx = 0;
	private float dp = 0;
	private Activity thisactivity;
	public ImageLoader600 imageLoader;
	public RewardsAdapter(Activity activity, List<RewardsModel> imageAndTexts, ListView listView) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		this.thisactivity = activity;
		imageLoader = new ImageLoader600(activity.getApplicationContext());

		ImageLoader = new ImageLoader();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();

		// Inflate the views from XML
		View rowView = convertView;
		RewardsCache viewCache;
		 
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.item_rewards, null);
			viewCache = new RewardsCache(rowView);
			rowView.setTag(viewCache);
		 
		RewardsModel imageAndText = getItem(position);

		// Load the image and set it on the ImageView
		String imageUrl = imageAndText.getBackGround();
		ImageView imageView = viewCache.getImageBackGround();
		if (!(imageAndText.getBackGround().equals("") || imageAndText.getBackGround() == null)) {

			viewCache.getImageBackGround().setTag(imageAndText.getBackGround());

			try {

				imageLoader.DisplayImage(URLcontainer.urlip+URLcontainer.GetFile+imageAndText.getBackGround(), activity, viewCache.getImageBackGround(), R.drawable.cotent);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
		}else {
			imageView.setImageResource(R.drawable.cotent);
		}
		TextView TextTime = viewCache.getTextTime();
		TextTime.setText(imageAndText.getTime());
		TextView TextTitle = viewCache.getTextTitle();
		TextTitle.setText("" + imageAndText.getTitle());
		TextView TextDetail = viewCache.getTextDetail();
		try {
			if (imageAndText.getSummary().equals("") || imageAndText.getSummary().equals("null")
					|| imageAndText.getSummary().equals("null")) {
				TextDetail.setText(getcha.gethan(imageAndText.getDetail()));
			}else {
				TextDetail.setText(imageAndText.getSummary());
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}

		screenwidth = thisactivity.getWindow().getWindowManager().getDefaultDisplay().getWidth();
		DisplayMetrics mMetrics = new DisplayMetrics();
		thisactivity.getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
		scale = thisactivity.getResources().getDisplayMetrics().density;
		Rect frame = new Rect();

		thisactivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

		int statusBarHeight = frame.top;
		dp = screenwidth / scale + 0.5f;
		scalepx = screenwidth / dp;
		int height = (int) ((screenwidth - 60 * scalepx) / 2);
		LinearLayout.LayoutParams layoutParams1 = (android.widget.LinearLayout.LayoutParams) imageView
				.getLayoutParams();
		layoutParams1.height = height;
		imageView.setLayoutParams(layoutParams1);

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

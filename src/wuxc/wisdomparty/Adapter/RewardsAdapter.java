package wuxc.wisdomparty.Adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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

public class RewardsAdapter extends ArrayAdapter<RewardsModel> implements OnClickListener {
	private ListView listView;
	private ImageLoader ImageLoader;
	private String imageurl = "";
	private int screenwidth = 0;
	private float scale = 0;
	private float scalepx = 0;
	private float dp = 0;
	private Activity thisactivity;
	public ImageLoader600 imageLoader;
	private Callback mCallback;

	public RewardsAdapter(Activity activity, List<RewardsModel> imageAndTexts, ListView listView, Callback callback) {
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

				imageLoader.DisplayImage(URLcontainer.urlip + URLcontainer.GetFile + imageAndText.getBackGround(),
						activity, viewCache.getImageBackGround(), R.drawable.cotent);
			} catch (Exception e) {
				// TODO: handle exception
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
			}
		} else {
			imageView.setImageResource(R.drawable.cotent);
		}
		TextView TextTime = viewCache.getTextTime();
		TextTime.setText(imageAndText.getTime());
		TextView TextTitle = viewCache.getTextTitle();
		TextTitle.setText("" + imageAndText.getTitle());
		TextView TextDetail = viewCache.getTextDetail();
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);
		try {
			if (imageAndText.getSummary().equals("") || imageAndText.getSummary().equals("null")
					|| imageAndText.getSummary().equals("null")) {
				TextDetail.setText(getcha.gethan(imageAndText.getDetail()));
			} else {
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

}

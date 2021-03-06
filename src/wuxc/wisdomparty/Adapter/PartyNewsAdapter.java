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
import wuxc.wisdomparty.Cache.PartyNewsCache;
import wuxc.wisdomparty.Internet.ImageLoader;
import wuxc.wisdomparty.Internet.ImageLoader.ImageCallback;
import wuxc.wisdomparty.Internet.URLcontainer;
import wuxc.wisdomparty.Internet.getcha;
import wuxc.wisdomparty.Model.PartyNewsModel;;

public class PartyNewsAdapter extends ArrayAdapter<PartyNewsModel> implements OnClickListener {
	private ListView listView;
	private ImageLoader ImageLoader;
	private String imageurl = "";
	private int screenwidth = 0;
	private Activity thisactivity;
	private Callback mCallback;

	public PartyNewsAdapter(Activity activity, List<PartyNewsModel> imageAndTexts, ListView listView,
			Callback callback) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		this.thisactivity = activity;
		ImageLoader = new ImageLoader();
		mCallback = callback;
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
		PartyNewsCache viewCache;

		LayoutInflater inflater = activity.getLayoutInflater();
		if (position == 0) {
			rowView = inflater.inflate(R.layout.item_party_news_1, null);
			// } else if (position == 2 || position == 3 || position == 8 ||
			// position == 9 || position == 6) {
			// rowView = inflater.inflate(R.layout.item_party_news2, null);
		} else {
			rowView = inflater.inflate(R.layout.item_party_news, null);
		}

		viewCache = new PartyNewsCache(rowView);
		rowView.setTag(viewCache);

		PartyNewsModel imageAndText = getItem(position);

		TextView TextDetail = viewCache.getTextDetail();
		if (imageAndText.getSummary().equals("") || imageAndText.getSummary().equals("null")
				|| imageAndText.getSummary().equals("null")) {
			TextDetail.setText(getcha.gethan(imageAndText.getDetail()));
		} else {
			TextDetail.setText(imageAndText.getSummary());
		}
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);
		TextView TextTitle = viewCache.getTextTitle();
		TextTitle.setText("" + imageAndText.getTitle());
		TextView TextTime = viewCache.getTextTime();
		TextTime.setText("" + imageAndText.getTime());

		return rowView;
	}

}

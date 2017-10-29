package wuxc.wisdomparty.Adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Adapter.RewardsAdapter.Callback;
import wuxc.wisdomparty.Cache.PartyRespondCache;
import wuxc.wisdomparty.Internet.ImageLoader;
import wuxc.wisdomparty.Model.RespondModel;;

public class PartyRespondAdapter extends ArrayAdapter<RespondModel> implements OnClickListener {
	private ListView listView;
	private ImageLoader ImageLoader;
	private String imageurl = "";
	private Callback mCallback;

	public PartyRespondAdapter(Activity activity, List<RespondModel> imageAndTexts, ListView listView,
			Callback callback) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
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
		PartyRespondCache viewCache;

		LayoutInflater inflater = activity.getLayoutInflater();
		rowView = inflater.inflate(R.layout.item_respond, null);
		viewCache = new PartyRespondCache(rowView);
		rowView.setTag(viewCache);

		RespondModel imageAndText = getItem(position);

		TextView TextTitle = viewCache.getTextTitle();
		TextTitle.setText("" + imageAndText.getTitle());
		TextView TextTime = viewCache.getTextTime();
		TextTime.setText("" + imageAndText.getTime());
		LinearLayout lin_all = viewCache.getlin_all();
		lin_all.setTag(position);
		lin_all.setOnClickListener(this);
		return rowView;
	}

}

package wuxc.wisdomparty.Adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Cache.PartyRespondCache;
import wuxc.wisdomparty.Internet.ImageLoader;
import wuxc.wisdomparty.Model.RespondModel;;

public class PartyRespondAdapter extends ArrayAdapter<RespondModel> {
	private ListView listView;
	private ImageLoader ImageLoader;
	private String imageurl = "";

	public PartyRespondAdapter(Activity activity, List<RespondModel> imageAndTexts, ListView listView) {
		super(activity, 0, imageAndTexts);
		this.listView = listView;
		ImageLoader = new ImageLoader();
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

		return rowView;
	}

	 
}

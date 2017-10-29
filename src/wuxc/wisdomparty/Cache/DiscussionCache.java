package wuxc.wisdomparty.Cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.layout.RoundImageView;

public class DiscussionCache {

	private View baseView;
	private RoundImageView RoundImageview;
	private TextView TextTitle;
	private TextView TextBrowseNumber;
	private TextView TextanswerNumber;
	private TextView TextName;
	private TextView TextTime;

	private LinearLayout lin_all;

	public LinearLayout getlin_all() {
		if (lin_all == null) {
			lin_all = (LinearLayout) baseView.findViewById(R.id.lin_all);
		}
		return lin_all;
	}

	public DiscussionCache(View baseView) {
		this.baseView = baseView;
	}

	public RoundImageView getRoundImageview() {
		if (RoundImageview == null) {
			RoundImageview = (RoundImageView) baseView.findViewById(R.id.round_headimg);
		}
		return RoundImageview;
	}

	public TextView getTextTitle() {
		if (TextTitle == null) {
			TextTitle = (TextView) baseView.findViewById(R.id.text_title);
		}
		return TextTitle;
	}

	public TextView getTextTime() {
		if (TextTime == null) {
			TextTime = (TextView) baseView.findViewById(R.id.text_time);
		}
		return TextTime;
	}

	public TextView getTextName() {
		if (TextName == null) {
			TextName = (TextView) baseView.findViewById(R.id.text_name);
		}
		return TextName;
	}

	public TextView getTextanswerNumber() {
		if (TextanswerNumber == null) {
			TextanswerNumber = (TextView) baseView.findViewById(R.id.text_answer_number);
		}
		return TextanswerNumber;
	}

	public TextView getTextBrowseNumber() {
		if (TextBrowseNumber == null) {
			TextBrowseNumber = (TextView) baseView.findViewById(R.id.text_browse_number);
		}
		return TextBrowseNumber;
	}
}

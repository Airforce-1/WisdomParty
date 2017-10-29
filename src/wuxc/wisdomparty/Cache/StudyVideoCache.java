package wuxc.wisdomparty.Cache;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.layout.SelectableRoundedImageView;

public class StudyVideoCache {

	private View baseView;
	private TextView TextTime;
	private TextView TextNumberCollect;
	private SelectableRoundedImageView ImageHeadimg;
	private TextView TextNumberGreat;
	private TextView TextTitle;
	private RelativeLayout RelaHalf;
	private LinearLayout lin_all;

	public LinearLayout getlin_all() {
		if (lin_all == null) {
			lin_all = (LinearLayout) baseView.findViewById(R.id.lin_all);
		}
		return lin_all;
	}

	public StudyVideoCache(View baseView) {
		this.baseView = baseView;
	}

	public RelativeLayout getRelaHalf() {
		if (RelaHalf == null) {
			RelaHalf = (RelativeLayout) baseView.findViewById(R.id.rela_half);
		}
		return RelaHalf;
	}

	public TextView getTextTitle() {
		if (TextTitle == null) {
			TextTitle = (TextView) baseView.findViewById(R.id.text_title);
		}
		return TextTitle;
	}

	public TextView getTextNumberGreat() {
		if (TextNumberGreat == null) {
			TextNumberGreat = (TextView) baseView.findViewById(R.id.text_number_great);
		}
		return TextNumberGreat;
	}

	public SelectableRoundedImageView getImageHeadimg() {
		if (ImageHeadimg == null) {
			ImageHeadimg = (SelectableRoundedImageView) baseView.findViewById(R.id.image_headimg);
		}
		return ImageHeadimg;
	}

	public TextView getTextNumberCollect() {
		if (TextNumberCollect == null) {
			TextNumberCollect = (TextView) baseView.findViewById(R.id.text_number_collect);
		}
		return TextNumberCollect;
	}

	public TextView getTextTime() {
		if (TextTime == null) {
			TextTime = (TextView) baseView.findViewById(R.id.text_time);
		}
		return TextTime;
	}

}

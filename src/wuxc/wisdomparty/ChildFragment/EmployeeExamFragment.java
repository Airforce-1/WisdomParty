package wuxc.wisdomparty.ChildFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Internet.GetChannelByKey;
import wuxc.wisdomparty.PartyManage.SpecialDetailActivity;

public class EmployeeExamFragment extends Fragment implements OnClickListener {
	private TextView text_exam_4;
	private TextView text_exam_3;
	private TextView text_exam_2;
	private TextView text_exam_1;
	private SharedPreferences PreALLChannel;// 存储所用频道信息

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.employee_exam, container, false);
		text_exam_1 = (TextView) view.findViewById(R.id.text_exam_1);
		text_exam_2 = (TextView) view.findViewById(R.id.text_exam_2);
		text_exam_3 = (TextView) view.findViewById(R.id.text_exam_3);
		text_exam_4 = (TextView) view.findViewById(R.id.text_exam_4);
		text_exam_1.setOnClickListener(this);
		text_exam_2.setOnClickListener(this);
		text_exam_3.setOnClickListener(this);
		text_exam_4.setOnClickListener(this);
		PreALLChannel = getActivity().getSharedPreferences("ALLChannel", Context.MODE_PRIVATE);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.text_exam_1:
			
			try {
				String url = GetChannelByKey.GetUrl (PreALLChannel, "考试须知");
				Intent intent = new Intent();
				intent.setAction("android.intent.action.VIEW");
				String path = url;
				Uri content_url = Uri.parse(path);
				intent.setData(content_url);
				startActivity(intent);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			break;
		case R.id.text_exam_2:
			try {
				String url2 = GetChannelByKey.GetUrl(PreALLChannel, "网上报名");
				Intent intent2 = new Intent();
				intent2.setAction("android.intent.action.VIEW");
				String path2 = url2;
				Uri content_url2 = Uri.parse(path2);
				intent2.setData(content_url2);
				startActivity(intent2);
			} catch (Exception e) {
				// TODO: handle exception
			}
		
			break;
		case R.id.text_exam_3:
			try {
				String url3 = GetChannelByKey.GetUrl(PreALLChannel, "成绩查询");
				Intent intent3 = new Intent();
				intent3.setAction("android.intent.action.VIEW");
				String path3 = url3;
				Uri content_url3 = Uri.parse(path3);
				intent3.setData(content_url3);
				startActivity(intent3);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			break;
		case R.id.text_exam_4:
			try {
				String url4 = GetChannelByKey.GetUrl(PreALLChannel, "信息查询");
				Intent intent4 = new Intent();
				intent4.setAction("android.intent.action.VIEW");
				String path4 = url4;
				Uri content_url4 = Uri.parse(path4);
				intent4.setData(content_url4);
				startActivity(intent4);
			} catch (Exception e) {
				// TODO: handle exception
			}
		
			break;

		default:
			break;
		}
	}
}

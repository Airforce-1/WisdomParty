package wuxc.wisdomparty.PartyManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Adapter.CandicateAdapter;
import wuxc.wisdomparty.Internet.GetChannelByKey;
import wuxc.wisdomparty.Internet.HttpGetData;
import wuxc.wisdomparty.Model.CandicateModel;
import wuxc.wisdomparty.Model.CandicateModel;

public class ChangeTermsDetailActivity extends Activity
		implements OnTouchListener, OnClickListener, OnItemClickListener {
	private ListView ListData;
	private ImageView ImageBack;
	List<CandicateModel> list = new ArrayList<CandicateModel>();
	private static CandicateAdapter mAdapter;
	private int firstItemIndex = 0;
	private int lastItemIndex = 0;
	private float startY = 0;
	private float startYfoot = 0;
	private boolean isRecored;
	private boolean isRecoredfoot;
	private int pageSize = 10;
	private int totalPage = 5;
	private int curPage = 1;
	private final static int RATIO = 2;
	private TextView headTextView = null;
	private double[] scale = { 1, 0.8, 0.5, 0.2, 0.1, 0.9, 0.6, 0.7, 0.3, 0 };
	private TextView TextTitle;
	private TextView TextStartTime;
	private TextView TextEndTime;
	private String Title;
	private String StartTime;
	private String EndTime;
	private String Id = "";
	private int ticket;
	private String chn;
	private String userPhoto;
	private String LoginId;
	private SharedPreferences PreUserInfo;// �洢������Ϣ
	private SharedPreferences PreALLChannel;// �洢����Ƶ����Ϣ
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;

	public Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_DUE_DATA:
				GetDataDueData(msg.obj);
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.change_terms_detail_activity);
		initview();
		setonclicklistener();
		Intent intent = this.getIntent(); // ��ȡ���е�intent����
		Bundle bundle = intent.getExtras(); // ��ȡintent�����bundle����
		StartTime = bundle.getString("StartTime");
		Title = bundle.getString("Title");
		EndTime = bundle.getString("EndTime");
		Id=bundle.getString("EndTime");
		TextTitle.setText(Title);
		TextStartTime.setText(StartTime);
		TextEndTime.setText(EndTime);
		setheadtextview();
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		PreALLChannel = getSharedPreferences("ALLChannel", Context.MODE_PRIVATE);
		ReadTicket();
		GetData();
	}

	private void setheadtextview() {
		headTextView = new TextView(this);
		headTextView.setGravity(Gravity.CENTER);
		headTextView.setMinHeight(100);
		headTextView.setText("����ˢ��...");
		headTextView.setTypeface(Typeface.DEFAULT_BOLD);
		headTextView.setTextSize(15);
		headTextView.setBackgroundColor(Color.WHITE);
		headTextView.invalidate();
		ListData.addHeaderView(headTextView, null, false);
		ListData.setPadding(0, -100, 0, 0);
		ListData.setOnTouchListener(this);
	}

	private void getdatalist(int arg) {
		String[] name = { "������", "��־��", "�Խ���", "��־��", "�챾��", "������", "��־��", "������", "����ԣ", "�ﲩ��" };
		if (arg == 1) {
			list.clear();
		}
		// TODO Auto-generated method stub

		try {

			for (int i = 0; i < 10; i++) {

				CandicateModel listinfo = new CandicateModel();
				listinfo.setName(name[i]);
				listinfo.setNumber("65");
				listinfo.setImageUrl("");
				listinfo.setScale(scale[i]);
				list.add(listinfo);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (arg == 1) {
			go();
		} else {
			mAdapter.notifyDataSetChanged();
		}

	}

	protected void go() {
		ListData.setPadding(0, -100, 0, 0);
		mAdapter = new CandicateAdapter(this, list, ListData);
		ListData.setAdapter(mAdapter);
	}

	private void initview() {
		// TODO Auto-generated method stub
		ListData = (ListView) findViewById(R.id.list_data);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		TextTitle = (TextView) findViewById(R.id.text_title);
		TextStartTime = (TextView) findViewById(R.id.text_start_time);
		TextEndTime = (TextView) findViewById(R.id.text_end_time);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ImageBack.setOnClickListener(this);
		ListData.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		default:
			break;
		}
	}

	protected void GetDataDueData(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;
		String pager = null;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");
			pager = demoJson.getString("pager");
			Data = demoJson.getString("datas");
			if (Type.equals(GET_SUCCESS_RESULT)) {
				GetPager(pager);
				GetDataList(Data, curPage);
			} else if (Type.equals(GET_FAIL_RESULT)) {
				Toast.makeText(getApplicationContext(), "����������ʧ��", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "���ݸ�ʽУ��ʧ��", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetDataList(String data, int arg) {
		;
		if (arg == 1) {
			list.clear();
		}
		JSONArray jArray = null;
		try {
			jArray = new JSONArray(data);
			JSONObject json_data = null;
			if (jArray.length() == 0) {
				// / Toast.makeText(getApplicationContext(), "������",
				// Toast.LENGTH_SHORT).show();

			} else {
				int total = 1;
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					JSONObject jsonObject = json_data.getJSONObject("data");

					total = total + jsonObject.getInt("voteCount");

				}
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);

					JSONObject jsonObject = json_data.getJSONObject("data");
					CandicateModel listinfo = new CandicateModel();
					// listinfo.setTitle("������ѧ");
					listinfo.setImageUrl("");
					// listinfo.setTitle(json_data.getString("title"));
					// listinfo.setDetail(json_data.getString("content"));
					// listinfo.setUrl(json_data.getString("otherLinks"));
					listinfo.setName(jsonObject.getString("name"));
					listinfo.setNumber(jsonObject.getString("voteCount"));
					listinfo.setId(jsonObject.getString("keyid"));
					listinfo.setScale((double) jsonObject.getInt("voteCount") / (double) total);
					listinfo.setRemark(jsonObject.getString("remark1"));
					// listinfo.setDetail(
					// "����ʡί������ѧ����ʡί������ѧ����ʡί������ѧ����ʡί������ѧ����ʡί������ѧ����ʡί������ѧ����ʡί������ѧ����ʡί������ѧ����ʡί������ѧ����ʡί������ѧ����ʡί������ѧ����ʡί������ѧ");
					// listinfo.setBackGround("");
					// listinfo.setAim("12");
					// listinfo.setNumber("3421");
					// listinfo.setTime(json_data.getString("createtime"));
					// listinfo.setTitle(json_data.getString("title"));
					// listinfo.setBackGround(json_data.getString("sacleImage"));
					// listinfo.setDetail(json_data.getString("content"));
					// listinfo.setTime("2016-12-14");
					// listinfo.setDetail(
					// "�˴�ר����ķ�Χ������ũ�񹤽϶�Ľ��������졢�ɿ󡢲�����������С���Ͷ��ܼ�����ҵ�Լ����徭����֯��������ݰ������ǹ���ҵ���Ͷ���ǩ���Ͷ���ͬ��������չ���֧���йع涨֧��ְ�����������������͹��ʹ涨������֧���Ӱ๤������������μ���ᱣ�պͽ�����ᱣ�շ���������ؽ�ֹʹ��ͯ���涨�Լ�Ůְ����δ���깤�����Ͷ������涨��������������Ͷ����Ϸ��ɷ���������"
					// + arg);
					// listinfo.setTitle("���ؿ�չ�ǹ���ҵ��������ר���" + arg);
					// listinfo.setBackGround("");
					list.add(listinfo);

				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (arg == 1) {
			go();
		} else {
			mAdapter.notifyDataSetChanged();
		}

	}

	private void GetPager(String pager) {
		// TODO Auto-generated method stub
		try {
			JSONObject demoJson = new JSONObject(pager);

			totalPage = demoJson.getInt("totalPage");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getInt("ticket", 0);
		userPhoto = PreUserInfo.getString("userPhoto", "");
		LoginId = PreUserInfo.getString("userName", "");
	}

	private void GetData() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", "" + ticket));
		ArrayValues.add(new BasicNameValuePair("voteItemDto.par_keyid", Id));
		new Thread(new Runnable() { // �����߳��ϴ��ļ�
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/cms/voteItem/getListJsonData", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_DUE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		float tempY = event.getY();
		float tempyfoot = event.getY();
		firstItemIndex = ListData.getFirstVisiblePosition();
		lastItemIndex = ListData.getLastVisiblePosition();
		// Toast.makeText(getActivity(), " lastItemIndex" +
		// lastItemIndex, Toast.LENGTH_SHORT).show();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			if (!isRecored && (firstItemIndex == 0)) {
				isRecored = true;
				startY = tempY;
			}
			int temp = 1;
			temp = (lastItemIndex) % pageSize;
			if (!isRecoredfoot && (temp == 0)) {
				isRecoredfoot = true;
				startYfoot = tempyfoot;
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			isRecored = false;
			isRecoredfoot = false;
			break;

		default:
			break;
		}

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			ListData.setPadding(0, 0, 0, 0);
			if (tempY - startY < 400) {
				ListData.setPadding(0, -100, 0, 0);
			} else {
				curPage = 1;
				Toast.makeText(getApplicationContext(), "����ˢ��", Toast.LENGTH_SHORT).show();
				GetData();
			}
			int temp = 1;
			temp = (lastItemIndex) % pageSize;
			// temp = 0;
			if (temp == 0 && (startYfoot - tempyfoot > 400)) {
				curPage++;
				if (curPage > totalPage) {
					Toast.makeText(getApplicationContext(), " û�и�����", Toast.LENGTH_SHORT).show();
					// // listinfoagain();
				} else {
					GetData();
					Toast.makeText(getApplicationContext(), "���ڼ�����һҳ", Toast.LENGTH_SHORT).show();
				}

			} else {

			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (isRecored && tempY > startY) {
				ListData.setPadding(0, (int) ((tempY - startY) / RATIO - 100), 0, 0);
			}
			if (isRecoredfoot && startYfoot > tempyfoot) {
				// footTextView.setVisibility(View.VISIBLE);
				ListData.setPadding(0, -100, 0, (int) ((startYfoot - tempyfoot) / RATIO));
			}
			break;

		default:
			break;
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		CandicateModel data = list.get(position - 1);
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), CandicateDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("Name", data.getName());
		bundle.putString("Number", data.getNumber());
		bundle.putString("Id", data.getId());
		bundle.putString("remark", data.getRemark());
		intent.putExtras(bundle);
		startActivity(intent);
	}

}

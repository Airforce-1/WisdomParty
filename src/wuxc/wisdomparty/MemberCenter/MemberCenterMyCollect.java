package wuxc.wisdomparty.MemberCenter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.umeng.socialize.utils.Log;

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
import wuxc.wisdomparty.Adapter.CollectAdapter;
import wuxc.wisdomparty.Adapter.CollectAdapter.Callback;
import wuxc.wisdomparty.Internet.HttpGetData;
import wuxc.wisdomparty.Internet.webview;
import wuxc.wisdomparty.Model.CollectModel;

public class MemberCenterMyCollect extends Activity
		implements Callback, OnTouchListener, OnClickListener, OnItemClickListener {
	private ListView ListData;
	private ImageView ImageBack;
	List<CollectModel> list = new ArrayList<CollectModel>();
	private static CollectAdapter mAdapter;
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
	private TextView TextChange;
	private int TYPE = 1;// 1-正常 0- 编辑
	private String ticket;
	private SharedPreferences PreUserInfo;// 存储个人信息
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
		setContentView(R.layout.member_center_mycollect);
		initview();
		setonclicklistener();
		setheadtextview();
		GetData();
		// Toast.makeText(getApplicationContext(), "正在加载数据",
		// Toast.LENGTH_SHORT).show();

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
				Toast.makeText(getApplicationContext(), "服务器数据失败", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "数据格式校验失败", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetDataList(String data, int arg) {

		if (arg == 1) {
			list.clear();
		}
		JSONArray jArray = null;
		try {
			jArray = new JSONArray(data);
			JSONObject json_data = null;
			if (jArray.length() == 0) {
				Toast.makeText(getApplicationContext(), "无数据", Toast.LENGTH_SHORT).show();

			} else {
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					Log.e("json_data", "" + json_data);
					JSONObject jsonObject = json_data.getJSONObject("data");
					CollectModel listinfo = new CollectModel();

					listinfo.setTime("收藏于：" + jsonObject.getString("createTime"));
					listinfo.setTitle(jsonObject.getString("title"));
					listinfo.setKeyid(jsonObject.getString("keyid"));
					listinfo.setUrl(jsonObject.getString("url"));
					if (TYPE == 1) {
						listinfo.setDelete(false);
					} else {
						listinfo.setDelete(true);
					}

					listinfo.setIsselected(false);
					listinfo.setImageUrl("");
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
		ticket = PreUserInfo.getString("ticket", "");
	}

	private void GetData() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pubshare/myCollection/getListJsonData", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_DUE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	private void setheadtextview() {
		headTextView = new TextView(this);
		headTextView.setGravity(Gravity.CENTER);
		headTextView.setMinHeight(100);
		headTextView.setText("正在刷新...");
		headTextView.setTypeface(Typeface.DEFAULT_BOLD);
		headTextView.setTextSize(15);
		headTextView.setBackgroundColor(Color.WHITE);
		headTextView.invalidate();
		ListData.addHeaderView(headTextView, null, false);
		ListData.setPadding(0, -100, 0, 0);
		ListData.setOnTouchListener(this);
	}

	private void getdatalist(int arg) {
		if (arg == 1) {
			list.clear();
		}
		// TODO Auto-generated method stub

		try {

			for (int i = 0; i < 10; i++) {

				CollectModel listinfo = new CollectModel();
				listinfo.setTime("收藏于：2016-12-14");
				listinfo.setTitle("党内监督没有禁区没有例外" + arg);
				if (TYPE == 1) {
					listinfo.setDelete(false);
				} else {
					listinfo.setDelete(true);
				}

				listinfo.setIsselected(false);
				listinfo.setImageUrl("");
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
		mAdapter = new CollectAdapter(this, list, ListData, this);
		ListData.setAdapter(mAdapter);
	}

	private void initview() {
		// TODO Auto-generated method stub
		ListData = (ListView) findViewById(R.id.list_data);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		TextChange = (TextView) findViewById(R.id.text_change);
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ImageBack.setOnClickListener(this);
		ListData.setOnItemClickListener(this);
		TextChange.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.text_change:
			if (list.size() != 0) {
				if (TYPE == 0) {
					TYPE = 1;
				} else {
					TYPE = 0;
				}
				ChangeMode(TYPE);
			}

			break;
		default:
			break;
		}
	}

	private void ChangeMode(int type) {
		// TODO Auto-generated method stub
		if (type == 0) {
			for (int i = 0; i < list.size(); i++) {
				CollectModel data = list.get(i);
				data.setDelete(true);

			}
			TextChange.setText("删除");
		} else {
			for (int i = 0; i < list.size(); i++) {
				CollectModel data = list.get(i);
				data.setDelete(false);
			}
			TextChange.setText("编辑");
		}
		String KeyId = "";
		for (int i = 0; i < list.size(); i++) {
			CollectModel data = list.get(i);
			if (data.isIsselected()) {
				KeyId = KeyId + data.getKeyid() + ",";
				list.remove(i);
				i--;
			}
		}

		mAdapter.notifyDataSetChanged();
		if (!KeyId.equals("")) {
			deletekeyid(KeyId);
		}

	}

	private void deletekeyid(String keyId) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		ArrayValues.add(new BasicNameValuePair("datakey", "" + keyId));
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pubshare/myCollection/delete", ArrayValues);
				// Message msg = new Message();
				// msg.obj = DueData;
				// msg.what = GET_DUE_DATA;
				// uiHandler.sendMessage(msg);
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
				Toast.makeText(getApplicationContext(), "正在刷新", Toast.LENGTH_SHORT).show();
				GetData();
			}
			int temp = 1;
			temp = (lastItemIndex) % pageSize;
			// temp = 0;
			if (temp == 0 && (startYfoot - tempyfoot > 400)) {
				curPage++;
				if (curPage > totalPage) {
					Toast.makeText(getApplicationContext(), " 没有更多了", Toast.LENGTH_SHORT).show();
					// // listinfoagain();
				} else {
					GetData();
					Toast.makeText(getApplicationContext(), "正在加载下一页", Toast.LENGTH_SHORT).show();
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
		if (TYPE == 1) {
			CollectModel data = list.get(position - 1);
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), webview.class);
			Bundle bundle = new Bundle();
			bundle.putString("url", data.getUrl());
			// // bundle.putString("Time", "2016-11-23");
			// // bundle.putString("Name", "小李");
			// // bundle.putString("PageTitle", "收藏详情");
			// // bundle.putString("Detail",
			// //
			// "中国共产主义青年团，简称共青团，原名中国社会主义青年团，是中国共产党领导的一个由信仰共产主义的中国青年组成的群众性组织。共青团中央委员会受中共中央委员会领导，共青团的地方各级组织受同级党的委员会领导，同时受共青团上级组织领导。1922年5月，团的第一次代表大会在广州举行，正式成立中国社会主义青年团，1925年1月26日改称中国共产主义青年团。1959年5月4日共青团中央颁布共青团团徽。");
			intent.putExtras(bundle);
			startActivity(intent);
		} else {
			CollectModel data = list.get(position - 1);
			if (data.isIsselected()) {
				data.setIsselected(false);
			} else {
				data.setIsselected(true);
			}
			mAdapter.notifyDataSetChanged();
		}

	}

	@Override
	public void click(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_all:
			if (TYPE == 1) {
				CollectModel data = list.get((Integer) v.getTag());
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), webview.class);
				Bundle bundle = new Bundle();
				bundle.putString("url", data.getUrl());
				// // bundle.putString("Time", "2016-11-23");
				// // bundle.putString("Name", "小李");
				// // bundle.putString("PageTitle", "收藏详情");
				// // bundle.putString("Detail",
				// //
				// "中国共产主义青年团，简称共青团，原名中国社会主义青年团，是中国共产党领导的一个由信仰共产主义的中国青年组成的群众性组织。共青团中央委员会受中共中央委员会领导，共青团的地方各级组织受同级党的委员会领导，同时受共青团上级组织领导。1922年5月，团的第一次代表大会在广州举行，正式成立中国社会主义青年团，1925年1月26日改称中国共产主义青年团。1959年5月4日共青团中央颁布共青团团徽。");
				intent.putExtras(bundle);
				startActivity(intent);
			} else {
				CollectModel data = list.get((Integer) v.getTag());
				if (data.isIsselected()) {
					data.setIsselected(false);
				} else {
					data.setIsselected(true);
				}
				mAdapter.notifyDataSetChanged();
			}
			break;

		default:
			break;
		}
	}

}

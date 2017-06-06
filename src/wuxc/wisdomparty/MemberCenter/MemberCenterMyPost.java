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
import android.content.DialogInterface;
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
import wuxc.wisdomparty.Adapter.MyPostAdapter;
import wuxc.wisdomparty.Adapter.MyPostAdapter.Callback;
import wuxc.wisdomparty.Internet.HttpGetData;
import wuxc.wisdomparty.Internet.webview;
import wuxc.wisdomparty.Model.CollectModel;
import wuxc.wisdomparty.Model.MyPostModel;
import wuxc.wisdomparty.layout.dialogtwo;

public class MemberCenterMyPost extends Activity
		implements OnClickListener, OnTouchListener, OnItemClickListener, Callback {
	private ImageView ImageBack;
	private ListView ListData;
	List<MyPostModel> list = new ArrayList<MyPostModel>();
	private static MyPostAdapter mAdapter;
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
	private String ticket;
	private String userPhoto;
	private String LoginId;
	private SharedPreferences PreUserInfo;// �洢������Ϣ
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
		setContentView(R.layout.member_center_mypost);
		initview();

		setonclicklistener();
		setheadtextview();
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();
		GetData();
//		Toast.makeText(getApplicationContext(), "���ڼ�������", Toast.LENGTH_SHORT).show();

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

		if (arg == 1) {
			list.clear();
		}
		JSONArray jArray = null;
		try {
			jArray = new JSONArray(data);
			JSONObject json_data = null;
			if (jArray.length() == 0) {
				Toast.makeText(getApplicationContext(), "������", Toast.LENGTH_SHORT).show();

			} else {
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					Log.e("json_data", "" + json_data);
					JSONObject jsonObject = json_data.getJSONObject("data");
					MyPostModel listinfo = new MyPostModel();

					listinfo.setTime(jsonObject.getString("createTime"));
					listinfo.setTitle(jsonObject.getString("title"));
					listinfo.setKeyid(jsonObject.getString("keyid"));
					listinfo.setUrl(jsonObject.getString("url"));
					listinfo.setReBack(jsonObject.getString("content"));
					listinfo.setName(LoginId);
					listinfo.setHeadImgUrl(userPhoto);
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
		userPhoto = PreUserInfo.getString("userPhoto", "");
		LoginId = PreUserInfo.getString("userName", "");
	}

	private void GetData() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		new Thread(new Runnable() { // �����߳��ϴ��ļ�
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pubshare/myComment/getListJsonData", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_DUE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	private void deletekeyid(String keyId) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		ArrayValues.add(new BasicNameValuePair("datakey", "" + keyId));
		new Thread(new Runnable() { // �����߳��ϴ��ļ�
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pubshare/myComment/delete", ArrayValues);
				// Message msg = new Message();
				// msg.obj = DueData;
				// msg.what = GET_DUE_DATA;
				// uiHandler.sendMessage(msg);
			}
		}).start();

	}

	private void getdatalist(int arg) {
		if (arg == 1) {
			list.clear();
		}
		// TODO Auto-generated method stub

		try {

			for (int i = 0; i < 10; i++) {

				MyPostModel listinfo = new MyPostModel();
				listinfo.setTime("  " + "����" + "  " + "23:34");
				listinfo.setReBack(
						"С��ܺõ��ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û�����⵳�ڼලû�н���û������");
				listinfo.setTitle("���ڼලû�н���û������" + arg);
				listinfo.setName("���³�");
				listinfo.setHeadImgUrl("");
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
		mAdapter = new MyPostAdapter(this, list, ListData, this);
		ListData.setAdapter(mAdapter);
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

	private void initview() {
		// TODO Auto-generated method stub
		ImageBack = (ImageView) findViewById(R.id.image_back);
		ListData = (ListView) findViewById(R.id.list_data);
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
		MyPostModel data = list.get(position - 1);
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), webview.class);
		Bundle bundle = new Bundle();
		bundle.putString("url", data.getUrl());

		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void click(View v) {
		// TODO Auto-generated method stub
		// Toast.makeText(MemberCenterMyPost.this, "ɾ����" + (Integer) v.getTag()
		// + "��", Toast.LENGTH_SHORT).show();
		showAlertDialog((Integer) v.getTag());
	}

	public void showAlertDialog(final Integer integer) {

		dialogtwo.Builder builder = new dialogtwo.Builder(this);
		builder.setMessage("ȷ��ɾ������������");
		builder.setTitle("ɾ��ȷ��");
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				MyPostModel data = list.get(integer);
				deletekeyid(data.getKeyid());
				list.remove(integer-1);
				mAdapter.notifyDataSetChanged();
			}
		});

		builder.setNegativeButton("ȡ��", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.create().show();

	}
}

package wuxc.wisdomparty.PartyManage;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Adapter.CommentAdapter;
import wuxc.wisdomparty.HomeOfMember.CommentDetailActivity;
import wuxc.wisdomparty.Internet.HttpGetData;
import wuxc.wisdomparty.Model.CommentModel;
import wuxc.wisdomparty.Model.CommentModel;
import wuxc.wisdomparty.Model.CommentModel;
import wuxc.wisdomparty.layout.RoundImageView;

public class SpecialDetailActivity extends Activity implements OnClickListener, OnItemClickListener {
	private EditText EditAnswer;
	private Button BtnAnswer;
	private ImageView ImageBack;
	private ImageView ImagePic;
	private ScrollView ScrollLayout;
	private ListView ListData;
	private TextView TextWarning;
	private TextView TextDetail;
	private TextView TextTime;
	private TextView TextName;
	private TextView TextTitle;
	private RoundImageView RoundHeadimg;
	private String Name;
	private String Title;
	private String Time;
	List<CommentModel> list = new ArrayList<CommentModel>();
	private static CommentAdapter mAdapter;
	private int pageSize = 10;
	private int totalPage = 5;
	private int curPage = 1;
	private int screenwidth = 0;
	private float scale = 0;
	private float scalepx = 0;
	private float dp = 0;
	private String detail = "此次专项检查的范围是招用农民工较多的建筑、制造、采矿、餐饮和其他中小型劳动密集型企业以及个体经济组织。检查内容包括：非公企业与劳动者签订劳动合同情况；按照工资支付有关规定支付职工工资情况；遵守最低工资规定及依法支付加班工资情况；依法参加社会保险和缴纳社会保险费情况；遵守禁止使用童工规定以及女职工和未成年工特殊劳动保护规定情况；其他遵守劳动保障法律法规的情况。";
	private String Id = "";
	private String ticket;
	private String chn;
	private String userPhoto;
	private String LoginId;
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				GetData();
				Toast.makeText(getApplicationContext(), "正在加载数据", Toast.LENGTH_SHORT).show();

				break;
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
		setContentView(R.layout.special_detail_activity);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		Name = bundle.getString("Name");
		Title = bundle.getString("Title");
		Time = bundle.getString("Time");
		Id = bundle.getString("Id");
		ticket = bundle.getString("ticket");
		chn = bundle.getString("chn");
		try {
			detail = bundle.getString("detail");
		} catch (Exception e) {
			// TODO: handle exception
		}
		initview();
		setonclicklistener();
		setlistheight(0);
		settext();
		starttimedelay();

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
					CommentModel listinfo = new CommentModel();

					listinfo.setTime(jsonObject.getString("createTime"));
					listinfo.setComment(jsonObject.getString("content"));
					listinfo.setRoundUrl(jsonObject.getString("userPhoto"));
					listinfo.setName(jsonObject.getString("user_name"));
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

	private void GetData() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		ArrayValues.add(new BasicNameValuePair("chn", chn));
		ArrayValues.add(new BasicNameValuePair("datakey", Id));
		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/cms/common/getChannelCommentData", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_DUE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	private void starttimedelay() {
		// 原因：不延时的话list会滑到顶部
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				Message msg1 = new Message();
				msg1.what = 0;
				uiHandler.sendMessage(msg1);

			}

		}, 2000);
	}

	private void getdatalist(int arg) {
		if (arg == 1) {
			list.clear();
		}
		// TODO Auto-generated method stub

		try {

			for (int i = 0; i < 10; i++) {

				CommentModel listinfo = new CommentModel();
				listinfo.setTime("2016-12-14 20:00:00");
				listinfo.setComment("这真是一篇好文章，学习了");
				listinfo.setRoundUrl("");
				listinfo.setName("刘志刚");

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
		setlistheight(list.size());
		if (arg == totalPage) {
			TextWarning.setText("没有更多了");
		} else {
			TextWarning.setText("点击加载更多");
		}
	}

	private void setlistheight(int size) {
		// TODO Auto-generated method stub
		screenwidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		DisplayMetrics mMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
		scale = getResources().getDisplayMetrics().density;
		dp = screenwidth / scale + 0.5f;
		scalepx = screenwidth / dp;
		int height = (int) (size * 91 * scalepx);
		RelativeLayout.LayoutParams layoutParams1 = (android.widget.RelativeLayout.LayoutParams) ListData
				.getLayoutParams();
		layoutParams1.height = height;
		ListData.setLayoutParams(layoutParams1);
		height = (int) ((screenwidth - 20 * scalepx) / 2);
		layoutParams1 = (android.widget.RelativeLayout.LayoutParams) ImagePic.getLayoutParams();
		layoutParams1.height = height;
		ImagePic.setLayoutParams(layoutParams1);

	}

	protected void go() {
		mAdapter = new CommentAdapter(this, list, ListData);
		ListData.setAdapter(mAdapter);
	}

	private void settext() {
		// TODO Auto-generated method stub
		TextWarning.setText("正在加载数据...");
		TextDetail.setText(detail);
		TextTime.setText(Time);
		TextName.setText(Name);
		TextTitle.setText(Title);
	}

	private void initview() {
		// TODO Auto-generated method stub
		EditAnswer = (EditText) findViewById(R.id.edit_answer);
		BtnAnswer = (Button) findViewById(R.id.btn_answer);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		ImagePic = (ImageView) findViewById(R.id.image_pic);
		ScrollLayout = (ScrollView) findViewById(R.id.scrolllayout);
		ListData = (ListView) findViewById(R.id.list_data);
		TextWarning = (TextView) findViewById(R.id.text_warning);
		TextDetail = (TextView) findViewById(R.id.text_detail);
		TextTime = (TextView) findViewById(R.id.text_time);
		TextName = (TextView) findViewById(R.id.text_name);
		TextTitle = (TextView) findViewById(R.id.text_title);
		RoundHeadimg = (RoundImageView) findViewById(R.id.round_headimg);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		BtnAnswer.setOnClickListener(this);
		ImageBack.setOnClickListener(this);
		ListData.setOnItemClickListener(this);
		TextWarning.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.btn_answer:

			break;
		case R.id.text_warning:
			curPage++;
			if (!(curPage > totalPage)) {
				getdatalist(curPage);
				Toast.makeText(getApplicationContext(), "正在加载", Toast.LENGTH_SHORT).show();
			}

			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		CommentModel data = list.get(position);
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), CommentDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("Name", data.getName());
		bundle.putString("Time", data.getTime());
		bundle.putString("Comment", data.getComment());
		intent.putExtras(bundle);
		startActivity(intent);
	}

}

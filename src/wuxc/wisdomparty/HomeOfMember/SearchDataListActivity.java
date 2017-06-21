package wuxc.wisdomparty.HomeOfMember;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.umeng.socialize.utils.Log;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Adapter.SearchAdapter;
import wuxc.wisdomparty.Internet.HttpGetData;
import wuxc.wisdomparty.Internet.URLcontainer;
import wuxc.wisdomparty.Internet.webview;
import wuxc.wisdomparty.Model.SearchModel;
import wuxc.wisdomparty.layout.Childviewpaper;

public class SearchDataListActivity extends FragmentActivity
		implements OnTouchListener, OnClickListener, OnItemClickListener {
	private RelativeLayout RelativeViewPage;
	private Childviewpaper ViewPaper;
	private ImageView dot1, dot2, dot3, dot4, dot5, dot6, dot7, dot8, dot9, dot10;
	private ImageView[] dot = { dot1, dot2, dot3, dot4, dot5, dot6, dot7, dot8, dot9, dot10 };
	private int screenwidth;
	private int ScreenHeight = 0;
	public List<Fragment> Fragments = new ArrayList<Fragment>();
	private FragmentManager FragmentManager;
	private int NumberPicture = 1;
	private String[] Title = { "同心共筑中国梦", "习大大的讲话2", "习大大的讲话3", "习大大的讲话4", "习大大的讲话5", "习大大的讲话6", "习大大的讲话7", "习大大的讲话8",
			"习大大的讲话9", "习大大的讲话10" };
	private TextView TextTitle;
	private ListView ListData;
	private ImageView ImageBack;
	List<SearchModel> list = new ArrayList<SearchModel>();
	private static SearchAdapter mAdapter;
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
	private String chn;
	private String userPhoto;
	private String LoginId;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private SharedPreferences PreALLChannel;// 存储所用频道信息
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;
private String SearchText ="";
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
		;
		if (arg == 1) {
			list.clear();
		}
		JSONArray jArray = null;
		try {
			jArray = new JSONArray(data);
			JSONObject json_data = null;
			if (jArray.length() == 0) {
				// / Toast.makeText(getApplicationContext(), "无数据",
				// Toast.LENGTH_SHORT).show();

			} else {
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
				
					 JSONObject jsonObject = json_data.getJSONObject("data");
					SearchModel listinfo = new SearchModel();

					listinfo.setTime(jsonObject.getString("bigClassify"));
					listinfo.setTitle(jsonObject.getString("title"));
					listinfo.setImageUrl("");
					listinfo.setDetail(jsonObject.getString("keywords"));
					listinfo.setNumber(jsonObject.getString("viewUrl"));
					// listinfo.setTime("2016-12-14");
					// listinfo.setDetail(
					// "此次专项检查的范围是招用农民工较多的建筑、制造、采矿、餐饮和其他中小型劳动密集型企业以及个体经济组织。检查内容包括：非公企业与劳动者签订劳动合同情况；按照工资支付有关规定支付职工工资情况；遵守最低工资规定及依法支付加班工资情况；依法参加社会保险和缴纳社会保险费情况；遵守禁止使用童工规定以及女职工和未成年工特殊劳动保护规定情况；其他遵守劳动保障法律法规的情况。"
					// + arg);
					// listinfo.setTitle("宁县开展非公企业党建工作专项督查活动" + arg);
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
		ticket = PreUserInfo.getString("ticket", "");
		userPhoto = PreUserInfo.getString("userPhoto", "");
		LoginId = PreUserInfo.getString("userName", "");
	}

	private void GetData() {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", ticket));
Log.d("eeeeee",SearchText);
		ArrayValues.add(new BasicNameValuePair("searchContent.title", SearchText));
		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/cms/searchContent/getListJsonData", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_DUE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_datalist_activity);
		initview();
		setonclicklistener();
		initviewHeight();
//		Fragments.clear();// 清空list
//		initfragment();// list 装填fragment
//		FragmentManager = getSupportFragmentManager();
//		ViewPaper.setOffscreenPageLimit(NumberPicture);
//		ViewPaper.setOnPageChangeListener(new MyOnPageChangeListener());
//		ViewPaper.setAdapter(new MyPagerAdapter());
//		initdot(NumberPicture);
//		godotchange(0);// 显示第一个逗点为绿色
		setheadtextview();
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		PreALLChannel = getSharedPreferences("ALLChannel", Context.MODE_PRIVATE);
		ReadTicket();
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		SearchText = bundle.getString("search_text");
		GetData();
	}

	private void setheadtextview() {
		headTextView = new TextView(this);
		headTextView.setGravity(Gravity.CENTER);
		headTextView.setMinHeight(100);
		headTextView.setText("正在刷新...");
		headTextView.setTypeface(Typeface.DEFAULT_BOLD);
		headTextView.setTextSize(15);
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

				SearchModel listinfo = new SearchModel();
				listinfo.setTime("2016-12-14");
				listinfo.setDetail(
						"中共党史是中国共产党历史的简称。是中国共产党从1921年7月1日成立以来整个发展过程的全部历史。主要包括中国共产党历次代表大会的情况、党章的不断完善过程、党在各个不同时期的组织建设和发展状况、党领导全国各族人民进行革命和建设的发展历程和全部史实的记载。"
								+ arg);
				listinfo.setTitle("中共党史研究新成果" + arg);
				listinfo.setNumber("23");
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
		mAdapter = new SearchAdapter(this, list, ListData);
		ListData.setAdapter(mAdapter);
	}

	private void initdot(int numpic) {
		// TODO Auto-generated method stub
		for (int i = 9; i >= numpic; i--) {
			dot[i].setVisibility(View.GONE);
		}
	}

	private void godotchange(int position) {
		for (int i = 0; i < NumberPicture; i++) {
			dot[i].setBackgroundResource(R.drawable.dotn);
		}
		dot[position].setBackgroundResource(R.drawable.dotc);
		TextTitle.setText(Title[position]);
	}

	private void initfragment() {
		// TODO Auto-generated method stub
//		Fragments.add(new SearchTopOneFragment());
//		Fragments.add(new SearchTopTwoFragment());
//		Fragments.add(new SearchTopThreeFragment());
//		Fragments.add(new SearchTopFourFragment());
//		Fragments.add(new SearchTopFiveFragment());
//		Fragments.add(new SearchTopSixFragment());
//		Fragments.add(new SearchTopSevenFragment());
//		Fragments.add(new SearchTopEightFragment());
//		Fragments.add(new SearchTopNineFragment());
//		Fragments.add(new SearchTopTenFragment());
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ImageBack.setOnClickListener(this);
		ListData.setOnItemClickListener(this);
	}

	private void initviewHeight() {
		// TODO Auto-generated method stub
		screenwidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		ScreenHeight = (int) (screenwidth / 2);
		LinearLayout.LayoutParams LayoutParams = (android.widget.LinearLayout.LayoutParams) RelativeViewPage
				.getLayoutParams();
		LayoutParams.height = ScreenHeight;
		RelativeViewPage.setLayoutParams(LayoutParams);
	}

	private void initview() {
		// TODO Auto-generated method stub
		RelativeViewPage = (RelativeLayout) findViewById(R.id.rel_viewpaper);
		ViewPaper = (Childviewpaper) findViewById(R.id.viewPager);
		TextTitle = (TextView) findViewById(R.id.text_title);
		dot[0] = (ImageView) findViewById(R.id.dot1);
		dot[1] = (ImageView) findViewById(R.id.dot2);
		dot[2] = (ImageView) findViewById(R.id.dot3);
		dot[3] = (ImageView) findViewById(R.id.dot4);
		dot[4] = (ImageView) findViewById(R.id.dot5);
		dot[5] = (ImageView) findViewById(R.id.dot6);
		dot[6] = (ImageView) findViewById(R.id.dot7);
		dot[7] = (ImageView) findViewById(R.id.dot8);
		dot[8] = (ImageView) findViewById(R.id.dot9);
		dot[9] = (ImageView) findViewById(R.id.dot10);
		ListData = (ListView) findViewById(R.id.list_data);
		ImageBack = (ImageView) findViewById(R.id.image_back);
	}

	private class MyPagerAdapter extends PagerAdapter {
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			return NumberPicture;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(Fragments.get(position).getView());
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Fragment fragment = Fragments.get(position);
			if (!fragment.isAdded()) {
				FragmentTransaction ft = FragmentManager.beginTransaction();
				ft.add(fragment, fragment.getClass().getSimpleName());
				ft.commit();
				FragmentManager.executePendingTransactions();
			}

			if (fragment.getView().getParent() == null) {
				container.addView(fragment.getView());
			}
			return fragment.getView();
		}
	};

	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			godotchange(arg0);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
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
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		SearchModel data = list.get(position - 1);
//		Intent intent = new Intent();
//		intent.setClass(getApplicationContext(), SearchDetailActivity.class);
//		Bundle bundle = new Bundle();
//		bundle.putString("Title", data.getTitle());
//		bundle.putString("detail", data.getDetail());
//		bundle.putString("Time", data.getTime());
//		bundle.putString("Name", "名字");
//		intent.putExtras(bundle);
//		startActivity(intent);
//		CollectModel data = list.get(position - 1);
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), webview.class);
		Bundle bundle = new Bundle();
		bundle.putString("url",URLcontainer.urlip+ data.getNumber());
//		// bundle.putString("Time", "2016-11-23");
//		// bundle.putString("Name", "小李");
//		// bundle.putString("PageTitle", "收藏详情");
//		// bundle.putString("Detail",
//		// "中国共产主义青年团，简称共青团，原名中国社会主义青年团，是中国共产党领导的一个由信仰共产主义的中国青年组成的群众性组织。共青团中央委员会受中共中央委员会领导，共青团的地方各级组织受同级党的委员会领导，同时受共青团上级组织领导。1922年5月，团的第一次代表大会在广州举行，正式成立中国社会主义青年团，1925年1月26日改称中国共产主义青年团。1959年5月4日共青团中央颁布共青团团徽。");
		intent.putExtras(bundle);
		startActivity(intent);
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

}

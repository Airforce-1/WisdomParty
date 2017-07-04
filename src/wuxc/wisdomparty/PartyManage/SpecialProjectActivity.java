package wuxc.wisdomparty.PartyManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Adapter.SpecialAdapter;
import wuxc.wisdomparty.ChildFragment.SpecialTopEightFragment;
import wuxc.wisdomparty.ChildFragment.SpecialTopFiveFragment;
import wuxc.wisdomparty.ChildFragment.SpecialTopFourFragment;
import wuxc.wisdomparty.ChildFragment.SpecialTopNineFragment;
import wuxc.wisdomparty.ChildFragment.SpecialTopOneFragment;
import wuxc.wisdomparty.ChildFragment.SpecialTopSevenFragment;
import wuxc.wisdomparty.ChildFragment.SpecialTopSixFragment;
import wuxc.wisdomparty.ChildFragment.SpecialTopTenFragment;
import wuxc.wisdomparty.ChildFragment.SpecialTopThreeFragment;
import wuxc.wisdomparty.ChildFragment.SpecialTopTwoFragment;
import wuxc.wisdomparty.Internet.GetChannelByKey;
import wuxc.wisdomparty.Internet.HttpGetData;
import wuxc.wisdomparty.Internet.webview;
import wuxc.wisdomparty.Model.SpecialModel;
import wuxc.wisdomparty.Model.SpecialModel;
import wuxc.wisdomparty.layout.Childviewpaper;

public class SpecialProjectActivity extends FragmentActivity
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
	private String[] Title = { "ͬ�Ĺ����й���", "ϰ���Ľ���2", "ϰ���Ľ���3", "ϰ���Ľ���4", "ϰ���Ľ���5", "ϰ���Ľ���6", "ϰ���Ľ���7", "ϰ���Ľ���8",
			"ϰ���Ľ���9", "ϰ���Ľ���10" };
	private TextView TextTitle;
	private ListView ListData;
	private ImageView ImageBack;
	List<SpecialModel> list = new ArrayList<SpecialModel>();
	private static SpecialAdapter mAdapter;
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
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);

					// JSONObject jsonObject = json_data.getJSONObject("data");
					SpecialModel listinfo = new SpecialModel();

					listinfo.setTime(json_data.getString("createtime"));
					listinfo.setTitle(json_data.getString("title"));
					listinfo.setImageUrl(json_data.getString("sacleImage"));
					listinfo.setDetail(json_data.getString("content"));
					listinfo.setNumber(json_data.getString("author"));
					// listinfo.setTime("2016-12-14");
					listinfo.setCont(true);
					try {
						listinfo.setLink(json_data.getString("otherLinks"));
						if (json_data.getString("content").equals("") || json_data.getString("content") == null
								|| json_data.getString("content").equals("null")) {
							listinfo.setDetail(json_data.getString("source"));
							listinfo.setCont(false);
						}

					} catch (Exception e) {
						// TODO: handle exception
					} // listinfo.setDetail(
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
		ticket = PreUserInfo.getString("ticket", "");
		userPhoto = PreUserInfo.getString("userPhoto", "");
		LoginId = PreUserInfo.getString("userName", "");
	}

	private void GetData() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", ticket));

		ArrayValues.add(new BasicNameValuePair("chn", "zxhd"));
		// ArrayValues.add(new BasicNameValuePair("applyType", "" + 2));
		// ArrayValues.add(new BasicNameValuePair("helpSType", "" + 1));
		// ArrayValues.add(new BasicNameValuePair("modelSign", "KNDY_APPLY"));
		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		new Thread(new Runnable() { // �����߳��ϴ��ļ�
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/cms/channel/channleListData", ArrayValues);
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
		setContentView(R.layout.special_project_activity);
		initview();
		setonclicklistener();
		initviewHeight();
		Fragments.clear();// ���list
		initfragment();// list װ��fragment
		FragmentManager = getSupportFragmentManager();
		ViewPaper.setOffscreenPageLimit(NumberPicture);
		ViewPaper.setOnPageChangeListener(new MyOnPageChangeListener());
		ViewPaper.setAdapter(new MyPagerAdapter());
		initdot(NumberPicture);
		godotchange(0);// ��ʾ��һ������Ϊ��ɫ
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

				SpecialModel listinfo = new SpecialModel();
				listinfo.setTime("2016-12-14");
				listinfo.setDetail(
						"�й���ʷ���й���������ʷ�ļ�ơ����й���������1921��7��1�ճ�������������չ���̵�ȫ����ʷ����Ҫ�����й����������δ��������������µĲ������ƹ��̡����ڸ�����ͬʱ�ڵ���֯����ͷ�չ״�������쵼ȫ������������и����ͽ���ķ�չ���̺�ȫ��ʷʵ�ļ��ء�"
								+ arg);
				listinfo.setTitle("�й���ʷ�о��³ɹ�" + arg);
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
		mAdapter = new SpecialAdapter(this, list, ListData);
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
		Fragments.add(new SpecialTopOneFragment());
		Fragments.add(new SpecialTopTwoFragment());
		Fragments.add(new SpecialTopThreeFragment());
		Fragments.add(new SpecialTopFourFragment());
		Fragments.add(new SpecialTopFiveFragment());
		Fragments.add(new SpecialTopSixFragment());
		Fragments.add(new SpecialTopSevenFragment());
		Fragments.add(new SpecialTopEightFragment());
		Fragments.add(new SpecialTopNineFragment());
		Fragments.add(new SpecialTopTenFragment());
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
		SpecialModel data = list.get(position - 1);
		if (data.isCont()) {
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), SpecialDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("Title", data.getTitle());
			bundle.putString("detail", data.getDetail());
			bundle.putString("Time", data.getTime());
			bundle.putString("Name", data.getNumber());
			intent.putExtras(bundle);
			startActivity(intent);
		} else {
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), webview.class);
			Bundle bundle = new Bundle();
			bundle.putString("url", data.getLink());
			// // bundle.putString("Time", "2016-11-23");
			// // bundle.putString("Name", "С��");
			// // bundle.putString("PageTitle", "�ղ�����");
			// // bundle.putString("Detail",
			// //
			// "�й��������������ţ���ƹ����ţ�ԭ���й�������������ţ����й��������쵼��һ������������������й�������ɵ�Ⱥ������֯������������ίԱ�����й�����ίԱ���쵼�������ŵĵط�������֯��ͬ������ίԱ���쵼��ͬʱ�ܹ������ϼ���֯�쵼��1922��5�£��ŵĵ�һ�δ������ڹ��ݾ��У���ʽ�����й�������������ţ�1925��1��26�ոĳ��й��������������š�1959��5��4�չ���������䲼�������Żա�");
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		float tempY = event.getY();
		float tempyfoot = event.getY();
		firstItemIndex = ListData.getFirstVisiblePosition();
		lastItemIndex = ListData.getLastVisiblePosition();
		// Toast.makeText(getApplicationContext(), " lastItemIndex" +
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

}

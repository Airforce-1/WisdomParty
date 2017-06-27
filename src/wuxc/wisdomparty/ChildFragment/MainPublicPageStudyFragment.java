package wuxc.wisdomparty.ChildFragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alipay.android.phone.mrpc.core.t;
import com.umeng.socialize.utils.Log;

import android.R.bool;
import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Adapter.StudyArticleAdapter;
import wuxc.wisdomparty.Adapter.StudyVideoAadapter;
import wuxc.wisdomparty.Internet.GetChannelByKey;
import wuxc.wisdomparty.Internet.HttpGetData;
import wuxc.wisdomparty.Internet.URLcontainer;
import wuxc.wisdomparty.Internet.webview;
import wuxc.wisdomparty.Model.StudyArticleModel;
import wuxc.wisdomparty.Model.StudyArticleModel;
import wuxc.wisdomparty.Model.StudyVideoModel;
import wuxc.wisdomparty.PartyManage.AssistanceDetailActivity;
import wuxc.wisdomparty.PartyManage.StudyArticleDetailActivity;
import wuxc.wisdomparty.PartyManage.StudyArticleDetailActivity;
import wuxc.wisdomparty.PartyManage.StudyVideoDetailActivity;

public class MainPublicPageStudyFragment extends Fragment
		implements OnTouchListener, OnClickListener, OnItemClickListener {
	private TextView text_list_title;
	private ListView ListData;
	List<StudyArticleModel> list = new ArrayList<StudyArticleModel>();
	private static StudyArticleAdapter mAdapter;
	List<StudyVideoModel> listVideo = new ArrayList<StudyVideoModel>();
	private static StudyVideoAadapter VAdapter;
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
	private int type = 0;// 0=article 1=video
	private TextView TextArticle;
	private TextView TextVideo;
	private String ticket;
	private String chn;
	private String userPhoto;
	private String LoginId;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private SharedPreferences PreALLChannel;// 存储所用频道信息
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
			case 20:
				GetDataDueDatavideo(msg.obj);
				break;
			default:
				break;
			}
		}
	};

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// startpicturefly();
		// changetime();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.main_publicpage_study_fragment, container, false);
		text_list_title = (TextView) view.findViewById(R.id.text_list_title);
		initview(view);
		setonclicklistener();
		setheadtextview();
		PreUserInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		PreALLChannel = getActivity().getSharedPreferences("ALLChannel", Context.MODE_PRIVATE);
		ReadTicket();
		GetData();
		return view;
	}

	private void setheadtextview() {
		headTextView = new TextView(getActivity());
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
			if (type == 0) {
				for (int i = 0; i < 10; i++) {

					StudyArticleModel listinfo = new StudyArticleModel();
					listinfo.setTime("2016-12-14");
					listinfo.setDetail("2016年12月18日，三门峡市党规党纪知识竞赛顺利举行，其中，三门峡医药总公司代表队获得一等奖");
					listinfo.setTitle("三门峡市党规党纪知识竞赛纪实" + arg);
					listinfo.setBackGround("");
					;
					list.add(listinfo);

				}
			} else {
				for (int i = 0; i < 10; i++) {
					StudyVideoModel listinfo = new StudyVideoModel();
					listinfo.setTime("20:15");
					listinfo.setTitle("中共十八大习近平总书记的讲话" + arg);
					listinfo.setNumberCollect("12");
					listinfo.setNumberGreat("23");
					listinfo.setImageUrl("");
					listVideo.add(listinfo);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (arg == 1) {
			go();
		} else {
			if (type == 0) {
				mAdapter.notifyDataSetChanged();
			} else {
				VAdapter.notifyDataSetChanged();
			}

		}

	}

	protected void GetDataDueDatavideo(Object obj) {

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
				GetDataListvideo(Data, curPage);
			} else if (Type.equals(GET_FAIL_RESULT)) {
				Toast.makeText(getActivity(), "服务器数据失败", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getActivity(), "数据格式校验失败", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetDataListvideo(String data, int arg) {
		text_list_title.setVisibility(View.GONE);
		;
		if (arg == 1) {
			listVideo.clear();
		}
		JSONArray jArray = null;
		try {
			jArray = new JSONArray(data);
			JSONObject json_data = null;
			if (jArray.length() == 0) {
				// / Toast.makeText(getActivity(), "无数据",
				// Toast.LENGTH_SHORT).show();

			} else {
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					Log.e("json_data", "" + json_data);
					// JSONObject jsonObject = json_data.getJSONObject("data");
					StudyVideoModel listinfo = new StudyVideoModel();
					listinfo.setTime(json_data.getString("createtime"));
					listinfo.setTitle(json_data.getString("title"));
					listinfo.setNumberCollect("12");
					listinfo.setNumberGreat("23");

					listinfo.setUrl(json_data.getString("otherLinks"));
					try {
						JSONArray jArray1 = new JSONArray(json_data.getString("sacleImage"));
						JSONObject demoJson = jArray1.getJSONObject(0);
						listinfo.setImageUrl(demoJson.getString("filePath"));

					} catch (Exception e) {
						// TODO: handle exception
						listinfo.setImageUrl("");
					}
					listVideo.add(listinfo);

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
				Toast.makeText(getActivity(), "服务器数据失败", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getActivity(), "数据格式校验失败", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetDataList(String data, int arg) {
		text_list_title.setVisibility(View.GONE);
		;
		if (arg == 1) {
			list.clear();
		}
		JSONArray jArray = null;
		try {
			jArray = new JSONArray(data);
			JSONObject json_data = null;
			if (jArray.length() == 0) {
				// / Toast.makeText(getActivity(), "无数据",
				// Toast.LENGTH_SHORT).show();

			} else {
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					Log.e("json_data", "" + json_data);
					// JSONObject jsonObject = json_data.getJSONObject("data");
					StudyArticleModel listinfo = new StudyArticleModel();

					listinfo.setTime(json_data.getString("createtime"));
					listinfo.setTitle(json_data.getString("title"));
					listinfo.setBackGround(json_data.getString("sacleImage"));
					listinfo.setDetail(json_data.getString("content"));
					listinfo.setCont(true);
					if (json_data.getString("content").equals("") || json_data.getString("content") == null
							|| json_data.getString("content").equals("null")) {
						listinfo.setDetail(json_data.getString("source"));
						listinfo.setCont(false);
					}
					listinfo.setLink(json_data.getString("otherLinks"));
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
		ArrayValues.add(new BasicNameValuePair("chn", "learnwz"));
		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		new Thread(new Runnable() { // 开启线程上传文件
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

	protected void go() {
		ListData.setPadding(0, -100, 0, 0);
		if (type == 0) {
			mAdapter = new StudyArticleAdapter(getActivity(), list, ListData);
			ListData.setAdapter(mAdapter);
		} else {
			VAdapter = new StudyVideoAadapter(getActivity(), listVideo, ListData);
			ListData.setAdapter(VAdapter);
		}

	}

	private void initview(View view) {
		// TODO Auto-generated method stub
		ListData = (ListView) view.findViewById(R.id.list_data);
		TextArticle = (TextView) view.findViewById(R.id.text_article);
		TextVideo = (TextView) view.findViewById(R.id.text_video);
		TextArticle.setTextColor(Color.RED);
		TextVideo.setTextColor(Color.BLACK);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ListData.setOnItemClickListener(this);
		TextArticle.setOnClickListener(this);
		TextVideo.setOnClickListener(this);
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
				// Toast.makeText(getActivity(), "正在刷新study",
				// Toast.LENGTH_SHORT).show();
				if (type == 1) {
					getdatavideo();
				} else {
					GetData();
				}

			}
			int temp = 1;
			temp = (lastItemIndex) % pageSize;
			// temp = 0;
			if (temp == 0 && (startYfoot - tempyfoot > 400)) {
				curPage++;
				if (curPage > totalPage) {
					Toast.makeText(getActivity(), " 没有更多了", Toast.LENGTH_SHORT).show();
					// // listinfoagain();
				} else {
					if (type == 1) {
						getdatavideo();
					} else {
						GetData();
					}
					Toast.makeText(getActivity(), "正在加载下一页", Toast.LENGTH_SHORT).show();
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
		if (type == 0) {

			StudyArticleModel data = list.get(position - 1);
			if (data.isCont()) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), AssistanceDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("Title", data.getTitle());
				bundle.putString("Time", data.getTime());
				bundle.putString("content", data.getDetail());
				intent.putExtras(bundle);
				startActivity(intent);
			} else {
				Intent intent = new Intent();
				intent.setClass(getActivity(), webview.class);
				Bundle bundle = new Bundle();
				bundle.putString("url", data.getLink());
				// // bundle.putString("Time", "2016-11-23");
				// // bundle.putString("Name", "小李");
				// // bundle.putString("PageTitle", "收藏详情");
				// // bundle.putString("Detail",
				// //
				// "中国共产主义青年团，简称共青团，原名中国社会主义青年团，是中国共产党领导的一个由信仰共产主义的中国青年组成的群众性组织。共青团中央委员会受中共中央委员会领导，共青团的地方各级组织受同级党的委员会领导，同时受共青团上级组织领导。1922年5月，团的第一次代表大会在广州举行，正式成立中国社会主义青年团，1925年1月26日改称中国共产主义青年团。1959年5月4日共青团中央颁布共青团团徽。");
				intent.putExtras(bundle);
				startActivity(intent);
			}

		} else {
			StudyVideoModel data = listVideo.get(position - 1);
			// Intent intent = new Intent();
			// intent.setClass(getActivity(), webview.class);
			// Bundle bundle = new Bundle();
			// bundle.putString("url", data.getUrl());
			// // // bundle.putString("Time", "2016-11-23");
			// // // bundle.putString("Name", "小李");
			// // // bundle.putString("PageTitle", "收藏详情");
			// // // bundle.putString("Detail",
			// // //
			// //
			// "中国共产主义青年团，简称共青团，原名中国社会主义青年团，是中国共产党领导的一个由信仰共产主义的中国青年组成的群众性组织。共青团中央委员会受中共中央委员会领导，共青团的地方各级组织受同级党的委员会领导，同时受共青团上级组织领导。1922年5月，团的第一次代表大会在广州举行，正式成立中国社会主义青年团，1925年1月26日改称中国共产主义青年团。1959年5月4日共青团中央颁布共青团团徽。");
			// intent.putExtras(bundle);
			// startActivity(intent);
			try {
				Intent intent = new Intent();
				intent.setAction("android.intent.action.VIEW");
				String path = data.getUrl();
				Uri content_url = Uri.parse(path);
				intent.setData(content_url);
				startActivity(intent);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

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
		case R.id.text_list_title:

			break;
		case R.id.text_article:
			type = 0;
			GetData();
			curPage = 1;
			TextArticle.setTextColor(Color.RED);
			TextVideo.setTextColor(Color.BLACK);
			break;
		case R.id.text_video:
			type = 1;
			getdatavideo();
			curPage = 1;
			TextVideo.setTextColor(Color.RED);
			TextArticle.setTextColor(Color.BLACK);
			break;
		default:
			break;
		}
	}

	private void getdatavideo() {
		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		ArrayValues.add(new BasicNameValuePair("chn", "sp"));
		ArrayValues.add(new BasicNameValuePair("curPage", "" + curPage));
		ArrayValues.add(new BasicNameValuePair("pageSize", "" + pageSize));
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/cms/channel/channleListData", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = 20;
				uiHandler.sendMessage(msg);
			}
		}).start();
	}

}

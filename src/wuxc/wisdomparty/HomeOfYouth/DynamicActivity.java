
package wuxc.wisdomparty.HomeOfYouth;

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
import wuxc.wisdomparty.Adapter.RewardsAdapter;
import wuxc.wisdomparty.Internet.GetChannelByKey;
import wuxc.wisdomparty.Internet.HttpGetData;
import wuxc.wisdomparty.Model.RewardsModel;
import wuxc.wisdomparty.PartyManage.AssistanceDetailActivity;
import wuxc.wisdomparty.PartyManage.RewardsDetailActivity;
import wuxc.wisdomparty.add.orgDetailActivity;
import wuxc.wisdomparty.Model.RewardsModel;

public class DynamicActivity extends Activity implements OnTouchListener, OnClickListener, OnItemClickListener {
	private ListView ListData;
	private ImageView ImageBack;
	List<RewardsModel> list = new ArrayList<RewardsModel>();
	private static RewardsAdapter mAdapter;
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
		setContentView(R.layout.organization_life_activity2);
		initview();
		setonclicklistener();
		setheadtextview();
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		PreALLChannel = getSharedPreferences("ALLChannel", Context.MODE_PRIVATE);
		ReadTicket();
		GetData();
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

					// JSONObject jsonObject = json_data.getJSONObject("data");
					RewardsModel listinfo = new RewardsModel();

					listinfo.setTime(json_data.getString("createtime"));
					listinfo.setTitle(json_data.getString("title"));
					listinfo.setBackGround(json_data.getString("sacleImage"));
					listinfo.setDetail(json_data.getString("content"));
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
		chn = GetChannelByKey.GetSign(PreALLChannel, "动态信息");
		ArrayValues.add(new BasicNameValuePair("chn", chn));
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

				RewardsModel listinfo = new RewardsModel();
				listinfo.setTime("2016-12-14");
				listinfo.setDetail(
						"此次专项检查的范围是招用农民工较多的建筑、制造、采矿、餐饮和其他中小型劳动密集型企业以及个体经济组织。检查内容包括：非公企业与劳动者签订劳动合同情况；按照工资支付有关规定支付职工工资情况；遵守最低工资规定及依法支付加班工资情况；依法参加社会保险和缴纳社会保险费情况；遵守禁止使用童工规定以及女职工和未成年工特殊劳动保护规定情况；其他遵守劳动保障法律法规的情况。"
								+ arg);
				listinfo.setTitle("宁县开展非公企业党建工作专项督查活动" + arg);
				listinfo.setBackGround("");
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
		mAdapter = new RewardsAdapter(this, list, ListData);
		ListData.setAdapter(mAdapter);
	}

	private void initview() {
		// TODO Auto-generated method stub
		ListData = (ListView) findViewById(R.id.list_data);
		ImageBack = (ImageView) findViewById(R.id.image_back);
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
		RewardsModel data = list.get(position - 1);
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), AssistanceDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("content", data.getDetail());
		bundle.putString("Title", data.getTitle());
		bundle.putString("Time", data.getTime());
		intent.putExtras(bundle);
		startActivity(intent);
	}

}

// package wuxc.wisdomparty.HomeOfYouth;
//
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.LinkedList;
// import java.util.List;
//
// import org.json.JSONArray;
// import org.json.JSONException;
// import org.json.JSONObject;
//
// import android.app.Activity;
// import android.content.Context;
// import android.content.Intent;
// import android.os.AsyncTask;
// import android.os.AsyncTask.Status;
// import android.os.Bundle;
// import android.support.v4.app.FragmentActivity;
// import android.text.TextUtils;
// import android.util.Log;
// import android.view.LayoutInflater;
// import android.view.Menu;
// import android.view.MenuItem;
// import android.view.View;
// import android.view.ViewGroup;
// import android.view.Window;
// import android.view.View.OnClickListener;
// import android.widget.BaseAdapter;
// import android.widget.ImageView;
// import android.widget.TextView;
// import android.widget.Toast;
// import single.wuxc.wisdomparty.R;
// import wuxc.wisdomparty.Model.DynamicModel;
// import wuxc.wisdomparty.PartyManage.SpecialDetailActivity;
// import wuxc.wisdomparty.WaterFallFlow.Helper;
// import wuxc.wisdomparty.WaterFallFlow.ImageFetcher;
// import wuxc.wisdomparty.WaterFallFlow.PLA_AdapterView;
// import wuxc.wisdomparty.WaterFallFlow.PLA_AdapterView.OnItemClickListener;
// import wuxc.wisdomparty.WaterFallFlow.ScaleImageView;
// import wuxc.wisdomparty.WaterFallFlow.XListView;
// import wuxc.wisdomparty.WaterFallFlow.XListView.IXListViewListener;
//
// public class DynamicActivity extends FragmentActivity
// implements OnClickListener, IXListViewListener, OnItemClickListener {
// private ImageView image_back;
// private ImageFetcher mImageFetcher;
// private XListView mAdapterView = null;
// private StaggeredAdapter mAdapter = null;
// private int currentPage = 0;
// ContentTask task = new ContentTask(this, 2);
// private String[] pic = { "one", "two", "two", "two", "two", "two", "two",
// "two", "two", "two" };
// private int[] height = { 13, 23, 23, 23, 23, 23, 23, 23, 23, 23 };
//
// @Override
// protected void onCreate(Bundle savedInstanceState) {
// // TODO Auto-generated method stub
// super.onCreate(savedInstanceState);
// requestWindowFeature(Window.FEATURE_NO_TITLE);
// setContentView(R.layout.dynamicactivity);
// image_back = (ImageView) findViewById(R.id.image_back);
// image_back.setOnClickListener(this);
// mAdapterView = (XListView) findViewById(R.id.list);
// mAdapterView.setPullLoadEnable(true);
// mAdapterView.setXListViewListener(this);
// mAdapterView.setOnItemClickListener(this);
// mAdapter = new StaggeredAdapter(this, mAdapterView);
// mImageFetcher = new ImageFetcher(this, 240);
// mImageFetcher.setLoadingImage(R.drawable.empty_photo);
//
// }
//
// private class ContentTask extends AsyncTask<String, Integer,
// List<DynamicModel>> {
//
// private Context mContext;
// private int mType = 1;
//
// public ContentTask(Context context, int type) {
// super();
// mContext = context;
// mType = type;
// }
//
// @Override
// protected List<DynamicModel> doInBackground(String... params) {
// try {
// return parseNewsJSON(params[0]);
// } catch (IOException e) {
// e.printStackTrace();
// }
// return null;
// }
//
// @Override
// protected void onPostExecute(List<DynamicModel> result) {
// if (mType == 1) {
//
// mAdapter.addItemTop(result);
// mAdapter.notifyDataSetChanged();
// mAdapterView.stopRefresh();
//
// } else if (mType == 2) {
// mAdapterView.stopLoadMore();
// mAdapter.addItemLast(result);
// mAdapter.notifyDataSetChanged();
// }
//
// }
//
// @Override
// protected void onPreExecute() {
// }
//
// public List<DynamicModel> parseNewsJSON(String url) throws IOException {
// List<DynamicModel> Dynamic = new ArrayList<DynamicModel>();
// String json = "";
// if (Helper.checkConnection(mContext)) {
// try {
// json = Helper.getStringFromUrl(url);
//
// } catch (IOException e) {
// Log.e("IOException is : ", e.toString());
// e.printStackTrace();
// return Dynamic;
// }
// }
// Log.d("MainActiivty", "json:" + json);
//
// try {
// // if (null != json) {
// // JSONObject newsObject = new JSONObject(json);
// // JSONObject jsonObject = newsObject.getJSONObject("data");
// // JSONArray blogsJson = jsonObject.getJSONArray("blogs");
// //
// // for (int i = 0; i < blogsJson.length(); i++) {
// // JSONObject newsInfoLeftObject = blogsJson.getJSONObject(i);
// // DynamicModel newsInfo1 = new DynamicModel();
// // newsInfo1.setAlbid(
// // newsInfoLeftObject.isNull("albid") ? "" :
// // newsInfoLeftObject.getString("albid"));
// // newsInfo1
// // .setIsrc(newsInfoLeftObject.isNull("isrc") ? "" :
// // newsInfoLeftObject.getString("isrc"));
// // newsInfo1.setMsg(newsInfoLeftObject.isNull("msg") ? "" :
// // newsInfoLeftObject.getString("msg"));
// // newsInfo1.setHeight(200);
// // Dynamic.add(newsInfo1);
// // }
// // }
// for (int i = 0; i < 10; i++) {
// DynamicModel dynamicmodel = new DynamicModel();
// dynamicmodel.setTitle("中国共产党研究新成果");
// dynamicmodel.setLabel("党史研究");
// dynamicmodel.setCollect(true);
// dynamicmodel.setGreat(false);
// dynamicmodel.setImageUrl(pic[i]);
// dynamicmodel.setWidth(20);
// dynamicmodel.setHeight(height[i]);
// Dynamic.add(dynamicmodel);
// }
// } catch (Exception e) {
// e.printStackTrace();
// }
// return Dynamic;
// }
// }
//
// /**
// * 添加内容
// *
// * @param pageindex
// * @param type
// * 1为下拉刷新 2为加载更多
// */
// private void AddItemToContainer(int pageindex, int type) {
// if (task.getStatus() != Status.RUNNING) {
// String url = "http://www.duitang.com/album/1733789/masn/p/" + pageindex +
// "/5/";
// Log.d("MainActivity", "current url:" + url);
// ContentTask task = new ContentTask(this, type);
// task.execute(url);
//
// }
// }
//
// public class StaggeredAdapter extends BaseAdapter {
// private Context mContext;
// private LinkedList<DynamicModel> mInfos;
// private XListView mListView;
//
// public StaggeredAdapter(Context context, XListView xListView) {
// mContext = context;
// mInfos = new LinkedList<DynamicModel>();
// mListView = xListView;
// }
//
// @Override
// public View getView(int position, View convertView, ViewGroup parent) {
//
// ViewHolder holder;
// DynamicModel dynamicmodel = mInfos.get(position);
//
// if (convertView == null) {
// LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());
// convertView = layoutInflator.inflate(R.layout.item_dynamic, null);
// holder = new ViewHolder();
// holder.imageheadimg = (ScaleImageView)
// convertView.findViewById(R.id.image_headimg);
// holder.texttitle = (TextView) convertView.findViewById(R.id.text_title);
// holder.textlabel = (TextView) convertView.findViewById(R.id.text_label);
// holder.imagecollect = (ImageView)
// convertView.findViewById(R.id.image_collect);
// holder.imagegreat = (ImageView) convertView.findViewById(R.id.image_great);
// convertView.setTag(holder);
// }
//
// holder = (ViewHolder) convertView.getTag();
// holder.imageheadimg.setImageWidth(dynamicmodel.getWidth());
// holder.imageheadimg.setImageHeight(dynamicmodel.getHeight());
// holder.texttitle.setText(dynamicmodel.getTitle());
// holder.textlabel.setText(dynamicmodel.getLabel());
// if (dynamicmodel.isCollect()) {
// holder.imagecollect.setImageResource(R.drawable.collect);
// } else {
// holder.imagecollect.setImageResource(R.drawable.no_collect);
// }
// if (dynamicmodel.isGreat()) {
// holder.imagegreat.setImageResource(R.drawable.great);
// } else {
// holder.imagegreat.setImageResource(R.drawable.no_great);
// }
// if (dynamicmodel.getImageUrl().equals("one")) {
// holder.imageheadimg.setImageResource(R.drawable.dynamic_one);
// } else if (dynamicmodel.getImageUrl().equals("two")) {
// holder.imageheadimg.setImageResource(R.drawable.dynamic_two);
// } else if (!TextUtils.isEmpty(dynamicmodel.getImageUrl())) {
// mImageFetcher.loadImage(dynamicmodel.getImageUrl(), holder.imageheadimg);
// } else {
// holder.imageheadimg.setImageResource(R.drawable.empty_photo);
// }
//
// return convertView;
// }
//
// class ViewHolder {
// ScaleImageView imageheadimg;
// TextView texttitle;
// TextView textlabel;
// ImageView imagecollect;
// ImageView imagegreat;
// }
//
// @Override
// public int getCount() {
// return mInfos.size();
// }
//
// @Override
// public Object getItem(int arg0) {
// return mInfos.get(arg0);
// }
//
// @Override
// public long getItemId(int arg0) {
// return 0;
// }
//
// public void addItemLast(List<DynamicModel> datas) {
// mInfos.addAll(datas);
// }
//
// public void addItemTop(List<DynamicModel> datas) {
// for (DynamicModel info : datas) {
// mInfos.addFirst(info);
// }
// }
// }
//
// @Override
// public boolean onCreateOptionsMenu(Menu menu) {
// return super.onCreateOptionsMenu(menu);
// }
//
// @Override
// public boolean onOptionsItemSelected(MenuItem item) {
//
// return true;
// }
//
// @Override
// protected void onResume() {
// super.onResume();
// mImageFetcher.setExitTasksEarly(false);
// mAdapterView.setAdapter(mAdapter);
// AddItemToContainer(currentPage, 2);
// }
//
// @Override
// protected void onDestroy() {
// super.onDestroy();
//
// }
//
// @Override
// public void onRefresh() {
// AddItemToContainer(++currentPage, 1);
//
// }
//
// @Override
// public void onLoadMore() {
// AddItemToContainer(++currentPage, 2);
//
// }
//
// @Override
// public void onItemClick(PLA_AdapterView<?> parent, View view, int position,
// long id) {
// // TODO Auto-generated method stub
// Toast.makeText(getApplicationContext(), position + "页",
// Toast.LENGTH_SHORT).show();
// Intent intent = new Intent();
// intent.setClass(getApplicationContext(), YouthDetailActivity.class);
// Bundle bundle = new Bundle();
// bundle.putString("Title", "14年抗战表述更完整、更全面、更客观");
// bundle.putString("Time", "2016-11-23");
// bundle.putString("Name", "新华社");
// bundle.putString("PageTitle", "动态信息");
// bundle.putString("Detail",
// "1931年日本发动‘九一八’事变，中国开启抗日序幕。1937年7月7日，宛平城外卢沟桥的炮声和枪响，把中国带入全面抗战。从‘九一八’事变到太平洋战争爆发，中国是反抗日本法西斯的唯一战场。14年抗战比8年抗战的表述，更完整、更全面、更客观。”11日，中国人民抗日战争胜利受降纪念馆馆长吴建宏对新华社记者说。");
// intent.putExtras(bundle);
// startActivity(intent);
// }
//
// @Override
// public void onClick(View v) {
//
// // TODO Auto-generated method stub
// switch (v.getId()) {
//
// case R.id.image_back:
// finish();
// break;
//
// default:
// break;
// }
// }
//
// }

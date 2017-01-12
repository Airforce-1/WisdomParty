package wuxc.wisdomparty.HomeOfMember;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
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
import wuxc.wisdomparty.Model.CommentModel;
import wuxc.wisdomparty.Model.CommentModel;
import wuxc.wisdomparty.layout.RoundImageView;

public class MemberDiscussionDetailActivity extends Activity implements OnClickListener, OnItemClickListener {
	private EditText EditAnswer;
	private Button BtnAnswer;
	private ImageView ImageBack;
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
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg1) {
			switch (msg1.what) {
			case 0:
				getdatalist(curPage);
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
		setContentView(R.layout.discussion_detail_activity);
		Intent intent = this.getIntent(); // ��ȡ���е�intent����
		Bundle bundle = intent.getExtras(); // ��ȡintent�����bundle����
		Name = bundle.getString("Name");
		Title = bundle.getString("Title");
		Time = bundle.getString("Time");

		initview();
		setonclicklistener();
		setlistheight(0);
		settext();
		starttimedelay();

	}

	private void starttimedelay() {
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
				listinfo.setComment(
						"����ʮ�˽�����ȫ������ͨ���ġ��й����������ڼල�����������¼�ơ���������ͻ��ȫ������ε�������⣬����������������ķ�չ�仯���ڣ���������䲼ʩ�еġ��й����������ڼල���������У��������Ͻ����޶������ƣ��ѵ���ʮ�˴�������ǿ���Ľ��衢ǿ�����ڼල��ʵ��̽����ʱת��Ϊ�ƶȳɹ���ʵ���˵��ڼල�ƶȵ���ʱ��������������۽����ڼල���ڵı������ڣ���ȷ���������¼�ǿ���ڼල��ָ��˼�롢����ԭ����Ҫ���ݡ����񡢶���ͷ�ʽ�ȣ�Ϊȫ������ε��������µ��ƶ�����");
				listinfo.setRoundUrl("");
				listinfo.setName("Ѧ��");

				list.add(listinfo);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setlistheight(list.size());
		if (arg == 1) {
			go();
		} else {
			mAdapter.notifyDataSetChanged();
		}

		if (arg == totalPage) {
			TextWarning.setText("û�и�����");
		} else {
			TextWarning.setText("������ظ���");
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

	}

	protected void go() {
		mAdapter = new CommentAdapter(this, list, ListData);
		ListData.setAdapter(mAdapter);
	}

	private void settext() {
		// TODO Auto-generated method stub
		TextDetail.setText(
				"��ǿ���ڼල�����˼����������һ��Ҫ�󡣳������������ǵ��߶����ӵ��ڼල����ȡ��������ʩ��ȡ���������ɼ�����ҲӦ��������ǰ���ڼල�ƶȲ���ȫ�����ǲ���λ�����β�������ִ�в��������ⲻ�ɺ��ӡ����⼲֮���������������֪��֮���ڼ�֮��������̩ɽ����Ī֮�ܡ���ȫ������ε�����Ӹ����Ͻ����������ȱʧ���ල����ȱλ���ܵ��ε�����������⣬�ͱ�ȻҪ��ץ�õ��ڼල��������Թ��̣����ƶȵ��������ø������Ѽල���ƶ����Ƴ���ͷų�����");
		TextTime.setText(Time);
		TextName.setText(Name);
		TextTitle.setText(Title);
		TextWarning.setText("���ڼ�������...");
	}

	private void initview() {
		// TODO Auto-generated method stub
		EditAnswer = (EditText) findViewById(R.id.edit_answer);
		BtnAnswer = (Button) findViewById(R.id.btn_answer);
		ImageBack = (ImageView) findViewById(R.id.image_back);
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
				Toast.makeText(getApplicationContext(), "���ڼ���", Toast.LENGTH_SHORT).show();
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

package wuxc.wisdomparty.MemberCenter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.view.Window;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Adapter.MyfundAdapter;
import wuxc.wisdomparty.Model.MyfundModel;
import wuxc.wisdomparty.layout.dialogtwo;

public class MemberCenterMyMarkTransfer extends Activity
		implements OnTouchListener, OnClickListener, OnItemClickListener {
	private ListView ListData;
	private ImageView ImageBack;
	List<MyfundModel> list = new ArrayList<MyfundModel>();
	private static MyfundAdapter mAdapter;
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
	private TextView TextMarkNumber;
	private String MarkNumber;
	private LinearLayout LinTransferRule;
	private RelativeLayout RelaTransferRule;
	private TextView TextTransferRule;
	private TextView TextTransferIn;
	private TextView TextTransferOut;
	private TextView TextTransfer;
	private ImageView ImageTimeClose;
	private RelativeLayout RelaTime;
	private TextView TextTimeAll;
	private TextView TextTimeThree;
	private TextView TextTimeSix;
	private TextView TextTimeOneYear;
	private String StrTime = "ȫ��ת����¼";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.membercentermymarktransfer);
		initview();
		setonclicklistener();
		setheadtextview();
		getdatalist(curPage);
		Intent intent = this.getIntent(); // ��ȡ���е�intent����
		Bundle bundle = intent.getExtras(); // ��ȡintent�����bundle����
		MarkNumber = bundle.getString("mark");
		TextMarkNumber.setText(MarkNumber);
		settimelayout();
		settextinORout();
		TextTransferRule.setText(StrTime);
	}

	private void settimelayout() {
		// TODO Auto-generated method stub
		RelaTime.setVisibility(View.GONE);
	}

	private void settextinORout() {
		// TODO Auto-generated method stub
		TextTransferIn.setTextColor(Color.BLACK);
		TextTransferOut.setTextColor(Color.BLACK);

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
		if (arg == 1) {
			list.clear();
		}
		// TODO Auto-generated method stub

		try {

			for (int i = 0; i < 10; i++) {

				MyfundModel listinfo = new MyfundModel();
				listinfo.setDetail("���»����");
				listinfo.setChange("+14");
				listinfo.setTime("2016-11-03");
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
		mAdapter = new MyfundAdapter(this, list, ListData);
		ListData.setAdapter(mAdapter);
	}

	private void initview() {
		// TODO Auto-generated method stub
		ListData = (ListView) findViewById(R.id.list_data);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		TextMarkNumber = (TextView) findViewById(R.id.text_number);
		LinTransferRule = (LinearLayout) findViewById(R.id.lin_transfer_rule);
		RelaTransferRule = (RelativeLayout) findViewById(R.id.rela_transfer_rule);
		TextTransferRule = (TextView) findViewById(R.id.text_transfer_rule);
		TextTransferIn = (TextView) findViewById(R.id.text_transfer_in);
		TextTransferOut = (TextView) findViewById(R.id.text_transfer_out);
		TextTransfer = (TextView) findViewById(R.id.text_transfer);
		ImageTimeClose = (ImageView) findViewById(R.id.image_time_close);
		RelaTime = (RelativeLayout) findViewById(R.id.rela_time);
		TextTimeAll = (TextView) findViewById(R.id.text_time_all);
		TextTimeThree = (TextView) findViewById(R.id.text_time_three);
		TextTimeSix = (TextView) findViewById(R.id.text_time_six);
		TextTimeOneYear = (TextView) findViewById(R.id.text_time_oneyear);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ImageBack.setOnClickListener(this);
		ListData.setOnItemClickListener(this);
		LinTransferRule.setOnClickListener(this);
		RelaTransferRule.setOnClickListener(this);
		TextTransferRule.setOnClickListener(this);
		TextTransferIn.setOnClickListener(this);
		TextTransferOut.setOnClickListener(this);
		TextTransfer.setOnClickListener(this);
		ImageTimeClose.setOnClickListener(this);
		RelaTime.setOnClickListener(this);
		TextTimeAll.setOnClickListener(this);
		TextTimeThree.setOnClickListener(this);
		TextTimeSix.setOnClickListener(this);
		TextTimeOneYear.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_transfer_rule:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), MemberCenterMyMarkTransferRule.class);
			startActivity(intent);
			break;
		case R.id.rela_transfer_rule:
			RelaTime.setVisibility(View.VISIBLE);
			break;
		case R.id.text_transfer_rule:
			RelaTime.setVisibility(View.VISIBLE);
			break;
		case R.id.text_transfer_in:
			settextinORout();
			TextTransferIn.setTextColor(Color.BLUE);
			break;
		case R.id.text_transfer_out:
			settextinORout();
			TextTransferOut.setTextColor(Color.BLUE);
			break;
		case R.id.image_time_close:
			RelaTime.setVisibility(View.GONE);
			break;
		case R.id.rela_time:
			RelaTime.setVisibility(View.GONE);
			break;
		case R.id.text_time_all:
			StrTime = "ȫ��ת����¼";
			TextTransferRule.setText(StrTime);
			RelaTime.setVisibility(View.GONE);
			break;
		case R.id.text_time_three:
			StrTime = "���������ת����¼";
			TextTransferRule.setText(StrTime);
			RelaTime.setVisibility(View.GONE);
			break;
		case R.id.text_time_six:
			StrTime = "���������ת����¼";
			TextTransferRule.setText(StrTime);
			RelaTime.setVisibility(View.GONE);
			break;
		case R.id.text_time_oneyear:
			StrTime = "���һ��ת����¼";
			TextTransferRule.setText(StrTime);
			RelaTime.setVisibility(View.GONE);
			break;
		case R.id.image_back:
			finish();
			break;
		case R.id.text_transfer:
			showAlertDialog();

			break;
		default:
			break;
		}
	}

	public void showAlertDialog() {

		dialogtwo.Builder builder = new dialogtwo.Builder(this);
		builder.setMessage("�������л��ֶ���ת��Ϊ����,��ȷ��");
		builder.setTitle("����ת��ȷ��");
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});

		builder.setNegativeButton("ȡ��", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.create().show();

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		float tempY = event.getY();
		float tempyfoot = event.getY();
		firstItemIndex = ListData.getFirstVisiblePosition();
		lastItemIndex = ListData.getLastVisiblePosition();
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
				getdatalist(curPage);
			}
			int temp = 1;
			temp = (lastItemIndex) % pageSize;
			if (temp == 0 && (startYfoot - tempyfoot > 400)) {
				curPage++;
				if (curPage > totalPage) {
					Toast.makeText(getApplicationContext(), " û�и�����", Toast.LENGTH_SHORT).show();
					// // listinfoagain();
				} else {
					getdatalist(curPage);
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
	}

}

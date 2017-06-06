package wuxc.wisdomparty.PartyManage;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import single.wuxc.wisdomparty.R;
import single.wuxc.wisdomparty.R.id;
import wuxc.wisdomparty.Internet.GetChannelByKey;
import wuxc.wisdomparty.Internet.HttpGetData;
import wuxc.wisdomparty.layout.RoundImageView;
import android.view.Window;

public class CandicateDetailActivity extends Activity implements OnClickListener {
	private RoundImageView RoundHeadimg;
	private ImageView ImageBack;
	private ImageView ImageSymbol;
	private ImageView ImageSelected;
	private TextView TextName;
	private TextView TextPartyMember;
	private TextView TextNumber;
	private TextView TextDetail;
	private String Name;
	private String Number;
	private String Id = "";
	private String remark = "";
	private String ticket;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private SharedPreferences PreALLChannel;// 存储所用频道信息
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.candicate_detail_activity);
		initview();
		setonclick();
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		Name = bundle.getString("Name");
		Number = bundle.getString("Number");
		TextName.setText(Name);
		Id = bundle.getString("Id");
		remark = bundle.getString("remark");
		TextNumber.setText("当前票数：" + Number);
		TextDetail.setText(remark);
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		PreALLChannel = getSharedPreferences("ALLChannel", Context.MODE_PRIVATE);
		ReadTicket();
	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getString("ticket", "");

	}

	private void initview() {
		// TODO Auto-generated method stub
		RoundHeadimg = (RoundImageView) findViewById(R.id.round_headimg);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		ImageSymbol = (ImageView) findViewById(R.id.image_symbol);
		ImageSelected = (ImageView) findViewById(R.id.image_selected);
		TextName = (TextView) findViewById(R.id.text_name);
		TextPartyMember = (TextView) findViewById(R.id.text_party_member);
		TextNumber = (TextView) findViewById(R.id.text_number);
		TextDetail = (TextView) findViewById(R.id.text_detail);
	}

	private void setonclick() {
		// TODO Auto-generated method stub
		ImageBack.setOnClickListener(this);
		ImageSelected.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.image_selected:
			Toast.makeText(getApplicationContext(), "正在确认", Toast.LENGTH_SHORT).show();
			final ArrayList ArrayValues = new ArrayList();
			ArrayValues.add(new BasicNameValuePair("ticket", ticket));
			ArrayValues.add(new BasicNameValuePair("voteItemId", "" + Id));
			new Thread(new Runnable() { // 开启线程上传文件
				@Override
				public void run() {
					String DueData = "";
					DueData = HttpGetData.GetData("api/cms/vote/vote", ArrayValues);
				}
			}).start();

			break;
		default:
			break;
		}
	}

}

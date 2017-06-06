package wuxc.wisdomparty.HomeOfMember;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Internet.HttpGetData;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MemberPartyTransformActivity extends Activity implements OnClickListener {
	private ImageView ImageBack;
	private TextView TextTransformName;
	private TextView TextTransformPartyName;
	private TextView TextTransformPartyAddress;
	private TextView TextTransformTime;
	private Button BtnTransformConfirm;
	private String BranchName = "";
	private String BranchAddress = "";
	private String Name = "";
	private String BranchTime = "";
	private String ticket;
	private String userPhoto;
	private String LoginId;
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
		setContentView(R.layout.member_party_transform_activity);
		initview();
		setonclicklistener();

		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();
		GetData();
	}

	protected void GetDataDueData(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = "";
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");

			Data = demoJson.getString("data");
			if (Type.equals(GET_SUCCESS_RESULT)) {
				JSONObject demoJsondata = new JSONObject(Data);
				try {
					BranchAddress = demoJsondata.getString("address");
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					BranchTime = demoJsondata.getString("operateTime");
				} catch (Exception e) {
					// TODO: handle exception
					BranchTime = "无数据";
				}

				BranchName = demoJsondata.getString("name");
				Name = demoJsondata.getString("userName");

				setdata();
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

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getString("ticket", "");
		userPhoto = PreUserInfo.getString("userPhoto", "");
		LoginId = PreUserInfo.getString("loginId", "");
	}

	private void GetData() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", ticket));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/pb/relationChange/getParty", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_DUE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	private void setdata() {
		// TODO Auto-generated method stub

		TextTransformName.setText(Name);
		TextTransformPartyName.setText(BranchName);
		TextTransformPartyAddress.setText(BranchAddress);
		TextTransformTime.setText(BranchTime);
	}

	private void initview() {
		// TODO Auto-generated method stub
		TextTransformName = (TextView) findViewById(R.id.transform_name);
		TextTransformPartyName = (TextView) findViewById(R.id.transform_party_name);
		TextTransformPartyAddress = (TextView) findViewById(R.id.transform_party_address);
		TextTransformTime = (TextView) findViewById(R.id.transform_time);
		BtnTransformConfirm = (Button) findViewById(R.id.btn_transform_confirm);
		ImageBack = (ImageView) findViewById(R.id.image_back);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ImageBack.setOnClickListener(this);
		BtnTransformConfirm.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.btn_transform_confirm:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), PartyBranchDataListActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("BranchName", BranchName);
			bundle.putString("BranchAddress", BranchAddress);
			bundle.putString("Name", Name);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

}

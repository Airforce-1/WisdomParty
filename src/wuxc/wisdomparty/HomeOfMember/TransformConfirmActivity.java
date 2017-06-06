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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Internet.HttpGetData;
import android.view.Window;

public class TransformConfirmActivity extends Activity implements OnClickListener {
	private String BranchIntoName;
	private String BranchIntoAddress;
	private String BranchName;
	private String BranchAddress;
	private String Name;
	private Button BtnCancel;
	private Button BtnConfirm;
	private TextView TextName;
	private TextView TextFromName;
	private TextView TextFromAddress;
	private TextView TextIntoName;
	private TextView TextIntoAddress;
	private ImageView ImageBack;
	private String ToId = "";
	private String FromId = "";
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
		setContentView(R.layout.transform_confirm_activity);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		BranchName = bundle.getString("BranchName");
		BranchAddress = bundle.getString("BranchAddress");
		Name = bundle.getString("Name");
		ToId = bundle.getString("ToId");
		BranchIntoName = bundle.getString("BranchIntoName");
		BranchIntoAddress = bundle.getString("BranchIntoAddress");
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

				BranchName = demoJsondata.getString("name");
				Name = demoJsondata.getString("userName");
				FromId = demoJsondata.getString("deptId");
				try {
					BranchAddress = demoJsondata.getString("address");
				} catch (Exception e) {
					// TODO: handle exception
				}

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
		TextName.setText(Name);
		TextFromName.setText(BranchName);
		TextFromAddress.setText(BranchAddress);
		TextIntoName.setText(BranchIntoName);
		TextIntoAddress.setText(BranchIntoAddress);
	}

	private void initview() {
		// TODO Auto-generated method stub
		BtnCancel = (Button) findViewById(R.id.btn_transform_cancel);
		BtnConfirm = (Button) findViewById(R.id.btn_transform_confirm);
		TextName = (TextView) findViewById(R.id.transform_name);
		TextFromName = (TextView) findViewById(R.id.transform_party_from_name);
		TextFromAddress = (TextView) findViewById(R.id.transform_party_from_address);
		TextIntoName = (TextView) findViewById(R.id.transform_into_name);
		TextIntoAddress = (TextView) findViewById(R.id.transform_into_address);
		ImageBack = (ImageView) findViewById(R.id.image_back);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ImageBack.setOnClickListener(this);
		BtnConfirm.setOnClickListener(this);
		BtnCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.btn_transform_cancel:
			finish();
			break;
		case R.id.btn_transform_confirm:
			Toast.makeText(getApplicationContext(), "请等待审核", Toast.LENGTH_SHORT).show();
			final ArrayList ArrayValues = new ArrayList();
			ArrayValues.add(new BasicNameValuePair("ticket", ticket));
			ArrayValues.add(new BasicNameValuePair("relationChangeDto.fromOrg", FromId));
			ArrayValues.add(new BasicNameValuePair("relationChangeDto.toOrg", ToId));

			new Thread(new Runnable() { // 开启线程上传文件
				@Override
				public void run() {
					String DueData = "";
					DueData = HttpGetData.GetData("api/pb/relationChange/save", ArrayValues);
					// Message msg = new Message();
					// msg.obj = DueData;
					// msg.what = GET_DUE_DATA;
					// uiHandler.sendMessage(msg);
				}
			}).start();

			break;
		default:
			break;
		}
	}

}

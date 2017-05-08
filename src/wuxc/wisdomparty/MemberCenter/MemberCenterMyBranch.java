package wuxc.wisdomparty.MemberCenter;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.umeng.socialize.utils.Log;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Window;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.HomeOfMember.MemberPartyBranchDetailActivity;
import wuxc.wisdomparty.Internet.HttpGetData;
import wuxc.wisdomparty.Model.MydueModel;

public class MemberCenterMyBranch extends Activity implements OnClickListener {
	private ImageView ImageBack;
	private LinearLayout LinBranchBack;
	private int screenwidth;
	private int ScreenHeight = 0;
	private TextView TextDetail;
	private TextView TextPhoneNumber;
	private TextView TextCharge;
	private TextView TextAddress;
	private TextView TextTime;
	private TextView TextBranch;
	private TextView TextName;
	private String StrPhoneNumber = "���ڼ���";
	private String StrCharge = "���ڼ���";
	private String StrAddress = "���ڼ���";
	private String StrTime = "���ڼ���";
	private String StrBranch = "���ڼ���";
	private String StrName = "���ڼ���";
	private String ticket;
	private String userPhoto;
	private String LoginId;
	private SharedPreferences PreUserInfo;// �洢������Ϣ
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
		setContentView(R.layout.member_center_mybranch);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		LinBranchBack = (LinearLayout) findViewById(R.id.lin_branch_info);
		TextDetail = (TextView) findViewById(R.id.text_branch_detail);
		TextPhoneNumber = (TextView) findViewById(R.id.text_branch_phonenumber);
		TextCharge = (TextView) findViewById(R.id.text_branch_charge);
		TextAddress = (TextView) findViewById(R.id.text_branch_address);
		TextTime = (TextView) findViewById(R.id.text_branch_transfer_time);
		TextBranch = (TextView) findViewById(R.id.text_branch);
		TextName = (TextView) findViewById(R.id.text_name);
		ImageBack.setOnClickListener(this);
		TextDetail.setOnClickListener(this);
		setlayoutheight();
		settext();
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();
		GetData();
		Toast.makeText(getApplicationContext(), "���ڼ�������", Toast.LENGTH_SHORT).show();

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
				StrAddress = demoJsondata.getString("address");
				try {
					StrTime = demoJsondata.getString("operateTime");
				} catch (Exception e) {
					// TODO: handle exception
					StrTime = "������";
				}
				
				StrBranch = demoJsondata.getString("name");
				StrName = demoJsondata.getString("userName");
				try {
					StrPhoneNumber = demoJsondata.getString("phonenumber");
				} catch (Exception e) {
					// TODO: handle exception
					StrPhoneNumber = "������";
				}
				try {
					StrCharge = demoJsondata.getString("charge");
				} catch (Exception e) {
					// TODO: handle exception
					StrCharge = "������";
				}
				settext();
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

		new Thread(new Runnable() { // �����߳��ϴ��ļ�
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

	private void settext() {
		// TODO Auto-generated method stub
		TextPhoneNumber.setText("֧����ϵ�绰��" + StrPhoneNumber);
		TextCharge.setText("֧�������ˣ�" + StrCharge);
		TextAddress.setText("֧�����ڵأ�" + StrAddress);
		TextTime.setText("��ʱת����֧����" + StrTime);
		TextBranch.setText("����֧����" + StrBranch);
		TextName.setText("������" + StrName);
	}

	private void setlayoutheight() {
		// TODO Auto-generated method stub
		screenwidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		ScreenHeight = screenwidth / 6 * 5;
		LinearLayout.LayoutParams LayoutParams = (android.widget.LinearLayout.LayoutParams) LinBranchBack
				.getLayoutParams();
		LayoutParams.height = ScreenHeight;
		LinBranchBack.setLayoutParams(LayoutParams);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.text_branch_detail:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), MemberPartyBranchDetailActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("Name", "��������֧��");
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

}

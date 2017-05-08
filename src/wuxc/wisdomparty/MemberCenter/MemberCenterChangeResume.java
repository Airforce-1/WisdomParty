package wuxc.wisdomparty.MemberCenter;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Window;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Internet.GetBitmapFromServer;
import wuxc.wisdomparty.Internet.HttpGetData;
import wuxc.wisdomparty.Internet.URLcontainer;
import wuxc.wisdomparty.layout.RoundedImageView;
import wuxc.wisdomparty.layout.dialogtwo;

public class MemberCenterChangeResume extends Activity implements OnClickListener {
	private ImageView ImageBack;
	private RoundedImageView RoundedHeadImg;
	private ImageView ImageHeadParty;
	private TextView TextName;
	private TextView TextSex;
	private TextView TextIfParty;
	private EditText EditName;
	private EditText EditPhoneNumber;
	private EditText EditAddress;
	private EditText EditPartyAge;
	private EditText EditMotto;
	private Button BtnChange;
	private String StrName = "���ڼ���";
	private String StrNameList = "���ڼ���";
	private String StrSex = "���ڼ���";
	private String StrPhoneNumber = "���ڼ���";
	private String StrAddress = "���ڼ���";
	private String StrIfParty = "���ڼ���";
	private String StrPartyAge = "���ڼ���";
	private String StrMotto = "���ڼ���";
	private String LoginId;
	private String ticket;
	private String userPhoto;
	private SharedPreferences PreUserInfo;// �洢������Ϣ
	private final static int GET_USER_HEAD_IMAGE = 6;
	private static final String GET_SUCCESS_RESULT = "success";
	private static final String GET_FAIL_RESULT = "fail";
	private static final int GET_DUE_DATA = 9;
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_USER_HEAD_IMAGE:
				ShowHeadImage(msg.obj);
				break;
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
		setContentView(R.layout.member_center_changeresume);
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		RoundedHeadImg = (RoundedImageView) findViewById(R.id.rounded_headimg);
		ImageHeadParty = (ImageView) findViewById(R.id.image_head_party);
		TextName = (TextView) findViewById(R.id.text_name);
		TextSex = (TextView) findViewById(R.id.text_sex);
		TextIfParty = (TextView) findViewById(R.id.text_ifparty);
		EditName = (EditText) findViewById(R.id.edit_name);
		EditPhoneNumber = (EditText) findViewById(R.id.edit_phonenumber);
		EditAddress = (EditText) findViewById(R.id.edit_address);
		EditPartyAge = (EditText) findViewById(R.id.edit_partyage);
		EditMotto = (EditText) findViewById(R.id.edit_motto);
		BtnChange = (Button) findViewById(R.id.btn_change);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		ImageBack.setOnClickListener(this);
		BtnChange.setOnClickListener(this);
		TextSex.setOnClickListener(this);
		TextIfParty.setOnClickListener(this);
		ReadTicket();
		GetHeadPic();
		settext();
		GetData();
		Toast.makeText(getApplicationContext(), "���ڼ�������", Toast.LENGTH_SHORT).show();

	}

	private void settext() {
		// TODO Auto-generated method stub
		TextName.setText(StrName);
		EditName.setText(StrNameList);
		TextSex.setText(StrSex);
		EditPhoneNumber.setText(StrPhoneNumber);
		EditAddress.setText(StrAddress);
		TextIfParty.setText("���ڼ���");
		EditPartyAge.setText("���ڼ���");
		EditMotto.setText(StrMotto);
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
				StrNameList = demoJsondata.getString("realName");
				try {
					StrName = demoJsondata.getString("userName");
				} catch (Exception e) {
					// TODO: handle exception
					StrName = "������";
				}

				StrSex = demoJsondata.getString("sex");
				if (StrSex.equals("male")) {
					StrSex = "��";
				} else {
					StrSex = "Ů";
				}
				StrAddress = demoJsondata.getString("address");
				try {
					StrMotto = demoJsondata.getString("sign");
				} catch (Exception e) {
					// TODO: handle exception
					StrMotto = "������";
				}
				try {
					StrPhoneNumber = demoJsondata.getString("mobile");
				} catch (Exception e) {
					// TODO: handle exception
					StrPhoneNumber = "������";
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

	private void GetData() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", ticket));
		ArrayValues.add(new BasicNameValuePair("queryUserId", LoginId));

		new Thread(new Runnable() { // �����߳��ϴ��ļ�
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/member/getUserInfo", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_DUE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	protected void ShowHeadImage(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj == null)) {
			try {
				Bitmap HeadImage = (Bitmap) obj;
				RoundedHeadImg.setImageBitmap(HeadImage);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		LoginId = PreUserInfo.getString("loginId", null);
		ticket = PreUserInfo.getString("ticket", null);
		userPhoto = PreUserInfo.getString("userPhoto", null);
	}

	private void GetHeadPic() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() { // �����߳��ϴ��ļ�
			@Override
			public void run() {
				Bitmap HeadImage = null;
				HeadImage = GetBitmapFromServer
						.getBitmapFromServer(URLcontainer.urlip + URLcontainer.GetFile + userPhoto);
				Message msg = new Message();
				msg.what = GET_USER_HEAD_IMAGE;
				msg.obj = HeadImage;
				uiHandler.sendMessage(msg);
			}
		}).start();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;

		case R.id.btn_change:
			StrNameList = EditName.getText().toString();
			StrPhoneNumber = EditPhoneNumber.getText().toString();
			StrAddress = EditAddress.getText().toString();
			StrPartyAge = EditPartyAge.getText().toString();
			StrMotto = EditMotto.getText().toString();
			if (TextUtils.isEmpty(StrNameList)) {
				Toast.makeText(getApplicationContext(), "����������", Toast.LENGTH_SHORT).show();
			} else if (TextUtils.isEmpty(StrAddress)) {
				Toast.makeText(getApplicationContext(), "�������ַ", Toast.LENGTH_SHORT).show();
			} else if (TextUtils.isEmpty(StrPhoneNumber)) {
				Toast.makeText(getApplicationContext(), "������绰����", Toast.LENGTH_SHORT).show();
			} else if (TextUtils.isEmpty(StrPartyAge)) {
				Toast.makeText(getApplicationContext(), "�����뵳��", Toast.LENGTH_SHORT).show();
			} else if (TextUtils.isEmpty(StrMotto)) {
				Toast.makeText(getApplicationContext(), "���������ǩ��", Toast.LENGTH_SHORT).show();
			} else if (TextUtils.isEmpty(StrSex)) {
				Toast.makeText(getApplicationContext(), "��ѡ�������Ա�", Toast.LENGTH_SHORT).show();
			} else if (TextUtils.isEmpty(StrIfParty)) {
				Toast.makeText(getApplicationContext(), "��ѡ���Ƿ��ǵ�Ա", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "���", Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.text_sex:
			showAlertDialog("��ѡ�������Ա�", "��", "Ů", 1);
			break;
		case R.id.text_ifparty:
			showAlertDialog("���Ƿ��ǵ�Ա��", "��", "��", 2);
			break;
		default:
			break;

		}
	}

	public void showAlertDialog(String arg, final String firstlabel, final String secondlabel, final int type) {
		// 1-sex
		// 2-ifparty

		dialogtwo.Builder builder = new dialogtwo.Builder(this);
		builder.setMessage(arg);
		builder.setTitle("��Ϣ�޸�");
		builder.setPositiveButton(firstlabel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				if (type == 1) {
					StrSex = firstlabel;
					TextSex.setText(StrSex);
				} else {
					StrIfParty = firstlabel;
					TextIfParty.setText(StrIfParty);
				}
			}
		});

		builder.setNegativeButton(secondlabel, new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				if (type == 1) {
					StrSex = secondlabel;
					TextSex.setText(StrSex);
				} else {
					StrIfParty = secondlabel;
					TextIfParty.setText(StrIfParty);
				}
			}
		});

		builder.create().show();

	}
}

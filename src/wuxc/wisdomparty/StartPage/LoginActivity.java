package wuxc.wisdomparty.StartPage;

import java.io.WriteAbortedException;
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
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Internet.HttpGetData;
import wuxc.wisdomparty.Internet.URLcontainer;
import wuxc.wisdomparty.MemberCenter.MySettingChangePasswordActivity;
import wuxc.wisdomparty.main.MainActivity;

public class LoginActivity extends Activity implements OnClickListener {
	private boolean IsAutoLogin = true;// 自动登录标志字
	private boolean IsLogin = true;// 防止重复点击
	private ImageView ImageFinish;
	private EditText EditAccount;
	private EditText EditPassword;
	private Button BtnLogin;
	private String StrAccount;
	private String StrPassword;
	private ImageView ImageCheck;
	private TextView TextAutoLogin;
	private TextView TextToRegister;
	private String userPhoto;
	private String address;
	private String ticket;
	private String loginId;
	private String sex;
	private String sessionId;
	private String username;
	private SharedPreferences PreAccount;// 存储用户名和密码，用于自动登录
	private SharedPreferences PreUserInfo;// 存储个人信息
	private static final String ERROR_CODE = "0204";
	private static final int GET_LOGININ_RESULT_DATA = 1;
	private static final String GET_SUCCESS_RESULT = "success";

	private static final int GET_ALL_CHANNEL = 5;
	private SharedPreferences PreALLChannel;// 存储所用频道信息
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_LOGININ_RESULT_DATA:
				GetDataDetailFromLoginResultData(msg.obj);
				break;
			case GET_ALL_CHANNEL:
				GetDataDetailData(msg.obj);
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
		setContentView(R.layout.login_activity);
		initview();
		setonclicklistener();
	}

	public void GetDataDetailFromLoginResultData(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;
		BtnLogin.setText("登录");
		IsLogin = true;
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");

			if (Type.equals(GET_SUCCESS_RESULT)) {
				Data = demoJson.getString("data");
				Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
				GetDetailData(Data);
			} else if (Type.equals("accountPwdError")) {
				Toast.makeText(getApplicationContext(), "用户名和密码不匹配", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("userLocked")) {
				Toast.makeText(getApplicationContext(), "账号被禁用", Toast.LENGTH_SHORT).show();

			} else {
				Toast.makeText(getApplicationContext(), "登陆失败", Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetDetailData(String data) {
		// TODO Auto-generated method stub
		try {
			JSONObject demoJson = new JSONObject(data);

			userPhoto = demoJson.getString("userPhoto");
			address = demoJson.getString("address");
			ticket = demoJson.getString("ticket");
			loginId = demoJson.getString("loginId");
			sex = demoJson.getString("sex");
			sessionId = demoJson.getString("sessionId");
			username = demoJson.getString("username");
			WriteAccount();
			WriteUserInfo();
			GetAllData();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetAllData() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", ticket));

		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String DueData = "";
				DueData = HttpGetData.GetData("api/cms/channel/getAllChannel", ArrayValues);
				Message msg = new Message();
				msg.obj = DueData;
				msg.what = GET_ALL_CHANNEL;
				uiHandler.sendMessage(msg);
			}
		}).start();

	}

	protected void GetDataDetailData(Object obj) {

		// TODO Auto-generated method stub
		String Type = null;
		String Data = null;
		JumpPageToMainPage();
		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");

			Data = demoJson.getString("datas");
			if (Type.equals(GET_SUCCESS_RESULT)) {

				GetDataList(Data);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetDataList(String data) {
		// TODO Auto-generated method stub
		JSONArray jArray = null;
		Editor edit = PreALLChannel.edit();
		try {
			jArray = new JSONArray(data);
			JSONObject json_data = null;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				try {
					edit.putString("AC_keyid" + i, json_data.getString("keyid"));

				} catch (Exception e) {
					// TODO: handle exception
					edit.putString("AC_keyid" + i, "No");

				}
				try {
					edit.putString("AC_name" + i, json_data.getString("node_name"));

				} catch (Exception e) {
					edit.putString("AC_name" + i, "No");

					// TODO: handle exception
				}
				try {
					edit.putString("AC_sign" + i, json_data.getString("node_sign"));

				} catch (Exception e) {
					// TODO: handle exception
					edit.putString("AC_sign" + i, "No");
				}
				try {
					edit.putString("AC_url" + i, json_data.getString("linkAddress"));

				} catch (Exception e) {
					// TODO: handle exception
					edit.putString("AC_url" + i, "No");

				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		edit.commit();
	}

	private void JumpPageToMainPage() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), MainActivity.class);
		startActivity(intent);
		finish();
	}

	private void WriteUserInfo() {
		// TODO Auto-generated method stub
		Editor edit = PreUserInfo.edit();
		edit.putString("userPhoto", userPhoto);
		edit.putString("address", address);
		edit.putString("ticket", ticket);
		edit.putString("sex", sex);
		edit.putString("loginId", loginId);
		edit.putString("sessionId", sessionId);
		edit.putString("sex", sex);
		edit.commit();
	}

	private void WriteAccount() {
		// TODO Auto-generated method stub
		if (IsAutoLogin) {
			Editor edit = PreAccount.edit();
			edit.putString("LoginId", StrAccount);
			edit.putString("pwd", StrPassword);
			edit.commit();
		} else {
			Editor edit = PreAccount.edit();
			edit.putString("LoginId", null);
			edit.putString("pwd", null);
			edit.commit();
		}

	}

	private void initview() {
		// TODO Auto-generated method stub
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		PreAccount = getSharedPreferences("Account", Context.MODE_PRIVATE);
		PreALLChannel = getSharedPreferences("ALLChannel", Context.MODE_PRIVATE);
		ImageFinish = (ImageView) findViewById(R.id.image_finish);
		EditAccount = (EditText) findViewById(R.id.edit_account);
		EditPassword = (EditText) findViewById(R.id.edit_password);
		BtnLogin = (Button) findViewById(R.id.btn_login);
		ImageCheck = (ImageView) findViewById(R.id.image_check);
		TextAutoLogin = (TextView) findViewById(R.id.text_auto_login);
		TextToRegister = (TextView) findViewById(R.id.text_toregister);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ImageFinish.setOnClickListener(this);
		BtnLogin.setOnClickListener(this);
		ImageCheck.setOnClickListener(this);
		TextAutoLogin.setOnClickListener(this);
		TextToRegister.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_finish:
			finish();
			break;
		case R.id.btn_login:

			StrAccount = EditAccount.getText().toString();
			StrPassword = EditPassword.getText().toString();
			if (StrAccount.equals("") || StrAccount == null) {
				Toast.makeText(getApplicationContext(), "账户名不可为空", Toast.LENGTH_SHORT).show();
			} else if (StrPassword.equals("") || StrPassword == null) {
				Toast.makeText(getApplicationContext(), "密码不可为空", Toast.LENGTH_SHORT).show();
			} else {
				if (IsLogin) {
					final ArrayList ArrayValues = new ArrayList();
					ArrayValues.add(new BasicNameValuePair("login_id", StrAccount));
					ArrayValues.add(new BasicNameValuePair("pwd", StrPassword));
					new Thread(new Runnable() { // 开启线程上传文件
						@Override
						public void run() {
							String LoginResultData = "";
							LoginResultData = HttpGetData.GetData(URLcontainer.LoginIn, ArrayValues);
							Message msg = new Message();
							msg.obj = LoginResultData;
							msg.what = GET_LOGININ_RESULT_DATA;
							uiHandler.sendMessage(msg);
						}
					}).start();
					BtnLogin.setText("正在登录...");
					IsLogin = false;
				} else {
					Toast.makeText(getApplicationContext(), "请勿重复点击", Toast.LENGTH_SHORT).show();
				}
				// Toast.makeText(getApplicationContext(), "登陆成功",
				// Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.text_toregister:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), MySettingChangePasswordActivity.class);
			startActivity(intent);
			break;
		case R.id.image_check:
			if (IsAutoLogin) {
				IsAutoLogin = false;
				ImageCheck.setImageResource(R.drawable.check_no);
			} else {
				IsAutoLogin = true;
				ImageCheck.setImageResource(R.drawable.check_yes);
			}
			break;
		case R.id.text_auto_login:
			if (IsAutoLogin) {
				IsAutoLogin = false;
				ImageCheck.setImageResource(R.drawable.check_no);
			} else {
				IsAutoLogin = true;
				ImageCheck.setImageResource(R.drawable.check_yes);
			}
			break;
		default:
			break;
		}
	}

}

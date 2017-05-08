package wuxc.wisdomparty.MemberCenter;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Internet.HttpGetData;
import wuxc.wisdomparty.Internet.URLcontainer;

public class MySettingChangePasswordActivity extends Activity implements OnClickListener {
	private Button btn_ok;
	private EditText edit_new1;
	private EditText edit_new;
	private EditText edit_old;
	private ImageView image_finish;
	private String ticket;
	private SharedPreferences PreUserInfo;// 存储个人信息
	private String Old = "";
	private String New;
	private String New1;
	private boolean canChange = true;
	private static final String GET_SUCCESS_RESULT = "success";
	private static final int GET_NOTICE_DATA = 6;
	private static final int INTENT_GO_DETAIL_NOTICE = 6;
	public Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GET_NOTICE_DATA:
				GetDataNoticeData(msg.obj);
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
		setContentView(R.layout.activity_changepassword);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		edit_new1 = (EditText) findViewById(R.id.edit_new1);
		edit_new = (EditText) findViewById(R.id.edit_new);
		edit_old = (EditText) findViewById(R.id.edit_old);
		image_finish = (ImageView) findViewById(R.id.image_finish);
		btn_ok.setOnClickListener(this);
		image_finish.setOnClickListener(this);
		PreUserInfo = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		ReadTicket();
	}

	protected void GetDataNoticeData(Object obj) {
		canChange = true;
		btn_ok.setText("确认修改");

		String Type = null;

		try {
			JSONObject demoJson = new JSONObject(obj.toString());
			Type = demoJson.getString("type");

			if (Type.equals(GET_SUCCESS_RESULT)) {
				Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
				finish();
			} else if (Type.equals("mobileCodeError")) {
				Toast.makeText(getApplicationContext(), "手机验证码错误", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("mobileCodeNull")) {
				Toast.makeText(getApplicationContext(), "验证码不能为空", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("valCodeError")) {
				Toast.makeText(getApplicationContext(), "验证码错误", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("passwordNull")) {
				Toast.makeText(getApplicationContext(), "密码不可为空", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("oldPasswordError")) {
				Toast.makeText(getApplicationContext(), "旧密码错误", Toast.LENGTH_SHORT).show();

			} else if (Type.equals("fail")) {
				Toast.makeText(getApplicationContext(), "操作失败，请重试", Toast.LENGTH_SHORT).show();

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "修改失败", Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(), "异常", Toast.LENGTH_SHORT).show();

		}
	}

	private void ReadTicket() {
		// TODO Auto-generated method stub
		ticket = PreUserInfo.getString("ticket", null);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_ok:
			Old = edit_old.getText().toString();
			New = edit_new.getText().toString();
			New1 = edit_new1.getText().toString();
			if (Old.equals("")) {
				Toast.makeText(getApplicationContext(), "就密码不可为空", Toast.LENGTH_SHORT).show();

			} else if (New.equals("")) {
				Toast.makeText(getApplicationContext(), "新密码不可为空", Toast.LENGTH_SHORT).show();

			} else if (New1.equals("")) {
				Toast.makeText(getApplicationContext(), "确认新密码不可为空", Toast.LENGTH_SHORT).show();

			} else if (!New1.equals(New)) {
				Toast.makeText(getApplicationContext(), "两次输入不一致", Toast.LENGTH_SHORT).show();

			} else if (!canChange) {
				Toast.makeText(getApplicationContext(), "请勿重复点击", Toast.LENGTH_SHORT).show();

			} else {
				ChangePwd();
				btn_ok.setText("正在写入...");
				canChange = false;
			}
			break;
		case R.id.image_finish:
			finish();
			break;

		default:
			break;
		}
	}

	private void ChangePwd() {
		// TODO Auto-generated method stub
		final ArrayList ArrayValues = new ArrayList();
		ArrayValues.add(new BasicNameValuePair("ticket", ticket));

		ArrayValues.add(new BasicNameValuePair("orgUserDto.pwd", Old));
		ArrayValues.add(new BasicNameValuePair("orgUserDto.pwd2", New));
		ArrayValues.add(new BasicNameValuePair("actionType", ""));
		new Thread(new Runnable() { // 开启线程上传文件
			@Override
			public void run() {
				String NoticeData = "";
				NoticeData = HttpGetData.GetData("api/member/modifyPassword", ArrayValues);
				Message msg = new Message();
				msg.obj = NoticeData;
				msg.what = GET_NOTICE_DATA;
				uiHandler.sendMessage(msg);
			}
		}).start();
	}

}

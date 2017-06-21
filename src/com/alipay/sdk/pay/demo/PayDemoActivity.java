package com.alipay.sdk.pay.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.alipay.sdk.app.PayTask;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import single.wuxc.wisdomparty.R;

public class PayDemoActivity extends FragmentActivity {

	// �̻�PID
	public static final String PARTNER = "2088521589085287";
	// �̻��տ��˺�
	public static final String SELLER = "18039980404@163.com";
	// �̻�˽Կ��pkcs8��ʽ
	// public static final String RSA_PRIVATE =
	// "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMBgYF7xR1HCKSon\n"
	// + "maVMUG4kd/6ck1eom5fwC+va0Hr5BeAmuO8nZYUmJrKnhtYX3TA9YBn+75EhZEEn\n"
	// + "CyxdSgotNIEKgDJEctXl/K904ujwQEKwqejxrJbi3VYxi7oPsqCw2mVdmV7CBZyB\n"
	// + "OsKzpO1VQ/QSilxbOBdLYKzVsd0BAgMBAAECgYA6ds+y6ua+zSAPhJtoJeonnwOs\n"
	// + "qE8LC93FbhlhY02KodWMSphXbgSnU3HdR5ZtyDDbcTuciuKpm5lJR6FYqT1PlXVy\n"
	// + "n86Mx+5LqpyiJAHFJAwXCTwz+LYdMJHn3YVUfW5FijiLH8RJfa8K8G6KROpftoU0\n"
	// + "UCPy0NtM2JEBOAobAQJBAOYme9WGOJNy1OzXT8KZEOhlMA3g7TVxo/bTQhy6ZNlm\n"
	// + "+iJp4oywYtJItNKAF+kQu7vbNRvtrxrMUWDQBLDGC7ECQQDV+8fHDW2Btiqh0QiK\n"
	// + "5ORO13+dfIdtLl3kP7+XQDs5yCYA+XwQZnsYTx3ab+3G7uD8BWRKmvEvHnA5ibS2\n"
	// + "10pRAkAu24D03Snho0yEl+WuSfmuRwO8n6HL8sIeO67HKI/lz1h37zTnzfWguJrQ\n"
	// + "DLJRik2elqe+curmutiKrG94CipRAkEAzvyIGPHSNaCiyHhNDR2wAtvNo+crkN9D\n"
	// + "D2Di0UnegFws9tBdw/d9ptwRApU2qjG0C9SjWM9b8bo90Ep2Nk82oQJBAIpn5p98\n"
	// + "9gXlWjxBnAzD31afKJhTmxnBoH8geFQkMgnQg8O33dFNu7/zzbXz7pjXvZSyAf9t\n" +
	// "cW8xJYEN/e/HE2o=";
	// // ֧������Կ
	// public static final String RSA_PUBLIC =
	// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDAYGBe8UdRwikqJ5mlTFBuJHf+\n"
	// + "nJNXqJuX8Avr2tB6+QXgJrjvJ2WFJiayp4bWF90wPWAZ/u+RIWRBJwssXUoKLTSB\n"
	// + "CoAyRHLV5fyvdOLo8EBCsKno8ayW4t1WMYu6D7KgsNplXZlewgWcgTrCs6TtVUP0\n" +
	// "EopcWzgXS2Cs1bHdAQIDAQAB\n";
	public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOA6E96rqjKwPrMpZkIxhBxP3Maf6GzuBfVcxKIBROHuFveUCnObfmeHxApZkKs5UskQUxUFvCEH/fbdbQHH8x6DYMJdfrBKME1BGDmXoLZt7/zFna93im/SQ1th5U4zV4DiyZnmBQQR1e9+SgB++LfMZeHUrPwayRcC/rLVnSYFAgMBAAECgYEAgWEhi+8zgpHta8Vsiq81knyGYFlubEQfTgdcMjpXO6H8wJg8O56yHhzZgjtR3WpzGS8BMXUgq1KIOMRlHIz5+0Fr2G15Bgv18teGrSOJhTjLfQjPuturvBRE40R0iGK0FmwY5gzgqymoVe6ngZrOXiYiBiA4Pke64F/7GBWk5hUCQQD8IVa9jhhvtRIgp9VuBpnXfoRclnQ4JEkAeFp6ZTTnsP82kcG2EVLLpizuqSFJryPT6dnDwdfyWPflZlL4IL7bAkEA46saFJLa4Z+gcnVbFS/EOeG4uKm6rEKa6HOkdB4E3pJ7CX2C8YlI09WhMVllBJXtmXLpLIDB2NugEVa0+9CUnwJBALKmWNTla3Ezpw2WfoeAi9+CmP0V6nGhXEzF+q5BKhSFeMDM1KpSag08yReupZSVUdilGZU4s+/XlIdqUp3YbgMCQQDPeJ5kv0t+LuSdSbr+PLBaewvGJM57qprWWIQ2WSDg36YGCP3qNMxMVzL08N0w55xFqUU6i/+bKtHc2yJFFFhLAkBsvvZf1QNvmwwv/N9p6tQwXX0Efk/v0R1GNnRX3NHHStZsJ449FdTQkgfLOK0jwvD23tLDFA6Bj4F8rhTWHHXO";
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgOhPeq6oysD6zKWZCMYQcT9zGn+hs7gX1XMSiAUTh7hb3lApzm35nh8QKWZCrOVLJEFMVBbwhB/323W0Bx/Meg2DCXX6wSjBNQRg5l6C2be/8xZ2vd4pv0kNbYeVOM1eA4smZ5gUEEdXvfkoAfvi3zGXh1Kz8GskXAv6y1Z0mBQIDAQAB";
	private static final String notifyurl = "http://www.smxyyzgs.cn/member/alipay/partyfare/noticeAsyn";
	private static final int SDK_PAY_FLAG = 1;
	private TextView product_subject;
	private TextView detail;
	private TextView product_price;
	private String rechargemoney = "0";
	private String orderId = "";
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				PayResult payResult = new PayResult((String) msg.obj);
				/**
				 * ͬ�����صĽ��������õ�����˽�����֤����֤�Ĺ����뿴https://doc.open.alipay.com/doc2/
				 * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
				 * docType=1) �����̻������첽֪ͨ
				 */
				String resultInfo = payResult.getResult();// ͬ��������Ҫ��֤����Ϣ

				String resultStatus = payResult.getResultStatus();
				// �ж�resultStatus Ϊ��9000�������֧���ɹ�������״̬�������ɲο��ӿ��ĵ�
				if (TextUtils.equals(resultStatus, "9000")) {
					Toast.makeText(PayDemoActivity.this, "֧���ɹ�", Toast.LENGTH_SHORT).show();
					finish();
				} else {
					// �ж�resultStatus Ϊ��"9000"��������֧��ʧ��
					// "8000"����֧�������Ϊ֧������ԭ�����ϵͳԭ���ڵȴ�֧�����ȷ�ϣ����ս����Ƿ�ɹ��Է�����첽֪ͨΪ׼��С����״̬��
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(PayDemoActivity.this, "֧�����ȷ����", Toast.LENGTH_SHORT).show();

					} else {
						// ����ֵ�Ϳ����ж�Ϊ֧��ʧ�ܣ������û�����ȡ��֧��������ϵͳ���صĴ���
						Toast.makeText(PayDemoActivity.this, "֧��ʧ��", Toast.LENGTH_SHORT).show();

					}
				}
				break;
			}
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pay_main);
		product_subject = (TextView) findViewById(R.id.product_subject);
		detail = (TextView) findViewById(R.id.detail);
		product_price = (TextView) findViewById(R.id.product_price);
		Intent intent = this.getIntent(); // ��ȡ���е�intent����
		Bundle bundle = intent.getExtras(); // ��ȡintent�����bundle����
		rechargemoney = bundle.getString("rechargemoney");
		orderId = bundle.getString("orderId");
		product_subject.setText("���ѽ���");
		detail.setText("����Ͽ��ҽҩ�ܹ�˾��Ա���ϵ��ѽ���");
		product_price.setText(rechargemoney + "Ԫ");
	}

	/**
	 * call alipay sdk pay. ����SDK֧��
	 * 
	 */
	public void pay(View v) {
		if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
			new AlertDialog.Builder(this).setTitle("����").setMessage("��Ҫ����PARTNER | RSA_PRIVATE| SELLER")
					.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface, int i) {
							//
							finish();
						}
					}).show();
			return;
		}
		if (orderId.equals("")) {
			new AlertDialog.Builder(this).setTitle("����").setMessage("δ��ȷ��ȡ������")
					.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface, int i) {
							//
							finish();
						}
					}).show();
			return;
		}
		String orderInfo = getOrderInfo("�������߽���", "����Ͽ��ҽҩ�ܹ�˾�������߽���", rechargemoney);

		/**
		 * �ر�ע�⣬�����ǩ���߼���Ҫ���ڷ���ˣ�����˽Կй¶�ڴ����У�
		 */
		String sign = sign(orderInfo);
		try {
			/**
			 * �����sign ��URL����
			 */
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		/**
		 * �����ķ���֧���������淶�Ķ�����Ϣ
		 */
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// ����PayTask ����
				PayTask alipay = new PayTask(PayDemoActivity.this);
				// ����֧���ӿڣ���ȡ֧�����
				String result = alipay.pay(payInfo, true);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// �����첽����
		Thread payThread = new Thread(payRunnable);
		payThread.start();

	}

	/**
	 * get the sdk version. ��ȡSDK�汾��
	 * 
	 */
	public void getSDKVersion() {
		PayTask payTask = new PayTask(this);
		String version = payTask.getVersion();
		Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
	}

	/**
	 * ԭ����H5���ֻ���ҳ��֧����natvie֧���� ����Ӧҳ����ҳ֧����ť��
	 * 
	 * @param v
	 */
	public void h5Pay(View v) {
		Intent intent = new Intent(this, H5PayDemoActivity.class);
		Bundle extras = new Bundle();
		/**
		 * url�ǲ��Ե���վ����app�ڲ���ҳ���ǻ���webview�򿪵ģ�demo�е�webview��H5PayDemoActivity��
		 * demo������url����֧�����߼�����H5PayDemoActivity��shouldOverrideUrlLoading����ʵ�֣�
		 * �̻����Ը����Լ���������ʵ��
		 */
		String url = "http://m.taobao.com";
		// url������һ�ŵ�����Ա��ȵ������Ĺ���wapվ�㣬�ڸ���վ��֧�������У�֧����sdk�������֧��
		extras.putString("url", url);
		intent.putExtras(extras);
		startActivity(intent);
	}

	/**
	 * create the order info. ����������Ϣ
	 * 
	 */
	private String getOrderInfo(String subject, String body, String price) {

		// ǩԼ���������ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// ǩԼ����֧�����˺�
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// �̻���վΨһ������
		// orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";
		orderInfo += "&out_trade_no=" + "\"" + orderId + "\"";

		// ��Ʒ����
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// ��Ʒ����
		orderInfo += "&body=" + "\"" + body + "\"";

		// ��Ʒ���
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// �������첽֪ͨҳ��·��
		orderInfo += "&notify_url=" + "\"" + notifyurl + "\"";
		// orderInfo += "&notify_url=" + "\"" +
		// "http://notify.msp.hk/notify.htm" + "\"";
		// ����ӿ����ƣ� �̶�ֵ
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// ֧�����ͣ� �̶�ֵ
		orderInfo += "&payment_type=\"1\"";

		// �������룬 �̶�ֵ
		orderInfo += "&_input_charset=\"utf-8\"";

		// ����δ����׵ĳ�ʱʱ��
		// Ĭ��30���ӣ�һ����ʱ���ñʽ��׾ͻ��Զ����رա�
		// ȡֵ��Χ��1m��15d��
		// m-���ӣ�h-Сʱ��d-�죬1c-���죨���۽��׺�ʱ����������0��رգ���
		// �ò�����ֵ������С���㣬��1.5h����ת��Ϊ90m��
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_tokenΪ���������Ȩ��ȡ����alipay_open_id,���ϴ˲����û���ʹ����Ȩ���˻�����֧��
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// ֧��������������󣬵�ǰҳ����ת���̻�ָ��ҳ���·�����ɿ�
		orderInfo += "&return_url=\"m.alipay.com\"";

		// �������п�֧���������ô˲���������ǩ���� �̶�ֵ ����ҪǩԼ���������п����֧��������ʹ�ã�
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	 * get the out_trade_no for an order. �����̻������ţ���ֵ���̻���Ӧ����Ψһ�����Զ����ʽ�淶��
	 * 
	 */
	private String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	/**
	 * sign the order info. �Զ�����Ϣ����ǩ��
	 * 
	 * @param content
	 *            ��ǩ��������Ϣ
	 */
	private String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. ��ȡǩ����ʽ
	 * 
	 */
	private String getSignType() {
		return "sign_type=\"RSA\"";
	}

}

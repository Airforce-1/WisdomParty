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

	// 商户PID
	public static final String PARTNER = "2088521589085287";
	// 商户收款账号
	public static final String SELLER = "18039980404@163.com";
	// 商户私钥，pkcs8格式
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
	// // 支付宝公钥
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
				 * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
				 * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
				 * docType=1) 建议商户依赖异步通知
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息

				String resultStatus = payResult.getResultStatus();
				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, "9000")) {
					Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
					finish();
				} else {
					// 判断resultStatus 为非"9000"则代表可能支付失败
					// "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(PayDemoActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

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
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		rechargemoney = bundle.getString("rechargemoney");
		orderId = bundle.getString("orderId");
		product_subject.setText("党费缴纳");
		detail.setText("三门峡市医药总公司党员网上党费缴纳");
		product_price.setText(rechargemoney + "元");
	}

	/**
	 * call alipay sdk pay. 调用SDK支付
	 * 
	 */
	public void pay(View v) {
		if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
			new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface, int i) {
							//
							finish();
						}
					}).show();
			return;
		}
		if (orderId.equals("")) {
			new AlertDialog.Builder(this).setTitle("警告").setMessage("未正确获取订单号")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface, int i) {
							//
							finish();
						}
					}).show();
			return;
		}
		String orderInfo = getOrderInfo("党费在线缴纳", "三门峡市医药总公司党费在线缴纳", rechargemoney);

		/**
		 * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
		 */
		String sign = sign(orderInfo);
		try {
			/**
			 * 仅需对sign 做URL编码
			 */
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		/**
		 * 完整的符合支付宝参数规范的订单信息
		 */
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(PayDemoActivity.this);
				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo, true);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();

	}

	/**
	 * get the sdk version. 获取SDK版本号
	 * 
	 */
	public void getSDKVersion() {
		PayTask payTask = new PayTask(this);
		String version = payTask.getVersion();
		Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
	 * 
	 * @param v
	 */
	public void h5Pay(View v) {
		Intent intent = new Intent(this, H5PayDemoActivity.class);
		Bundle extras = new Bundle();
		/**
		 * url是测试的网站，在app内部打开页面是基于webview打开的，demo中的webview是H5PayDemoActivity，
		 * demo中拦截url进行支付的逻辑是在H5PayDemoActivity中shouldOverrideUrlLoading方法实现，
		 * 商户可以根据自己的需求来实现
		 */
		String url = "http://m.taobao.com";
		// url可以是一号店或者淘宝等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
		extras.putString("url", url);
		intent.putExtras(extras);
		startActivity(intent);
	}

	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	private String getOrderInfo(String subject, String body, String price) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// 商户网站唯一订单号
		// orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";
		orderInfo += "&out_trade_no=" + "\"" + orderId + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + notifyurl + "\"";
		// orderInfo += "&notify_url=" + "\"" +
		// "http://notify.msp.hk/notify.htm" + "\"";
		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	 * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
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
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	private String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	private String getSignType() {
		return "sign_type=\"RSA\"";
	}

}

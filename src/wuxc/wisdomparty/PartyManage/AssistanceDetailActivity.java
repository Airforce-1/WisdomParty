package wuxc.wisdomparty.PartyManage;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.PartyManage.AssistancePayActivity;
import wuxc.wisdomparty.layout.dialogfour;

public class AssistanceDetailActivity extends Activity implements OnClickListener {
	private ImageView ImageBackground;
	private int screenwidth = 0;
	private ImageView ImageBack;
	private ImageView ImageShare;
	private TextView TextTitle;
	private TextView TextAuthorAandTime;
	private TextView TextDetail;
	private Button BtnConfirm;
	private String Time;
	private String Title;
	private UMImage image = null;
	private String Tag = "";
	private String content;

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.assistance_detail_activity);
		initview();
		setonclicklistener();
		setlayout();
		Intent intent = this.getIntent(); // ��ȡ���е�intent����
		Bundle bundle = intent.getExtras(); // ��ȡintent�����bundle����

		Title = bundle.getString("Title");
		Time = bundle.getString("Time");
		content = bundle.getString("content");
		TextTitle.setText(Title);
		TextAuthorAandTime.setText(Time);
		TextDetail.setText(ToDBC(content));

		TextAuthorAandTime.setText(Time);
		String html = "<html>" + "<body>" + "<table>" + "<tr>" + "<td>�ɶ��츮</td>" + "</tr>" + "</table>" + "</body>"
				+ "</html>";
		webView = (android.webkit.WebView) findViewById(R.id.webview);
		// StringBuilder sb = new StringBuilder();
		// sb.append(detail);
		// webView.loadUrl("http://ww.baidu.com");
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient());
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);

		webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);

	}

	private void initview() {
		// TODO Auto-generated method stub
		ImageBackground = (ImageView) findViewById(R.id.image_background);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		ImageShare = (ImageView) findViewById(R.id.image_share);
		TextTitle = (TextView) findViewById(R.id.text_title);
		TextAuthorAandTime = (TextView) findViewById(R.id.text_time);
		TextDetail = (TextView) findViewById(R.id.text_detail);
		BtnConfirm = (Button) findViewById(R.id.btn_confirm);

	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ImageBackground.setOnClickListener(this);
		ImageBack.setOnClickListener(this);
		ImageShare.setOnClickListener(this);
		BtnConfirm.setOnClickListener(this);
	}

	public String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375) {
				c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);

	}

	private void setlayout() {
		// TODO Auto-generated method stub
		screenwidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		int height = (int) (screenwidth / 1.52);
		LinearLayout.LayoutParams layoutParams1 = (android.widget.LinearLayout.LayoutParams) ImageBackground
				.getLayoutParams();
		layoutParams1.height = height;
		ImageBackground.setLayoutParams(layoutParams1);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_share:
			ShareDialog();
			break;
		case R.id.image_back:
			finish();
			break;
		case R.id.btn_confirm:
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), AssistancePayActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	private void ShareDialog() {
		final String title = "�Ƽ�һ��ܰ���APP";
		final String content = "����һ���Ƹ��ӦƸ����ҵ������ʱͨѶ��һ���APP������������ĺð��֣�";
		final String targeturl = "http://fir.im/j4zk";
		// String img = "";
		// String url = "";
		// if (!img.equals("")) {
		// url = URLcontainer.urlip + img;
		// image = new UMImage(AssistanceDetailActivity.this, url);
		// // image=new UMImage(AssistanceDetailActivity.this,
		// R.drawable.qidongye);
		// } else {
		image = new UMImage(AssistanceDetailActivity.this, R.drawable.image_share);
		// }

		dialogfour.Builder builder = new dialogfour.Builder(this);
		builder.setMessage("��δ��ȷѡ��");
		builder.setTitle("��ʾ");
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
		builder.setQQImage(new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Toast.makeText(getApplicationContext(), "qq", Toast.LENGTH_SHORT).show();
				Tag = "QQ����";
				if (image == null) {
					new ShareAction(AssistanceDetailActivity.this).setPlatform(SHARE_MEDIA.QQ)
							.setCallback(umShareListener).withText(content).withTitle(title).withTargetUrl(targeturl)
							.share();
				} else {
					new ShareAction(AssistanceDetailActivity.this).setPlatform(SHARE_MEDIA.QQ)
							.setCallback(umShareListener).withText(content).withTitle(title).withTargetUrl(targeturl)
							.withMedia(image).share();
				}
			}
		});
		builder.setQQZoneImage(new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Tag = "QQ�ռ�";
				Toast.makeText(getApplicationContext(), "qqzone", Toast.LENGTH_SHORT).show();
				if (image == null) {
					new ShareAction(AssistanceDetailActivity.this).setPlatform(SHARE_MEDIA.QZONE)
							.setCallback(umShareListener).withText(content).withTitle(title).withTargetUrl(targeturl)
							.share();
				} else {
					new ShareAction(AssistanceDetailActivity.this).setPlatform(SHARE_MEDIA.QZONE)
							.setCallback(umShareListener).withText(content).withTitle(title).withTargetUrl(targeturl)
							.withMedia(image).share();
				}
			}
		});
		builder.setWeChatImage(new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Tag = "΢�ź���";
				Toast.makeText(getApplicationContext(), "wechat", Toast.LENGTH_SHORT).show();
				if (image == null) {
					new ShareAction(AssistanceDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
							.setCallback(umShareListener).withText(content).withTitle(title).withTargetUrl(targeturl)
							.share();
				} else {
					new ShareAction(AssistanceDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
							.setCallback(umShareListener).withText(content).withTitle(title).withTargetUrl(targeturl)
							.withMedia(image).share();
				}
			}
		});
		builder.setWeFriendsImage(new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Tag = "΢������Ȧ";
				Toast.makeText(getApplicationContext(), "wefriends", Toast.LENGTH_SHORT).show();
				if (image == null) {
					new ShareAction(AssistanceDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
							.setCallback(umShareListener).withText(content).withTitle(title).withTargetUrl(targeturl)
							.share();
				} else {
					new ShareAction(AssistanceDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
							.setCallback(umShareListener).withText(content).withTitle(title).withTargetUrl(targeturl)
							.withMedia(image).share();
				}
			}
		});
		builder.create().show();

	}

	private UMShareListener umShareListener = new UMShareListener() {
		@Override
		public void onResult(SHARE_MEDIA platform) {
			// sendshareinfo();
			Toast.makeText(getApplicationContext(), Tag + "����ɹ���", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onError(SHARE_MEDIA platform, Throwable t) {
			Toast.makeText(getApplicationContext(), Tag + "����ʧ�ܣ�", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onCancel(SHARE_MEDIA platform) {
			Toast.makeText(getApplicationContext(), Tag + "����ȡ����", Toast.LENGTH_SHORT).show();
		}
	};
}

package wuxc.wisdomparty.Internet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import single.wuxc.wisdomparty.R;

public class webview extends Activity implements OnClickListener {
	private WebView WebView;
	private String targeturl;

	private ImageView ImageBack;
	private TextView text_1;
	private TextView text_2;
	private TextView text_3;
	private TextView text_4;
	private TextView text_5;
	private TextView text_6;
	private TextView text_7;
	private LinearLayout lin_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.webview);
		WebView = (android.webkit.WebView) findViewById(R.id.webview);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		targeturl = bundle.getString("url");
		WebView.loadUrl(targeturl);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		ImageBack.setOnClickListener(this);
		text_1 = (TextView) findViewById(R.id.text_1);
		text_2 = (TextView) findViewById(R.id.text_2);
		text_3 = (TextView) findViewById(R.id.text_3);
		text_4 = (TextView) findViewById(R.id.text_4);
		text_5 = (TextView) findViewById(R.id.text_5);
		text_6 = (TextView) findViewById(R.id.text_6);
		text_7 = (TextView) findViewById(R.id.text_7);
		text_1.setOnClickListener(this);
		text_2.setOnClickListener(this);
		text_3.setOnClickListener(this);
		text_4.setOnClickListener(this);
		text_5.setOnClickListener(this);
		text_6.setOnClickListener(this);
		text_7.setOnClickListener(this);
		lin_text = (LinearLayout) findViewById(R.id.lin_text);
		lin_text.setVisibility(View.GONE);
		WebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onShowCustomView(View view, CustomViewCallback callback) {
				super.onShowCustomView(view, callback);
			}

		});
		// WebView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		// WebView.getSettings().setLoadWithOverviewMode(true);

		WebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
			}

			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// loadTime();
				return true; // 表 示已经处理了这次URL的请求
			}

		});
		WebSettings settings = WebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		settings.setTextSize(WebSettings.TextSize.NORMAL);
	}

	@Override
	protected void onPause() {
		WebView.reload();

		super.onPause();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		WebSettings settings = WebView.getSettings();
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.text_1:
			lin_text.setVisibility(View.GONE);

			settings.setTextSize(WebSettings.TextSize.SMALLEST);
			break;
		case R.id.text_2:
			lin_text.setVisibility(View.GONE);

			settings.setTextSize(WebSettings.TextSize.SMALLER);
			break;
		case R.id.text_3:
			lin_text.setVisibility(View.GONE);
			settings.setTextSize(WebSettings.TextSize.NORMAL);
			break;
		case R.id.text_4:
			lin_text.setVisibility(View.GONE);
			settings.setTextSize(WebSettings.TextSize.LARGER);
			break;
		case R.id.text_5:
			lin_text.setVisibility(View.GONE);
			settings.setTextSize(WebSettings.TextSize.LARGEST);
			break;
		case R.id.text_6:
			lin_text.setVisibility(View.GONE);
			break;
		case R.id.text_7:
			lin_text.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

}

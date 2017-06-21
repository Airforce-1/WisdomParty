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
import single.wuxc.wisdomparty.R;

public class webview extends Activity implements OnClickListener {
	private WebView WebView;
	private String targeturl;

	private ImageView ImageBack;

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
		WebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onShowCustomView(View view, CustomViewCallback callback) {
				super.onShowCustomView(view, callback);
			}

		});
//		WebView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
//		WebView.getSettings().setLoadWithOverviewMode(true);
		
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
		settings.setTextSize(WebSettings.TextSize.LARGEST);
	}
	@Override
	protected void onPause ()
	{
		WebView.reload ();
	 
	    super.onPause ();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;

		default:
			break;
		}
	}

}

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
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.layout.dialogfour;

public class AssistanceDetailActivity extends Activity implements OnClickListener {
	private ImageView ImageBackground;
	private int screenwidth = 0;
	private ImageView ImageBack;
	private ImageView ImageShare;
	private TextView TextTitle;
	private TextView TextTime;
	private TextView TextAuthor;
	private TextView TextReadNumber;
	private TextView TextDetail;
	private Button BtnConfirm;
	private String Time;
	private String Title;
	private UMImage image = null;
	private String Tag = "";

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
		TextTime.setText("���ڣ�" + Time);
		TextTitle.setText(Title);
		TextAuthor.setText("���ߣ���������Ա");
		TextReadNumber.setText("��������2341");
		TextDetail.setText(ToDBC(
				"�����á���ò������Զ������Ĺؼ�ȴ�ǡ�������򡱡����ڻ���Ϊ��ʮ���꣬����������˽����Ϊ������η����1859�꣬����Ѳ�������������ȥ������Ϊ�����ľ��Ӱ������ݺ��ڡ����������飬һ˿������������ͼ��κ���Ѳ������ɭ����ʵ��Ƽ�����˵���ǹ������е��Ͳţ��������ٽ�ʹ��Ū����׼����������ƹ��򡰱�����α���������ܶ�����Ҳ���������������ġ�̰ӹ��忡����־��������ܲ������־���(1817-1892)�ֵ��������������(����������)�ˣ�������ʵ�ʱ�����ѧʿ��Ϊ���������飬���ҹ���ʷ������Ϊ����������ר�ң��о�ʱ����֮�ơ�����ɽ������կԩ��ʹ���гݡ��־��������ʮ����(1845)�н�ʿ�����λ������£���������ʹ������ʹ����ɽ������ʹ��ɽ��Ѳ���ȡ�1882����λ������飬1883�������󳼣���������������Ŵ󳼣���Э���ѧʿ��1885���ڶ����ѧʿ��1892�����׷��̫���ٱ������Ľ顣���������и�������һ�죬�����켸���±������������һ��������ǿ����Ů��Ů����ӣ����������ҵ����������߸�ĸ���Ǹ�״���ء�����Ա�����ҹ��ʡ��־�����֪���º�Ȼ��ŭ������Ϊ��������Ƕ����˵�־���Ҫ�����ʰ����Ͻ��ܵ����ĵ��ܶ����У����ľ����������������־����ҵ��ܶ����������Ҫ�׷���������˵�Լ����أ��ܲ��Ӽ����־���������Ӵ���:ȥ���ҵı�������!�Ҿ����ܶ������ŷ�������ס�ޡ��칫���ܶ��Ĳ����ã����־���������ȥ!�־������������ס�����������ȥ�����ı�������ʵ���벻���ܿ͵İ취��ֻ�����������Ѳ������ɭ�����֪������������Ȱ˵�־�����ȥ���ϡ�����˰ٰ�Ȱ˵���־������Ĳ�ɱ�׷������ظ����������Σ�ֻ�ó�����������־���������һ�����ӣ��־��������������:���������׷������ڰ����׷��Ĺ�ְ��Ѻ��ԭ����������Ƭʱ������ֻ�ý������������׷��������־���һ���׷����������۽���߷����ã���ȥ�·�������������ʮ���ȱϾ��ɷ��䱨�ߣ�����ִ�С�"));

	}

	private void initview() {
		// TODO Auto-generated method stub
		ImageBackground = (ImageView) findViewById(R.id.image_background);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		ImageShare = (ImageView) findViewById(R.id.image_share);
		TextTitle = (TextView) findViewById(R.id.text_title);
		TextTime = (TextView) findViewById(R.id.text_time);
		TextAuthor = (TextView) findViewById(R.id.text_author);
		TextReadNumber = (TextView) findViewById(R.id.text_readnumber);
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
		int height = screenwidth / 2;
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
			Intent intent=new Intent();
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

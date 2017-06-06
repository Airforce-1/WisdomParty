package wuxc.wisdomparty.HomeOfHealth;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Adapter.CommentAdapter;
import wuxc.wisdomparty.HomeOfMember.CommentDetailActivity;
import wuxc.wisdomparty.Model.CommentModel;
import wuxc.wisdomparty.add.orgDetailActivity;
import wuxc.wisdomparty.layout.dialogfour;

public class RegimenDetailActivity extends Activity implements OnClickListener, OnItemClickListener {
	private EditText EditAnswer;
	private Button BtnAnswer;
	private ImageView ImageBack;
	private ImageView ImagePic;
	private ImageView ImageCollect;
	private ImageView ImageGreat;
	private ImageView ImageShare;
	private ScrollView ScrollLayout;
	private ListView ListData;
	private TextView TextWarning;
	private TextView TextDetail;
	private TextView TextTimeAndName;
	private TextView TextTitle;
	private TextView TextPageTitle;
	private String Name;
	private String Title;
	private String Time;
	private String PageTitle;
	private String Detail = "����";
	List<CommentModel> list = new ArrayList<CommentModel>();
	private static CommentAdapter mAdapter;
	private int pageSize = 10;
	private int totalPage = 5;
	private int curPage = 1;
	private int screenwidth = 0;
	private float scale = 0;
	private float scalepx = 0;
	private float dp = 0;
	private boolean Collect = false;
	private boolean Great = false;
	private UMImage image = null;
	private String Tag = "";
	private Handler uiHandler = new Handler() {
		@Override
		public void handleMessage(Message msg1) {
			switch (msg1.what) {
			case 0:
				getdatalist(curPage);
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
		setContentView(R.layout.regimendetail);
		Intent intent = this.getIntent(); // ��ȡ���е�intent����
		Bundle bundle = intent.getExtras(); // ��ȡintent�����bundle����
		Name = bundle.getString("Name");
		Title = bundle.getString("Title");
		Time = bundle.getString("Time");
		PageTitle = bundle.getString("PageTitle");
		Detail = bundle.getString("Detail");
		initview();
		setonclicklistener();
		setlistheight(0);
		settext();
		starttimedelay();
	}

	private void starttimedelay() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				Message msg1 = new Message();
				msg1.what = 0;
				uiHandler.sendMessage(msg1);

			}

		}, 2000);
	}

	private void getdatalist(int arg) {
		if (arg == 1) {
			list.clear();
		}
		// TODO Auto-generated method stub

		try {

			for (int i = 0; i < 10; i++) {

				CommentModel listinfo = new CommentModel();
				listinfo.setTime("2016-12-14 20:00:00");
				listinfo.setComment("������һƪ�����£�ѧϰ��");
				listinfo.setRoundUrl("");
				listinfo.setName("��־��");

				list.add(listinfo);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setlistheight(list.size());
		if (arg == 1) {
			go();
		} else {
			mAdapter.notifyDataSetChanged();
		}
		if (arg == totalPage) {
			TextWarning.setText("û�и�����");
		} else {
			TextWarning.setText("������ظ���");
		}
	}

	private void setlistheight(int size) {
		// TODO Auto-generated method stub
		screenwidth = getWindow().getWindowManager().getDefaultDisplay().getWidth();
		DisplayMetrics mMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
		scale = getResources().getDisplayMetrics().density;
		dp = screenwidth / scale + 0.5f;
		scalepx = screenwidth / dp;
		int height = (int) (size * 91 * scalepx);
		RelativeLayout.LayoutParams layoutParams1 = (android.widget.RelativeLayout.LayoutParams) ListData
				.getLayoutParams();
		layoutParams1.height = height;
		ListData.setLayoutParams(layoutParams1);
		height = (int) ((screenwidth - 20 * scalepx) / 1.38);
		layoutParams1 = (android.widget.RelativeLayout.LayoutParams) ImagePic.getLayoutParams();
		layoutParams1.height = height;
		ImagePic.setLayoutParams(layoutParams1);

	}

	protected void go() {
		mAdapter = new CommentAdapter(this, list, ListData);
		ListData.setAdapter(mAdapter);
	}

	private void settext() {
		// TODO Auto-generated method stub
		TextWarning.setText("���ڼ�������...");
		TextDetail.setText(Detail);
		TextTimeAndName.setText("������:" + Name + "    " + Time);
		TextTitle.setText(Title);
		TextPageTitle.setText(PageTitle);
		if (Collect) {
			ImageCollect.setImageResource(R.drawable.collect);
		} else {
			ImageCollect.setImageResource(R.drawable.no_collect);
		}
		if (Great) {
			ImageGreat.setImageResource(R.drawable.great);
		} else {
			ImageGreat.setImageResource(R.drawable.no_great);
		}
	}

	private void initview() {
		// TODO Auto-generated method stub
		EditAnswer = (EditText) findViewById(R.id.edit_answer);
		BtnAnswer = (Button) findViewById(R.id.btn_answer);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		ImagePic = (ImageView) findViewById(R.id.image_pic);
		ScrollLayout = (ScrollView) findViewById(R.id.scrolllayout);
		ListData = (ListView) findViewById(R.id.list_data);
		TextWarning = (TextView) findViewById(R.id.text_warning);
		TextDetail = (TextView) findViewById(R.id.text_detail);
		TextTimeAndName = (TextView) findViewById(R.id.text_timeandname);
		TextTitle = (TextView) findViewById(R.id.text_title);
		ImageCollect = (ImageView) findViewById(R.id.image_collect);
		ImageGreat = (ImageView) findViewById(R.id.image_great);
		TextPageTitle = (TextView) findViewById(R.id.text_page_title);
		ImageShare = (ImageView) findViewById(R.id.image_share);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		BtnAnswer.setOnClickListener(this);
		ImageBack.setOnClickListener(this);
		ListData.setOnItemClickListener(this);
		TextWarning.setOnClickListener(this);
		ImageCollect.setOnClickListener(this);
		ImageGreat.setOnClickListener(this);
		ImageShare.setOnClickListener(this);
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
		image = new UMImage(RegimenDetailActivity.this, R.drawable.image_share);
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
					new ShareAction(RegimenDetailActivity.this).setPlatform(SHARE_MEDIA.QQ).setCallback(umShareListener)
							.withText(content).withTitle(title).withTargetUrl(targeturl).share();
				} else {
					new ShareAction(RegimenDetailActivity.this).setPlatform(SHARE_MEDIA.QQ).setCallback(umShareListener)
							.withText(content).withTitle(title).withTargetUrl(targeturl).withMedia(image).share();
				}
			}
		});
		builder.setQQZoneImage(new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Tag = "QQ�ռ�";
				Toast.makeText(getApplicationContext(), "qqzone", Toast.LENGTH_SHORT).show();
				if (image == null) {
					new ShareAction(RegimenDetailActivity.this).setPlatform(SHARE_MEDIA.QZONE)
							.setCallback(umShareListener).withText(content).withTitle(title).withTargetUrl(targeturl)
							.share();
				} else {
					new ShareAction(RegimenDetailActivity.this).setPlatform(SHARE_MEDIA.QZONE)
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
					new ShareAction(RegimenDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
							.setCallback(umShareListener).withText(content).withTitle(title).withTargetUrl(targeturl)
							.share();
				} else {
					new ShareAction(RegimenDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
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
					new ShareAction(RegimenDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
							.setCallback(umShareListener).withText(content).withTitle(title).withTargetUrl(targeturl)
							.share();
				} else {
					new ShareAction(RegimenDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.btn_answer:

			break;
		case R.id.image_share:
			ShareDialog();
			break;
		case R.id.image_collect:
			if (Collect) {
				ImageCollect.setImageResource(R.drawable.collect);
				Collect = false;
			} else {
				ImageCollect.setImageResource(R.drawable.no_collect);
				Collect = true;
			}

			break;
		case R.id.image_great:
			if (Great) {
				Great = false;
				ImageGreat.setImageResource(R.drawable.great);
			} else {
				Great = true;
				ImageGreat.setImageResource(R.drawable.no_great);
			}
			break;
		case R.id.text_warning:
			curPage++;
			if (!(curPage > totalPage)) {
				getdatalist(curPage);
				Toast.makeText(getApplicationContext(), "���ڼ���", Toast.LENGTH_SHORT).show();
			}

			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		CommentModel data = list.get(position);
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), orgDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("Title", data.getName());
		bundle.putString("Time", data.getTime());
		bundle.putString("content", data.getComment());
		intent.putExtras(bundle);
		startActivity(intent);
	}

}

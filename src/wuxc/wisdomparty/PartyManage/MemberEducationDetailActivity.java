package wuxc.wisdomparty.PartyManage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import single.wuxc.wisdomparty.R;
import android.view.Window;

public class MemberEducationDetailActivity extends Activity implements OnClickListener {
	private ImageView ImageBack;
	private TextView TextTitle;
	private TextView TextTime;
	private TextView TextAuthor;
	private TextView TextReadNumber;
	private TextView TextDetail;
	private TextView TextLastEducation;
	private TextView TextNextEducation;
	private String Time;
	private String Title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.member_education_detail_activity);
		Intent intent = this.getIntent(); // ��ȡ���е�intent����
		Bundle bundle = intent.getExtras(); // ��ȡintent�����bundle����

		Title = bundle.getString("Title");
		Time = bundle.getString("Time");
		initview();
		setonclicklistener();
		TextTime.setText("���ڣ�" + Time);
		TextTitle.setText(Title);
		TextAuthor.setText("���ߣ���������Ա");
		TextReadNumber.setText("��������2341");
		TextDetail.setText(ToDBC(
				"��ʮ�˽��������ξֳ�ί15����������߼��档�й����������ϰ��ƽ˵����������ʷ�Ĵ����ߣ�Ⱥ����������Ӣ�ۡ�����Ⱥ��������������ԴȪ����������֪����ÿ���˵����������޵ģ���ֻҪ��������һ�ģ���־�ɳǣ���û�п˷����˵����ѣ�ÿ���˵Ĺ���ʱ�������޵ģ���ȫ��ȫ��Ϊ������������޵ġ� ϰ��ƽ˵����������̩ɽ����ҵ���ص�Զ������һ��Ҫʼ��������������ӡ��������ͬ�ʹ��ࡢ�������Ž�ܶ�����ҹ�ڹ������㹤����Ŭ������ʷ��������һ�ݺϸ�Ĵ�� ϰ��ƽ�Լ�����˵���й���Ҫ������˽����磬����Ҳ��Ҫ������˽��й���ϣ�����ǽ��Ҫ����Ϊ�����й�������������໥�˽�����Ŭ���͹��ס���Դ�� �»�"));
		TextLastEducation.setText("����鿴��һƪ");
		TextNextEducation.setText("����鿴��һƪ");
	}

	private void initview() {
		// TODO Auto-generated method stub
		ImageBack = (ImageView) findViewById(R.id.image_back);
		TextTitle = (TextView) findViewById(R.id.text_title);
		TextTime = (TextView) findViewById(R.id.text_time);
		TextAuthor = (TextView) findViewById(R.id.text_author);
		TextReadNumber = (TextView) findViewById(R.id.text_readnumber);
		TextDetail = (TextView) findViewById(R.id.text_detail);
		TextLastEducation = (TextView) findViewById(R.id.text_last_education);
		TextNextEducation = (TextView) findViewById(R.id.text_next_education);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ImageBack.setOnClickListener(this);
		TextLastEducation.setOnClickListener(this);
		TextNextEducation.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;
		case R.id.text_last_education:
			Toast.makeText(getApplicationContext(), "text_last_education", Toast.LENGTH_SHORT).show();
			break;
		case R.id.text_next_education:
			Toast.makeText(getApplicationContext(), "text_next_education", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}

}

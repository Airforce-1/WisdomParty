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
		setContentView(R.layout.membereducationdetailactivity);
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
				"�����á���ò������Զ������Ĺؼ�ȴ�ǡ�������򡱡����ڻ���Ϊ��ʮ���꣬����������˽����Ϊ������η����1859�꣬����Ѳ�������������ȥ������Ϊ�����ľ��Ӱ������ݺ��ڡ����������飬һ˿������������ͼ��κ���Ѳ������ɭ����ʵ��Ƽ�����˵���ǹ������е��Ͳţ��������ٽ�ʹ��Ū����׼����������ƹ��򡰱�����α���������ܶ�����Ҳ���������������ġ�̰ӹ��忡����־��������ܲ������־���(1817-1892)�ֵ��������������(����������)�ˣ�������ʵ�ʱ�����ѧʿ��Ϊ���������飬���ҹ���ʷ������Ϊ����������ר�ң��о�ʱ����֮�ơ�����ɽ������կԩ��ʹ���гݡ��־��������ʮ����(1845)�н�ʿ�����λ������£���������ʹ������ʹ����ɽ������ʹ��ɽ��Ѳ���ȡ�1882����λ������飬1883�������󳼣���������������Ŵ󳼣���Э���ѧʿ��1885���ڶ����ѧʿ��1892�����׷��̫���ٱ������Ľ顣���������и�������һ�죬�����켸���±������������һ��������ǿ����Ů��Ů����ӣ����������ҵ����������߸�ĸ���Ǹ�״���ء�����Ա�����ҹ��ʡ��־�����֪���º�Ȼ��ŭ������Ϊ��������Ƕ����˵�־���Ҫ�����ʰ����Ͻ��ܵ����ĵ��ܶ����У����ľ����������������־����ҵ��ܶ����������Ҫ�׷���������˵�Լ����أ��ܲ��Ӽ����־���������Ӵ���:ȥ���ҵı�������!�Ҿ����ܶ������ŷ�������ס�ޡ��칫���ܶ��Ĳ����ã����־���������ȥ!�־������������ס�����������ȥ�����ı�������ʵ���벻���ܿ͵İ취��ֻ�����������Ѳ������ɭ�����֪������������Ȱ˵�־�����ȥ���ϡ�����˰ٰ�Ȱ˵���־������Ĳ�ɱ�׷������ظ����������Σ�ֻ�ó�����������־���������һ�����ӣ��־��������������:���������׷������ڰ����׷��Ĺ�ְ��Ѻ��ԭ����������Ƭʱ������ֻ�ý������������׷��������־���һ���׷����������۽���߷����ã���ȥ�·�������������ʮ���ȱϾ��ɷ��䱨�ߣ�����ִ�С�"));
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

package wuxc.wisdomparty.OtherActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import single.wuxc.wisdomparty.R;
import android.view.Window;

public class MemberPartyBranchDetailActivity extends Activity implements OnClickListener {
	private String Name;
	private TextView TextTitle;
	private TextView TextName;
	private TextView TextManager;
	private TextView TextPhoneNumber;
	private TextView TextAddress;
	private TextView TextDetail;
	private ImageView ImageBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.member_party_branch_detail_activity);
		Intent intent = this.getIntent(); // ��ȡ���е�intent����
		Bundle bundle = intent.getExtras(); // ��ȡintent�����bundle����
		Name = bundle.getString("Name");
		initview();
		setonclicklistener();
		TextTitle.setText(Name);
		TextName.setText(Name);
		TextManager.setText("�����ˣ�������");
		TextPhoneNumber.setText("֧���绰��029-85463524");
		TextAddress.setText("֧����ַ�������и�����·");
		TextDetail.setText(
				"֧�����ܣ�\n֧������֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧�����ܲ�����֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������֧������");
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		ImageBack.setOnClickListener(this);
	}

	private void initview() {
		// TODO Auto-generated method stub
		TextTitle = (TextView) findViewById(R.id.text_title);
		TextName = (TextView) findViewById(R.id.text_party_name);
		TextManager = (TextView) findViewById(R.id.text_party_manager);
		TextPhoneNumber = (TextView) findViewById(R.id.text_party_phonenumber);
		TextAddress = (TextView) findViewById(R.id.text_party_address);
		TextDetail = (TextView) findViewById(R.id.text_party_detail);
		ImageBack = (ImageView) findViewById(R.id.image_back);
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

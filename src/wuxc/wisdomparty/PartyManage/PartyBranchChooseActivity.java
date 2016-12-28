package wuxc.wisdomparty.PartyManage;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.Adapter.PartyBranchDataListAdapter;
import wuxc.wisdomparty.Model.PartyBranchDataListModel;

public class PartyBranchChooseActivity extends Activity implements OnClickListener, OnItemClickListener {
	private ListView ListData;
	private PartyBranchDataListAdapter mAdapter;
	List<PartyBranchDataListModel> list = new ArrayList<PartyBranchDataListModel>();
	List<PartyBranchDataListModel> initList = new ArrayList<PartyBranchDataListModel>();
	List<PartyBranchDataListModel> ShowList = new ArrayList<PartyBranchDataListModel>();
	private RelativeLayout RelativeNextPage;
	private RelativeLayout RelativeLastPage;
	private int Page = 1;
	private int TotalPage = 0;
	private int initTotalPage = 0;
	private int TotalItem = 0;
	private TextView TextPage;
	private EditText EditSearch;
	private ImageView ImageBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.member_party_branch_activity);
		initview();
		setonclicklistener();
		getinfo();
		go(Page);
		EditSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				Searchlist(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
	}

	protected void Searchlist(String string) {
		list.clear();

		Page = 1;
		int temptotalitem = 0;
		// Toast.makeText(getApplicationContext(), "��"+string,
		// Toast.LENGTH_SHORT).show();
		// TODO Auto-generated method stub
		if (TextUtils.isEmpty(string)) {
			for (int i = 0; i < initTotalPage; i++) {
				PartyBranchDataListModel data = initList.get(i);
				temptotalitem++;
				list.add(data);
			}
		} else {
			for (int i = 0; i < initTotalPage; i++) {
				PartyBranchDataListModel data = initList.get(i);
				if ((data.getPartyAddress()).indexOf(string) != -1 || (data.getPartyPhonenumber()).indexOf(string) != -1
						|| (data.getPartyName()).indexOf(string) != -1) {
					temptotalitem++;
					list.add(data);
				}

			}
		}
		TotalItem = temptotalitem;
		TotalPage = TotalItem / 6;
		if (TotalPage * 6 < TotalItem) {
			TotalPage++;
		}
		go(Page);
	}

	private void getinfo() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++) {
			try {
				PartyBranchDataListModel data = new PartyBranchDataListModel();

				data.setIsSelected(false);
				data.setPartyAddress("����ʡμ������μ��");
				data.setPartyName("��μ����ί��֧���ڶ��ֲ�");
				data.setPartyPhonenumber("13022889658");
				list.add(data);
				initList.add(data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
		}
		for (int i = 0; i < 9; i++) {
			try {
				PartyBranchDataListModel data = new PartyBranchDataListModel();

				data.setIsSelected(false);
				data.setPartyAddress("����ʡ�����п�����");
				data.setPartyName("������ͳս����֧��");
				data.setPartyPhonenumber("15158745896");
				list.add(data);
				initList.add(data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
		}
		for (int i = 0; i < 9; i++) {
			try {
				PartyBranchDataListModel data = new PartyBranchDataListModel();
				data.setIsSelected(false);
				data.setPartyAddress("����ʡ�����б�����");
				data.setPartyName("��������ί��֧��");
				data.setPartyPhonenumber("18654875326");
				list.add(data);
				initList.add(data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
		}
		initTotalPage = 27;
		TotalItem = 27;
		TotalPage = TotalItem / 6;
		if (TotalPage * 6 < TotalItem) {
			TotalPage++;
		}
		// }

	}

	protected void go(int page) {
		// TODO Auto-generated method stub

		mAdapter = new PartyBranchDataListAdapter(this, this, getData(page));
		ListData.setAdapter(mAdapter);
		TextPage.setText(Page + "/" + TotalPage);
		// Toast.makeText(choosemember.this, "go", Toast.LENGTH_SHORT).show();
	}

	private List<PartyBranchDataListModel> getData(int page) {
		ShowList.clear();
		for (int i = 0; i < 6; i++) {
			if (i + (page - 1) * 6 >= TotalItem) {
				break;
			} else {

				try {
					PartyBranchDataListModel data = list.get(i + (page - 1) * 6);

					data.setIsSelected(false);

					ShowList.add(data);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				;

			}

		}
		// }
		return ShowList;
	}

	private void initview() {
		// TODO Auto-generated method stub
		ListData = (ListView) findViewById(R.id.list_data);
		RelativeLastPage = (RelativeLayout) findViewById(R.id.realative_last_page);
		RelativeNextPage = (RelativeLayout) findViewById(R.id.realative_next_page);
		TextPage = (TextView) findViewById(R.id.text_page);
		EditSearch = (EditText) findViewById(R.id.edit_search);
		ImageBack = (ImageView) findViewById(R.id.image_back);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		RelativeLastPage.setOnClickListener(this);
		RelativeNextPage.setOnClickListener(this);
		ListData.setOnItemClickListener(this);
		ImageBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.realative_last_page:
			if (Page == 1) {
				Toast.makeText(getApplicationContext(), "���Ѿ��ǵ�һҳ��", Toast.LENGTH_SHORT).show();
			} else {
				Page = Page - 1;
				go(Page);
			}
			break;
		case R.id.realative_next_page:
			if (Page == TotalPage || Page > TotalPage) {
				Toast.makeText(getApplicationContext(), "���Ѿ������һҳ��", Toast.LENGTH_SHORT).show();
			} else {
				Page = Page + 1;
				go(Page);
			}
			break;
		case R.id.image_back:
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		PartyBranchDataListModel data = list.get((Page - 1) * 6 + position);
		Intent intent = new Intent();
		intent.putExtra("Branch", data.getPartyName());
		setResult(0, intent);
		finish();
	}

}

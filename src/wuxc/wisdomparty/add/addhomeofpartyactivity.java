package wuxc.wisdomparty.add;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.HomeOfMember.MemberDiscussionActivity;
import wuxc.wisdomparty.HomeOfMember.MemberPartyBranchActivity;
import wuxc.wisdomparty.HomeOfMember.MemberPartyDuesActivity;
import wuxc.wisdomparty.HomeOfMember.MemberPartyTransformActivity;
import wuxc.wisdomparty.HomeOfMember.MemberRespondActivity;
import wuxc.wisdomparty.HomeOfMember.MemberRulesActivity;

public class addhomeofpartyactivity extends Activity implements OnClickListener {
	private LinearLayout LinMemberRespond;
	private LinearLayout LinMemberPartyTransform;
	private LinearLayout LinMemberRules;
	private LinearLayout LinMemberPartyDues;
	private LinearLayout LinMemberDiscussion;
	private LinearLayout LinMemberPartyBranch;
	private ImageView ImageBack;
	private LinearLayout lin_notice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_home_of_party_activity);
		initview();
		setonclicklistener();
		setlayoutheight();
	}

	private void setlayoutheight() {
		// TODO Auto-generated method stub

	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		LinMemberRespond.setOnClickListener(this);
		LinMemberPartyTransform.setOnClickListener(this);
		LinMemberRules.setOnClickListener(this);
		LinMemberPartyDues.setOnClickListener(this);
		LinMemberDiscussion.setOnClickListener(this);
		LinMemberPartyBranch.setOnClickListener(this);
		ImageBack.setOnClickListener(this);
	}

	private void initview() {
		// TODO Auto-generated method stub
		LinMemberRespond = (LinearLayout) findViewById(R.id.lin_member_respond);
		LinMemberPartyTransform = (LinearLayout) findViewById(R.id.lin_member_party_transform);
		LinMemberRules = (LinearLayout) findViewById(R.id.lin_member_rules);
		LinMemberPartyDues = (LinearLayout) findViewById(R.id.lin_member_party_dues);
		LinMemberDiscussion = (LinearLayout) findViewById(R.id.lin_member_discussion);
		LinMemberPartyBranch = (LinearLayout) findViewById(R.id.lin_member_party_branch);
		ImageBack = (ImageView) findViewById(R.id.image_back);
		lin_notice = (LinearLayout) findViewById(R.id.lin_notice);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_back:
			finish();
			break;

		case R.id.lin_member_discussion:
			Intent intent1 = new Intent();
			intent1.setClass(getApplicationContext(), MemberDiscussionActivity.class);
			startActivity(intent1);
			break;
		case R.id.lin_member_party_branch:
			Intent intent2 = new Intent();
			intent2.setClass(getApplicationContext(), MemberPartyBranchActivity.class);
			startActivity(intent2);
			break;
		case R.id.lin_member_party_dues:
			Intent intent3 = new Intent();
			intent3.setClass(getApplicationContext(), MemberPartyDuesActivity.class);
			startActivity(intent3);
			break;
		case R.id.lin_member_party_transform:
			Intent intent4 = new Intent();
			intent4.setClass(getApplicationContext(), MemberPartyTransformActivity.class);
			startActivity(intent4);
			break;
		case R.id.lin_member_respond:
			Intent intent5 = new Intent();
			intent5.setClass(getApplicationContext(), MemberRespondActivity.class);
			startActivity(intent5);
			break;
		case R.id.lin_member_rules:
			Intent intent6 = new Intent();
			intent6.setClass(getApplicationContext(), MemberRulesActivity.class);
			startActivity(intent6);
			break;
		default:
			break;
		}
	}

}

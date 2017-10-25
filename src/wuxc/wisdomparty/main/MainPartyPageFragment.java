package wuxc.wisdomparty.main;
//

//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import single.wuxc.wisdomparty.R;
//import wuxc.wisdomparty.HomeActivity.HomeSearchActivity;
//import wuxc.wisdomparty.HomeActivity.HomeSettingActivity;
//import wuxc.wisdomparty.PartyManage.AssistanceToPauperActivity;
//import wuxc.wisdomparty.PartyManage.ChangeTermsActivity;
//import wuxc.wisdomparty.PartyManage.CreationAndFightingActivity;
//import wuxc.wisdomparty.PartyManage.DemocraticCommentActivity;
//import wuxc.wisdomparty.PartyManage.InterPartyOnlineActivity;
//import wuxc.wisdomparty.PartyManage.MemberEducationActivity;
//import wuxc.wisdomparty.PartyManage.MemberManageActivity;
//import wuxc.wisdomparty.PartyManage.MemberRewardsActivity;
//import wuxc.wisdomparty.PartyManage.OrganizationLifeActivity;
//import wuxc.wisdomparty.PartyManage.SpecialProjectActivity;
//
//public class MainPartyPageFragment extends MainBaseFragment implements OnClickListener {
//	private LinearLayout LinBtnZone;
//	private int screenwidth = 0;
//	private LinearLayout LinAssistancePauper;
//	private LinearLayout LinChangeTerm;
//	private LinearLayout LinSpecialProject;
//	private LinearLayout LinCreationFighting;
//	private LinearLayout LinMemberRewards;
//	private LinearLayout LinDemocraticComment;
//	private LinearLayout LinMemberEducation;
//	private LinearLayout LinMemberManage;
//	private LinearLayout LinOranizationLife;
//	private LinearLayout LinInterPartyOnline;
//	private ImageView ImageSearch;
//	private ImageView ImageSetting;
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		View view = inflater.inflate(R.layout.mainpartypage, container, false);
//		initview(view);
//		setonclicklistener();
//		setheight();
//		return view;
//	}
//
//	private void setheight() {
//		// TODO Auto-generated method stub
//		screenwidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
//		int height = (int) 23 * screenwidth / 25;
//		LinearLayout.LayoutParams layoutParams1 = (android.widget.LinearLayout.LayoutParams) LinBtnZone
//				.getLayoutParams();
//		layoutParams1.height = height;
//		LinBtnZone.setLayoutParams(layoutParams1);
//	}
//
//	private void initview(View view) {
//		// TODO Auto-generated method stub
//		LinBtnZone = (LinearLayout) view.findViewById(R.id.lin_btn_zone);
//		LinAssistancePauper = (LinearLayout) view.findViewById(R.id.lin_assistance_pauper);
//		LinChangeTerm = (LinearLayout) view.findViewById(R.id.lin_change_term);
//		LinSpecialProject = (LinearLayout) view.findViewById(R.id.lin_special_project);
//		LinCreationFighting = (LinearLayout) view.findViewById(R.id.lin_creation_fighting);
//		LinMemberRewards = (LinearLayout) view.findViewById(R.id.lin_member_rewards);
//		LinDemocraticComment = (LinearLayout) view.findViewById(R.id.lin_democratic_comment);
//		LinMemberEducation = (LinearLayout) view.findViewById(R.id.lin_member_education);
//		LinMemberManage = (LinearLayout) view.findViewById(R.id.lin_member_manage);
//		LinOranizationLife = (LinearLayout) view.findViewById(R.id.lin_organization_life);
//		LinInterPartyOnline = (LinearLayout) view.findViewById(R.id.lin_inter_party_online);
//		ImageSearch = (ImageView) view.findViewById(R.id.image_search);
//		ImageSetting = (ImageView) view.findViewById(R.id.image_setting);
//	}
//
//	private void setonclicklistener() {
//		// TODO Auto-generated method stub
//		LinAssistancePauper.setOnClickListener(this);
//		LinChangeTerm.setOnClickListener(this);
//		LinSpecialProject.setOnClickListener(this);
//		LinCreationFighting.setOnClickListener(this);
//		LinMemberRewards.setOnClickListener(this);
//		LinDemocraticComment.setOnClickListener(this);
//		LinMemberEducation.setOnClickListener(this);
//		LinMemberManage.setOnClickListener(this);
//		LinOranizationLife.setOnClickListener(this);
//		LinInterPartyOnline.setOnClickListener(this);
//		ImageSearch.setOnClickListener(this);
//		ImageSetting.setOnClickListener(this);
//	}
//
//	@Override
//	public void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//
//		MainActivity.curFragmentTag = getString(R.string.str_party);
//
//	}
//
//	@Override
//	public void onClick(View v) {
//
//		// TODO Auto-generated method stub
//		switch (v.getId()) {
//		case R.id.lin_assistance_pauper:
//			Intent intent1 = new Intent();
//			intent1.setClass(getActivity(), AssistanceToPauperActivity.class);
//			startActivity(intent1);
//			break;
//		case R.id.lin_change_term:
//			Intent intent2 = new Intent();
//			intent2.setClass(getActivity(), ChangeTermsActivity.class);
//			startActivity(intent2);
//			break;
//		case R.id.lin_special_project:
//			Intent intent3 = new Intent();
//			intent3.setClass(getActivity(), SpecialProjectActivity.class);
//			startActivity(intent3);
//			break;
//		case R.id.lin_creation_fighting:
//			Intent intent4 = new Intent();
//			intent4.setClass(getActivity(), CreationAndFightingActivity.class);
//			startActivity(intent4);
//			break;
//		case R.id.lin_member_rewards:
//			Intent intent5 = new Intent();
//			intent5.setClass(getActivity(), MemberRewardsActivity.class);
//			startActivity(intent5);
//			break;
//		case R.id.lin_democratic_comment:
//			Intent intent6 = new Intent();
//			intent6.setClass(getActivity(), DemocraticCommentActivity.class);
//			startActivity(intent6);
//			break;
//		case R.id.lin_member_education:
//			Intent intent7 = new Intent();
//			intent7.setClass(getActivity(), MemberEducationActivity.class);
//			startActivity(intent7);
//			break;
//		case R.id.lin_member_manage:
//			Intent intent8 = new Intent();
//			intent8.setClass(getActivity(), MemberManageActivity.class);
//			startActivity(intent8);
//			break;
//		case R.id.lin_organization_life:
//			Intent intent9 = new Intent();
//			intent9.setClass(getActivity(), OrganizationLifeActivity.class);
//			startActivity(intent9);
//			break;
//		case R.id.lin_inter_party_online:
//			Intent intent10 = new Intent();
//			intent10.setClass(getActivity(), InterPartyOnlineActivity.class);
//			startActivity(intent10);
//			break;
//		case R.id.image_search:
//			Intent intent_search = new Intent();
//			intent_search.setClass(getActivity(), HomeSearchActivity.class);
//			startActivity(intent_search);
//			break;
//		case R.id.image_setting:
//			Intent intent_setting = new Intent();
//			intent_setting.setClass(getActivity(), HomeSettingActivity.class);
//			startActivity(intent_setting);
//			break;
//		default:
//			break;
//		}
//	}
//}

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.HomeActivity.HomeSearchActivity;
import wuxc.wisdomparty.HomeActivity.HomeSettingActivity;
import wuxc.wisdomparty.PartyManage.AssistanceToPauperActivity;
import wuxc.wisdomparty.PartyManage.ChangeTermsActivity;
import wuxc.wisdomparty.PartyManage.CreationAndFightingActivity;
import wuxc.wisdomparty.PartyManage.DemocraticCommentActivity;
import wuxc.wisdomparty.PartyManage.InterPartyOnlineActivity;
import wuxc.wisdomparty.PartyManage.MemberEducationActivity;
import wuxc.wisdomparty.PartyManage.MemberManageActivity;
import wuxc.wisdomparty.PartyManage.MemberRewardsActivity;
import wuxc.wisdomparty.PartyManage.OrganizationLifeActivity;
import wuxc.wisdomparty.PartyManage.SpecialProjectActivity;
import wuxc.wisdomparty.main.MainActivity;
import wuxc.wisdomparty.main.MainBaseFragment;

public class MainPartyPageFragment extends MainBaseFragment implements OnClickListener {
	private LinearLayout lin_top_image;
	private LinearLayout lin_zuzhishenghuo;
	private LinearLayout lin_huanjiexuanju;
	private LinearLayout lin_dangyuanguanli;
	private LinearLayout lin_mingzhupingyi;
	private LinearLayout lin_dangyuanjiangcheng;
	private LinearLayout lin_kunnanbuzhu;
	private LinearLayout lin_chuangxianzhengyou;
	private LinearLayout lin_zhuanxianghuodong;
	private LinearLayout lin_zaixianrudang;
	private LinearLayout lin_dangyuanjiaoyu;
	private int screenwidth = 0;
	private ImageView ImageSearch;
	private ImageView ImageSetting;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.add_main_party_fragment, container, false);
		initview(view);
		setonclicklistener();
		setheight();
		return view;
	}

	private void setheight() {
		// TODO Auto-generated method stub
		screenwidth = getActivity().getWindow().getWindowManager().getDefaultDisplay().getWidth();
		int height = (int) 456 * screenwidth / 960;
		LinearLayout.LayoutParams layoutParams1 = (android.widget.LinearLayout.LayoutParams) lin_top_image
				.getLayoutParams();
		layoutParams1.height = height;
		lin_top_image.setLayoutParams(layoutParams1);
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		lin_zuzhishenghuo.setOnClickListener(this);
		lin_huanjiexuanju.setOnClickListener(this);
		lin_dangyuanguanli.setOnClickListener(this);
		lin_mingzhupingyi.setOnClickListener(this);
		lin_dangyuanjiangcheng.setOnClickListener(this);
		lin_kunnanbuzhu.setOnClickListener(this);
		lin_chuangxianzhengyou.setOnClickListener(this);
		lin_zhuanxianghuodong.setOnClickListener(this);
		lin_zaixianrudang.setOnClickListener(this);
		lin_dangyuanjiaoyu.setOnClickListener(this);
		ImageSearch.setOnClickListener(this);
		ImageSetting.setOnClickListener(this);
	}

	private void initview(View view) {
		// TODO Auto-generated method stub
		lin_top_image = (LinearLayout) view.findViewById(R.id.lin_top_image);
		lin_zuzhishenghuo = (LinearLayout) view.findViewById(R.id.lin_zuzhishenghuo);
		lin_huanjiexuanju = (LinearLayout) view.findViewById(R.id.lin_huanjiexuanju);
		lin_dangyuanguanli = (LinearLayout) view.findViewById(R.id.lin_dangyuanguanli);
		lin_mingzhupingyi = (LinearLayout) view.findViewById(R.id.lin_mingzhupingyi);
		lin_dangyuanjiangcheng = (LinearLayout) view.findViewById(R.id.lin_dangyuanjiangcheng);
		lin_kunnanbuzhu = (LinearLayout) view.findViewById(R.id.lin_kunnanbuzhu);
		lin_chuangxianzhengyou = (LinearLayout) view.findViewById(R.id.lin_chuangxianzhengyou);
		lin_zhuanxianghuodong = (LinearLayout) view.findViewById(R.id.lin_zhuanxianghuodong);
		lin_zaixianrudang = (LinearLayout) view.findViewById(R.id.lin_zaixianrudang);
		lin_dangyuanjiaoyu = (LinearLayout) view.findViewById(R.id.lin_dangyuanjiaoyu);
		ImageSearch = (ImageView) view.findViewById(R.id.image_search);
		ImageSetting = (ImageView) view.findViewById(R.id.image_setting);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		MainActivity.curFragmentTag = getString(R.string.str_party);

	}

	@Override
	public void onClick(View v) {

		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lin_kunnanbuzhu:
			Intent intent1 = new Intent();
			intent1.setClass(getActivity(), AssistanceToPauperActivity.class);
			startActivity(intent1);
			break;
		case R.id.lin_huanjiexuanju:
			Intent intent2 = new Intent();
			intent2.setClass(getActivity(), ChangeTermsActivity.class);
			startActivity(intent2);
			break;
		case R.id.lin_zhuanxianghuodong:
			Intent intent3 = new Intent();
			intent3.setClass(getActivity(), SpecialProjectActivity.class);
			startActivity(intent3);
			break;
		case R.id.lin_chuangxianzhengyou:
			Intent intent4 = new Intent();
			intent4.setClass(getActivity(), CreationAndFightingActivity.class);
			startActivity(intent4);
			break;
		case R.id.lin_dangyuanjiangcheng:
			Intent intent5 = new Intent();
			intent5.setClass(getActivity(), MemberRewardsActivity.class);
			startActivity(intent5);
			break;
		case R.id.lin_mingzhupingyi:
			Intent intent6 = new Intent();
			intent6.setClass(getActivity(), DemocraticCommentActivity.class);
			startActivity(intent6);
			break;
		case R.id.lin_dangyuanjiaoyu:
			Intent intent7 = new Intent();
			intent7.setClass(getActivity(), MemberEducationActivity.class);
			startActivity(intent7);
			break;
		case R.id.lin_dangyuanguanli:
			Intent intent8 = new Intent();
			intent8.setClass(getActivity(), MemberManageActivity.class);
			startActivity(intent8);
			break;
		case R.id.lin_zuzhishenghuo:
			Intent intent9 = new Intent();
			intent9.setClass(getActivity(), OrganizationLifeActivity.class);
			startActivity(intent9);
			break;
		case R.id.lin_zaixianrudang:
			Intent intent10 = new Intent();
			intent10.setClass(getActivity(), InterPartyOnlineActivity.class);
			startActivity(intent10);
			break;
		case R.id.image_search:
			Intent intent_search = new Intent();
			intent_search.setClass(getActivity(), HomeSearchActivity.class);
			startActivity(intent_search);
			break;
		case R.id.image_setting:
			Intent intent_setting = new Intent();
			intent_setting.setClass(getActivity(), HomeSettingActivity.class);
			startActivity(intent_setting);
			break;
		default:
			break;
		}
	}
}

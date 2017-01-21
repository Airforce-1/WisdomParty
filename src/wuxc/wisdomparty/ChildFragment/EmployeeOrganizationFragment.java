package wuxc.wisdomparty.ChildFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import single.wuxc.wisdomparty.R;
import wuxc.wisdomparty.HomeOfEmployee.AppeaeanceOfVipDetail;
import wuxc.wisdomparty.HomeOfEmployee.AppearanceVipActivity;
import wuxc.wisdomparty.MemberCenter.NoticeDatalistActivity;
import wuxc.wisdomparty.PartyManage.SpecialDetailActivity;
import wuxc.wisdomparty.PartyManage.SpecialProjectActivity;

public class EmployeeOrganizationFragment extends Fragment implements OnClickListener {

	private RelativeLayout RelaSpecial;
	private RelativeLayout RelaInform;
	private RelativeLayout RelaAppearance;
	private LinearLayout LinSpecialLeft;
	private LinearLayout LinSpecialRightTop;
	private LinearLayout LinSpecialRightBottom;
	private ImageView ImageAppearanceLeft;
	private ImageView ImageCenterTop;
	private ImageView ImageRightTop;
	private ImageView ImageCenterBottom;
	private ImageView ImageRightBottom;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.employee_organization, container, false);
		initview(view);
		setonclicklistener();
		return view;
	}

	private void setonclicklistener() {
		// TODO Auto-generated method stub
		RelaSpecial.setOnClickListener(this);
		RelaInform.setOnClickListener(this);
		RelaAppearance.setOnClickListener(this);
		LinSpecialLeft.setOnClickListener(this);
		LinSpecialRightTop.setOnClickListener(this);
		LinSpecialRightBottom.setOnClickListener(this);
		ImageAppearanceLeft.setOnClickListener(this);
		ImageCenterTop.setOnClickListener(this);
		ImageRightTop.setOnClickListener(this);
		ImageCenterBottom.setOnClickListener(this);
		ImageRightBottom.setOnClickListener(this);
	}

	private void initview(View view) {
		// TODO Auto-generated method stub

		RelaSpecial = (RelativeLayout) view.findViewById(R.id.rela_special);
		RelaInform = (RelativeLayout) view.findViewById(R.id.rela_inform);
		RelaAppearance = (RelativeLayout) view.findViewById(R.id.rela_appearance);
		LinSpecialLeft = (LinearLayout) view.findViewById(R.id.lin_special_left);
		LinSpecialRightTop = (LinearLayout) view.findViewById(R.id.lin_special_right_top);
		LinSpecialRightBottom = (LinearLayout) view.findViewById(R.id.lin_special_right_bottom);
		ImageAppearanceLeft = (ImageView) view.findViewById(R.id.image_appearance_left);
		ImageCenterTop = (ImageView) view.findViewById(R.id.image_center_top);
		ImageRightTop = (ImageView) view.findViewById(R.id.image_right_top);
		ImageCenterBottom = (ImageView) view.findViewById(R.id.image_center_bottom);
		ImageRightBottom = (ImageView) view.findViewById(R.id.image_right_bottom);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rela_special:
			GoSpecialProject();
			break;
		case R.id.rela_inform:
			GoNotice();
			break;
		case R.id.rela_appearance:
			Govip();
			break;
		case R.id.lin_special_left:
			GoSpecialProjectDetail();
			break;
		case R.id.lin_special_right_top:
			GoSpecialProjectDetail();
			break;
		case R.id.lin_special_right_bottom:
			GoSpecialProjectDetail();
			break;
		case R.id.image_appearance_left:
			Govipappearance();
			break;
		case R.id.image_center_top:
			Govipappearance();
			break;
		case R.id.image_right_top:
			Govipappearance();
			break;
		case R.id.image_center_bottom:
			Govipappearance();
			break;
		case R.id.image_right_bottom:
			Govipappearance();
			break;

		default:
			break;
		}
	}

	private void GoSpecialProject() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(getActivity(), SpecialProjectActivity.class);
		startActivity(intent);
	}

	private void GoSpecialProjectDetail() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(getActivity(), SpecialDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("Title", "����1");
		bundle.putString("detail", "����1");
		bundle.putString("Time", "����1");
		bundle.putString("Name", "����");
		intent.putExtras(bundle);
		startActivity(intent);
	}

	private void GoNotice() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(getActivity(), NoticeDatalistActivity.class);
		startActivity(intent);
	}

	private void Govip() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(getActivity(), AppearanceVipActivity.class);
		startActivity(intent);
	}

	private void Govipappearance() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(getActivity(), AppeaeanceOfVipDetail.class);
		Bundle bundle = new Bundle();
		bundle.putString("Title", "��Ա���");
		bundle.putString("Time", "2016-11-23");
		bundle.putString("Name", "С��");
		bundle.putString("PageTitle", "��Ա���");
		bundle.putString("Detail",
				"�й��������������ţ���ƹ����ţ�ԭ���й�������������ţ����й��������쵼��һ������������������й�������ɵ�Ⱥ������֯������������ίԱ�����й�����ίԱ���쵼�������ŵĵط�������֯��ͬ������ίԱ���쵼��ͬʱ�ܹ������ϼ���֯�쵼��1922��5�£��ŵĵ�һ�δ������ڹ��ݾ��У���ʽ�����й�������������ţ�1925��1��26�ոĳ��й��������������š�1959��5��4�չ���������䲼�������Żա�");
		intent.putExtras(bundle);
		startActivity(intent);
	}
}

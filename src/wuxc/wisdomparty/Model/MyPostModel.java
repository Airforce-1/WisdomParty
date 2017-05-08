package wuxc.wisdomparty.Model;

import android.R.string;

public class MyPostModel {

	private String Title;
	private String Time;
	private String ReBack;
	private String HeadImgUrl;
	private String Name;
	private String url;
	private String keyid;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKeyid() {
		return keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getReBack() {
		return ReBack;
	}

	public void setReBack(String reBack) {
		ReBack = reBack;
	}

	public String getHeadImgUrl() {
		return HeadImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		HeadImgUrl = headImgUrl;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}

package wuxc.wisdomparty.Model;

public class VolunteerModel {
	private String BackGround;
	private String link;
	private boolean cont;
private String summary;
	public String getSummary() {
	return summary;
}

public void setSummary(String summary) {
	this.summary = summary;
}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isCont() {
		return cont;
	}

	public void setCont(boolean cont) {
		this.cont = cont;
	}
	private String Title;
	private String Detail;
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}

	public String getBackGround() {
		return BackGround;
	}

	public void setBackGround(String imageUrl) {
		BackGround = imageUrl;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String name) {
		Title = name;
	}
}

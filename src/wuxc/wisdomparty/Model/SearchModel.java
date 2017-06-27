package wuxc.wisdomparty.Model;

public class SearchModel {
	private String ImageUrl;
	private String Number;
	private String Title;
	private String Detail;
	private String Time;
	private String link;
	private boolean cont;

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
	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String name) {
		Title = name;
	}
}

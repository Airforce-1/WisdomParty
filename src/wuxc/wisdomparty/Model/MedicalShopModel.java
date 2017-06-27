package wuxc.wisdomparty.Model;

public class MedicalShopModel {
	private String ImageUrl;

	private String Title;
	private String Detail;
private String url;
private String time;	private String link;
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
	public String getTime() {
	return time;
}

public void setTime(String time) {
	this.time = time;
}

	public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
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

	public String getTitle() {
		return Title;
	}

	public void setTitle(String name) {
		Title = name;
	}
}

package wuxc.wisdomparty.Model;

public class RespondModel {
	private String ImageUrl;
	private String Title;
	private String Time;
private String CONT;	private String link;
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
	public String getCONT() {
	return CONT;
}

public void setCONT(String cONT) {
	CONT = cONT;
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

	public void setTitle(String title) {
		Title = title;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

}

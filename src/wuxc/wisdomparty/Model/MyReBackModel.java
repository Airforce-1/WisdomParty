package wuxc.wisdomparty.Model;

public class MyReBackModel {

	private String Title;
	private String Time;
	private String ReBack;
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
	public String getReBack() {
		return ReBack;
	}

	public void setReBack(String reBack) {
		ReBack = reBack;
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

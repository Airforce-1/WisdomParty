package wuxc.wisdomparty.Model;

public class HealthModel {
	private String IamgeUrl;
	private String Title;
	private String Label;
	private boolean IsCollect;
	private boolean IsGreat;
	private String Collect;
	private String Great;
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
	public String getIamgeUrl() {
		return IamgeUrl;
	}

	public void setIamgeUrl(String iamgeUrl) {
		IamgeUrl = iamgeUrl;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public boolean isIsCollect() {
		return IsCollect;
	}

	public void setIsCollect(boolean isCollect) {
		IsCollect = isCollect;
	}

	public boolean isIsGreat() {
		return IsGreat;
	}

	public void setIsGreat(boolean isGreat) {
		IsGreat = isGreat;
	}

	public String getCollect() {
		return Collect;
	}

	public void setCollect(String collect) {
		Collect = collect;
	}

	public String getGreat() {
		return Great;
	}

	public void setGreat(String great) {
		Great = great;
	}
}

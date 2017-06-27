package wuxc.wisdomparty.Model;

public class ChangeTermsModel {
	private String Title;
	private String Number;
	private boolean isSelecting;
	private boolean isSelected;
	private String Id;
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
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public boolean isSelecting() {
		return isSelecting;
	}

	public void setSelecting(boolean isSelecting) {
		this.isSelecting = isSelecting;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}

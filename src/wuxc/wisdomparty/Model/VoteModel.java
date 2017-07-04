package wuxc.wisdomparty.Model;

public class VoteModel {
	private String ImageUrl;
	private String Title;
	private String Detail;
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

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}
}

package wuxc.wisdomparty.Model;

public class MemberEducationModel {
	private String ImageUrl;
	private String title;
	private String Time;
private String content;
	public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

	public String getImageUrl() {
		return ImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}
}

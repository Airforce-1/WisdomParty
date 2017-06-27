package wuxc.wisdomparty.Model;

public class CommentModel {
	private String RoundUrl;
	private String Name;
	private String Comment;
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
	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	public String getRoundUrl() {
		return RoundUrl;
	}

	public void setRoundUrl(String roundUrl) {
		RoundUrl = roundUrl;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}
}

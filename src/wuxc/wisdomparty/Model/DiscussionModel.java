package wuxc.wisdomparty.Model;

public class DiscussionModel {
	private String RoundUrl;
	private String Name;
	private String Title;
	private String BrowseNumber;
	private String AnswerNumber;
	private String Time;
private String content;
	public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
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

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getBrowseNumber() {
		return BrowseNumber;
	}

	public void setBrowseNumber(String browseNumber) {
		BrowseNumber = browseNumber;
	}

	public String getAnswerNumber() {
		return AnswerNumber;
	}

	public void setAnswerNumber(String answerNumber) {
		AnswerNumber = answerNumber;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}
}

package wuxc.wisdomparty.Model;

public class VolunteerModel {
	private String BackGround;

	private String Title;
	private String Detail;
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}

	public String getBackGround() {
		return BackGround;
	}

	public void setBackGround(String imageUrl) {
		BackGround = imageUrl;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String name) {
		Title = name;
	}
}

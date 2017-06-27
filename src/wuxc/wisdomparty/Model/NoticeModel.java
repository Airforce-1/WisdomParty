package wuxc.wisdomparty.Model;

public class NoticeModel {
	private String Time;
	private String Style;
	private String KeyID;
	private int readState;
	private String sendUserName;
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
	public String getSendUserName() {
		return sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	public String getKeyID() {
		return KeyID;
	}

	public void setKeyID(String keyID) {
		KeyID = keyID;
	}

	public int getReadState() {
		return readState;
	}

	public void setReadState(int readState) {
		this.readState = readState;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getStyle() {
		return Style;
	}

	public void setStyle(String style) {
		Style = style;
	}

}

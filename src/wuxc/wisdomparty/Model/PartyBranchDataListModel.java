package wuxc.wisdomparty.Model;
public class PartyBranchDataListModel {
	private String PartyName;
	private String PartyAddress;
	private String PartyPhonenumber;
	private String Id;
	private boolean IsSelected;
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
	public String getPartyName() {
		return PartyName;
	}

	public void setPartyName(String partyName) {
		PartyName = partyName;
	}

	public String getPartyAddress() {
		return PartyAddress;
	}

	public void setPartyAddress(String partyAddress) {
		PartyAddress = partyAddress;
	}

	public String getPartyPhonenumber() {
		return PartyPhonenumber;
	}

	public void setPartyPhonenumber(String partyPhonenumber) {
		PartyPhonenumber = partyPhonenumber;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public boolean isIsSelected() {
		return IsSelected;
	}

	public void setIsSelected(boolean isSelected) {
		IsSelected = isSelected;
	}
}

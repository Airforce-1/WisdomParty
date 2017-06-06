package wuxc.wisdomparty.Model;

public class CandicateModel {
	private String ImageUrl;
	private double Scale;
	private String Number;
	private String Name;
	private String Id;
	private String Remark;

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
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

	public double getScale() {
		return Scale;
	}

	public void setScale(double scale) {
		Scale = scale;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}

	package bean;

import java.io.Serializable;

public class Address implements Serializable {
	private int customerID;
	private int addressID;
	private String addressDetail;
	private String addressPostcode;
	private String addressPhone;
	private String addressName;//收件人

	private static final long serialVersionUID = 1L;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(int customerID, int addressID, String addressDetail, String addressPostcode, String addressPhone,
			String addressName) {
		super();
		this.customerID = customerID;
		this.addressID = addressID;
		this.addressDetail = addressDetail;
		this.addressPostcode = addressPostcode;
		this.addressPhone = addressPhone;
		this.addressName = addressName;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getAddressPostcode() {
		return addressPostcode;
	}

	public void setAddressPostcode(String addressPostcode) {
		this.addressPostcode = addressPostcode;
	}

	public String getAddressPhone() {
		return addressPhone;
	}

	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	@Override
	public String toString() {
		return "Address [customerID=" + customerID + ", addressID=" + addressID + ", addressDetail=" + addressDetail
				+ ", addressPostcode=" + addressPostcode + ", addressPhone=" + addressPhone + ", addressName="
				+ addressName + "]";
	}

}

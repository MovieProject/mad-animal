package movie.business.domain;

public class Member {
	private String memberID;
	private String password;
	private String member_Name;
	private String address;
	private String email;
	private String tel;
	private int power;

	public Member() {

	}

	public Member(String memberID, String password, String member_Name,
			String address, String email, String tel, int power) {
		super();
		this.memberID = memberID;
		this.password = password;
		this.member_Name = member_Name;
		this.address = address;
		this.email = email;
		this.tel = tel;
		this.power = power;
	}

	public String getMemberID() {
		return memberID;
	}

	public String getPassword() {
		return password;
	}

	public String getMember_Name() {
		return member_Name;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getTel() {
		return tel;
	}

	public int getPower() {
		return power;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMember_Name(String member_Name) {
		this.member_Name = member_Name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setPower(int power) {
		this.power = power;
	}

	@Override
	public String toString() {
		return "Member [memberID=" + memberID + ", password=" + password
				+ ", member_Name=" + member_Name + ", address=" + address
				+ ", email=" + email + ", tel=" + tel + ", power=" + power
				+ "]";
	}

}

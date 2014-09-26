package movie.business.domain;

public class Member {
	private String memberID;
	private String password;
	private String memberName;
	private String address;
	private String email;
	private String tel;
	private int grade;

	public Member() {

	}

	public Member(String memberID, String password, String memberName,
			String address, String email, String tel, int grade) {
		super();
		this.memberID = memberID;
		this.password = password;
		this.memberName = memberName;
		this.address = address;
		this.email = email;
		this.tel = tel;
		this.grade = grade;
	}

	public String getMemberID() {
		return memberID;
	}

	public String getPassword() {
		return password;
	}

	public String getmemberName() {
		return memberName;
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

	public int getGrade() {
		return grade;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setmemberName(String memberName) {
		this.memberName = memberName;
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

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Member [memberID=" + memberID + ", password=" + password
				+ ", memberName=" + memberName + ", address=" + address
				+ ", email=" + email + ", tel=" + tel + ", grade=" + grade
				+ "]";
	}

}

package jp.co.ntt.usermanager.app.user.search;

import java.io.Serializable;
import java.util.Date;

public class SearchForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ユーザID
	 */
	private String userId;
	/**
	 * 名前
	 */
	private String userName;
	/**
	 * 生年月日
	 */
	private Date userBirth;
	/**
	 * 住所
	 */
	private String address;
	/**
	 * 電話番号
	 */
	private String telNum;
	/**
	 * 権限
	 */
	private String role;
	/**
	 * 状態
	 */
	private String userStatus;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

}

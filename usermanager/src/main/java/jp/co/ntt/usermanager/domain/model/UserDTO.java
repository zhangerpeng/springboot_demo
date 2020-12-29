package jp.co.ntt.usermanager.domain.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDTO implements UserDetails, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ユーザID
	 */
	private String userId;
	/**
	 * ユーザ名前
	 */
	private String username;
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
	 * 状態
	 */
	private String status;
	/**
	 * 権限
	 */
	private List<String> roles;
	/**
	 * パスワード
	 */
	private String password;
	/**
	 * バージョン番号
	 */
	private int recodeversion;
	/**
	 * 更新日時
	 */
	private String lastupdatetime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRecodeversion() {
		return recodeversion;
	}

	public void setRecodeversion(int recodeversion) {
		this.recodeversion = recodeversion;
	}

	public String getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(String lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String[] authorArrayString = null;
		if (getRoles()!= null || getRoles().size() == 0) {
			return null;
		} else {
			int index = 0;
			authorArrayString = new String[this.roles.size()];
			for (String userRole : roles) {
				if ("ADMN".equals(userRole)) {
					authorArrayString[index++] = "ROLE_ADMIN";
				} else {
					authorArrayString[index++] = "ROLE_USER";
				}
			}

		}

		return AuthorityUtils.createAuthorityList(authorArrayString);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", username=" + username + ", userBirth=" + userBirth + ", address="
				+ address + ", telNum=" + telNum + ", status=" + status + ", roles=" + roles + ", password=" + password
				+ ", recodeversion=" + recodeversion + ", lastupdatetime=" + lastupdatetime + "]";
	}

}

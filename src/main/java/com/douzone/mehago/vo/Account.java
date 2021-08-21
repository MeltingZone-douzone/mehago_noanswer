package com.douzone.mehago.vo;

import lombok.Data;

@Data
public class Account {

    private Long no;
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String phoneNumber;
    private String thumbnailUrl;
    private String token;

    @Override
	public String toString() {
		return "Account [no=" + no + ", email=" + email + ", password=" + password + ", nickname=" + nickname
				+ ", name=" + name + ", phoneNumber=" + phoneNumber + ", thumbnailUrl=" + thumbnailUrl + "]";
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
    public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}

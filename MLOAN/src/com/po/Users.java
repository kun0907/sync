package com.po;

import java.io.Serializable;

public class Users implements Serializable {

  private static final long serialVersionUID = 1L;
  private Integer id;
  private String username;
  private String password;
  private String usermail;
  private String status;
  private String sessionid;
  
  
  
  

  public String getUsermail() {
	return usermail;
}

public void setUsermail(String usermail) {
	this.usermail = usermail;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getSessionid() {
	return sessionid;
}

public void setSessionid(String sessionid) {
	this.sessionid = sessionid;
}

public Users() {
    super();
  }

  public Users(Integer id, String username, String password) {
    super();
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
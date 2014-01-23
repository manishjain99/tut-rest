package com.yummynoodlebar.core.domain;

import java.io.Serializable;
import java.util.Date;

import com.yummynoodlebar.config.UtilMethods;



public class User implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -1211802439119529774L;

	private Date createdAt;

	private int id;

	private int ownedRepos;

	private int contributedRepos;

	private String avatarUrl;

	private String blog;

	private String email;

	private String gravatarId;

	private String htmlUrl;

	private String location;

	private String login;

	private String name;

	private String url;


	/**
	 * @return createdAt
	 */
	public Date getCreatedAt() {
		return UtilMethods.clone(createdAt);
	}

	/**
	 * @param createdAt
	 * @return this user
	 */
	public User setCreatedAt(Date createdAt) {
		this.createdAt = UtilMethods.clone(createdAt);
		return this;
	}

	public int getId() {
		return id;
	}

	public User setId(int id) {
		this.id = id;
		return this;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public User setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
		return this;
	}

	public String getBlog() {
		return blog;
	}

	public User setBlog(String blog) {
		this.blog = blog;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGravatarId() {
		return gravatarId;
	}

	public User setGravatarId(String gravatarId) {
		this.gravatarId = gravatarId;
		return this;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public User setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
		return this;
	}

	public String getLocation() {
		return location;
	}

	public String getLogin() {
		return login;
	}

	public User setLogin(String login) {
		this.login = login;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public User setUrl(String url) {
		this.url = url;
		return this;
	}
	public int getOwnedRepoCount(){
		return ownedRepos;
	}
	public int getContributedReposCount(){
		return ownedRepos;
	}
	

}
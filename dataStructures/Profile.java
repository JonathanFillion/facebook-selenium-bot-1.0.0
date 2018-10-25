package dataStructures;

import java.util.ArrayList;

public class Profile {
	String name;
	String location;
	String linksExternal;
	ArrayList<Node> followers;
	ArrayList<Node> following;
	ArrayList<Discussion> listeDiscussion;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLinksExternal() {
		return linksExternal;
	}

	public void setLinksExternal(String linksExternal) {
		this.linksExternal = linksExternal;
	}

	public ArrayList<Node> getFollowers() {
		return followers;
	}

	public void setFollowers(ArrayList<Node> followers) {
		this.followers = followers;
	}

	public ArrayList<Node> getFollowing() {
		return following;
	}

	public void setFollowing(ArrayList<Node> following) {
		this.following = following;
	}

}

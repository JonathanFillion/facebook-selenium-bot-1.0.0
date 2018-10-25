package dataStructures;

import java.util.ArrayList;

public class NodeInterface {

	ArrayList<Node> followers;
	ArrayList<Node> friends;
	ArrayList<Node> following;

	public NodeInterface(ArrayList<Node> friends, ArrayList<Node> followers, ArrayList<Node> following) {
		super();
		this.friends = friends;
		this.followers = followers;
		this.following = following;
	}

	public ArrayList<Node> getFollowers() {
		return followers;
	}

	public void setFollowers(ArrayList<Node> followers) {
		this.followers = followers;
	}

	public ArrayList<Node> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<Node> friends) {
		this.friends = friends;
	}

	public ArrayList<Node> getFollowing() {
		return following;
	}

	public void setFollowing(ArrayList<Node> following) {
		this.following = following;
	}

}

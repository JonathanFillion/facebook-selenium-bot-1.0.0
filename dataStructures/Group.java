package dataStructures;

public class Group {
	String name;
	String url;
	String info;
	int numMembers;

	public Group(String name, String url, String info, int numMembers) {
		this.name = name;
		this.url = url;
		this.info = info;
		this.numMembers = numMembers;
	}

	public Group(String name, String url, int numMembers) {
		this.name = name;
		this.url = url;
		this.numMembers = numMembers;
	}
}

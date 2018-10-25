package dataStructures;

public class Node {

	String name;
	String url;
	String other;

	public Node(String name, String url, String other) {
		this.name = name;
		this.url = url;
		this.other = other;
		System.out.println(name + "   " + url + "   " + other);
	}

	public Node(String name, String url) {
		this.name = name;
		this.url = url;
		System.out.println(name + "   " + url);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}

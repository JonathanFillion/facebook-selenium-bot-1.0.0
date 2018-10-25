package dataStructures;

import java.util.ArrayList;

public class Comment {
	String author;
	String url;
	String comment;
	String likes;
	ArrayList<Comment> answer = new ArrayList<Comment>();

	public Comment(String name, String profileUrl, String text, String numberOfLikes, ArrayList<Comment> object) {
		author = name;
		url = profileUrl;
		comment = text;
		likes = numberOfLikes;
		answer = object;
		System.out.println("Comment [author=" + author + ", url=" + url + ", comment=" + comment + ", likes=" + likes
				+ ", answer=" + answer + "]");
	}

	public String toString() {
		return "Comment [author=" + author + ", url=" + url + ", comment=" + comment + ", likes=" + likes + ", answer="
				+ answer + "]";
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public ArrayList<Comment> getAnswer() {
		return answer;
	}

	public void setAnswer(ArrayList<Comment> answer) {
		this.answer = answer;
	}

	public void addReply(Comment comment) {

		if (answer == null) {
			answer = new ArrayList<Comment>();
		}
		answer.add(comment);
	}
}

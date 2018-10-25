package dataStructures;

import java.util.ArrayList;

public class Discussion {
	ArrayList<Comment> listeComment;

	public Discussion() {
		listeComment = new ArrayList<Comment>();
	}

	public void addComment(Comment comment) {
		listeComment.add(comment);
	}

	public void addToLastAsReply(Comment comment) {
		listeComment.get(listeComment.size() - 1).addReply(comment);
	}

}

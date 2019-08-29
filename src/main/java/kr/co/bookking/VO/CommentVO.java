package kr.co.bookking.VO;

public class CommentVO {
	int commentId, commentQnaId;
	String commentUserId, commentContents;
	
	public CommentVO(){}
	
	public CommentVO(int commentId, int commentQnaId, String commentUserId, String commentContents) {
		super();
		this.commentId = commentId;
		this.commentQnaId = commentQnaId;
		this.commentUserId = commentUserId;
		this.commentContents = commentContents;
	}


	public int getCommentId() {
		return commentId;
	}


	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}


	public int getCommentQnaId() {
		return commentQnaId;
	}


	public void setCommentQnaId(int commentQnaId) {
		this.commentQnaId = commentQnaId;
	}


	public String getCommentUserId() {
		return commentUserId;
	}


	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}


	public String getCommentContents() {
		return commentContents;
	}


	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}


	@Override
	public String toString() {
		return "CommentVO [commentId=" + commentId + ", commentQnaId=" + commentQnaId + ", commentUserId="
				+ commentUserId + ", commentContents=" + commentContents + "]";
	}

	
	
}

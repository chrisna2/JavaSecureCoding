package kr.co.bookking.VO;

public class QnaVO {
	int qnaId, qnaBookId;
	String qnaUserId, qnaTitle, qnaContents, qnaRegistrationDate;
	
	public QnaVO(){}
	
	public QnaVO(int qnaId, int qnaBookId, String qnaUserId, String qnaTitle, String qnaContent,
			String qnaRegistrationDate) {
		super();
		this.qnaId = qnaId;
		this.qnaBookId = qnaBookId;
		this.qnaUserId = qnaUserId;
		this.qnaTitle = qnaTitle;
		this.qnaContents = qnaContent;
		this.qnaRegistrationDate = qnaRegistrationDate;
	}

	public int getQnaId() {
		return qnaId;
	}

	public void setQnaId(int qnaId) {
		this.qnaId = qnaId;
	}

	public int getQnaBookId() {
		return qnaBookId;
	}

	public void setQnaBookId(int qnaBookId) {
		this.qnaBookId = qnaBookId;
	}

	public String getQnaUserId() {
		return qnaUserId;
	}

	public void setQnaUserId(String qnaUserId) {
		this.qnaUserId = qnaUserId;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaContents() {
		return qnaContents;
	}

	public void setQnaContents(String qnaContents) {
		this.qnaContents = qnaContents;
	}

	public String getQnaRegistrationDate() {
		return qnaRegistrationDate;
	}

	public void setQnaRegistrationDate(String qnaRegistrationDate) {
		this.qnaRegistrationDate = qnaRegistrationDate;
	}

	@Override
	public String toString() {
		return "QnaVO [qnaId=" + qnaId + ", qnaBookId=" + qnaBookId + ", qnaUserId=" + qnaUserId + ", qnaTitle="
				+ qnaTitle + ", qnaContent=" + qnaContents + ", qnaRegistrationDate=" + qnaRegistrationDate + "]";
	}
	
	
}

package kr.co.bookking.VO;

public class ProductCommentVO {
	int pCommentId, pCommentBookId, pCommentPoint, pCommentRecommend, pCommentNonRecommend;
	String pCommentUserId, pCommentLatterPart, pCommentRegistrationDate;
	
	public ProductCommentVO(){}
	
	public ProductCommentVO(int pCommentId, int pCommentBookId, int pCommentPoint, int pCommentRecommend,
			int pCommentNonRecommend, String pCommentUserId, String pCommentLatterPart, String pCommentRegistrationDate) {
		super();
		this.pCommentId = pCommentId;
		this.pCommentBookId = pCommentBookId;
		this.pCommentPoint = pCommentPoint;
		this.pCommentRecommend = pCommentRecommend;
		this.pCommentNonRecommend = pCommentNonRecommend;
		this.pCommentUserId = pCommentUserId;
		this.pCommentLatterPart = pCommentLatterPart;
		this.pCommentRegistrationDate = pCommentRegistrationDate;
	}

	public int getpCommentId() {
		return pCommentId;
	}

	public void setpCommentId(int pCommentId) {
		this.pCommentId = pCommentId;
	}

	public int getpCommentBookId() {
		return pCommentBookId;
	}

	public void setpCommentBookId(int pCommentBookId) {
		this.pCommentBookId = pCommentBookId;
	}

	public int getpCommentPoint() {
		return pCommentPoint;
	}

	public void setpCommentPoint(int pCommentPoint) {
		this.pCommentPoint = pCommentPoint;
	}

	public int getpCommentRecommend() {
		return pCommentRecommend;
	}

	public void setpCommentRecommend(int pCommentRecommend) {
		this.pCommentRecommend = pCommentRecommend;
	}

	public int getpCommentNonRecommend() {
		return pCommentNonRecommend;
	}

	public void setpCommentNonRecommend(int pCommentNonRecommend) {
		this.pCommentNonRecommend = pCommentNonRecommend;
	}

	public String getpCommentUserId() {
		return pCommentUserId;
	}

	public void setpCommentUserId(String pCommentUserId) {
		this.pCommentUserId = pCommentUserId;
	}

	public String getpCommentLatterPart() {
		return pCommentLatterPart;
	}

	public void setpCommentLatterPart(String pCommentLatterPart) {
		this.pCommentLatterPart = pCommentLatterPart;
	}

	public String getpCommentRegistrationDate() {
		return pCommentRegistrationDate;
	}

	public void setpCommentRegistrationDate(String pCommentRegistrationDate) {
		this.pCommentRegistrationDate = pCommentRegistrationDate;
	}

	@Override
	public String toString() {
		return "ProductCommentVO [pCommentId=" + pCommentId + ", pCommentBookId=" + pCommentBookId + ", pCommentPoint="
				+ pCommentPoint + ", pCommentRecommend=" + pCommentRecommend + ", pCommentNonRecommend="
				+ pCommentNonRecommend + ", pCommentUserId=" + pCommentUserId + ", pCommentLatterPart="
				+ pCommentLatterPart + ", pCommentRegistrationDate=" + pCommentRegistrationDate + "]";
	}

	
	
}

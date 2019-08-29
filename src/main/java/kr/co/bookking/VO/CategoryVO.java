package kr.co.bookking.VO;

public class CategoryVO {
	int categoryId;
	String categoryKind1, categoryKind2;
	
	public CategoryVO(){}
	
	public CategoryVO(int categoryId, String categoryKind1, String categoryKind2) {
		super();
		this.categoryId = categoryId;
		this.categoryKind1 = categoryKind1;
		this.categoryKind2 = categoryKind2;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryKind1() {
		return categoryKind1;
	}

	public void setCategoryKind1(String categoryKind1) {
		this.categoryKind1 = categoryKind1;
	}

	public String getCategoryKind2() {
		return categoryKind2;
	}

	public void setCategoryKind2(String categoryKind2) {
		this.categoryKind2 = categoryKind2;
	}

	@Override
	public String toString() {
		return "CategoryVO [categoryId=" + categoryId + ", categoryKind1=" + categoryKind1 + ", categoryKind2="
				+ categoryKind2 + "]";
	}
	
	
}

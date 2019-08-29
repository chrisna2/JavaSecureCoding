package kr.co.bookking.VO;

public class CartVO {
	int bookId, cartStock;
	String cartUserId, cartCreateDate;
	
	public CartVO(){}
	
	public CartVO(int bookId, int cartStock, String cartUserId, String cartCreateDate) {
		super();
		this.bookId = bookId;
		this.cartStock = cartStock;
		this.cartUserId = cartUserId;
		this.cartCreateDate = cartCreateDate;
	}
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getCartStock() {
		return cartStock;
	}
	public void setCartStock(int cartStock) {
		this.cartStock = cartStock;
	}
	public String getCartUserId() {
		return cartUserId;
	}
	public void setCartUserId(String cartUserId) {
		this.cartUserId = cartUserId;
	}
	public String getCartCreateDate() {
		return cartCreateDate;
	}
	public void setCartCreateDate(String cartCreateDate) {
		this.cartCreateDate = cartCreateDate;
	}
	
	@Override
	public String toString() {
		return "CartVO [bookId=" + bookId + ", cartStock=" + cartStock + ", cartUserId=" + cartUserId
				+ ", cartCreateDate=" + cartCreateDate + "]";
	}
	
}

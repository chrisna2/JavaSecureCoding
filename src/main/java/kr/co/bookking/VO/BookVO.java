package kr.co.bookking.VO;

public class BookVO {
	int bookId, bookCategoryId, bookStock, bookPrice, bookHits, bookSellCount;
	String bookName, bookWriter, bookPublisher, bookContents, bookDetailImage, bookListImage, bookDate, bookTranslator;
	
	public BookVO(){}
	
	public BookVO(int bookId, int bookCategoryId, int bookStock, int bookPrice, int bookHits, int bookSellCount,
			String bookName, String bookWriter, String bookPublisher, String bookContents, String bookDetailImage,
			String bookListImage, String bookDate, String bookTranslator) {
		super();
		this.bookId = bookId;
		this.bookCategoryId = bookCategoryId;
		this.bookStock = bookStock;
		this.bookPrice = bookPrice;
		this.bookHits = bookHits;
		this.bookSellCount = bookSellCount;
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookPublisher = bookPublisher;
		this.bookContents = bookContents;
		this.bookDetailImage = bookDetailImage;
		this.bookListImage = bookListImage;
		this.bookDate = bookDate;
		this.bookTranslator = bookTranslator;
	}
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getBookCategoryId() {
		return bookCategoryId;
	}
	public void setBookCategoryId(int bookCategoryId) {
		this.bookCategoryId = bookCategoryId;
	}
	public int getBookStock() {
		return bookStock;
	}
	public void setBookStock(int bookStock) {
		this.bookStock = bookStock;
	}
	public int getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	public int getBookHits() {
		return bookHits;
	}
	public void setBookHits(int bookHits) {
		this.bookHits = bookHits;
	}
	public int getBookSellCount() {
		return bookSellCount;
	}
	public void setBookSellCount(int bookSellCount) {
		this.bookSellCount = bookSellCount;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	public String getBookContents() {
		return bookContents;
	}
	public void setBookContents(String bookContents) {
		this.bookContents = bookContents;
	}
	public String getBookDetailImage() {
		return bookDetailImage;
	}
	public void setBookDetailImage(String bookDetailImage) {
		this.bookDetailImage = bookDetailImage;
	}
	public String getBookListImage() {
		return bookListImage;
	}
	public void setBookListImage(String bookListImage) {
		this.bookListImage = bookListImage;
	}
	public String getBookDate() {
		return bookDate;
	}
	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}
	public String getBookTranslator() {
		return bookTranslator;
	}
	public void setBookTranslator(String bookTranslator) {
		this.bookTranslator = bookTranslator;
	}
	
	@Override
	public String toString() {
		return "BookVO [bookId=" + bookId + ", bookCategoryId=" + bookCategoryId + ", bookStock=" + bookStock
				+ ", bookPrice=" + bookPrice + ", bookHits=" + bookHits + ", bookSellCount=" + bookSellCount
				+ ", bookName=" + bookName + ", bookWriter=" + bookWriter + ", bookPublisher=" + bookPublisher
				+ ", bookContents=" + bookContents + ", bookDetailImage=" + bookDetailImage + ", bookListImage="
				+ bookListImage + ", bookDate=" + bookDate + ", bookTranslator=" + bookTranslator + "]";
	}
	
	

}

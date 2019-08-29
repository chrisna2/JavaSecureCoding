package kr.co.bookking.util;

/**
 * 웹페이지 페이지네비게이션 처리를 위한 유틸리티
 * @author 김기정
 */
public class Pagination {

	/** 페이지에서 보여질 행의 수 */
	private int lineSize;
	/** 페이지에서 보여질 페이지 수 */
	private int pageSize;
	/** 사용자 요청 페이지 */
	private int requestPage;

	/** 검색된 행의 수 */
	private int totalRowCount;

	/** 검색된 행의 수에 따른 전체페이지 수 */
	private int totalPageCount;
	/** 목록 번호 */
	private int listNo;
	/** 현재 목록의 시작페이지 번호 */
	private int beginNoOfCurrentList;
	/** 현재 목록의 마지막페이지 번호 */
	private int endNoOfCurrentList;
	/** 이전 목록의 시작페이지 번호 */
	private int beginNoOfPreviousList;
	/** 다음 목록의 시작페이지 번호 */
	private int beginNoOfNextList;

	/**
	 * @param lineSize 페이지에서 보여질 행의 수
	 * @param pageSize 페이지에서 보여질 페이지 수
	 * @param totalRowCount 검색타입별 검색된 행의 수
	 * @param requestPage 사용자 요청 페이지
	 */
	public Pagination(int lineSize, int pageSize, int totalRowCount, int requestPage) {
		this.lineSize = lineSize;
		this.pageSize = pageSize;
		this.totalRowCount = totalRowCount;
		this.requestPage = requestPage;
	}

	public int getLineSize() {
		return lineSize;
	}

	public void setLineSize(int lineSize) {
		this.lineSize = lineSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRequestPage() {
		return requestPage;
	}

	public void setRequestPage(int requestPage) {
		this.requestPage = requestPage;
	}

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getListNo() {
		return listNo;
	}

	public void setListNo(int listNo) {
		this.listNo = listNo;
	}

	public int getBeginNoOfCurrentList() {
		return beginNoOfCurrentList;
	}

	public void setBeginNoOfCurrentList(int beginNoOfCurrentList) {
		this.beginNoOfCurrentList = beginNoOfCurrentList;
	}

	public int getEndNoOfCurrentList() {
		return endNoOfCurrentList;
	}

	public void setEndNoOfCurrentList(int endNoOfCurrentList) {
		this.endNoOfCurrentList = endNoOfCurrentList;
	}

	public int getBeginNoOfPreviousList() {
		return beginNoOfPreviousList;
	}

	public void setBeginNoOfPreviousList(int beginNoOfPreviousList) {
		this.beginNoOfPreviousList = beginNoOfPreviousList;
	}

	public int getBeginNoOfNextList() {
		return beginNoOfNextList;
	}

	public void setBeginNoOfNextList(int beginNoOfNextList) {
		this.beginNoOfNextList = beginNoOfNextList;
	}

	/** 페이지네이션 계산 */
	public void paginate(){
		// DB로부터 검색된 행의 수에 따른 전체페이지수 계산
		totalPageCount = (int)Math.ceil((double)totalRowCount / lineSize);

		// 목록별 번호
		listNo = (requestPage - 1) / pageSize;
		//(1~5): 0, (6~10): 1, (11~15): 2, .....

		// 현재 목록의 시작페이지번호와 마지막페이지번호 계산
		beginNoOfCurrentList = (listNo * pageSize) + 1;
		endNoOfCurrentList = (listNo * pageSize) + pageSize;

		/*
if (beginNoOfCurrentList > totalPageCount){
beginNoOfCurrentList = beginNoOfCurrentList - pageSize + 1;
}
		 */
		if (endNoOfCurrentList > totalPageCount){
			endNoOfCurrentList = totalPageCount;
		}

		// 이전 목록의 시작페이지 번호 계산
		beginNoOfPreviousList = beginNoOfCurrentList - pageSize;
		// 첫번째 목록인 경우 1페이지로 설정
		if (beginNoOfPreviousList < 0) { 
			beginNoOfPreviousList = 1;
		}

		// 다음 목록의 시작페이지 번호 계산
		beginNoOfNextList = beginNoOfCurrentList + pageSize;
	}

	/** 현재 목록에서 [처음으로] 출력 여부 반환 */
	public boolean isShowFirst() {
		return listNo > 0;
	}

	/** 현재 목록에서 [이전목록] 출력 여부 반환 */
	public boolean isShowPreviousList() {
		return listNo > 0;
	}

	/** 현재 목록에서 [이전페이지] 출력 여부 반환 */
	public boolean isShowPreviousPage() {
		return requestPage > 1;
	}

	/** 현재 목록에서 [다음페이지] 출력 여부 반환 */
	public boolean isShowNextPage() {
		return requestPage < totalPageCount;
	}

	/** 현재 목록에서 [다음목록] 출력 여부 반환 */
	public boolean isShowNextList() {
		return endNoOfCurrentList < totalPageCount;
	}

	/** 현재 목록에서 [끝으로] 출력 여부 반환 */
	public boolean isShowLast() {
		return endNoOfCurrentList < totalPageCount;
	}



	@Override
	public String toString() {
		return "Pagination [lineSize=" + lineSize + ", pageSize=" + pageSize + ", requestPage=" + requestPage
				+ ", totalRowCount=" + totalRowCount + ", totalPageCount=" + totalPageCount + ", listNo=" + listNo
				+ ", beginNoOfCurrentList=" + beginNoOfCurrentList + ", endNoOfCurrentList=" + endNoOfCurrentList
				+ ", beginNoOfPreviousList=" + beginNoOfPreviousList + ", beginNoOfNextList=" + beginNoOfNextList + "]";
	}

	/** 페이징 처리 목록을 HTML로 반환 */
	public String toHtml(String searchType, String searchValue){
		StringBuilder sb = new StringBuilder();
		String parameters ="";

		// 조건검색이 있는 경우 요청파라메터 추가
		if(searchType != null && searchType.trim().length() != 0){
			parameters = "&searchType=" + searchType + "&searchValue=" + searchValue;
		}

		// 처음으로 보여주기 여부
		if(isShowFirst()){
			sb.append("<li><a href=\"\">처음으로</a></li>");
		}

		// 이전목록 보여주기 여부
		if(isShowPreviousList()){
			sb.append("<li><a href=\"\">이전목록</a></li>");
		}

		// 이전 페이지 보여주기 여부
		if(isShowPreviousPage()){
			sb.append("<li><a href=\"\">이전페이지</a></li>");
		}

		// 페이지 번호 반복
		for(int i=beginNoOfCurrentList; i<=endNoOfCurrentList; i++){
			if(i == requestPage){
				sb.append("<li class=\"active\"><a href=\"\">" + i + "</a></li>");
			}else{
				sb.append("<li><a href=\"\">" + i + "</a></li>");
			}
		}

		// 다음 페이지 보여주기 여부
		if(isShowNextPage()){
			sb.append("<li><a href=\"\">다음페이지</a></li>");
		}

		// 다음 목록 보여주기 여부
		if(isShowNextList()){
			sb.append("<li><a href=\"\">다음목록</a></li>");
		}

		// 끝으로 보여주기 여부
		if(isShowLast()){
			sb.append("<li><a href=\"\">끝으로</a></li>");
		}
		return sb.toString();
	}


	/** 테스트을 위한 main */
	public static void main(String[] args) {
		/** 페이지에서 보여질 행의 수, 페이지 수 */
		Pagination pagination = new Pagination(10, 5, 56, 2);
		pagination.paginate();
		System.out.println("검색된 행수: " + pagination.getTotalRowCount());
		System.out.println("요청페이지: " + pagination.getRequestPage());
		System.out.println("전체페이지수: " + pagination.getTotalPageCount());
		System.out.println("현재목록의 시작페이지: " + pagination.beginNoOfCurrentList);
		System.out.println("현재목록의 끝페이지: " + pagination.endNoOfCurrentList);

		System.out.println(pagination.toHtml("", ""));//전체검색
		//System.out.println(pagination.toHtml("writer", "bangry"));//작성자로검색

	}
}

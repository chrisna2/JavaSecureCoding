package kr.co.bookking.VO;

public class OrderVO {
	int orderId, orderBookId, orderCellphone1, orderCellphone2, orderCellphone3, orderPrice, orderStock;
	int orderDiscount, orderDeliveryCharge, orderZip, orderTotalPrice;
	String orderUserId, orderDate, orderAddr1, orderAddr2, orderEmail, orderConsingee, orderMemo, orderPaymentKind;
	
	public OrderVO(){}

	public OrderVO(int orderId, int orderBookId, int orderCellphone1, int orderCellphone2, int orderCellphone3,
			int orderPrice, int orderStock, int orderDiscount, int orderDeliveryCharge, int orderZip,
			String orderUserId, String orderDate, String orderAddr1, String orderAddr2, String orderEmail,
			String orderConsingee, String orderMemo, String orderPaymentKind, int orderTotalPrice) {
		super();
		this.orderId = orderId;
		this.orderBookId = orderBookId;
		this.orderCellphone1 = orderCellphone1;
		this.orderCellphone2 = orderCellphone2;
		this.orderCellphone3 = orderCellphone3;
		this.orderPrice = orderPrice;
		this.orderStock = orderStock;
		this.orderDiscount = orderDiscount;
		this.orderDeliveryCharge = orderDeliveryCharge;
		this.orderZip = orderZip;
		this.orderUserId = orderUserId;
		this.orderDate = orderDate;
		this.orderAddr1 = orderAddr1;
		this.orderAddr2 = orderAddr2;
		this.orderEmail = orderEmail;
		this.orderConsingee = orderConsingee;
		this.orderMemo = orderMemo;
		this.orderPaymentKind = orderPaymentKind;
		this.orderTotalPrice = orderTotalPrice;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderBookId() {
		return orderBookId;
	}

	public void setOrderBookId(int orderBookId) {
		this.orderBookId = orderBookId;
	}

	public int getOrderCellphone1() {
		return orderCellphone1;
	}

	public void setOrderCellphone1(int orderCellphone1) {
		this.orderCellphone1 = orderCellphone1;
	}

	public int getOrderCellphone2() {
		return orderCellphone2;
	}

	public void setOrderCellphone2(int orderCellphone2) {
		this.orderCellphone2 = orderCellphone2;
	}

	public int getOrderCellphone3() {
		return orderCellphone3;
	}

	public void setOrderCellphone3(int orderCellphone3) {
		this.orderCellphone3 = orderCellphone3;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getOrderStock() {
		return orderStock;
	}

	public void setOrderStock(int orderStock) {
		this.orderStock = orderStock;
	}

	public int getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(int orderDiscount) {
		this.orderDiscount = orderDiscount;
	}

	public int getOrderDeliveryCharge() {
		return orderDeliveryCharge;
	}

	public void setOrderDeliveryCharge(int orderDeliveryCharge) {
		this.orderDeliveryCharge = orderDeliveryCharge;
	}

	public int getOrderZip() {
		return orderZip;
	}

	public void setOrderZip(int orderZip) {
		this.orderZip = orderZip;
	}

	public String getOrderUserId() {
		return orderUserId;
	}

	public void setOrderUserId(String orderUserId) {
		this.orderUserId = orderUserId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderAddr1() {
		return orderAddr1;
	}

	public void setOrderAddr1(String orderAddr1) {
		this.orderAddr1 = orderAddr1;
	}

	public String getOrderAddr2() {
		return orderAddr2;
	}

	public void setOrderAddr2(String orderAddr2) {
		this.orderAddr2 = orderAddr2;
	}

	public String getOrderEmail() {
		return orderEmail;
	}

	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}

	public String getOrderConsingee() {
		return orderConsingee;
	}

	public void setOrderConsingee(String orderConsingee) {
		this.orderConsingee = orderConsingee;
	}

	public String getOrderMemo() {
		return orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}

	public String getOrderPaymentKind() {
		return orderPaymentKind;
	}

	public void setOrderPaymentKind(String orderPaymentKind) {
		this.orderPaymentKind = orderPaymentKind;
	}

	public int getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(int orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	@Override
	public String toString() {
		return "OrderVO [orderId=" + orderId + ", orderBookId=" + orderBookId + ", orderCellphone1=" + orderCellphone1
				+ ", orderCellphone2=" + orderCellphone2 + ", orderCellphone3=" + orderCellphone3 + ", orderPrice="
				+ orderPrice + ", orderStock=" + orderStock + ", orderDiscount=" + orderDiscount
				+ ", orderDeliveryCharge=" + orderDeliveryCharge + ", orderZip=" + orderZip + ", orderTotalPrice="
				+ orderTotalPrice + ", orderUserId=" + orderUserId + ", orderDate=" + orderDate + ", orderAddr1="
				+ orderAddr1 + ", orderAddr2=" + orderAddr2 + ", orderEmail=" + orderEmail + ", orderConsingee="
				+ orderConsingee + ", orderMemo=" + orderMemo + ", orderPaymentKind=" + orderPaymentKind + "]";
	}

}

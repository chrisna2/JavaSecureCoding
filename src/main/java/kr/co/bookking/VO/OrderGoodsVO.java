package kr.co.bookking.VO;

public class OrderGoodsVO {
	int orderId, orderGoodsSeq, orderGoodsId, orderGoodsStock, orderGoodsPrice;
	
	public OrderGoodsVO(){}

	public OrderGoodsVO(int orderId, int orderGoodsSeq, int orderGoodsId, int orderGoodsStock, int orderGoodsPrice) {
		super();
		this.orderId = orderId;
		this.orderGoodsSeq = orderGoodsSeq;
		this.orderGoodsId = orderGoodsId;
		this.orderGoodsStock = orderGoodsStock;
		this.orderGoodsPrice = orderGoodsPrice;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderGoodsSeq() {
		return orderGoodsSeq;
	}

	public void setOrderGoodsSeq(int orderGoodsSeq) {
		this.orderGoodsSeq = orderGoodsSeq;
	}

	public int getOrderGoodsId() {
		return orderGoodsId;
	}

	public void setOrderGoodsId(int orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}

	public int getOrderGoodsStock() {
		return orderGoodsStock;
	}

	public void setOrderGoodsStock(int orderGoodsStock) {
		this.orderGoodsStock = orderGoodsStock;
	}

	public int getOrderGoodsPrice() {
		return orderGoodsPrice;
	}

	public void setOrderGoodsPrice(int orderGoodsPrice) {
		this.orderGoodsPrice = orderGoodsPrice;
	}

	@Override
	public String toString() {
		return "OrderGoodsVO [orderId=" + orderId + ", orderGoodsSeq=" + orderGoodsSeq + ", orderGoodsId="
				+ orderGoodsId + ", orderGoodsStock=" + orderGoodsStock + ", orderGoodsPrice=" + orderGoodsPrice + "]";
	}
	
}

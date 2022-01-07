package hello.core.order;

public class Order {

  private Long memberid;
  private String itemName;
  private int price;
  private int discountPrice;

  public Long getMemberid() {
	return memberid;
  }

  public void setMemberid(Long memberid) {
	this.memberid = memberid;
  }

  public String getItemName() {
	return itemName;
  }

  public void setItemName(String itemName) {
	this.itemName = itemName;
  }

  public int getPrice() {
	return price;
  }

  public void setPrice(int price) {
	this.price = price;
  }

  public int getDiscountPrice() {
	return discountPrice;
  }

  public void setDiscountPrice(int discountPrice) {
	this.discountPrice = discountPrice;
  }

  @Override
  public String toString() {
	return "Order{" +
		"memberid=" + memberid +
		", itemName='" + itemName + '\'' +
		", price=" + price +
		", discountPrice=" + discountPrice +
		'}';
  }

  public Order(Long memberid, String itemName, int price, int discountPrice) {
	this.memberid = memberid;
	this.itemName = itemName;
	this.price = price;
	this.discountPrice = discountPrice;
  }
  public int calculatePrice(){
	return price-discountPrice;
  }
}

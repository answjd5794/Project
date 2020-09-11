package bookStore;

public class OrdersVO {
   private String order_id;
   private String order_date;
   private int order_qty;
   private String mem_id;
   private String cart_id;

 
   public String getOrder_id() {
      return order_id;
   }
   public void setOrder_id(String order_id) {
      this.order_id = order_id;
   }
   public String getOrder_date() {
      return order_date;
   }
   public void setOrder_date(String order_date) {
      this.order_date = order_date;
   }
   public int getOrder_qty() {
      return order_qty;
   }
   public void setOrder_qty(int order_qty) {
      this.order_qty = order_qty;
   }
   public String getMem_id() {
      return mem_id;
   }
   public void setMem_id(String mem_id) {
      this.mem_id = mem_id;
   }
   public String getCart_id() {
      return cart_id;
   }
   public void setCart_id(String cart_id) {
      this.cart_id = cart_id;
   }
   }
   

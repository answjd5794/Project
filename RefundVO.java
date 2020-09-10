package bookStore;

public class RefundVO {
   // 반품
   private String refund_date;
   private String order_id;

   public String getOrder_id() {
   return order_id;
   }

   public void setOrder_id(String order_id) {
   this.order_id = order_id;
   }

   public String getRefund_date() {
      return refund_date;
   }

   public void setRefund_date(String refund_date) {
      this.refund_date = refund_date;
   }
   
   
}
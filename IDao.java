package bookStore;

import java.util.List;
import java.util.Map;


public interface  IDao {


   List<BooksVO> booKList();

   String managerlogIn(Map<String, String> params);

   int createBook(BooksVO bvo);

   List<MemberVO> memList();

   List<ReviewVO> reviewContent();

   int memDrop();

   int createMember(MemberVO mvo);

   int dupleId(String mem_id);

   List<BooksVO> showIT();

   
   
}
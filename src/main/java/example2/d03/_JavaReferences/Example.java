package example2.d03._JavaReferences;

public class Example {
    public static void main(String[] args) {
        // System: 클래스, System.out: 객체, print(): 함수
        // 클래스 설계 후 객체를 만들어서 사용
        System.out.println("출력");

        // 카테고리 2개 생성
        Category category1 = new Category();
        category1.setCno(1);
        category1.setCname("공지사항");
        Category category2 = new Category();
        category2.setCno(2);
        category2.setCname("자유게시판");

        // 게시물 생성, PK
        Board board1 = new Board();
        board1.setBno(1);
        board1.setBtitle("공지1");
        board1.setBcontent("공지내용1");
        board1.setCategory(category1); // 1번 게시물에 1번 공지사항 참조
        System.out.println(board1.getCategory().getCname());

        // 게시물 조회 (양방향)
        category1.getBoardList().add(board1);
        System.out.println(category1.getBoardList());

        Board board2 = new Board();
        board2.setBno(2);
        board2.setBtitle("공지1");
        board2.setBcontent("공지내용2");
        board2.setCategory(category1); // 단방향 참조
        category1.getBoardList().add(board2); // 양방향 참조 (카테고리 <-> 게시물 조회)
        System.out.println(category1.getBoardList());
        System.out.println(board2.getCategory());
    }
}

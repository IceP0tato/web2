use springweb2;

DROP TABLE IF EXISTS RENTALS;
DROP TABLE IF EXISTS BOOKS;

-- 1. 책 테이블
CREATE TABLE books (
	id INT NOT NULL AUTO_INCREMENT ,    -- 도서번호
	title VARCHAR(255) NOT NULL,        -- 도서 명
	stock INT NOT NULL DEFAULT 0,       -- 도서 재고
	PRIMARY KEY (id)
);

-- 2. 대출 기록 테이블
CREATE TABLE rentals (
	id INT NOT NULL AUTO_INCREMENT ,            -- 대출기록 번호
	book_id INT NOT NULL,                       -- **참조**하는 도서번호
	member VARCHAR(100) NOT NULL,               -- 대출 한 사람
	rent_date DATETIME DEFAULT NOW(),           -- 대출 일
	return_date DATETIME NULL, -- 반납 일
	PRIMARY KEY (id),
	FOREIGN KEY (book_id) REFERENCES books(id)
);

-- 3. 샘플 데이터 (책 목록)
INSERT INTO books (id, title, stock) VALUES (1, '자바의 정석', 3);
INSERT INTO books (id, title, stock) VALUES (2, '스프링 인 액션', 2);
INSERT INTO books (id, title, stock) VALUES (3, '토비의 스프링', 1);
INSERT INTO books (id, title, stock) VALUES (4, '리액트 교과서', 5);

-- 4. 샘플 데이터 (대출 기록)
INSERT INTO rentals (id, book_id, member) VALUES (1, 1, '홍길동');
INSERT INTO rentals (book_id, member) VALUES (2, '홍길동');

-- 5. 확인용 조회 쿼리
SELECT * FROM books;
SELECT * FROM rentals;
    
-- book 테이블에 price(int) 필드 추가
ALTER TABLE BOOKS ADD COLUMN price INT;
    
-- book 테이블에 title(longtext) 필드 수정
ALTER TABLE BOOKS MODIFY COLUMN title LONGTEXT;

select * from books where stock > (select avg(stock) from books);
select * from books where id = (select book_id from rentals group by book_id order by count(*) desc limit 1);

create or replace view rental_view as
            select books.id as bookid, title, stock, rentals.id as rentid, book_id, member, rent_date, return_date from rentals join books on books.id = rentals.book_id;
            
select * from rental_view;

create or replace view rental_stock_view as
            select * from books where stock > (select avg(stock) from books);
            
select * from rental_stock_view;
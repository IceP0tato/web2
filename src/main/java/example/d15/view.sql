USE SPRINGWEB2;

-- 1.기존 테이블 초기화
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS member;

-- 2.회원(member) 테이블 생성
CREATE TABLE member (
    mno INT AUTO_INCREMENT PRIMARY KEY,   -- 회원번호 (PK)
    name VARCHAR(50),                     -- 이름
    grade VARCHAR(20)                     -- 등급 (VIP, GOLD, SILVER)
);

-- 3. 주문(orders) 테이블 생성
CREATE TABLE orders (
    ono INT AUTO_INCREMENT PRIMARY KEY,   -- 주문번호 (PK)
    mno INT,                              -- 회원번호 (FK)
    product VARCHAR(50),                  -- 상품명
    price INT,                            -- 가격
    order_date DATE,                      -- 주문일자
    FOREIGN KEY (mno) REFERENCES member(mno)
);

-- 4. 샘플 데이터 삽입
INSERT INTO member (name, grade)
VALUES ('유재석', 'VIP'), ('강호동', 'GOLD'), ('신동엽', 'SILVER');

INSERT INTO orders (mno, product, price, order_date)
VALUES
(1, '노트북', 1500000, '2025-10-10'),
(1, '마우스', 30000, '2025-10-11'),
(2, '키보드', 50000, '2025-10-11'),
(3, '모니터', 200000, '2025-10-12');

# 뷰 (가상 테이블) : 하나 이상의 원본 테이블들을 기반으로 만들어진 가상 테이블
# 권한/보안, 복잡한 쿼리문(집계) 결과 저장 (재사용) 등
CREATE OR REPLACE VIEW ORDER_VIEW AS SELECT * FROM ORDERS;
# 목록 확인
SHOW FULL TABLES WHERE TABLE_TYPE = "VIEW";
# 뷰 수정
ALTER VIEW ORDER_VIEW AS SELECT PRODUCT, PRICE FROM ORDERS;
# 뷰 조회
SELECT * FROM ORDER_VIEW;
# 뷰 삭제
DROP VIEW IF EXISTS ORDER_VIEW;

# 주의 : 특별한 경우가 아니면 SELECT만 가능 (INSERT, UPDATE, DELETE는 조건 사용) -> 읽기 모드로 사용됨
# 수정 불가능한 뷰 : JOIN, GROUP BY, 함수, 계산식(집계/통계) 포함 시 읽기 모드만 가능
# 수정 가능한 뷰 : 단일 테이블 기반

# 조건 뷰 생성
CREATE OR REPLACE VIEW VIP_MEMBER AS
SELECT * FROM MEMBER WHERE GRADE = 'VIP';

SELECT * FROM VIP_MEMBER; -- 뷰 조회
SELECT * FROM MEMBER WHERE GRADE = 'VIP'; -- 원본 조회

# 뷰 데이터 수정
UPDATE VIP_MEMBER SET NAME = '유재석2';


# 조인 뷰 생성 (서로 다른 테이블 간의 연관 관계를 구조화하는 관계형 데이터베이스)
CREATE OR REPLACE VIEW VIEW_ORDER_SUMMARY AS
SELECT O.MNO, M.NAME FROM MEMBER M INNER JOIN ORDERS O ON M.MNO = O.MNO;

SELECT * FROM VIEW_ORDER_SUMMARY;
UPDATE VIEW_ORDER_SUMMARY SET NAME = '유재석3';

# 뷰 데이터 수정 조건 : 단일 테이블의 원본 필드는 가능, 집계/통계/그룹/계산 등은 불가능
CREATE OR REPLACE VIEW VIP_MEMBER2 AS SELECT *, 10+10 AS COUNT FROM MEMBER WHERE GRADE = 'VIP';
SELECT * FROM VIP_MEMBER2;
UPDATE VIP_MEMBER2 SET COUNT = 30; -- X
UPDATE VIP_MEMBER2 SET NAME = '유재석4'; -- O

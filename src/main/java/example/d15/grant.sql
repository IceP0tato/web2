# DCL (계정 사용 권한 관리 등)
# 데이터베이스 설치 시 root 계정 존재

# 계정 생성
CREATE USER 'dev1'@'localhost' IDENTIFIED BY '1234'; -- 로컬 전용
CREATE USER 'dev2'@'%' IDENTIFIED BY 'abcd'; -- 외부 접속 모두 허용
# 계정 접속 (홈 메뉴 -> Connect to database -> username, password)
# 계정 권한 부여
GRANT ALL PRIVILEGES ON springweb2.* TO 'dev1'@'localhost';
GRANT SELECT ON springweb2.student TO 'dev2'@'%';
SHOW GRANTS FOR 'dev1'@'localhost';
SHOW GRANTS FOR 'dev2'@'%';
# 계정 권한 회수
REVOKE SELECT ON springweb2.student FROM 'dev2'@'%';
# 계정 비밀번호 변경
ALTER USER 'dev2'@'%' IDENTIFIED BY '1234';
# 계정 삭제
DROP USER 'dev2'@'%';

CREATE USER 'dev3'@'localhost' IDENTIFIED BY '1234';
CREATE OR REPLACE VIEW STUDENT_VIEW AS SELECT * FROM STUDENT;
# 뷰 조회 권한 부여
GRANT SELECT ON springweb2.STUDENT_VIEW TO 'dev3'@'localhost';

# 데이터베이스 내 모든 계정 확인
SELECT * FROM MYSQL.USER;
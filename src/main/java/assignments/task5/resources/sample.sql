-- --------------------------------------- Task5 ----------------------------------------
DROP DATABASE IF EXISTS springweb2;
CREATE DATABASE springweb2;
USE springweb2;

CREATE TABLE member (
	mno int auto_increment,
    constraint primary key(mno),
    name varchar(20) not null,
    phone varchar(13) not null,
    age int not null
);

INSERT INTO member (name, phone, age) VALUES
('김민수', '010-1234-5678', 28),
('이영희', '010-2345-6789', 32),
('박준형', '010-3456-7890', 25),
('최지은', '010-4567-8901', 30),
('정우성', '010-5678-9012', 35),
('한예슬', '010-6789-0123', 27),
('오세훈', '010-7890-1234', 40),
('장나라', '010-8901-2345', 29),
('류준열', '010-9012-3456', 33),
('신세경', '010-0123-4567', 26);

select * from member;
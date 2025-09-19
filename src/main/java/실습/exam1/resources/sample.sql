drop database if exists springweb2;
create database springweb2;
use springweb2;

-- 영화 table --
create table movie (
	mNo int auto_increment,
    constraint primary key(mNo),
    mName varchar(30) not null,
    mDirector varchar(20) not null,
    mGenre varchar(10) not null,
    mRating int not null default 10,
    mDesc longtext not null,
    mPwd varchar(20) not null
);

INSERT INTO movie (mName, mDirector, mGenre, mRating, mDesc, mPwd) VALUES
('기생충', '봉준호', '스릴러', 9, '가난한 가족이 부잣집에 침투하면서 벌어지는 이야기', 'semi123'),
('올드보이', '박찬욱', '미스터리', 9, '15년간 감금된 남자가 복수를 시작한다', 'revenge'),
('태극기 휘날리며', '강제규', '전쟁', 8, '한국전쟁 속 형제의 비극적인 이야기', 'brother'),
('부산행', '연상호', '공포', 8, '좀비 바이러스가 퍼진 열차에서의 생존기', 'ktxzombie'),
('명량', '김한민', '역사', 7, '이순신 장군의 명량해전 이야기', 'navy1597'),
('극한직업', '이병헌', '코미디', 8, '치킨집으로 위장한 형사들의 마약 수사극', 'friedchicken'),
('암살', '최동훈', '액션', 8, '일제강점기 저격 작전과 독립운동가들의 이야기', 'sniper1930'),
('친절한 금자씨', '박찬욱', '드라마', 8, '복수를 위해 13년을 기다린 여자의 이야기', 'ladyvengeance'),
('국제시장', '윤제균', '가족', 7, '한 남자의 인생을 통해 본 한국 현대사', 'marketlife'),
('신과함께', '김용화', '판타지', 7, '사후 세계에서 벌어지는 저승사자의 재판', 'afterlife'),
('도둑들', '최동훈', '범죄', 8, '보석을 훔치기 위한 국제 도둑들의 협력과 배신', 'diamondjob'),
('해운대', '윤제균', '재난', 7, '부산에 닥친 대지진과 쓰나미 이야기', 'tsunami'),
('7번방의 선물', '이환경', '감동', 9, '지적장애인 아버지와 딸의 감동적인 이야기', 'love7room'),
('마더', '봉준호', '스릴러', 8, '아들의 누명을 벗기기 위한 엄마의 집념', 'mothertruth'),
('실미도', '강우석', '전쟁', 7, '북파 공작원들의 비극적인 실화', 'silmi684'),
('건축학개론', '이용주', '로맨스', 8, '첫사랑의 기억을 되살리는 건축 프로젝트', 'firstlove'),
('범죄도시', '강윤성', '액션', 9, '강력계 형사와 조폭의 대결', 'mafiahunt'),
('내부자들', '우민호', '정치', 8, '권력과 언론의 부패를 파헤치는 이야기', 'insider'),
('말아톤', '정윤철', '감동', 9, '자폐증을 가진 청년의 마라톤 도전기', 'runhero'),
('비열한 거리', '유하', '범죄', 8, '조직폭력배의 삶과 갈등을 그린 영화', 'darkstreet');

-- 토론글 table --
create table article (
	aNo int auto_increment,
    constraint primary key(aNo),
    aTitle varchar(30) not null,
    aDesc longtext not null,
    aPwd varchar(20) not null,
    mNo int not null,
    constraint foreign key(mNo) references movie(mNo)
);

INSERT INTO article (aTitle, aDesc, aPwd, mNo) VALUES
('기생충의 사회적 메시지', '빈부격차와 계급 구조를 날카롭게 드러낸 영화입니다.', 'msg001', 1),
('반지하의 상징성', '반지하 공간은 사회적 위치를 상징하는 중요한 요소입니다.', 'symbol002', 1),
('기생충의 촬영 기법', '카메라 앵글과 조명이 영화의 분위기를 극대화합니다.', 'tech003', 1),
('기생충과 해외 반응', '아카데미 수상 이후 세계적으로 큰 반향을 일으켰습니다.', 'global004', 1),
('기생충 속 가족의 의미', '가족 간의 유대와 갈등이 영화의 핵심 주제입니다.', 'family005', 1);

select * from movie;
select * from article;

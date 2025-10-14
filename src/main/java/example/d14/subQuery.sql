use springweb2;

select * from student;
select avg((kor+math)/2) avgScore from student;

# 중첩 select (서브쿼리)

# 평균보다 높은 점수의 학생명
select name from student
	where (kor+math)/2 > (
    select avg((kor+math)/2) avgScore from student
);

# 국어 점수가 평균 이상인 학생 조회 (서브쿼리를 우선 작성하는 것이 권장됨)
# 1. 국어 점수의 평균
select avg(kor) from student;
# 2. 국어 점수가 평균 이상인 점수
select kor from student where kor >= (select avg(kor) from student);
# 3. 국어 점수가 평균 이상인 점수를 가진 학생 조회
select name from student where kor in (
	select kor from student where kor >= (select avg(kor) from student)
);

# 각 학생들과 총점 비교
select s1.name, (
	select count(*) from student s2
    where (s2.kor+s2.math) > (s1.kor+s1.math)
) from student s1;

# 평균점수 정렬
select name, average from 
	(select name, (kor+math)/2 as average from student) as avgTable
    order by average desc;
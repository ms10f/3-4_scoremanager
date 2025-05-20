drop table class_num if exists;
drop table school if exists;
drop table student if exists;
drop table subject if exists;
drop table TEACHER if exists;
drop table test if exists;


create table school(
	cd char(3) not null,
	name varchar(20) default null,
	primary key(cd)
);

create table class_num(
	school_cd char(3) not null,
	class_num varchar(5) not null,
	primary key(school_cd, class_num),
	foreign key(school_cd) references school(cd)
);

create table student(
	school_cd char(3) not null,
	no varchar(10) not null,
	name varchar(30) default null,
	ent_year int default null
		check(ent_year < 10000),
	class_num char(3) default null,
	is_attend boolean default null,
	primary key(school_cd, no),
	foreign key(class_num) references class_num(class_num),
	foreign key(school_cd) references school(cd)
);

create table subject(
	school_cd char(3) not null,
	cd char(3) not null,
	name varchar(20) default null,
	primary key(school_cd, cd),
	foreign key(school_cd) references school(cd),
);

create table teacher(
	id varchar(10) not null,
	password varchar(30) default null,
	name varchar(10) default null,
	school_cd char(3) default null,
	primary key(id),
	foreign key(school_cd) references school(cd)
);

create table test(
	school_cd char(10) not null,
	student_cd varchar(10) not null,
	subject_cd char(3) not null,
	no int not null,
	point int default null
		check(-1 <= point and point <= 100),
	primary key(school_cd, student_cd, subject_cd, no),
	foreign key(school_cd) references school(cd),
	foreign key(student_cd) references student(no),
	foreign key(subject_cd) references subject(cd)
);


INSERT INTO SCHOOL(CD, NAME) VALUES
	('tes', 'テスト校');

INSERT INTO CLASS_NUM(SCHOOL_CD, CLASS_NUM) VALUES
	('tes', '101'),
	('tes', '102'),
	('tes', '201'),
	('tes', '202');


INSERT INTO TEACHER(ID, PASSWORD, NAME, SCHOOL_CD) VALUES
	('admin1', 'password', '管理者1', 'tes');


INSERT INTO STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD)
VALUES 
	(124, 'bbb', 2024, 102, FALSE, 'tes'),
	(125, 'ccc', 2025, 201, TRUE, 'tes'),
	(126, 'ddd', 2025, 202, FALSE, 'tes'),
	(127, '大原太郎', 2025, 202, FALSE, 'tes'),
	(128, 'eeee', 2023, 101, TRUE, 'tes'),
	(129, 'ffff', 2023, 102, TRUE, 'tes'),
	(130, 'gggg', 2024, 201, FALSE, 'tes'),
	(131, 'hhhh', 2024, 202, TRUE, 'tes'),
	(132, 'iiii', 2025, 101, FALSE, 'tes'),
	(133, 'jjjj', 2025, 102, TRUE, 'tes'),
	(134, 'kkkk', 2023, 201, FALSE, 'tes'),
	(135, 'llll', 2023, 202, TRUE, 'tes'),
	(136, 'mmmm', 2024, 101, FALSE, 'tes'),
	(137, 'nnnn', 2024, 102, TRUE, 'tes'),
	(138, 'oooo', 2025, 201, FALSE, 'tes'),
	(139, 'pppp', 2025, 202, TRUE, 'tes'),
	(140, 'qqqq', 2023, 101, FALSE, 'tes'),
	(141, 'rrrr', 2023, 102, TRUE, 'tes'),
	(142, 'ssss', 2024, 201, TRUE, 'tes'),
	(143, 'tttt', 2024, 202, FALSE, 'tes');
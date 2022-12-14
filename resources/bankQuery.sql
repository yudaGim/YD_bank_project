DROP TABLE Transaction;
DROP TABLE Transaction_class;
DROP TABLE Account;
DROP TABLE ACCOUNT_STATE;
DROP TABLE Customer;
DROP TABLE GRADE;

DROP SEQUENCE ACCOUNT_ID_SEQ;
DROP SEQUENCE TRANSACTION_ID_SEQ;

-- 등급
CREATE TABLE GRADE (
    GRADE_ID    NUMBER  PRIMARY KEY,
    GRADE_NAME  VARCHAR(15) NOT NULL,
    GRADE_MINIMUM   NUMBER  NOT NULL,
    GRADE_MAXIMUM   NUMBER  NOT NULL,
    CHARGE   NUMBER  NOT NULL
);

INSERT INTO GRADE VALUES(1, 'Admin', -1, -1, 0);
INSERT INTO GRADE VALUES(2, 'General', 0, 11999999, 1000);
INSERT INTO GRADE VALUES(3, 'Family', 12000000, 11999999, 500);
INSERT INTO GRADE VALUES(4, 'Royal', 20000000, 39999999, 300);
INSERT INTO GRADE VALUES(5, 'Honor', 40000000, 69999999, 100);
INSERT INTO GRADE VALUES(6, 'Prestige', 70000000, 9999999999, 0);

-- 고객
CREATE TABLE Customer (
	ID	VARCHAR2(30)	PRIMARY KEY,
	password	VARCHAR2(30)	NOT NULL,
	name	VARCHAR2(15)	NOT NULL,
    TEL VARCHAR2(11)    NOT NULL,
    GRADE_ID    NUMBER  REFERENCES GRADE(GRADE_ID)	NOT NULL,
	Signup_Date	DATE	DEFAULT SYSDATE	NOT NULL,
	UPDATE_Date	DATE	DEFAULT SYSDATE	NOT NULL,
    TOTAL_BALANCE   NUMBER  DEFAULT 0   NOT NULL
);

INSERT INTO CUSTOMER VALUES('admin', 'admin', '관리자', '00000000000', 1, sysdate, sysdate, 0);
INSERT INTO CUSTOMER VALUES('test', 'test', '테스트', '00000000000', 1, sysdate, sysdate, 0);
INSERT INTO CUSTOMER VALUES('tset', 'tset', '테스트', '00000000000', 1, sysdate, sysdate, 10000);
INSERT INTO CUSTOMER VALUES('yuda', '1234', '김유다', '01012345678', 6, sysdate, sysdate, 10000000000);
INSERT INTO CUSTOMER VALUES('hyun', '1224', '김유현', '01012245668',6, sysdate, sysdate, 99999999999);
INSERT INTO CUSTOMER VALUES('eunt', '7777', '고은태', '01077777777',6, sysdate, sysdate, 77777777777);
INSERT INTO CUSTOMER VALUES('mina', '0000', '박민아', '01000009999', 5, sysdate, sysdate, 50000000);
INSERT INTO CUSTOMER VALUES('aeri', '8888', '정애리', '01088888888', 4, sysdate, sysdate, 30000000);

CREATE TABLE ACCOUNT_STATE (
    STATE_ID    NUMBER  PRIMARY KEY,
    STATE_NAME  VARCHAR2(30)    NOT NULL
);

INSERT INTO ACCOUNT_STATE VALUES(1, '사용 가능');
INSERT INTO ACCOUNT_STATE VALUES(2, '해지');

-- 계좌
CREATE TABLE Account (
	account_id	NUMBER	PRIMARY KEY,
	ID	VARCHAR2(30)	REFERENCES  Customer(id)	NOT NULL,
    STATE_ID    NUMBER  REFERENCES  ACCOUNT_STATE(STATE_ID) NOT NULL,
	balance	NUMBER  DEFAULT 0	NOT NULL,
	open_Date	DATE	DEFAULT SYSDATE	NOT NULL,
    UPDATE_DATE DATE    DEFAULT SYSDATE NOT NULL
);

CREATE SEQUENCE ACCOUNT_ID_SEQ MINVALUE 10000 MAXVALUE 99999;

INSERT INTO ACCOUNT VALUES(ACCOUNT_ID_SEQ.NEXTVAL, 'yuda', 1, 5000000000, sysdate, sysdate);
INSERT INTO ACCOUNT VALUES(ACCOUNT_ID_SEQ.NEXTVAL, 'hyun', 1, 33333333333, sysdate, sysdate);
INSERT INTO ACCOUNT VALUES(ACCOUNT_ID_SEQ.NEXTVAL, 'eunt', 1, 77777777777, sysdate, sysdate);
INSERT INTO ACCOUNT VALUES(ACCOUNT_ID_SEQ.NEXTVAL, 'mina', 1, 50000000, sysdate, sysdate);
INSERT INTO ACCOUNT VALUES(ACCOUNT_ID_SEQ.NEXTVAL, 'hyun', 1, 33333333333, sysdate, sysdate);
INSERT INTO ACCOUNT VALUES(ACCOUNT_ID_SEQ.NEXTVAL, 'aeri', 1, 30000000, sysdate, sysdate);
INSERT INTO ACCOUNT VALUES(ACCOUNT_ID_SEQ.NEXTVAL, 'yuda', 1, 5000000000, sysdate, sysdate);
INSERT INTO ACCOUNT VALUES(ACCOUNT_ID_SEQ.NEXTVAL, 'hyun', 1, 33333333333, sysdate, sysdate);
INSERT INTO ACCOUNT VALUES(ACCOUNT_ID_SEQ.NEXTVAL, 'test', 1, 0, sysdate, sysdate);
INSERT INTO ACCOUNT VALUES(ACCOUNT_ID_SEQ.NEXTVAL, 'tset', 1, 0, sysdate, sysdate);

-- 거래 구분
CREATE TABLE Transaction_class (
	transaction_class_id	NUMBER	PRIMARY KEY,
	transaction_class_name	VARCHAR2(30)	NOT NULL
);

INSERT INTO TRANSACTION_CLASS VALUES(1, '입금');
INSERT INTO TRANSACTION_CLASS VALUES(2, '출금');
INSERT INTO TRANSACTION_CLASS VALUES(3, '계좌이체');

-- 거래
CREATE TABLE Transaction (
	transaction_id	NUMBER	PRIMARY KEY,
	id	VARCHAR2(30)	REFERENCES customer(id)	NOT NULL,
	deposit_account_id	NUMBER	REFERENCES Account(account_id),
	withdraw_account_id	NUMBER	REFERENCES Account(account_id),
	transaction_class_id	NUMBER	REFERENCES Transaction_class(transaction_class_id)	NOT NULL,
	transaction_amount	NUMBER	NOT NULL,
	transaction_Date	DATE	DEFAULT SYSDATE	NOT NULL
);

CREATE SEQUENCE TRANSACTION_ID_SEQ;

insert into transaction values(transaction_id_seq.nextval, 입금, 출금, 분류, 금액, sysdate);

select * from transaction order by transaction_date desc;

COMMIT;

select * from grade where grade_id = 1;

select * from account where id = 'yuda' and state_id = 1 order by open_date;


select id from account where account_id=10000;

select * from customer where grade_id = 6 order by total_balance desc;
update customer
set grade_id = (select grade_id from grade where 0 between GRADE_MINIMUM and GRADE_MAXIMUM) where id = 'test';
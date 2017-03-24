--------------------------------------------------------
--  File created - Friday-March-24-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence HF_CAT_PK_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HF_CAT_PK_SEQ"  MINVALUE 1 MAXVALUE 99999999999999 INCREMENT BY 1 START WITH 94 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
--------------------------------------------------------
--  DDL for Sequence HF_CAT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HF_CAT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 121 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
--------------------------------------------------------
--  DDL for Sequence HF_PLAYLIST_PK_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HF_PLAYLIST_PK_SEQ"  MINVALUE 1 MAXVALUE 99999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
--------------------------------------------------------
--  DDL for Sequence HF_PLAYLIST_POS_PK_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HF_PLAYLIST_POS_PK_SEQ"  MINVALUE 1 MAXVALUE 99999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
--------------------------------------------------------
--  DDL for Sequence HF_PLAYLIST_POS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HF_PLAYLIST_POS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 53 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
--------------------------------------------------------
--  DDL for Sequence HF_PLAYLIST_RAT_PK_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HF_PLAYLIST_RAT_PK_SEQ"  MINVALUE 1 MAXVALUE 99999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
--------------------------------------------------------
--  DDL for Sequence HF_PLAYLIST_RAT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HF_PLAYLIST_RAT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
--------------------------------------------------------
--  DDL for Sequence HF_PLAYLIST_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HF_PLAYLIST_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
--------------------------------------------------------
--  DDL for Sequence HF_USER_PK_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HF_USER_PK_SEQ"  MINVALUE 1 MAXVALUE 99999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
--------------------------------------------------------
--  DDL for Sequence HF_USER_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HF_USER_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 242337 START WITH 5089117 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
--------------------------------------------------------
--  DDL for Sequence PS_TXN_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PS_TXN_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 50 START WITH 2051 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
--------------------------------------------------------
--  DDL for Table HF_CAT
--------------------------------------------------------

  CREATE TABLE "HF_CAT" 
   (	"INT_KEY" NUMBER(11,0), 
	"HF_USER_IK" NUMBER(11,0), 
	"CAT_NAME" VARCHAR2(255 CHAR), 
	"PARENT_HF_CAT_IK" NUMBER(11,0), 
	"CAT_DESC" VARCHAR2(2000 CHAR), 
	"INT_SEQ" NUMBER(10,0), 
	"LAST_CHANGE" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table HF_PLAYLIST
--------------------------------------------------------

  CREATE TABLE "HF_PLAYLIST" 
   (	"INT_KEY" NUMBER(11,0), 
	"PLAYLIST_NAME" VARCHAR2(255 CHAR), 
	"HF_CAT_IK" NUMBER(11,0), 
	"HF_USER_IK" NUMBER(11,0), 
	"DESCRIPTION" VARCHAR2(2000 CHAR), 
	"INT_SEQ" NUMBER(10,0), 
	"LAST_CHANGE" DATE, 
	"SHARE_FLAG" VARCHAR2(1 CHAR)
   ) ;
--------------------------------------------------------
--  DDL for Table HF_PLAYLIST_POS
--------------------------------------------------------

  CREATE TABLE "HF_PLAYLIST_POS" 
   (	"INT_KEY" NUMBER(11,0), 
	"HF_PLAYLIST_IK" NUMBER(11,0), 
	"ORDER_NUM" NUMBER(3,0), 
	"INT_SEQ" NUMBER(10,0), 
	"LAST_CHANGE" DATE, 
	"TRACK_NAME" VARCHAR2(255 CHAR), 
	"ALBUM" VARCHAR2(255 CHAR), 
	"ARTIST" VARCHAR2(255 CHAR), 
	"ARTIST_ID" VARCHAR2(255 CHAR), 
	"ALBUM_ID" VARCHAR2(255 CHAR), 
	"TRACK_ID" VARCHAR2(255 CHAR), 
	"GENRE" VARCHAR2(255 CHAR), 
	"IMAGE_URL" VARCHAR2(255 CHAR)
   ) ;
--------------------------------------------------------
--  DDL for Table HF_PLAYLIST_RAT
--------------------------------------------------------

  CREATE TABLE "HF_PLAYLIST_RAT" 
   (	"INT_KEY" NUMBER(11,0), 
	"RATING" NUMBER(1,0), 
	"DESCRIPTION" VARCHAR2(2000 CHAR), 
	"HF_PLAYLIST_IK" NUMBER(11,0), 
	"RATER_NAME" VARCHAR2(255 CHAR), 
	"INT_SEQ" NUMBER(10,0), 
	"LAST_CHANGE" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table HF_USER
--------------------------------------------------------

  CREATE TABLE "HF_USER" 
   (	"INT_KEY" NUMBER(11,0), 
	"USERNAME" VARCHAR2(255 CHAR), 
	"PASSWORD" VARCHAR2(255 CHAR), 
	"LONGNAME" VARCHAR2(255 CHAR), 
	"INT_SEQ" NUMBER(10,0), 
	"LAST_CHANGE" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table PS_TXN
--------------------------------------------------------

  CREATE TABLE "PS_TXN" 
   (	"ID" NUMBER(20,0), 
	"PARENTID" NUMBER(20,0), 
	"COLLID" NUMBER(10,0), 
	"CONTENT" BLOB, 
	"CREATION_DATE" DATE DEFAULT sysdate
   ) ;
REM INSERTING into HF_CAT
SET DEFINE OFF;
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values ('30','2','Gelb',null,'Das gelbe vom Ei Rock and Roll und blau','84',to_date('17.03.17','DD.MM.RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values ('35','2','Kunterbunt',null,'Alle Farben alle Größen','103',to_date('22.03.17','DD.MM.RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values ('39','2','test',null,'test','0',to_date('05.03.17','DD.MM.RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values ('41','2','test2',null,'test 2','0',to_date('05.03.17','DD.MM.RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values ('25','2','Blau',null,'Alles ist Blau pop','101',to_date('22.03.17','DD.MM.RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values ('26','2','Grün',null,'Grün Grün welt funk','102',to_date('22.03.17','DD.MM.RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values ('27','2','dddd',null,'dummy d','82',to_date('17.03.17','DD.MM.RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values ('42','3','Hund',null,'Hund springt','85',to_date('17.03.17','DD.MM.RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values ('43','3','Katze',null,'Katze mau mau','86',to_date('17.03.17','DD.MM.RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values ('44','3','Maus',null,'Mausi ist lecker','87',to_date('17.03.17','DD.MM.RR'));
REM INSERTING into HF_PLAYLIST
SET DEFINE OFF;
Insert into HF_PLAYLIST (INT_KEY,PLAYLIST_NAME,HF_CAT_IK,HF_USER_IK,DESCRIPTION,INT_SEQ,LAST_CHANGE,SHARE_FLAG) values ('1','Unterwegs','35','2','Alles für unterwegs in rosa','21',to_date('17.03.17','DD.MM.RR'),'Y');
Insert into HF_PLAYLIST (INT_KEY,PLAYLIST_NAME,HF_CAT_IK,HF_USER_IK,DESCRIPTION,INT_SEQ,LAST_CHANGE,SHARE_FLAG) values ('2','GoGoGo','26','2','Schnelles für zwischendurch ice dzug','0',to_date('10.03.17','DD.MM.RR'),'N');
Insert into HF_PLAYLIST (INT_KEY,PLAYLIST_NAME,HF_CAT_IK,HF_USER_IK,DESCRIPTION,INT_SEQ,LAST_CHANGE,SHARE_FLAG) values ('3','ICE','41','2','ICE und laut muss es sein','0',to_date('10.03.17','DD.MM.RR'),'Y');
Insert into HF_PLAYLIST (INT_KEY,PLAYLIST_NAME,HF_CAT_IK,HF_USER_IK,DESCRIPTION,INT_SEQ,LAST_CHANGE,SHARE_FLAG) values ('4','Mausi','25','2','Musi Maus mau mau mau','0',to_date('10.03.17','DD.MM.RR'),'N');
Insert into HF_PLAYLIST (INT_KEY,PLAYLIST_NAME,HF_CAT_IK,HF_USER_IK,DESCRIPTION,INT_SEQ,LAST_CHANGE,SHARE_FLAG) values ('21','Futter','43','3','Futter für die Katz','22',to_date('17.03.17','DD.MM.RR'),'Y');
Insert into HF_PLAYLIST (INT_KEY,PLAYLIST_NAME,HF_CAT_IK,HF_USER_IK,DESCRIPTION,INT_SEQ,LAST_CHANGE,SHARE_FLAG) values ('22','Kamel','42','3','Ein Kamel hat zwei Höcker','23',to_date('17.03.17','DD.MM.RR'),'N');
REM INSERTING into HF_PLAYLIST_POS
SET DEFINE OFF;
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('76','2','10','35',to_date('23.03.17','DD.MM.RR'),'Bless Those Tired Eyes','Rest','Clem Leek',null,'0zR6lyMZuofipT1Mh4Glrn','1',null,'https://i.scdn.co/image/861d6b4ad81d2db5de2b6cd27681667269613280');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('77','2','20','36',to_date('23.03.17','DD.MM.RR'),'Ain''t No Rest for the Wicked','Cage The Elephant','Cage The Elephant',null,'2vs5faN4o8NCNXAGLh3HJf','3',null,'https://i.scdn.co/image/941149af63ec6888e1f0aef69fdd73a50f441407');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('78','2','30','37',to_date('23.03.17','DD.MM.RR'),'The Longest Rest','Morning Dew','Alan Ellis',null,'0gPXYxrGPArkcbUDWUkYYi','7',null,'https://i.scdn.co/image/7755b520b96b42f83929c3dfb55e251a27a197e4');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('74','2','10','33',to_date('23.03.17','DD.MM.RR'),'Do Not Disturb','More Life','Drake',null,'1lXY618HWkwYKJWBRYR4MK','22',null,'folgt');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('75','2','10','34',to_date('23.03.17','DD.MM.RR'),'What Do I Know?','÷ (Deluxe)','Ed Sheeran',null,'3T4tUhGYeRNVUGevb0wThu','10',null,'folgt');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('62','3','10','21',to_date('22.03.17','DD.MM.RR'),'Ice Melts','More Life','Drake; Young Thug',null,'1lXY618HWkwYKJWBRYR4MK','21',null,'folgt');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('63','1','10','22',to_date('22.03.17','DD.MM.RR'),'Sorry','Purpose (Deluxe)','Justin Bieber',null,'6Fr2rQkZ383FcMqFyT7yPr','4',null,'folgt');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('64','1','10','23',to_date('22.03.17','DD.MM.RR'),'Ice Melts','More Life','Drake; Young Thug',null,'1lXY618HWkwYKJWBRYR4MK','21',null,'folgt');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('65','1','10','24',to_date('22.03.17','DD.MM.RR'),'Talking To The Moon','Doo-Wops & Hooligans','Bruno Mars',null,'1uyf3l2d4XYwiEqAb7t7fX','7',null,'folgt');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('66','1','20','25',to_date('22.03.17','DD.MM.RR'),'Anna Sun','Walk The Moon','WALK THE MOON',null,'1QhonXpNQq8wrGEKX0ofbk','4',null,'folgt');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('67','1','10','26',to_date('22.03.17','DD.MM.RR'),'Another Brick in the Wall, Pt. 2','The Wall','Pink Floyd',null,'5Dbax7G8SWrP9xyzkOvy2F','5',null,'folgt');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('68','1','20','27',to_date('22.03.17','DD.MM.RR'),'Don''t Stop ''Til You Get Enough - Single Version','Off the Wall','Michael Jackson',null,'2ZytN2cY4Zjrr9ukb2rqTP','1',null,'folgt');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('69','1','30','28',to_date('22.03.17','DD.MM.RR'),'Another Brick in the Wall, Pt. 1','The Wall','Pink Floyd',null,'5Dbax7G8SWrP9xyzkOvy2F','3',null,'folgt');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('70','1','40','29',to_date('22.03.17','DD.MM.RR'),'Solid Wall of Sound','We got it from Here... Thank You 4 Your service','A Tribe Called Quest',null,'3WvQpufOsPzkZvcSuynCf3','4',null,'folgt');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('71','3','10','30',to_date('22.03.17','DD.MM.RR'),'Everything Will Be OK','When It''s Dark Out','G-Eazy; Kehlani',null,'09Q3WwGYsQe5ognkvVkmCu','15',null,'folgt');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('72','3','10','31',to_date('22.03.17','DD.MM.RR'),'Rumor Has It','Top 10','Clay Walker',null,'7eGKyGwULtBx4KPS7yiJv9','3',null,'folgt');
Insert into HF_PLAYLIST_POS (INT_KEY,HF_PLAYLIST_IK,ORDER_NUM,INT_SEQ,LAST_CHANGE,TRACK_NAME,ALBUM,ARTIST,ARTIST_ID,ALBUM_ID,TRACK_ID,GENRE,IMAGE_URL) values ('73','3','10','32',to_date('22.03.17','DD.MM.RR'),'Yesterday - Remastered','Help! (Remastered)','The Beatles',null,'0PT5m6hwPRrpBwIHVnvbFX','13',null,'folgt');
REM INSERTING into HF_PLAYLIST_RAT
SET DEFINE OFF;
REM INSERTING into HF_USER
SET DEFINE OFF;
Insert into HF_USER (INT_KEY,USERNAME,PASSWORD,LONGNAME,INT_SEQ,LAST_CHANGE) values ('2','Timo','444','Timo Hahn','727051',to_date('17.03.17','DD.MM.RR'));
Insert into HF_USER (INT_KEY,USERNAME,PASSWORD,LONGNAME,INT_SEQ,LAST_CHANGE) values ('3','Test1','1111','Tester 1','0',to_date('24.02.17','DD.MM.RR'));
REM INSERTING into PS_TXN
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index HF_USER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HF_USER_PK" ON "HF_USER" ("INT_KEY") 
  ;
--------------------------------------------------------
--  DDL for Index HF_PLAYLIST_POS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HF_PLAYLIST_POS_PK" ON "HF_PLAYLIST_POS" ("INT_KEY") 
  ;
--------------------------------------------------------
--  DDL for Index HF_USER_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "HF_USER_UK1" ON "HF_USER" ("USERNAME") 
  ;
--------------------------------------------------------
--  DDL for Index PS_TXN_IDX
--------------------------------------------------------

  CREATE INDEX "PS_TXN_IDX" ON "PS_TXN" ("COLLID", "ID") REVERSE 
  ;
--------------------------------------------------------
--  DDL for Index HF_PLAYLIST_RAT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HF_PLAYLIST_RAT_PK" ON "HF_PLAYLIST_RAT" ("INT_KEY") 
  ;
--------------------------------------------------------
--  DDL for Index HF_CAT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HF_CAT_PK" ON "HF_CAT" ("INT_KEY") 
  ;
--------------------------------------------------------
--  DDL for Index HF_PLAYLIST_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HF_PLAYLIST_PK" ON "HF_PLAYLIST" ("INT_KEY") 
  ;
--------------------------------------------------------
--  Constraints for Table PS_TXN
--------------------------------------------------------

  ALTER TABLE "PS_TXN" ADD CONSTRAINT "PS_TXN_PK" PRIMARY KEY ("COLLID", "ID")
  USING INDEX REVERSE  ENABLE;
--------------------------------------------------------
--  Constraints for Table HF_CAT
--------------------------------------------------------

  ALTER TABLE "HF_CAT" MODIFY ("HF_USER_IK" NOT NULL ENABLE);
  ALTER TABLE "HF_CAT" MODIFY ("CAT_NAME" NOT NULL ENABLE);
  ALTER TABLE "HF_CAT" MODIFY ("INT_KEY" NOT NULL ENABLE);
  ALTER TABLE "HF_CAT" ADD CONSTRAINT "HF_CAT_PK" PRIMARY KEY ("INT_KEY")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table HF_PLAYLIST
--------------------------------------------------------

  ALTER TABLE "HF_PLAYLIST" MODIFY ("PLAYLIST_NAME" NOT NULL ENABLE);
  ALTER TABLE "HF_PLAYLIST" MODIFY ("HF_CAT_IK" NOT NULL ENABLE);
  ALTER TABLE "HF_PLAYLIST" MODIFY ("HF_USER_IK" NOT NULL ENABLE);
  ALTER TABLE "HF_PLAYLIST" MODIFY ("INT_KEY" NOT NULL ENABLE);
  ALTER TABLE "HF_PLAYLIST" ADD CONSTRAINT "HF_PLAYLIST_PK" PRIMARY KEY ("INT_KEY")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table HF_PLAYLIST_POS
--------------------------------------------------------

  ALTER TABLE "HF_PLAYLIST_POS" MODIFY ("HF_PLAYLIST_IK" NOT NULL ENABLE);
  ALTER TABLE "HF_PLAYLIST_POS" MODIFY ("INT_KEY" NOT NULL ENABLE);
  ALTER TABLE "HF_PLAYLIST_POS" ADD CONSTRAINT "HF_PLAYLIST_POS_PK" PRIMARY KEY ("INT_KEY")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table HF_PLAYLIST_RAT
--------------------------------------------------------

  ALTER TABLE "HF_PLAYLIST_RAT" MODIFY ("RATING" NOT NULL ENABLE);
  ALTER TABLE "HF_PLAYLIST_RAT" MODIFY ("HF_PLAYLIST_IK" NOT NULL ENABLE);
  ALTER TABLE "HF_PLAYLIST_RAT" MODIFY ("INT_KEY" NOT NULL ENABLE);
  ALTER TABLE "HF_PLAYLIST_RAT" ADD CONSTRAINT "HF_PLAYLIST_RAT_PK" PRIMARY KEY ("INT_KEY")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table HF_USER
--------------------------------------------------------

  ALTER TABLE "HF_USER" MODIFY ("USERNAME" NOT NULL ENABLE);
  ALTER TABLE "HF_USER" MODIFY ("LONGNAME" NOT NULL ENABLE);
  ALTER TABLE "HF_USER" MODIFY ("INT_KEY" NOT NULL ENABLE);
  ALTER TABLE "HF_USER" ADD CONSTRAINT "HF_USER_PK" PRIMARY KEY ("INT_KEY")
  USING INDEX  ENABLE;
  ALTER TABLE "HF_USER" ADD CONSTRAINT "HF_USER_UK1" UNIQUE ("USERNAME")
  USING INDEX  ENABLE;
  ALTER TABLE "HF_USER" MODIFY ("PASSWORD" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table HF_CAT
--------------------------------------------------------

  ALTER TABLE "HF_CAT" ADD CONSTRAINT "HF_CAT_HF_USER_FK1" FOREIGN KEY ("HF_USER_IK")
	  REFERENCES "HF_USER" ("INT_KEY") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HF_PLAYLIST
--------------------------------------------------------

  ALTER TABLE "HF_PLAYLIST" ADD CONSTRAINT "HF_PLAYLIST_HF_CAT_FK1" FOREIGN KEY ("HF_CAT_IK")
	  REFERENCES "HF_CAT" ("INT_KEY") ENABLE;
  ALTER TABLE "HF_PLAYLIST" ADD CONSTRAINT "HF_PLAYLIST_HF_USER_FK1" FOREIGN KEY ("HF_USER_IK")
	  REFERENCES "HF_USER" ("INT_KEY") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HF_PLAYLIST_POS
--------------------------------------------------------

  ALTER TABLE "HF_PLAYLIST_POS" ADD CONSTRAINT "HF_PLAYLIST_POS_FK1" FOREIGN KEY ("HF_PLAYLIST_IK")
	  REFERENCES "HF_PLAYLIST" ("INT_KEY") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HF_PLAYLIST_RAT
--------------------------------------------------------

  ALTER TABLE "HF_PLAYLIST_RAT" ADD CONSTRAINT "HF_PLAYLIST_RAT_FK1" FOREIGN KEY ("HF_PLAYLIST_IK")
	  REFERENCES "HF_PLAYLIST" ("INT_KEY") ENABLE;
--------------------------------------------------------
--  DDL for Trigger HF_CAT_TRIG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "HF_CAT_TRIG" 
BEFORE INSERT or update  ON hf_cat FOR EACH ROW
BEGIN
 IF :NEW.int_key IS NULL OR :NEW.int_key < 0 THEN
   SELECT hf_cat_seq.NEXTVAL
     INTO :NEW.int_key
     FROM DUAL;
   END IF;
   

   SELECT hf_cat_seq.NEXTVAL,sysdate
     INTO :NEW.int_seq,:NEW.LAST_CHANGE
     FROM DUAL;

END;
/
ALTER TRIGGER "HF_CAT_TRIG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger HF_PLAYLIST_POS_TRIG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "HF_PLAYLIST_POS_TRIG" 
BEFORE INSERT or update  ON hf_playlist_pos FOR EACH ROW
BEGIN
 IF :NEW.int_key IS NULL OR :NEW.int_key < 0 THEN
   SELECT hf_playlist_pos_seq.NEXTVAL
     INTO :NEW.int_key
     FROM DUAL;
   END IF;
   

   SELECT hf_playlist_pos_seq.NEXTVAL,sysdate
     INTO :NEW.int_seq,:NEW.LAST_CHANGE
     FROM DUAL;

END;
/
ALTER TRIGGER "HF_PLAYLIST_POS_TRIG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger HF_PLAYLIST_RAT_TRIG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "HF_PLAYLIST_RAT_TRIG" 
BEFORE INSERT or update  ON hf_playlist_rat FOR EACH ROW
BEGIN
 IF :NEW.int_key IS NULL OR :NEW.int_key < 0 THEN
   SELECT hf_playlist_rat_seq.NEXTVAL
     INTO :NEW.int_key
     FROM DUAL;
   END IF;
   

   SELECT hf_playlist_rat_seq.NEXTVAL,sysdate
     INTO :NEW.int_seq,:NEW.LAST_CHANGE
     FROM DUAL;

END;
/
ALTER TRIGGER "HF_PLAYLIST_RAT_TRIG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger HF_PLAYLIST_TRIG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "HF_PLAYLIST_TRIG" 
BEFORE INSERT or update  ON hf_playlist FOR EACH ROW
BEGIN
 IF :NEW.int_key IS NULL OR :NEW.int_key < 0 THEN
   SELECT hf_playlist_seq.NEXTVAL
     INTO :NEW.int_key
     FROM DUAL;
   END IF;
   

   SELECT hf_playlist_seq.NEXTVAL,sysdate
     INTO :NEW.int_seq,:NEW.LAST_CHANGE
     FROM DUAL;

END;
/
ALTER TRIGGER "HF_PLAYLIST_TRIG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger HF_USER_TRIG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "HF_USER_TRIG" 
BEFORE INSERT or update ON hf_user FOR EACH ROW
BEGIN
 IF :NEW.int_key IS NULL OR :NEW.int_key < 0 THEN
   SELECT hf_user_seq.NEXTVAL+round(SYS.DBMS_RANDOM.value(0,90000))
     INTO :NEW.int_key
     FROM DUAL;
   END IF;
   
   SELECT hf_user_seq.NEXTVAL,sysdate
     INTO :NEW.int_seq,:NEW.LAST_CHANGE
     FROM DUAL;

   
END;
/
ALTER TRIGGER "HF_USER_TRIG" ENABLE;

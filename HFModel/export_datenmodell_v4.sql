--------------------------------------------------------
--  File created - Friday-March-17-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence HF_CAT_PK_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HF_CAT_PK_SEQ"  MINVALUE 1 MAXVALUE 99999999999999 INCREMENT BY 1 START WITH 62 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
--------------------------------------------------------
--  DDL for Sequence HF_CAT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HF_CAT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 101 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
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

   CREATE SEQUENCE  "HF_PLAYLIST_POS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
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
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values (30,2,'Gelb',null,'Das gelbe vom Ei Rock and Roll und blau',84,to_date('17-MAR-17','DD-MON-RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values (35,2,'Kunterbunt',null,'Alle Farben',0,to_date('05-MAR-17','DD-MON-RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values (39,2,'test',null,'test',0,to_date('05-MAR-17','DD-MON-RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values (41,2,'test2',null,'test 2',0,to_date('05-MAR-17','DD-MON-RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values (25,2,'Blau',null,'Alles ist Blau pop',null,to_date('04-MAR-17','DD-MON-RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values (26,2,'Grün',null,'Grün Grün welt funk',null,to_date('04-MAR-17','DD-MON-RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values (27,2,'dddd',null,'dummy d',82,to_date('17-MAR-17','DD-MON-RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values (42,3,'Hund',null,'Hund springt',85,to_date('17-MAR-17','DD-MON-RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values (43,3,'Katze',null,'Katze mau mau',86,to_date('17-MAR-17','DD-MON-RR'));
Insert into HF_CAT (INT_KEY,HF_USER_IK,CAT_NAME,PARENT_HF_CAT_IK,CAT_DESC,INT_SEQ,LAST_CHANGE) values (44,3,'Maus',null,'Mausi ist lecker',87,to_date('17-MAR-17','DD-MON-RR'));
REM INSERTING into HF_PLAYLIST
SET DEFINE OFF;
Insert into HF_PLAYLIST (INT_KEY,PLAYLIST_NAME,HF_CAT_IK,HF_USER_IK,DESCRIPTION,INT_SEQ,LAST_CHANGE,SHARE_FLAG) values (1,'Unterwegs',35,2,'Alles für unterwegs in rosa',21,to_date('17-MAR-17','DD-MON-RR'),'Y');
Insert into HF_PLAYLIST (INT_KEY,PLAYLIST_NAME,HF_CAT_IK,HF_USER_IK,DESCRIPTION,INT_SEQ,LAST_CHANGE,SHARE_FLAG) values (2,'GoGoGo',26,2,'Schnelles für zwischendurch ice dzug',0,to_date('10-MAR-17','DD-MON-RR'),'N');
Insert into HF_PLAYLIST (INT_KEY,PLAYLIST_NAME,HF_CAT_IK,HF_USER_IK,DESCRIPTION,INT_SEQ,LAST_CHANGE,SHARE_FLAG) values (3,'ICE',41,2,'ICE und laut muss es sein',0,to_date('10-MAR-17','DD-MON-RR'),'Y');
Insert into HF_PLAYLIST (INT_KEY,PLAYLIST_NAME,HF_CAT_IK,HF_USER_IK,DESCRIPTION,INT_SEQ,LAST_CHANGE,SHARE_FLAG) values (4,'Mausi',25,2,'Musi Maus mau mau mau',0,to_date('10-MAR-17','DD-MON-RR'),'N');
Insert into HF_PLAYLIST (INT_KEY,PLAYLIST_NAME,HF_CAT_IK,HF_USER_IK,DESCRIPTION,INT_SEQ,LAST_CHANGE,SHARE_FLAG) values (21,'Futter',43,3,'Futter für die Katz',22,to_date('17-MAR-17','DD-MON-RR'),'Y');
Insert into HF_PLAYLIST (INT_KEY,PLAYLIST_NAME,HF_CAT_IK,HF_USER_IK,DESCRIPTION,INT_SEQ,LAST_CHANGE,SHARE_FLAG) values (22,'Kamel',42,3,'Ein Kamel hat zwei Höcker',23,to_date('17-MAR-17','DD-MON-RR'),'N');
REM INSERTING into HF_PLAYLIST_POS
SET DEFINE OFF;
REM INSERTING into HF_PLAYLIST_RAT
SET DEFINE OFF;
REM INSERTING into HF_USER
SET DEFINE OFF;
Insert into HF_USER (INT_KEY,USERNAME,PASSWORD,LONGNAME,INT_SEQ,LAST_CHANGE) values (2,'Timo','444','Timo Hahn',727051,to_date('17-MAR-17','DD-MON-RR'));
Insert into HF_USER (INT_KEY,USERNAME,PASSWORD,LONGNAME,INT_SEQ,LAST_CHANGE) values (3,'Test1','1111','Tester 1',0,to_date('24-FEB-17','DD-MON-RR'));
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
--  DDL for Index HF_PLAYLIST_POS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HF_PLAYLIST_POS_PK" ON "HF_PLAYLIST_POS" ("INT_KEY") 
  ;
--------------------------------------------------------
--  DDL for Index HF_PLAYLIST_RAT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HF_PLAYLIST_RAT_PK" ON "HF_PLAYLIST_RAT" ("INT_KEY") 
  ;
--------------------------------------------------------
--  DDL for Index HF_USER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HF_USER_PK" ON "HF_USER" ("INT_KEY") 
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
--  Constraints for Table PS_TXN
--------------------------------------------------------

  ALTER TABLE "PS_TXN" ADD CONSTRAINT "PS_TXN_PK" PRIMARY KEY ("COLLID", "ID")
  USING INDEX REVERSE  ENABLE;
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

CREATE TABLE postal_code
(
  code character varying(5),
  old_postal_code character varying(5),
  postal_code character varying(7) NOT NULL,
  pref_kana character varying(64),
  ward_kana character varying(64),
  zone_kana character varying(64),
  pref character varying(64),
  ward character varying(64),
  zone character varying(64),
  flg1 bigint,
  flg2 bigint,
  flg3 bigint,
  flg4 bigint,
  flg5 bigint,
  flg6 bigint
)
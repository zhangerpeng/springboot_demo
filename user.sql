--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: t_role; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE t_role (
    user_id character varying(4),
    user_author character(4) DEFAULT 'USER'::bpchar NOT NULL
);


ALTER TABLE t_role OWNER TO postgres;

--
-- Name: t_user; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE t_user (
    user_id character varying(4) NOT NULL,
    user_name character varying(20),
    user_birth date,
    address character varying(30),
    tel_num character varying(11),
    user_pass character varying(65),
    user_status character varying(4) DEFAULT 'INIT'::character varying NOT NULL,
    last_update_time timestamp(0) without time zone DEFAULT ('now'::text)::timestamp(0) with time zone NOT NULL,
    recode_version integer DEFAULT 0 NOT NULL
);


ALTER TABLE t_user OWNER TO postgres;

--
-- Data for Name: t_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY t_role (user_id, user_author) FROM stdin;
0002	USER
0003	USER
0004	USER
0005	USER
0006	USER
0007	USER
0007	ADMN
\N	ADMN
\N	USER
\N	ADMN
\N	USER
\N	ADMN
\N	USER
\N	ADMN
\N	USER
\N	ADMN
\N	USER
\N	ADMN
\N	USER
0008	ADMN
0008	USER
0001	ADMN
0001	USER
0009	ADMN
\.


--
-- Data for Name: t_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY t_user (user_id, user_name, user_birth, address, tel_num, user_pass, user_status, last_update_time, recode_version) FROM stdin;
0003	banana	1993-03-03	東京都中央区築地一丁目1-1	09012345678	$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK	ACTV	2015-06-04 14:29:00	0
0004	peach	1994-04-04	東京都中央区銀座一丁目7-12	0387654321	$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK	ACTV	2015-06-05 14:29:00	0
0005	pineapple	1995-05-05	東京都千代田区有楽町一丁目11-1	08012345678	$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK	ACTV	2015-06-06 14:29:00	0
0006	pear	1996-06-06	東京都千代田区霞が関二丁目1-1	08087654321	$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK	RMVD	2015-06-07 14:29:00	0
0007	apple7	1990-01-01	西安	0012001300	$2a$10$/q65inY2r/F7m0GqgyO4EOlOeZI2z1S4T52PPflQqEzoI9Y1DERg6	RMVD	2019-07-15 11:41:42	0
0008	AUTO	1990-01-01	西安	0123456789	$2a$10$yXFs5oQYAT9M8.PgQeV3uOQav4JN33wYL6PKkdSWhBNmEFAVYiLkO	INIT	2019-08-20 03:13:12	0
0001	apple	1991-01-01	東京都江東区豊洲四丁目1-1	0312345678	$2a$10$W.Qwx9mEHzCRQZBZ4CZoHuPVjFfg8ZjDLuF83JnEVQAZz0yMCbwda	ACTV	2019-08-20 03:59:44	1
0002	orange	1992-02-02	東京都中央区月島一丁目3-9	0387654321	$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK	RMVD	2015-06-03 14:29:00	0
0009	DEMO	1990-01-01	西安	1234567890	demo	INIT	2019-12-18 11:24:30	0
\.


--
-- Name: t_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY t_user
    ADD CONSTRAINT t_user_pkey PRIMARY KEY (user_id);


--
-- Name: fk_t_user_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY t_role
    ADD CONSTRAINT fk_t_user_role FOREIGN KEY (user_id) REFERENCES t_user(user_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--


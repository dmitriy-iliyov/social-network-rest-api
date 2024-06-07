--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

-- Started on 2024-06-07 21:44:38 EEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 215 (class 1259 OID 24811)
-- Name: admin_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.admin_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.admin_sequence OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 221 (class 1259 OID 24829)
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories (
    id bigint NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24814)
-- Name: categories_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categories_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categories_sequence OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24813)
-- Name: post_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.post_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.post_sequence OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 24822)
-- Name: posts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.posts (
    id bigint NOT NULL,
    topic character varying NOT NULL,
    user_id bigint NOT NULL,
    category_id bigint NOT NULL,
    description character varying NOT NULL
);


ALTER TABLE public.posts OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 24812)
-- Name: user_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.user_sequence OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24815)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    name character varying NOT NULL,
    password character varying NOT NULL,
    email character varying NOT NULL,
    create_date timestamp without time zone NOT NULL,
    role character varying NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 3613 (class 0 OID 24829)
-- Dependencies: 221
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categories (id, name) FROM stdin;
7	new category
12	new category2
\.


--
-- TOC entry 3612 (class 0 OID 24822)
-- Dependencies: 220
-- Data for Name: posts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.posts (id, topic, user_id, category_id, description) FROM stdin;
4	fads	7	7	gyhjeldk;lw'kwfk;nlbje lnrw cverwe',c iwo[qpekdfmn po[pkfjn
\.


--
-- TOC entry 3611 (class 0 OID 24815)
-- Dependencies: 219
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, name, password, email, create_date, role) FROM stdin;
14	sayner	$2a$10$KvkfVEN71kCik.ChfajoGuP52sSXazQRJIhdzau1EvGlqP5O29jFG	vervmeo@fmvnif	2024-04-08 01:39:10.634283	USER
19	sayner2	$2a$10$F2kINIRE0Dj103DXsEdeXuLTBzfeVqyXindKTsN0A4Eu7ua02XnNe	dkeoni@poshta.com	2024-04-08 01:51:55.791725	USER
20	user	$2a$10$J1U8wljl3Jplpll/ClbCAuC3BKd4HV/L6OVJbkkE85WnJ8ImgjIT2	wmrknbj@email.com	2024-04-08 02:53:12.137948	USER
21	testuser1	$2a$10$SgBR6HDBEqlPO9qoreCNIeNsh8KsULUAh0Z1yzK6fkolUGyZ5G2/u	test@email.com	2024-04-08 06:00:36.766445	USER
22	test2	$2a$10$FyyolbhOLTMjbFeQ8sBPJusUqaR4ryxOcOXFaBICNvgQO.jOf0tHe	hfeuobf@email.com	2024-04-08 06:06:56.54458	USER
23	test3	$2a$10$PcKFUIC3UsvbqahDI27fpeoEOuQlvqG.tnHdVul0UwDzCQAFsnloK	qwert@email.com	2024-04-08 06:09:30.470883	USER
31	ali	$2a$10$KWCD0gFPQUsdSnjxCzo6QOkzWLaPoAG2VjZMHgEmhqAMVvKqiZD62	ali@email	2024-04-10 05:52:56.803182	USER
32	admin	admin	-	2024-04-10 05:56:07.56894	ADMIN
33	Anna Protchenko	$2a$10$5pRmUu6Z/wgTyzGmSItJ9.kcTWqRAQQ7aXl4bdJXSoaAehE3g9YFq	-	2024-04-10 06:04:03.054041	ADMIN
36	q	$2a$10$HoMrs8lpt0XjxhH6cGQwE.4mQbankWD7PWhjYXiHehZZ9y5pbblGW	-	2024-04-10 06:09:35.206918	ADMIN
37	Eva	$2a$10$oKTrAO2yQj4bILOcwU/3zO9kq0M8lDYiNZDKM39VU8XM5DhsCk5u2	-	2024-04-10 06:12:19.348167	ADMIN
38	qwas	$2a$10$rA/aCKWnbiOaa2lcCrcZ0uM0cV5lt/iVtUwNRNO7rISDoe1nnsqpe	qwas@email.com	2024-04-10 06:15:28.370329	USER
39	ьфтнф	$2a$10$lpvuXVigInJtsi5RSvrBHeh9rsx1SWgpZqTuvs/QtG.gWwM5PzWcq	d@emaoil	2024-04-10 06:17:25.094571	USER
40	qwasik	$2a$10$W535NDPjvv7uZy0prrqJ.u58fv0yVU6ApLz6IeBBBuLijniBoy8pO	qwasik@emali.com	2024-04-10 06:21:14.297145	USER
41	2	$2a$10$9XQCR0ke.wanGuxrQJeN6.070NvjLKrsOlRxiPWBNVmrMntWq8Cmi	-	2024-04-11 00:06:19.5302	ADMIN
42	1	$2a$10$rbzqn9Lv0Js.6ARum6SfXOY/Q02qC4zN45rTm5X5gY46240jim80y	1@email.com	2024-04-11 00:55:10.064012	USER
43	3	$2a$10$rbzqn9Lv0Js.6ARum6SfXOY/Q02qC4zN45rTm5X5gY46240jim80y	666@email.com	2024-04-12 14:45:34.189951	USER
44	dimasta	$2a$10$XU4opywBJngK2juj35VcaOA6xaN2sWqkEDS9ogglSGY.bnSTJQpdq	elmr@gmail.com	2024-06-04 21:26:02.135067	USER
\.


--
-- TOC entry 3619 (class 0 OID 0)
-- Dependencies: 215
-- Name: admin_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.admin_sequence', 6, true);


--
-- TOC entry 3620 (class 0 OID 0)
-- Dependencies: 218
-- Name: categories_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categories_sequence', 13, true);


--
-- TOC entry 3621 (class 0 OID 0)
-- Dependencies: 217
-- Name: post_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.post_sequence', 5, true);


--
-- TOC entry 3622 (class 0 OID 0)
-- Dependencies: 216
-- Name: user_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_sequence', 44, true);


--
-- TOC entry 3461 (class 2606 OID 24835)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- TOC entry 3459 (class 2606 OID 24828)
-- Name: posts posts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_pkey PRIMARY KEY (id);


--
-- TOC entry 3463 (class 2606 OID 24845)
-- Name: categories unique_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT unique_name UNIQUE (name);


--
-- TOC entry 3455 (class 2606 OID 24847)
-- Name: users unique_username; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT unique_username UNIQUE (name);


--
-- TOC entry 3457 (class 2606 OID 24821)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


-- Completed on 2024-06-07 21:44:39 EEST

--
-- PostgreSQL database dump complete
--


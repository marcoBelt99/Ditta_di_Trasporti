--
-- PostgreSQL database dump
--

-- Dumped from database version 14.12
-- Dumped by pg_dump version 14.3

-- Started on 2024-06-18 17:13:03

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

DROP DATABASE ditta_trasporti;
--
-- TOC entry 3433 (class 1262 OID 16384)
-- Name: ditta_trasporti; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE ditta_trasporti WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE ditta_trasporti OWNER TO postgres;

-- \connect ditta_trasporti

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
-- TOC entry 3434 (class 0 OID 0)
-- Dependencies: 3433
-- Name: DATABASE ditta_trasporti; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE ditta_trasporti IS 'Database progetto ditta di trasporti';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 211 (class 1259 OID 24583)
-- Name: linee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.linee (
    num_linea integer NOT NULL,
    destinazione character varying(30) NOT NULL
);


ALTER TABLE public.linee OWNER TO postgres;

--
-- TOC entry 3435 (class 0 OID 0)
-- Dependencies: 211
-- Name: TABLE linee; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.linee IS 'Tabella delle linee di percorrenza dei vari autobus.';


--
-- TOC entry 212 (class 1259 OID 24586)
-- Name: Linee_NumLinea_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Linee_NumLinea_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Linee_NumLinea_seq" OWNER TO postgres;

--
-- TOC entry 3436 (class 0 OID 0)
-- Dependencies: 212
-- Name: Linee_NumLinea_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Linee_NumLinea_seq" OWNED BY public.linee.num_linea;


--
-- TOC entry 213 (class 1259 OID 24587)
-- Name: turni; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.turni (
    id bigint NOT NULL,
    data date NOT NULL,
    ora_inizio time without time zone NOT NULL,
    ora_fine time without time zone NOT NULL,
    targa character varying(10) NOT NULL,
    num_linea integer NOT NULL,
    id_utente integer NOT NULL
);


ALTER TABLE public.turni OWNER TO postgres;

--
-- TOC entry 3437 (class 0 OID 0)
-- Dependencies: 213
-- Name: TABLE turni; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.turni IS 'Rappresenta i turni dei vari autisti';


--
-- TOC entry 214 (class 1259 OID 24590)
-- Name: Turni_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.turni ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Turni_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 216 (class 1259 OID 24594)
-- Name: Utenti_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Utenti_ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Utenti_ID_seq" OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 24576)
-- Name: autisti; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.autisti (
    matricola character varying(5) NOT NULL,
    cognome character varying(30) NOT NULL,
    nome character varying(25) NOT NULL,
    telefono character varying(10),
    id_utente integer NOT NULL
);


ALTER TABLE public.autisti OWNER TO postgres;

--
-- TOC entry 3438 (class 0 OID 0)
-- Dependencies: 209
-- Name: TABLE autisti; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.autisti IS 'Rappresenta le informazioni relative agli autisti';


--
-- TOC entry 210 (class 1259 OID 24579)
-- Name: autobus; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.autobus (
    targa character varying(10) NOT NULL,
    modello character varying(20) NOT NULL,
    capienza integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.autobus OWNER TO postgres;

--
-- TOC entry 3439 (class 0 OID 0)
-- Dependencies: 210
-- Name: TABLE autobus; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.autobus IS 'Tabella che rappresenta le informazioni sugli autobus';


--
-- TOC entry 215 (class 1259 OID 24591)
-- Name: utenti; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utenti (
    id integer DEFAULT nextval('public."Utenti_ID_seq"'::regclass) NOT NULL,
    username character varying(30) NOT NULL,
    password character varying(30) NOT NULL,
    ruolo character varying(7) DEFAULT 'AUTISTA'::character varying NOT NULL
);


ALTER TABLE public.utenti OWNER TO postgres;

--
-- TOC entry 3440 (class 0 OID 0)
-- Dependencies: 215
-- Name: TABLE utenti; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.utenti IS 'Per gestire i ruoli utente.
Il primo record, con ID=1 è riservato all''admin dell''applicazione';


--
-- TOC entry 3252 (class 2604 OID 24595)
-- Name: linee num_linea; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.linee ALTER COLUMN num_linea SET DEFAULT nextval('public."Linee_NumLinea_seq"'::regclass);


--
-- TOC entry 3420 (class 0 OID 24576)
-- Dependencies: 209
-- Data for Name: autisti; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.autisti (matricola, cognome, nome, telefono, id_utente) FROM stdin;
14888	Beltrame	Marco	3403183848	2
14579	Baretta	Laura	3280140209	4
14882	Romagnollo	Fasil	3213183748	5
12347	Girlandaio	Philippo	3697499999	6
14444	Beltrame	Moira	3962647895	9
12125	Bonfe	Marcello	3366887441	7
14799	Biondi	Diletta	3444423411	8
14143	Pacicca	Stella	3666666422	3
12335	Baldini	Sonia	3222113422	10
14755	Mori	Luca	3262647822	11
14111	Mattia	Forza	3122566421	12
\.


--
-- TOC entry 3421 (class 0 OID 24579)
-- Dependencies: 210
-- Data for Name: autobus; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.autobus (targa, modello, capienza) FROM stdin;
ABC123XYZA	MB Sprinter	20
DEF456WXYZ	Volvo 9700	50
GHI789UVWX	MAN Lion's City	30
JKL012LMNO	Iveco Crossway	40
PQR345STUV	Scania Touring	60
WXY678MNOP	Setra S 431 DT	45
QWE901BCDE	Solaris Urbino	35
ZXC234FGHI	Neoplan Cityliner	55
VBN567JKLM	Van Hool TX	25
RTY890QRST	Irizar i8	65
ASD123NBVC	VDL Futura	30
FGH456TYUI	Temsa Safari HD	50
HJK789UIOP	MCI J4500	40
UIO012MKLP	Dennis Trident 2	35
BVC345UIOA	Wright Eclipse	60
QWE678FGRT	Man NL202	45
RTY901CVBN	VDL Berkhof	55
UIO234GFDS	Volgren CR228L	70
CVB567LKJH	Optare Spectra	30
FGH890PLOI	Alexander Dennis	50
JKL123MNBV	Scania OmniExpress	35
BNM456UIOP	MB Citaro	65
VBN789XCVB	Ikarus EAG E94	40
QWE012CVBN	BYD K9	30
\.


--
-- TOC entry 3422 (class 0 OID 24583)
-- Dependencies: 211
-- Data for Name: linee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.linee (num_linea, destinazione) FROM stdin;
1	Ferrara - Stazione
2	Ferrara - Aleotti
3	Ferrara - Centro
4	Ferrara - Nord
5	Ferrara - Sud
6	Ferrara - Est
7	Ferrara - Ovest
8	Ferrara - Fiere
9	Ferrara - Boschetti
10	Ferrara - Cona
11	Rovigo
12	Adria
13	Ariano nel Polesine
14	Porto Tolle
15	Chioggia
16	Padova
\.


--
-- TOC entry 3424 (class 0 OID 24587)
-- Dependencies: 213
-- Data for Name: turni; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.turni (id, data, ora_inizio, ora_fine, targa, num_linea, id_utente) FROM stdin;
515	2024-01-03	06:36:07	09:36:07	ZXC234FGHI	10	8
516	2024-01-03	13:53:07	21:53:07	DEF456WXYZ	3	9
517	2024-01-03	05:39:31	12:09:31	VBN567JKLM	4	10
518	2024-01-03	12:45:42	17:15:42	JKL123MNBV	11	5
519	2024-01-03	13:37:34	21:37:34	WXY678MNOP	2	8
520	2024-01-04	12:17:22	15:17:22	QWE901BCDE	13	6
521	2024-01-04	06:11:24	09:11:24	DEF456WXYZ	14	9
522	2024-01-04	10:39:41	15:39:41	GHI789UVWX	15	9
523	2024-01-04	05:39:31	09:09:31	PQR345STUV	2	5
524	2024-01-04	09:42:31	12:12:31	DEF456WXYZ	12	5
525	2024-01-04	13:58:45	17:58:45	QWE012CVBN	8	3
526	2024-01-05	13:37:34	19:07:34	VBN567JKLM	9	8
527	2024-01-05	08:29:58	13:59:58	QWE901BCDE	3	2
528	2024-01-05	07:51:14	13:51:14	BNM456UIOP	1	10
529	2024-01-06	10:40:10	16:40:10	QWE012CVBN	4	4
530	2024-01-06	12:39:56	17:09:56	ZXC234FGHI	4	5
531	2024-01-06	13:58:45	19:58:45	UIO234GFDS	13	3
532	2024-01-06	07:59:32	10:59:32	UIO234GFDS	6	10
533	2024-01-06	06:31:49	14:01:49	QWE901BCDE	3	9
534	2024-01-08	10:31:56	13:31:56	VBN567JKLM	7	4
535	2024-01-08	12:39:56	20:09:56	GHI789UVWX	1	10
536	2024-01-08	05:34:04	12:34:04	FGH890PLOI	14	2
537	2024-01-09	13:58:45	18:58:45	QWE678FGRT	10	4
538	2024-01-10	13:53:27	17:53:27	WXY678MNOP	8	9
539	2024-01-10	05:51:50	12:21:50	JKL012LMNO	3	6
540	2024-01-10	10:14:22	16:14:22	BNM456UIOP	6	10
541	2024-01-10	10:06:21	14:06:21	BNM456UIOP	5	8
542	2024-01-11	06:36:07	12:36:07	JKL012LMNO	14	10
543	2024-01-11	11:17:44	16:47:44	UIO012MKLP	4	6
544	2024-01-11	11:20:44	17:20:44	JKL123MNBV	4	9
545	2024-01-12	11:08:44	14:08:44	ASD123NBVC	2	2
546	2024-01-12	12:01:56	18:31:56	VBN789XCVB	12	9
547	2024-01-12	09:54:32	17:24:32	QWE901BCDE	1	6
548	2024-01-12	13:44:42	15:44:42	RTY890QRST	3	10
549	2024-01-12	11:57:43	19:27:43	QWE678FGRT	9	4
550	2024-01-13	13:52:36	16:22:36	FGH890PLOI	12	5
551	2024-01-13	07:46:11	11:46:11	DEF456WXYZ	15	9
552	2024-01-13	11:27:44	15:57:44	VBN789XCVB	10	10
553	2024-01-13	13:52:36	19:52:36	BNM456UIOP	10	4
554	2024-01-13	06:32:03	13:32:03	WXY678MNOP	13	6
555	2024-01-14	10:06:21	18:06:21	JKL012LMNO	11	8
556	2024-01-14	09:40:47	16:40:47	DEF456WXYZ	2	4
557	2024-01-14	05:36:54	08:06:54	BNM456UIOP	11	9
558	2024-01-16	12:01:56	18:01:56	QWE901BCDE	11	5
559	2024-01-16	10:39:41	17:39:41	ZXC234FGHI	5	10
560	2024-01-16	06:09:55	10:39:55	QWE901BCDE	11	9
561	2024-01-16	12:52:35	18:22:35	BNM456UIOP	1	8
562	2024-01-20	11:43:17	15:43:17	ASD123NBVC	6	4
563	2024-01-20	10:01:52	12:01:52	FGH890PLOI	9	10
564	2024-01-20	05:03:33	12:03:33	JKL123MNBV	11	7
565	2024-01-21	11:17:44	18:17:44	GHI789UVWX	15	2
566	2024-01-21	08:18:32	15:18:32	QWE901BCDE	9	6
567	2024-01-21	12:39:56	14:39:56	FGH456TYUI	5	5
568	2024-01-21	06:09:55	09:39:55	JKL012LMNO	2	10
569	2024-01-21	09:44:13	12:14:13	RTY890QRST	15	5
570	2024-01-21	07:34:35	14:04:35	UIO234GFDS	4	9
571	2024-01-21	06:11:24	08:41:24	UIO234GFDS	5	4
572	2024-01-24	09:44:13	16:44:13	VBN567JKLM	3	5
573	2024-01-24	11:17:44	14:47:44	CVB567LKJH	8	9
574	2024-01-24	12:58:46	14:58:46	WXY678MNOP	11	4
575	2024-01-24	08:08:31	15:38:31	QWE012CVBN	2	10
576	2024-01-24	10:44:35	15:14:35	BNM456UIOP	10	7
577	2024-01-24	13:58:45	15:58:45	BVC345UIOA	12	6
578	2024-01-24	08:29:58	10:29:58	ASD123NBVC	2	7
579	2024-01-25	11:57:43	15:27:43	QWE012CVBN	4	3
580	2024-01-25	07:59:32	13:59:32	HJK789UIOP	12	4
581	2024-01-25	11:57:43	16:27:43	DEF456WXYZ	15	8
582	2024-01-25	09:46:06	15:16:06	PQR345STUV	8	6
583	2024-01-27	11:08:44	17:08:44	BVC345UIOA	13	3
584	2024-01-27	10:01:52	12:01:52	UIO234GFDS	11	5
585	2024-01-27	06:48:11	10:48:11	UIO012MKLP	8	3
586	2024-01-27	10:06:21	13:06:21	RTY890QRST	3	10
587	2024-01-27	07:51:14	09:51:14	VBN567JKLM	1	10
588	2024-01-30	10:37:59	14:37:59	VBN789XCVB	5	3
589	2024-01-30	11:43:17	17:43:17	CVB567LKJH	13	2
590	2024-01-30	05:03:33	09:03:33	ABC123XYZA	11	3
591	2024-01-30	05:34:04	12:04:04	PQR345STUV	12	8
592	2024-01-30	10:57:43	16:57:43	RTY901CVBN	12	7
593	2024-01-30	11:21:45	17:21:45	JKL012LMNO	2	10
594	2024-01-30	09:44:13	15:44:13	VBN567JKLM	2	9
595	2024-02-01	08:18:32	15:48:32	FGH456TYUI	8	10
596	2024-02-01	05:36:54	08:36:54	VBN567JKLM	1	8
597	2024-02-01	07:57:45	11:27:45	QWE012CVBN	3	6
598	2024-02-01	05:33:59	12:03:59	DEF456WXYZ	1	4
599	2024-02-01	08:53:17	13:23:17	VBN567JKLM	2	8
600	2024-02-01	05:03:33	07:03:33	CVB567LKJH	12	7
601	2024-02-02	09:46:06	16:16:06	VBN789XCVB	12	3
602	2024-02-02	07:03:13	11:03:13	QWE678FGRT	4	5
603	2024-02-02	08:17:48	14:47:48	RTY901CVBN	2	9
604	2024-02-02	12:52:35	18:22:35	PQR345STUV	6	6
605	2024-02-03	07:46:11	10:46:11	UIO234GFDS	7	7
606	2024-02-03	11:43:17	15:13:17	QWE012CVBN	7	4
607	2024-02-03	11:39:07	19:09:07	UIO234GFDS	9	7
608	2024-02-03	13:44:48	21:14:48	WXY678MNOP	9	10
609	2024-02-07	07:34:35	13:04:35	CVB567LKJH	6	8
610	2024-02-07	08:37:53	15:37:53	WXY678MNOP	12	9
611	2024-02-09	05:03:33	09:33:33	ABC123XYZA	4	6
612	2024-02-09	06:32:03	10:02:03	FGH456TYUI	11	2
613	2024-02-09	13:34:06	21:34:06	BNM456UIOP	15	6
614	2024-02-10	12:15:29	16:15:29	BNM456UIOP	4	5
615	2024-02-10	05:34:04	09:04:04	WXY678MNOP	5	5
616	2024-02-13	06:28:53	10:58:53	QWE901BCDE	12	8
617	2024-02-13	13:34:06	19:34:06	PQR345STUV	7	2
618	2024-02-16	13:34:06	20:04:06	QWE678FGRT	3	5
619	2024-02-16	09:19:38	15:49:38	PQR345STUV	6	2
620	2024-02-16	07:57:45	15:57:45	QWE901BCDE	7	8
621	2024-02-16	06:32:03	11:32:03	ABC123XYZA	5	10
622	2024-02-16	12:58:46	20:28:46	RTY901CVBN	4	7
623	2024-02-16	05:39:31	09:09:31	PQR345STUV	6	4
624	2024-02-17	07:57:45	13:27:45	WXY678MNOP	10	8
625	2024-02-18	06:31:49	10:31:49	UIO012MKLP	9	8
626	2024-02-20	07:03:13	10:03:13	UIO012MKLP	8	5
627	2024-02-21	06:36:07	13:36:07	CVB567LKJH	7	5
628	2024-02-21	11:27:40	17:57:40	QWE012CVBN	6	4
629	2024-02-21	06:31:49	12:31:49	ZXC234FGHI	12	8
630	2024-02-21	07:11:40	14:11:40	RTY890QRST	10	10
631	2024-02-21	08:53:17	13:23:17	QWE901BCDE	4	2
632	2024-02-22	10:44:35	14:44:35	ASD123NBVC	1	6
633	2024-02-22	12:45:42	16:15:42	GHI789UVWX	7	3
634	2024-02-23	09:42:31	15:12:31	FGH890PLOI	7	2
635	2024-02-23	12:15:29	14:15:29	HJK789UIOP	2	6
636	2024-02-25	12:19:13	17:49:13	QWE678FGRT	14	2
637	2024-02-25	07:03:13	12:33:13	UIO234GFDS	3	9
638	2024-02-25	09:46:06	17:46:06	VBN567JKLM	9	5
639	2024-02-25	09:12:22	15:12:22	JKL123MNBV	14	3
640	2024-02-26	13:02:23	16:32:23	HJK789UIOP	13	9
641	2024-02-26	13:52:36	16:52:36	QWE901BCDE	14	7
642	2024-02-26	05:36:54	09:06:54	PQR345STUV	8	2
643	2024-02-26	10:40:15	16:40:15	QWE012CVBN	12	6
644	2024-02-26	13:55:12	18:55:12	VBN789XCVB	14	4
645	2024-02-26	10:40:15	16:10:15	DEF456WXYZ	9	3
646	2024-02-26	07:55:10	11:25:10	UIO234GFDS	3	10
647	2024-02-27	12:39:56	17:09:56	QWE012CVBN	1	4
648	2024-02-27	06:04:01	12:34:01	RTY890QRST	3	6
649	2024-02-27	11:08:44	14:08:44	ZXC234FGHI	7	7
650	2024-02-27	06:09:55	13:09:55	QWE678FGRT	13	9
651	2024-02-27	12:17:22	16:47:22	BVC345UIOA	9	3
652	2024-02-28	13:53:27	19:53:27	VBN567JKLM	15	3
653	2024-02-28	05:03:33	09:03:33	JKL012LMNO	14	4
654	2024-03-01	12:41:02	19:11:02	GHI789UVWX	6	4
655	2024-03-01	13:53:07	18:23:07	CVB567LKJH	2	5
656	2024-03-01	07:03:13	14:03:13	ASD123NBVC	3	3
657	2024-03-02	09:44:13	12:44:13	HJK789UIOP	12	7
658	2024-03-04	10:40:15	12:40:15	GHI789UVWX	13	8
659	2024-03-04	09:16:31	16:46:31	QWE901BCDE	13	5
660	2024-03-04	12:42:07	17:42:07	PQR345STUV	5	3
661	2024-03-04	13:58:45	21:28:45	WXY678MNOP	12	6
662	2024-03-04	06:31:49	12:31:49	DEF456WXYZ	10	4
663	2024-03-04	08:29:58	14:29:58	CVB567LKJH	5	10
664	2024-03-05	13:53:27	15:53:27	QWE012CVBN	5	7
665	2024-03-05	13:44:42	16:44:42	RTY890QRST	14	9
666	2024-03-05	06:59:40	12:59:40	CVB567LKJH	5	5
667	2024-03-05	05:36:15	12:06:15	ZXC234FGHI	4	6
668	2024-03-06	09:19:38	17:19:38	BNM456UIOP	3	4
669	2024-03-06	06:36:07	12:06:07	UIO234GFDS	12	6
670	2024-03-06	08:08:31	11:38:31	BVC345UIOA	2	10
671	2024-03-06	11:39:07	13:39:07	FGH890PLOI	14	10
672	2024-03-06	07:51:14	12:21:14	RTY901CVBN	11	9
673	2024-03-06	10:22:55	14:22:55	BVC345UIOA	9	3
674	2024-03-06	13:53:07	21:23:07	UIO012MKLP	3	5
675	2024-03-06	09:12:22	12:42:22	JKL012LMNO	12	7
676	2024-03-06	10:22:55	13:22:55	RTY901CVBN	15	5
677	2024-03-08	06:59:40	10:29:40	QWE901BCDE	9	9
678	2024-03-08	06:09:55	13:09:55	BNM456UIOP	11	7
679	2024-03-08	12:15:29	15:45:29	RTY890QRST	10	8
680	2024-03-16	11:27:40	18:27:40	UIO234GFDS	3	5
681	2024-03-16	11:17:49	18:47:49	UIO234GFDS	12	8
682	2024-03-17	10:57:43	14:57:43	QWE012CVBN	14	2
683	2024-03-17	09:40:47	14:10:47	JKL123MNBV	2	8
684	2024-03-17	07:11:40	10:11:40	FGH456TYUI	8	2
685	2024-03-17	05:03:33	08:03:33	JKL012LMNO	10	8
686	2024-03-18	10:22:00	17:22:00	RTY890QRST	10	6
687	2024-03-18	13:37:34	21:07:34	BVC345UIOA	15	4
688	2024-03-18	08:53:17	12:23:17	RTY901CVBN	2	4
689	2024-03-18	12:58:46	16:58:46	ASD123NBVC	14	2
690	2024-03-18	13:52:36	19:52:36	ABC123XYZA	7	3
691	2024-03-18	09:12:48	17:12:48	ASD123NBVC	5	7
692	2024-03-18	06:31:49	10:01:49	HJK789UIOP	8	6
693	2024-03-18	08:08:31	12:38:31	BNM456UIOP	4	5
694	2024-03-19	10:22:55	14:52:55	VBN789XCVB	6	3
695	2024-03-19	06:32:03	09:32:03	QWE678FGRT	1	3
696	2024-03-19	09:31:02	12:01:02	RTY890QRST	6	4
697	2024-03-20	08:08:31	13:38:31	RTY901CVBN	6	8
698	2024-03-20	13:15:50	18:45:50	UIO012MKLP	13	7
699	2024-03-20	12:15:29	16:15:29	QWE012CVBN	3	2
700	2024-03-20	12:42:07	20:42:07	BVC345UIOA	1	10
701	2024-03-20	06:28:53	09:28:53	GHI789UVWX	7	6
702	2024-03-20	11:17:49	15:17:49	JKL012LMNO	5	3
703	2024-03-23	06:59:40	08:59:40	FGH890PLOI	9	4
704	2024-03-23	09:12:48	13:12:48	UIO012MKLP	3	7
705	2024-03-23	06:09:55	09:39:55	ASD123NBVC	14	5
706	2024-03-23	08:37:53	11:07:53	UIO234GFDS	13	9
707	2024-03-23	06:04:01	12:34:01	FGH890PLOI	15	8
708	2024-03-23	10:01:52	15:31:52	ASD123NBVC	4	2
709	2024-03-23	13:53:27	16:23:27	QWE012CVBN	8	10
710	2024-03-23	12:52:35	18:52:35	JKL123MNBV	12	5
711	2024-03-23	13:44:42	19:44:42	WXY678MNOP	13	3
712	2024-03-26	06:48:11	12:48:11	BVC345UIOA	5	4
713	2024-03-26	09:19:38	13:19:38	DEF456WXYZ	4	3
714	2024-03-26	06:36:07	10:06:07	RTY901CVBN	10	10
715	2024-03-26	11:17:49	19:17:49	VBN567JKLM	10	5
716	2024-03-26	05:33:59	07:33:59	RTY890QRST	5	3
717	2024-03-26	13:44:48	17:14:48	JKL123MNBV	14	10
718	2024-03-27	07:51:14	10:51:14	FGH456TYUI	8	3
719	2024-03-27	11:27:44	14:57:44	RTY890QRST	5	5
720	2024-03-27	09:44:13	12:14:13	JKL123MNBV	3	8
721	2024-03-27	11:10:37	15:40:37	DEF456WXYZ	3	4
722	2024-03-29	06:59:40	13:29:40	RTY901CVBN	1	5
723	2024-03-29	05:39:31	10:39:31	UIO012MKLP	11	7
724	2024-03-29	07:11:40	14:11:40	BVC345UIOA	7	4
725	2024-03-29	07:46:11	10:46:11	VBN789XCVB	13	2
726	2024-03-30	13:44:48	20:44:48	HJK789UIOP	14	4
727	2024-03-30	06:32:05	11:32:05	RTY890QRST	15	7
728	2024-03-30	10:37:59	15:07:59	QWE678FGRT	5	5
729	2024-03-30	11:21:45	16:21:45	ASD123NBVC	10	8
730	2024-03-30	10:14:22	12:14:22	ASD123NBVC	10	10
731	2024-03-31	09:12:48	15:12:48	VBN567JKLM	3	8
732	2024-03-31	13:52:36	21:22:36	QWE901BCDE	1	3
733	2024-04-03	12:41:02	16:11:02	BNM456UIOP	4	10
734	2024-04-07	05:36:15	13:36:15	JKL123MNBV	4	2
735	2024-04-07	06:32:03	10:32:03	VBN567JKLM	8	9
736	2024-04-07	13:53:27	17:23:27	QWE678FGRT	7	10
737	2024-04-07	13:53:07	20:53:07	QWE012CVBN	5	4
738	2024-04-10	12:01:56	18:31:56	CVB567LKJH	14	4
739	2024-04-10	11:44:07	14:44:07	QWE901BCDE	2	5
740	2024-04-10	12:48:39	18:18:39	WXY678MNOP	1	10
741	2024-04-10	09:19:38	15:49:38	WXY678MNOP	10	8
742	2024-04-10	07:11:40	12:41:40	FGH890PLOI	11	7
743	2024-04-11	13:55:12	17:55:12	QWE012CVBN	6	2
744	2024-04-11	11:17:49	13:47:49	JKL012LMNO	1	9
745	2024-04-13	06:31:49	11:31:49	GHI789UVWX	3	2
746	2024-04-13	10:39:41	17:09:41	DEF456WXYZ	13	4
747	2024-04-13	10:01:52	13:01:52	VBN789XCVB	11	9
748	2024-04-13	06:32:03	13:02:03	VBN789XCVB	12	6
749	2024-04-13	08:29:58	15:29:58	GHI789UVWX	1	7
750	2024-04-13	09:12:22	11:42:22	ZXC234FGHI	2	10
751	2024-04-13	12:01:56	19:31:56	VBN567JKLM	10	8
752	2024-04-14	12:41:02	16:11:02	QWE678FGRT	3	2
753	2024-04-14	06:31:49	09:01:49	RTY901CVBN	5	3
754	2024-04-14	11:44:07	14:14:07	UIO234GFDS	10	6
755	2024-04-14	13:32:39	16:02:39	QWE901BCDE	2	4
756	2024-04-15	10:01:52	16:01:52	ZXC234FGHI	3	10
757	2024-04-15	10:31:43	15:01:43	JKL123MNBV	5	5
758	2024-04-15	11:27:40	13:57:40	VBN567JKLM	5	9
759	2024-04-16	11:57:43	15:57:43	DEF456WXYZ	7	4
760	2024-04-16	12:42:07	17:12:07	RTY890QRST	3	2
761	2024-04-16	10:06:19	17:36:19	VBN789XCVB	5	8
762	2024-04-22	10:01:52	12:01:52	QWE901BCDE	14	6
763	2024-04-23	11:37:20	18:37:20	ASD123NBVC	10	3
764	2024-04-23	08:07:49	12:37:49	PQR345STUV	11	10
765	2024-04-23	05:39:31	10:09:31	JKL012LMNO	15	7
766	2024-04-23	09:19:38	17:19:38	PQR345STUV	8	2
767	2024-04-23	13:53:07	21:23:07	CVB567LKJH	4	9
768	2024-04-23	08:17:48	10:47:48	PQR345STUV	12	3
769	2024-04-25	11:10:37	13:10:37	ASD123NBVC	7	5
770	2024-04-25	09:46:06	13:16:06	QWE678FGRT	13	8
771	2024-04-25	13:32:39	18:02:39	PQR345STUV	8	3
772	2024-04-25	07:34:35	15:34:35	FGH890PLOI	11	9
773	2024-04-27	08:07:49	15:37:49	ASD123NBVC	6	5
774	2024-04-27	11:10:37	13:40:37	BNM456UIOP	12	3
775	2024-04-27	10:01:52	15:01:52	QWE901BCDE	7	4
776	2024-04-28	08:37:53	13:07:53	VBN567JKLM	9	9
777	2024-04-28	09:42:31	17:42:31	VBN789XCVB	7	4
778	2024-04-28	08:45:16	16:15:16	PQR345STUV	1	3
779	2024-04-28	13:34:06	16:34:06	VBN789XCVB	10	8
780	2024-05-03	07:11:40	09:11:40	UIO234GFDS	4	6
781	2024-05-03	12:52:35	15:22:35	FGH890PLOI	9	8
782	2024-05-03	11:17:44	18:17:44	ASD123NBVC	12	2
783	2024-05-03	08:45:16	13:15:16	QWE012CVBN	4	7
784	2024-05-03	07:59:32	10:59:32	ASD123NBVC	2	10
785	2024-05-03	10:06:21	18:06:21	UIO234GFDS	7	6
786	2024-05-04	11:39:07	16:39:07	RTY890QRST	15	6
787	2024-05-04	11:17:44	18:17:44	GHI789UVWX	6	7
788	2024-05-04	06:48:11	12:18:11	QWE678FGRT	15	3
789	2024-05-04	06:32:03	09:02:03	PQR345STUV	13	9
790	2024-05-04	10:40:15	18:40:15	PQR345STUV	11	10
791	2024-05-04	11:37:20	16:07:20	VBN789XCVB	10	2
792	2024-05-04	05:36:54	11:06:54	PQR345STUV	13	6
793	2024-05-04	07:55:10	09:55:10	QWE678FGRT	5	8
794	2024-05-04	06:32:05	13:32:05	ZXC234FGHI	3	5
795	2024-05-04	09:16:31	17:16:31	HJK789UIOP	5	9
796	2024-05-05	13:52:36	17:52:36	ZXC234FGHI	15	3
797	2024-05-05	09:12:48	16:12:48	VBN789XCVB	4	9
798	2024-05-05	10:06:19	13:06:19	UIO012MKLP	3	7
799	2024-05-05	11:17:49	18:47:49	QWE901BCDE	5	2
800	2024-05-05	05:36:15	12:06:15	RTY890QRST	14	4
801	2024-05-05	10:22:00	15:52:00	JKL123MNBV	10	10
802	2024-05-05	12:18:17	16:48:17	WXY678MNOP	3	6
803	2024-05-07	10:39:41	15:39:41	QWE012CVBN	8	2
804	2024-05-07	12:01:56	19:31:56	RTY901CVBN	9	8
805	2024-05-07	05:03:33	08:33:33	DEF456WXYZ	3	8
806	2024-05-07	09:19:38	15:49:38	HJK789UIOP	6	7
807	2024-05-08	10:22:55	12:52:55	HJK789UIOP	13	6
808	2024-05-09	09:40:47	14:40:47	HJK789UIOP	9	7
809	2024-05-09	11:27:40	16:27:40	HJK789UIOP	6	9
810	2024-05-09	07:34:35	12:34:35	FGH456TYUI	13	4
811	2024-05-09	08:07:49	12:37:49	FGH890PLOI	2	5
812	2024-05-12	11:43:17	15:43:17	UIO012MKLP	3	5
813	2024-05-12	05:34:04	10:04:04	HJK789UIOP	3	7
814	2024-05-12	10:01:52	15:01:52	RTY890QRST	5	3
815	2024-05-12	06:04:01	09:04:01	WXY678MNOP	13	6
816	2024-05-12	05:34:04	12:34:04	RTY890QRST	5	9
817	2024-05-14	12:41:02	15:11:02	UIO012MKLP	12	10
818	2024-05-14	07:11:40	14:11:40	QWE901BCDE	6	2
819	2024-05-14	06:28:53	09:28:53	FGH456TYUI	5	7
820	2024-05-15	11:44:07	18:14:07	CVB567LKJH	11	2
821	2024-05-15	11:37:20	18:07:20	FGH890PLOI	12	7
822	2024-05-15	07:03:13	12:33:13	JKL012LMNO	4	5
823	2024-05-15	06:32:03	14:32:03	JKL123MNBV	9	10
824	2024-05-15	10:30:39	18:00:39	DEF456WXYZ	8	4
825	2024-05-17	12:18:17	15:18:17	BNM456UIOP	4	8
826	2024-05-17	05:39:31	11:39:31	VBN789XCVB	2	2
827	2024-05-17	08:18:32	15:18:32	FGH456TYUI	8	3
828	2024-05-17	12:01:56	15:31:56	GHI789UVWX	15	10
829	2024-05-17	13:02:23	20:32:23	FGH456TYUI	10	6
830	2024-05-19	10:57:43	15:57:43	QWE012CVBN	7	6
831	2024-05-19	11:37:20	16:37:20	QWE901BCDE	14	8
832	2024-05-24	12:52:35	15:52:35	UIO234GFDS	14	2
833	2024-05-24	07:11:40	09:11:40	RTY890QRST	13	10
834	2024-05-25	06:48:11	11:18:11	PQR345STUV	1	10
835	2024-05-25	06:32:03	13:32:03	ABC123XYZA	14	8
836	2024-05-25	09:54:32	13:24:32	FGH456TYUI	3	5
837	2024-05-25	07:55:10	10:25:10	QWE012CVBN	15	3
838	2024-05-26	07:46:11	13:16:11	WXY678MNOP	14	8
839	2024-05-26	09:12:22	17:12:22	VBN789XCVB	8	5
840	2024-05-26	06:48:11	13:18:11	VBN789XCVB	11	10
841	2024-05-29	10:06:21	13:06:21	WXY678MNOP	9	6
842	2024-05-29	10:44:35	17:44:35	CVB567LKJH	5	5
843	2024-06-01	09:31:02	12:01:02	JKL012LMNO	13	6
844	2024-06-03	13:44:42	20:14:42	JKL012LMNO	13	10
845	2024-06-03	06:28:53	13:28:53	UIO012MKLP	5	3
846	2024-06-03	09:03:54	12:33:54	VBN789XCVB	12	8
847	2024-06-03	10:14:22	16:14:22	VBN789XCVB	6	5
848	2024-06-03	06:11:24	09:41:24	ASD123NBVC	5	4
849	2024-06-03	06:32:05	09:02:05	HJK789UIOP	13	10
850	2024-06-04	13:32:39	18:32:39	QWE901BCDE	13	2
851	2024-06-04	05:51:50	11:21:50	QWE901BCDE	3	4
852	2024-06-04	05:34:04	08:34:04	UIO012MKLP	9	7
853	2024-06-05	10:57:43	16:57:43	BVC345UIOA	1	7
854	2024-06-05	07:51:14	13:21:14	QWE012CVBN	15	3
855	2024-06-05	05:03:33	12:03:33	BVC345UIOA	9	6
856	2024-06-05	11:43:17	13:43:17	ZXC234FGHI	11	4
857	2024-06-05	09:54:32	17:54:32	QWE012CVBN	2	2
858	2024-06-05	07:34:35	15:04:35	DEF456WXYZ	3	10
859	2024-06-05	12:41:02	16:41:02	CVB567LKJH	11	5
860	2024-06-05	12:51:56	16:21:56	UIO012MKLP	5	8
861	2024-06-12	13:58:45	20:58:45	QWE901BCDE	6	2
862	2024-06-12	07:51:14	13:21:14	JKL012LMNO	10	2
863	2024-06-12	06:48:11	14:18:11	VBN567JKLM	2	10
864	2024-06-12	11:20:44	17:20:44	QWE678FGRT	5	9
865	2024-06-10	09:30:00	14:30:00	JKL123MNBV	4	12
866	2024-06-11	08:30:00	15:30:00	DEF456WXYZ	5	12
867	2024-06-12	07:25:00	14:30:00	RTY901CVBN	6	12
868	2024-06-13	13:30:00	21:30:00	ZXC234FGHI	7	12
869	2024-06-14	12:30:00	19:30:00	ASD123NBVC	11	12
870	2024-06-15	12:00:00	20:30:00	PQR345STUV	12	12
871	2024-06-16	11:30:00	16:30:00	BVC345UIOA	1	12
\.


--
-- TOC entry 3426 (class 0 OID 24591)
-- Dependencies: 215
-- Data for Name: utenti; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utenti (id, username, password, ruolo) FROM stdin;
1	admin	password	ADMIN
2	ebemarco	123stella	AUTISTA
4	lora90	passwd	AUTISTA
5	faz98	abclalala	AUTISTA
6	FILETTO	prova_password_scarsa	AUTISTA
9	moirahills	da_cambiare	AUTISTA
7	carlx	ciaone	AUTISTA
8	diltessbnd	sono_una_scorreggiona	AUTISTA
3	maria_stella_paci	pwdpwd	AUTISTA
10	sonia_b	1234xy	AUTISTA
11	lucaemme	changeme	AUTISTA
12	matti_forza	1234mf	AUTISTA
\.


--
-- TOC entry 3441 (class 0 OID 0)
-- Dependencies: 212
-- Name: Linee_NumLinea_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Linee_NumLinea_seq"', 16, true);


--
-- TOC entry 3442 (class 0 OID 0)
-- Dependencies: 214
-- Name: Turni_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Turni_ID_seq"', 871, true);


--
-- TOC entry 3443 (class 0 OID 0)
-- Dependencies: 216
-- Name: Utenti_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Utenti_ID_seq"', 12, true);


--
-- TOC entry 3256 (class 2606 OID 24598)
-- Name: autisti autisti_id_utente_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autisti
    ADD CONSTRAINT autisti_id_utente_key UNIQUE (id_utente);


--
-- TOC entry 3258 (class 2606 OID 24641)
-- Name: autisti autisti_matricola_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autisti
    ADD CONSTRAINT autisti_matricola_key UNIQUE (matricola);


--
-- TOC entry 3260 (class 2606 OID 41016)
-- Name: autisti autisti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autisti
    ADD CONSTRAINT autisti_pkey PRIMARY KEY (id_utente);


--
-- TOC entry 3262 (class 2606 OID 24602)
-- Name: autobus autobus_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autobus
    ADD CONSTRAINT autobus_pkey PRIMARY KEY (targa);


--
-- TOC entry 3264 (class 2606 OID 41032)
-- Name: autobus autobus_targa_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autobus
    ADD CONSTRAINT autobus_targa_key UNIQUE (targa);


--
-- TOC entry 3266 (class 2606 OID 41034)
-- Name: linee linee_num_linea_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.linee
    ADD CONSTRAINT linee_num_linea_key UNIQUE (num_linea);


--
-- TOC entry 3268 (class 2606 OID 24604)
-- Name: linee linee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.linee
    ADD CONSTRAINT linee_pkey PRIMARY KEY (num_linea);


--
-- TOC entry 3270 (class 2606 OID 41036)
-- Name: turni turni_id_targa_num_linea_id_utente_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.turni
    ADD CONSTRAINT turni_id_targa_num_linea_id_utente_key UNIQUE (id, targa, num_linea, id_utente);


--
-- TOC entry 3272 (class 2606 OID 24606)
-- Name: turni turni_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.turni
    ADD CONSTRAINT turni_pkey PRIMARY KEY (id);


--
-- TOC entry 3274 (class 2606 OID 41018)
-- Name: utenti utenti_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utenti
    ADD CONSTRAINT utenti_id_key UNIQUE (id);


--
-- TOC entry 3276 (class 2606 OID 24608)
-- Name: utenti utenti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utenti
    ADD CONSTRAINT utenti_pkey PRIMARY KEY (id);


--
-- TOC entry 3277 (class 2606 OID 24609)
-- Name: autisti autisti_utenti_id_utente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autisti
    ADD CONSTRAINT autisti_utenti_id_utente_fkey FOREIGN KEY (id_utente) REFERENCES public.utenti(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3278 (class 2606 OID 41026)
-- Name: turni turni_autisti_id_utente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.turni
    ADD CONSTRAINT turni_autisti_id_utente_fkey FOREIGN KEY (id_utente) REFERENCES public.autisti(id_utente) NOT VALID;


--
-- TOC entry 3279 (class 2606 OID 24619)
-- Name: turni turni_autobus_targa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.turni
    ADD CONSTRAINT turni_autobus_targa_fkey FOREIGN KEY (targa) REFERENCES public.autobus(targa) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3444 (class 0 OID 0)
-- Dependencies: 3279
-- Name: CONSTRAINT turni_autobus_targa_fkey ON turni; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT turni_autobus_targa_fkey ON public.turni IS 'Reference tra tabella Turni e tabella Autobus';


--
-- TOC entry 3280 (class 2606 OID 24624)
-- Name: turni turni_linee_num_linea_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.turni
    ADD CONSTRAINT turni_linee_num_linea_fkey FOREIGN KEY (num_linea) REFERENCES public.linee(num_linea) NOT VALID;


-- Completed on 2024-06-18 17:13:04

--
-- PostgreSQL database dump complete
--


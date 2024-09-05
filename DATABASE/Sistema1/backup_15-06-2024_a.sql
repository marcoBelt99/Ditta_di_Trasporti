--
-- PostgreSQL database dump
--

-- Dumped from database version 14.12
-- Dumped by pg_dump version 14.3

-- Started on 2024-06-15 11:45:58

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
-- TOC entry 3433 (class 0 OID 0)
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
-- TOC entry 3434 (class 0 OID 0)
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
-- TOC entry 3435 (class 0 OID 0)
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
-- TOC entry 3436 (class 0 OID 0)
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
-- TOC entry 3437 (class 0 OID 0)
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
-- TOC entry 3438 (class 0 OID 0)
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
12125	Bonfe	Marcello	3366887441	8
13325	Morandi	Gianni	3345687441	15
14444	Beltrame	Moira	3962647895	18
17785	Marin	Lisa	3788523411	20
14799	Biondi	Diletta	3444423411	22
12347	Girlandaio	Philippo	3697499999	6
14143	Pacicca	Stella	3666666422	25
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
2	2024-05-27	08:11:00	13:11:00	WXY678MNOP	5	2
4	2024-05-27	16:13:00	21:30:00	QWE678FGRT	4	4
5	2024-05-28	06:51:00	13:51:00	PQR345STUV	5	2
6	2024-05-29	06:51:00	13:51:00	QWE901BCDE	7	5
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
8	carlx	ciaone	AUTISTA
15	gmor	11223344	AUTISTA
18	moirahills	da_cambiare	AUTISTA
20	marili	123stella	AUTISTA
22	diltessbnd	sono_una_scorreggiona	AUTISTA
6	FILETTO	prova_password_scarsa	AUTISTA
25	maria_stella_paci	pwdpwd	AUTISTA
\.


--
-- TOC entry 3439 (class 0 OID 0)
-- Dependencies: 212
-- Name: Linee_NumLinea_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Linee_NumLinea_seq"', 16, true);


--
-- TOC entry 3440 (class 0 OID 0)
-- Dependencies: 214
-- Name: Turni_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Turni_ID_seq"', 7, true);


--
-- TOC entry 3441 (class 0 OID 0)
-- Dependencies: 216
-- Name: Utenti_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Utenti_ID_seq"', 25, true);


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
-- TOC entry 3442 (class 0 OID 0)
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


-- Completed on 2024-06-15 11:45:58

--
-- PostgreSQL database dump complete
--


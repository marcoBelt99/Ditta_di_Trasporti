--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.3

-- Started on 2024-06-01 14:09:34

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
-- TOC entry 3353 (class 1262 OID 16395)
-- Name: ditta_trasporti; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE ditta_trasporti WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Italian_Italy.1252';


ALTER DATABASE ditta_trasporti OWNER TO postgres;

\connect ditta_trasporti

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
-- TOC entry 3354 (class 0 OID 0)
-- Dependencies: 3353
-- Name: DATABASE ditta_trasporti; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE ditta_trasporti IS 'Database progetto ditta di trasporti';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 211 (class 1259 OID 16409)
-- Name: Autisti; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Autisti" (
    "Matricola" character varying(5) NOT NULL,
    "Cognome" character varying(30) NOT NULL,
    "Nome" character varying(25) NOT NULL,
    "Telefono" character varying(10),
    "ID_Utente" integer NOT NULL
);


ALTER TABLE public."Autisti" OWNER TO postgres;

--
-- TOC entry 3355 (class 0 OID 0)
-- Dependencies: 211
-- Name: TABLE "Autisti"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public."Autisti" IS 'Rappresenta le informazioni relative agli autisti';


--
-- TOC entry 210 (class 1259 OID 16403)
-- Name: Autobus; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Autobus" (
    "Targa" character varying(10) NOT NULL,
    "Modello" character varying(20) NOT NULL,
    "Capienza" integer DEFAULT 0 NOT NULL
);


ALTER TABLE public."Autobus" OWNER TO postgres;

--
-- TOC entry 3356 (class 0 OID 0)
-- Dependencies: 210
-- Name: TABLE "Autobus"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public."Autobus" IS 'Tabella che rappresenta le informazioni sugli autobus';


--
-- TOC entry 209 (class 1259 OID 16396)
-- Name: Linee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Linee" (
    "NumLinea" integer NOT NULL,
    "Destinazione" character varying(30) NOT NULL
);


ALTER TABLE public."Linee" OWNER TO postgres;

--
-- TOC entry 3357 (class 0 OID 0)
-- Dependencies: 209
-- Name: TABLE "Linee"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public."Linee" IS 'Tabella delle linee di percorrenza dei vari autobus.';


--
-- TOC entry 216 (class 1259 OID 73874)
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
-- TOC entry 3358 (class 0 OID 0)
-- Dependencies: 216
-- Name: Linee_NumLinea_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Linee_NumLinea_seq" OWNED BY public."Linee"."NumLinea";


--
-- TOC entry 213 (class 1259 OID 16415)
-- Name: Turni; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Turni" (
    "ID" bigint NOT NULL,
    "Data" date NOT NULL,
    "OraInizio" time without time zone NOT NULL,
    "OraFine" time without time zone NOT NULL,
    "Targa" character varying(10) NOT NULL,
    "Matricola" character varying(5) NOT NULL,
    "NumLinea" integer NOT NULL
);


ALTER TABLE public."Turni" OWNER TO postgres;

--
-- TOC entry 3359 (class 0 OID 0)
-- Dependencies: 213
-- Name: TABLE "Turni"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public."Turni" IS 'Rappresenta i turni dei vari autisti';


--
-- TOC entry 212 (class 1259 OID 16414)
-- Name: Turni_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public."Turni" ALTER COLUMN "ID" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Turni_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 215 (class 1259 OID 73804)
-- Name: Utenti; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Utenti" (
    "ID" integer NOT NULL,
    "Username" character varying(30) NOT NULL,
    "Password" character varying(30) NOT NULL,
    "Ruolo" character varying(7) NOT NULL
);


ALTER TABLE public."Utenti" OWNER TO postgres;

--
-- TOC entry 3360 (class 0 OID 0)
-- Dependencies: 215
-- Name: TABLE "Utenti"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public."Utenti" IS 'Per gestire i ruoli utente.
Il primo record, con ID=1 è riservato all''admin dell''applicazione';


--
-- TOC entry 214 (class 1259 OID 73803)
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
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 214
-- Name: Utenti_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Utenti_ID_seq" OWNED BY public."Utenti"."ID";


--
-- TOC entry 3182 (class 2604 OID 73875)
-- Name: Linee NumLinea; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Linee" ALTER COLUMN "NumLinea" SET DEFAULT nextval('public."Linee_NumLinea_seq"'::regclass);


--
-- TOC entry 3184 (class 2604 OID 73807)
-- Name: Utenti ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Utenti" ALTER COLUMN "ID" SET DEFAULT nextval('public."Utenti_ID_seq"'::regclass);


--
-- TOC entry 3342 (class 0 OID 16409)
-- Dependencies: 211
-- Data for Name: Autisti; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Autisti" ("Matricola", "Cognome", "Nome", "Telefono", "ID_Utente") VALUES ('14888', 'Beltrame', 'Marco', '3403183848', 2);
INSERT INTO public."Autisti" ("Matricola", "Cognome", "Nome", "Telefono", "ID_Utente") VALUES ('14566', 'Effron', 'Zack', '3217475569', 3);
INSERT INTO public."Autisti" ("Matricola", "Cognome", "Nome", "Telefono", "ID_Utente") VALUES ('14579', 'Baretta', 'Laura', '3280140209', 4);


--
-- TOC entry 3341 (class 0 OID 16403)
-- Dependencies: 210
-- Data for Name: Autobus; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('ABC123XYZA', 'MB Sprinter', 20);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('DEF456WXYZ', 'Volvo 9700', 50);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('GHI789UVWX', 'MAN Lion''s City', 30);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('JKL012LMNO', 'Iveco Crossway', 40);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('PQR345STUV', 'Scania Touring', 60);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('WXY678MNOP', 'Setra S 431 DT', 45);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('QWE901BCDE', 'Solaris Urbino', 35);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('ZXC234FGHI', 'Neoplan Cityliner', 55);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('VBN567JKLM', 'Van Hool TX', 25);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('RTY890QRST', 'Irizar i8', 65);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('ASD123NBVC', 'VDL Futura', 30);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('FGH456TYUI', 'Temsa Safari HD', 50);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('HJK789UIOP', 'MCI J4500', 40);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('UIO012MKLP', 'Dennis Trident 2', 35);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('BVC345UIOA', 'Wright Eclipse', 60);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('QWE678FGRT', 'Man NL202', 45);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('RTY901CVBN', 'VDL Berkhof', 55);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('UIO234GFDS', 'Volgren CR228L', 70);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('CVB567LKJH', 'Optare Spectra', 30);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('FGH890PLOI', 'Alexander Dennis', 50);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('JKL123MNBV', 'Scania OmniExpress', 35);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('BNM456UIOP', 'MB Citaro', 65);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('VBN789XCVB', 'Ikarus EAG E94', 40);
INSERT INTO public."Autobus" ("Targa", "Modello", "Capienza") VALUES ('QWE012CVBN', 'BYD K9', 30);


--
-- TOC entry 3340 (class 0 OID 16396)
-- Dependencies: 209
-- Data for Name: Linee; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (1, 'Ferrara - Stazione');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (2, 'Ferrara - Aleotti');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (3, 'Ferrara - Centro');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (4, 'Ferrara - Nord');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (5, 'Ferrara - Sud');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (6, 'Ferrara - Est');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (7, 'Ferrara - Ovest');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (8, 'Ferrara - Fiere');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (9, 'Ferrara - Boschetti');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (10, 'Ferrara - Cona');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (11, 'Rovigo');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (12, 'Adria');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (13, 'Ariano nel Polesine');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (14, 'Porto Tolle');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (15, 'Chioggia');
INSERT INTO public."Linee" ("NumLinea", "Destinazione") VALUES (16, 'Padova');


--
-- TOC entry 3344 (class 0 OID 16415)
-- Dependencies: 213
-- Data for Name: Turni; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Turni" ("ID", "Data", "OraInizio", "OraFine", "Targa", "Matricola", "NumLinea") OVERRIDING SYSTEM VALUE VALUES (1, '2024-05-27', '08:11:00', '13:11:00', 'WXY678MNOP', '14888', 5);
INSERT INTO public."Turni" ("ID", "Data", "OraInizio", "OraFine", "Targa", "Matricola", "NumLinea") OVERRIDING SYSTEM VALUE VALUES (2, '2024-05-27', '11:00:00', '17:30:00', 'PQR345STUV', '14566', 10);
INSERT INTO public."Turni" ("ID", "Data", "OraInizio", "OraFine", "Targa", "Matricola", "NumLinea") OVERRIDING SYSTEM VALUE VALUES (3, '2024-05-27', '16:13:00', '21:30:00', 'QWE678FGRT', '14579', 4);
INSERT INTO public."Turni" ("ID", "Data", "OraInizio", "OraFine", "Targa", "Matricola", "NumLinea") OVERRIDING SYSTEM VALUE VALUES (4, '2024-05-28', '06:51:00', '13:51:00', 'PQR345STUV', '14888', 5);


--
-- TOC entry 3346 (class 0 OID 73804)
-- Dependencies: 215
-- Data for Name: Utenti; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Utenti" ("ID", "Username", "Password", "Ruolo") VALUES (1, 'admin', 'password', 'ADMIN');
INSERT INTO public."Utenti" ("ID", "Username", "Password", "Ruolo") VALUES (2, 'ebemarco', '123stella', 'AUTISTA');
INSERT INTO public."Utenti" ("ID", "Username", "Password", "Ruolo") VALUES (3, 'zack23', 'cambiami', 'AUTISTA');
INSERT INTO public."Utenti" ("ID", "Username", "Password", "Ruolo") VALUES (4, 'lora90', 'passwd', 'AUTISTA');


--
-- TOC entry 3362 (class 0 OID 0)
-- Dependencies: 216
-- Name: Linee_NumLinea_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Linee_NumLinea_seq"', 16, true);


--
-- TOC entry 3363 (class 0 OID 0)
-- Dependencies: 212
-- Name: Turni_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Turni_ID_seq"', 4, true);


--
-- TOC entry 3364 (class 0 OID 0)
-- Dependencies: 214
-- Name: Utenti_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Utenti_ID_seq"', 4, true);


--
-- TOC entry 3190 (class 2606 OID 73811)
-- Name: Autisti Autisti_ID_Utente_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Autisti"
    ADD CONSTRAINT "Autisti_ID_Utente_key" UNIQUE ("ID_Utente");


--
-- TOC entry 3192 (class 2606 OID 16413)
-- Name: Autisti Autisti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Autisti"
    ADD CONSTRAINT "Autisti_pkey" PRIMARY KEY ("Matricola");


--
-- TOC entry 3188 (class 2606 OID 73828)
-- Name: Autobus Autobus_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Autobus"
    ADD CONSTRAINT "Autobus_pkey" PRIMARY KEY ("Targa");


--
-- TOC entry 3186 (class 2606 OID 73880)
-- Name: Linee Linee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Linee"
    ADD CONSTRAINT "Linee_pkey" PRIMARY KEY ("NumLinea");


--
-- TOC entry 3194 (class 2606 OID 16419)
-- Name: Turni Turni_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Turni"
    ADD CONSTRAINT "Turni_pkey" PRIMARY KEY ("ID");


--
-- TOC entry 3196 (class 2606 OID 73809)
-- Name: Utenti Utenti_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Utenti"
    ADD CONSTRAINT "Utenti_pkey" PRIMARY KEY ("ID");


--
-- TOC entry 3197 (class 2606 OID 73812)
-- Name: Autisti Autisti_ID_Utente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Autisti"
    ADD CONSTRAINT "Autisti_ID_Utente_fkey" FOREIGN KEY ("ID_Utente") REFERENCES public."Utenti"("ID") ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3198 (class 2606 OID 16425)
-- Name: Turni Turni_Autisti_Matricola; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Turni"
    ADD CONSTRAINT "Turni_Autisti_Matricola" FOREIGN KEY ("Matricola") REFERENCES public."Autisti"("Matricola") ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3200 (class 2606 OID 73890)
-- Name: Turni Turni_Autobus_Targa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Turni"
    ADD CONSTRAINT "Turni_Autobus_Targa" FOREIGN KEY ("Targa") REFERENCES public."Autobus"("Targa") ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3365 (class 0 OID 0)
-- Dependencies: 3200
-- Name: CONSTRAINT "Turni_Autobus_Targa" ON "Turni"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT "Turni_Autobus_Targa" ON public."Turni" IS 'Reference tra tabella Turni e tabella Autobus';


--
-- TOC entry 3199 (class 2606 OID 73881)
-- Name: Turni Turni_NumLinea_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Turni"
    ADD CONSTRAINT "Turni_NumLinea_fkey" FOREIGN KEY ("NumLinea") REFERENCES public."Linee"("NumLinea") NOT VALID;


-- Completed on 2024-06-01 14:09:34

--
-- PostgreSQL database dump complete
--


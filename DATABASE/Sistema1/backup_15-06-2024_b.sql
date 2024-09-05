--
-- PostgreSQL database dump
--

-- Dumped from database version 14.12
-- Dumped by pg_dump version 14.3

-- Started on 2024-06-15 11:49:10

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
-- TOC entry 3420 (class 0 OID 24576)
-- Dependencies: 209
-- Data for Name: autisti; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.autisti VALUES ('14888', 'Beltrame', 'Marco', '3403183848', 2);
INSERT INTO public.autisti VALUES ('14579', 'Baretta', 'Laura', '3280140209', 4);
INSERT INTO public.autisti VALUES ('14882', 'Romagnollo', 'Fasil', '3213183748', 5);
INSERT INTO public.autisti VALUES ('12125', 'Bonfe', 'Marcello', '3366887441', 8);
INSERT INTO public.autisti VALUES ('13325', 'Morandi', 'Gianni', '3345687441', 15);
INSERT INTO public.autisti VALUES ('14444', 'Beltrame', 'Moira', '3962647895', 18);
INSERT INTO public.autisti VALUES ('17785', 'Marin', 'Lisa', '3788523411', 20);
INSERT INTO public.autisti VALUES ('14799', 'Biondi', 'Diletta', '3444423411', 22);
INSERT INTO public.autisti VALUES ('12347', 'Girlandaio', 'Philippo', '3697499999', 6);
INSERT INTO public.autisti VALUES ('14143', 'Pacicca', 'Stella', '3666666422', 25);


--
-- TOC entry 3421 (class 0 OID 24579)
-- Dependencies: 210
-- Data for Name: autobus; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.autobus VALUES ('ABC123XYZA', 'MB Sprinter', 20);
INSERT INTO public.autobus VALUES ('DEF456WXYZ', 'Volvo 9700', 50);
INSERT INTO public.autobus VALUES ('GHI789UVWX', 'MAN Lion''s City', 30);
INSERT INTO public.autobus VALUES ('JKL012LMNO', 'Iveco Crossway', 40);
INSERT INTO public.autobus VALUES ('PQR345STUV', 'Scania Touring', 60);
INSERT INTO public.autobus VALUES ('WXY678MNOP', 'Setra S 431 DT', 45);
INSERT INTO public.autobus VALUES ('QWE901BCDE', 'Solaris Urbino', 35);
INSERT INTO public.autobus VALUES ('ZXC234FGHI', 'Neoplan Cityliner', 55);
INSERT INTO public.autobus VALUES ('VBN567JKLM', 'Van Hool TX', 25);
INSERT INTO public.autobus VALUES ('RTY890QRST', 'Irizar i8', 65);
INSERT INTO public.autobus VALUES ('ASD123NBVC', 'VDL Futura', 30);
INSERT INTO public.autobus VALUES ('FGH456TYUI', 'Temsa Safari HD', 50);
INSERT INTO public.autobus VALUES ('HJK789UIOP', 'MCI J4500', 40);
INSERT INTO public.autobus VALUES ('UIO012MKLP', 'Dennis Trident 2', 35);
INSERT INTO public.autobus VALUES ('BVC345UIOA', 'Wright Eclipse', 60);
INSERT INTO public.autobus VALUES ('QWE678FGRT', 'Man NL202', 45);
INSERT INTO public.autobus VALUES ('RTY901CVBN', 'VDL Berkhof', 55);
INSERT INTO public.autobus VALUES ('UIO234GFDS', 'Volgren CR228L', 70);
INSERT INTO public.autobus VALUES ('CVB567LKJH', 'Optare Spectra', 30);
INSERT INTO public.autobus VALUES ('FGH890PLOI', 'Alexander Dennis', 50);
INSERT INTO public.autobus VALUES ('JKL123MNBV', 'Scania OmniExpress', 35);
INSERT INTO public.autobus VALUES ('BNM456UIOP', 'MB Citaro', 65);
INSERT INTO public.autobus VALUES ('VBN789XCVB', 'Ikarus EAG E94', 40);
INSERT INTO public.autobus VALUES ('QWE012CVBN', 'BYD K9', 30);


--
-- TOC entry 3422 (class 0 OID 24583)
-- Dependencies: 211
-- Data for Name: linee; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.linee VALUES (1, 'Ferrara - Stazione');
INSERT INTO public.linee VALUES (2, 'Ferrara - Aleotti');
INSERT INTO public.linee VALUES (3, 'Ferrara - Centro');
INSERT INTO public.linee VALUES (4, 'Ferrara - Nord');
INSERT INTO public.linee VALUES (5, 'Ferrara - Sud');
INSERT INTO public.linee VALUES (6, 'Ferrara - Est');
INSERT INTO public.linee VALUES (7, 'Ferrara - Ovest');
INSERT INTO public.linee VALUES (8, 'Ferrara - Fiere');
INSERT INTO public.linee VALUES (9, 'Ferrara - Boschetti');
INSERT INTO public.linee VALUES (10, 'Ferrara - Cona');
INSERT INTO public.linee VALUES (11, 'Rovigo');
INSERT INTO public.linee VALUES (12, 'Adria');
INSERT INTO public.linee VALUES (13, 'Ariano nel Polesine');
INSERT INTO public.linee VALUES (14, 'Porto Tolle');
INSERT INTO public.linee VALUES (15, 'Chioggia');
INSERT INTO public.linee VALUES (16, 'Padova');


--
-- TOC entry 3424 (class 0 OID 24587)
-- Dependencies: 213
-- Data for Name: turni; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.turni OVERRIDING SYSTEM VALUE VALUES (2, '2024-05-27', '08:11:00', '13:11:00', 'WXY678MNOP', 5, 2);
INSERT INTO public.turni OVERRIDING SYSTEM VALUE VALUES (4, '2024-05-27', '16:13:00', '21:30:00', 'QWE678FGRT', 4, 4);
INSERT INTO public.turni OVERRIDING SYSTEM VALUE VALUES (5, '2024-05-28', '06:51:00', '13:51:00', 'PQR345STUV', 5, 2);
INSERT INTO public.turni OVERRIDING SYSTEM VALUE VALUES (6, '2024-05-29', '06:51:00', '13:51:00', 'QWE901BCDE', 7, 5);


--
-- TOC entry 3426 (class 0 OID 24591)
-- Dependencies: 215
-- Data for Name: utenti; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utenti VALUES (1, 'admin', 'password', 'ADMIN');
INSERT INTO public.utenti VALUES (2, 'ebemarco', '123stella', 'AUTISTA');
INSERT INTO public.utenti VALUES (4, 'lora90', 'passwd', 'AUTISTA');
INSERT INTO public.utenti VALUES (5, 'faz98', 'abclalala', 'AUTISTA');
INSERT INTO public.utenti VALUES (8, 'carlx', 'ciaone', 'AUTISTA');
INSERT INTO public.utenti VALUES (15, 'gmor', '11223344', 'AUTISTA');
INSERT INTO public.utenti VALUES (18, 'moirahills', 'da_cambiare', 'AUTISTA');
INSERT INTO public.utenti VALUES (20, 'marili', '123stella', 'AUTISTA');
INSERT INTO public.utenti VALUES (22, 'diltessbnd', 'sono_una_scorreggiona', 'AUTISTA');
INSERT INTO public.utenti VALUES (6, 'FILETTO', 'prova_password_scarsa', 'AUTISTA');
INSERT INTO public.utenti VALUES (25, 'maria_stella_paci', 'pwdpwd', 'AUTISTA');


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

SELECT pg_catalog.setval('public."Turni_ID_seq"', 7, true);


--
-- TOC entry 3443 (class 0 OID 0)
-- Dependencies: 216
-- Name: Utenti_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Utenti_ID_seq"', 25, true);


-- Completed on 2024-06-15 11:49:10

--
-- PostgreSQL database dump complete
--


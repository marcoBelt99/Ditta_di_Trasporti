--
-- PostgreSQL database dump
--

-- Dumped from database version 14.12
-- Dumped by pg_dump version 14.3

-- Started on 2024-06-06 20:56:38

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
-- TOC entry 3396 (class 1262 OID 16384)
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
-- TOC entry 3397 (class 0 OID 0)
-- Dependencies: 3396
-- Name: DATABASE ditta_trasporti; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE ditta_trasporti IS 'Database progetto ditta di trasporti';


SET default_tablespace = '';

SET default_table_access_method = heap;

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
    id_utente character varying(5) NOT NULL,
    num_linea integer NOT NULL
);


ALTER TABLE public.turni OWNER TO postgres;

--
-- TOC entry 3398 (class 0 OID 0)
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
-- TOC entry 3389 (class 0 OID 24587)
-- Dependencies: 213
-- Data for Name: turni; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.turni (id, data, ora_inizio, ora_fine, targa, id_utente, num_linea) OVERRIDING SYSTEM VALUE VALUES (1, '2024-05-27', '08:11:00', '13:11:00', 'WXY678MNOP', '14888', 5);
INSERT INTO public.turni (id, data, ora_inizio, ora_fine, targa, id_utente, num_linea) OVERRIDING SYSTEM VALUE VALUES (2, '2024-05-27', '11:00:00', '17:30:00', 'PQR345STUV', '14566', 10);
INSERT INTO public.turni (id, data, ora_inizio, ora_fine, targa, id_utente, num_linea) OVERRIDING SYSTEM VALUE VALUES (3, '2024-05-27', '16:13:00', '21:30:00', 'QWE678FGRT', '14579', 4);
INSERT INTO public.turni (id, data, ora_inizio, ora_fine, targa, id_utente, num_linea) OVERRIDING SYSTEM VALUE VALUES (4, '2024-05-28', '06:51:00', '13:51:00', 'PQR345STUV', '14888', 5);
INSERT INTO public.turni (id, data, ora_inizio, ora_fine, targa, id_utente, num_linea) OVERRIDING SYSTEM VALUE VALUES (5, '2024-05-29', '06:51:00', '13:51:00', 'QWE901BCDE', '14882', 7);
INSERT INTO public.turni (id, data, ora_inizio, ora_fine, targa, id_utente, num_linea) OVERRIDING SYSTEM VALUE VALUES (7, '2024-05-29', '09:15:00', '16:45:00', 'JKL123MNBV', '14566', 5);


--
-- TOC entry 3399 (class 0 OID 0)
-- Dependencies: 214
-- Name: Turni_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Turni_ID_seq"', 7, true);


--
-- TOC entry 3247 (class 2606 OID 24606)
-- Name: turni Turni_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.turni
    ADD CONSTRAINT "Turni_pkey" PRIMARY KEY (id);


--
-- TOC entry 3248 (class 2606 OID 24619)
-- Name: turni Turni_Autobus_Targa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.turni
    ADD CONSTRAINT "Turni_Autobus_Targa" FOREIGN KEY (targa) REFERENCES public.autobus(targa) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3400 (class 0 OID 0)
-- Dependencies: 3248
-- Name: CONSTRAINT "Turni_Autobus_Targa" ON turni; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT "Turni_Autobus_Targa" ON public.turni IS 'Reference tra tabella Turni e tabella Autobus';


--
-- TOC entry 3249 (class 2606 OID 24624)
-- Name: turni Turni_NumLinea_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.turni
    ADD CONSTRAINT "Turni_NumLinea_fkey" FOREIGN KEY (num_linea) REFERENCES public.linee(num_linea) NOT VALID;


-- Completed on 2024-06-06 20:56:38

--
-- PostgreSQL database dump complete
--


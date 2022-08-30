
CREATE TABLE public.drone (
    id integer NOT NULL,
    serial_number character varying(100) NOT NULL,
    model character varying(50) NOT NULL,
    weight_limit integer NOT NULL,
    registered_date timestamp without time zone NOT NULL
);

CREATE TABLE public.drone_battery_history (
    id integer NOT NULL,
    drone_id integer NOT NULL,
    battery_capacity integer NOT NULL,
    date_time timestamp without time zone NOT NULL
);



CREATE SEQUENCE public.drone_battery_history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.drone_battery_history_id_seq OWNED BY public.drone_battery_history.id;


CREATE SEQUENCE public.drone_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.drone_id_seq OWNED BY public.drone.id;

CREATE TABLE public.drone_medication (
    id integer NOT NULL,
    drone_id integer NOT NULL,
    medication_id integer NOT NULL,
    quantity integer NOT NULL
);


CREATE SEQUENCE public.drone_medication_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.drone_medication_id_seq OWNED BY public.drone_medication.id;

CREATE TABLE public.drone_status (
    id integer NOT NULL,
    drone_id integer NOT NULL,
    battery_capacity integer DEFAULT 0 NOT NULL,
    state character varying(50) NOT NULL
);


CREATE SEQUENCE public.drone_state_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.drone_state_id_seq OWNED BY public.drone_status.id;

CREATE TABLE public.medication (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    weight integer NOT NULL,
    code character varying(255) NOT NULL,
    image character varying(255)
);

COMMENT ON COLUMN public.medication.image IS 'CDN image path';

CREATE SEQUENCE public.medication_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.medication_id_seq OWNED BY public.medication.id;


--
-- Name: drone id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drone ALTER COLUMN id SET DEFAULT nextval('public.drone_id_seq'::regclass);


--
-- Name: drone_battery_history id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drone_battery_history ALTER COLUMN id SET DEFAULT nextval('public.drone_battery_history_id_seq'::regclass);


--
-- Name: drone_medication id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drone_medication ALTER COLUMN id SET DEFAULT nextval('public.drone_medication_id_seq'::regclass);


--
-- Name: drone_status id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drone_status ALTER COLUMN id SET DEFAULT nextval('public.drone_state_id_seq'::regclass);


--
-- Name: medication id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication ALTER COLUMN id SET DEFAULT nextval('public.medication_id_seq'::regclass);


--
-- Name: drone_battery_history drone_battery_history_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drone_battery_history
    ADD CONSTRAINT drone_battery_history_pkey PRIMARY KEY (id);


--
-- Name: drone_medication drone_medication_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drone_medication
    ADD CONSTRAINT drone_medication_pkey PRIMARY KEY (id);


--
-- Name: drone drone_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drone
    ADD CONSTRAINT drone_pkey PRIMARY KEY (id);


--
-- Name: drone_status drone_state_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drone_status
    ADD CONSTRAINT drone_state_pkey PRIMARY KEY (id);


--
-- Name: medication medication_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medication
    ADD CONSTRAINT medication_pkey PRIMARY KEY (id);


--
-- Name: drone_model; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX drone_model ON public.drone USING btree (model);


--
-- Name: drone_serial_number; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX drone_serial_number ON public.drone USING btree (serial_number);


--
-- Name: medication_code; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX medication_code ON public.medication USING btree (code);


--
-- Name: medication_name; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX medication_name ON public.medication USING btree (name);


--
-- Name: drone_battery_history fkdrone_batt909652; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drone_battery_history
    ADD CONSTRAINT fkdrone_batt909652 FOREIGN KEY (drone_id) REFERENCES public.drone(id);


--
-- Name: drone_status fkdrone_stat467675; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.drone_status
    ADD CONSTRAINT fkdrone_stat467675 FOREIGN KEY (drone_id) REFERENCES public.drone(id);



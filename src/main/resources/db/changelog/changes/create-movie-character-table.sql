CREATE TABLE IF NOT EXISTS public.movie_characters
(
    id bigint NOT NULL,
    external_id bigint NOT NULL,
    name character varying(256) NOT NULL,
    species character varying(256) NOT NULL,
    type character varying(256) NOT NULL,
    url character varying(256) NOT NULL,
    created character varying(256) NOT NULL,
    gender character varying(256) NOT NULL,
    status character varying(256) NOT NULL,
    CONSTRAINT movie_character_pk PRIMARY KEY (id)
);

--rollback DROP TABLE movie_character;
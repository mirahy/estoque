-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.1-beta
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.com.br
-- Model Author: ---


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: new_database | type: DATABASE --
-- -- DROP DATABASE IF EXISTS new_database;
-- CREATE DATABASE new_database
-- ;
-- -- ddl-end --
-- 

-- object: public.marca | type: TABLE --
-- DROP TABLE IF EXISTS public.marca CASCADE;
CREATE TABLE public.marca(
	id integer NOT NULL,
	nome varchar(255) NOT NULL,
	CONSTRAINT pk_marca PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.marca OWNER TO postgres;
-- ddl-end --

INSERT INTO public.marca (id, nome) VALUES (E'1', E'Leite Moça');
-- ddl-end --
INSERT INTO public.marca (id, nome) VALUES (E'2', E'Isopor');
-- ddl-end --
INSERT INTO public.marca (id, nome) VALUES (E'3', E'Cotonete');
-- ddl-end --
INSERT INTO public.marca (id, nome) VALUES (E'4', E'Maizena');
-- ddl-end --
INSERT INTO public.marca (id, nome) VALUES (E'5', E'Bombril');
-- ddl-end --

-- object: public.unidade_medida | type: TABLE --
-- DROP TABLE IF EXISTS public.unidade_medida CASCADE;
CREATE TABLE public.unidade_medida(
	id integer NOT NULL,
	nome varchar(255) NOT NULL,
	fracionado boolean NOT NULL DEFAULT false,
	CONSTRAINT pk_unidade_medida PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.unidade_medida OWNER TO postgres;
-- ddl-end --

-- object: public.grupo_produto | type: TABLE --
-- DROP TABLE IF EXISTS public.grupo_produto CASCADE;
CREATE TABLE public.grupo_produto(
	id integer NOT NULL,
	nome varchar(255) NOT NULL,
	sub_grupo integer,
	CONSTRAINT pk_grupo_produto PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.grupo_produto OWNER TO postgres;
-- ddl-end --

-- object: public.produto | type: TABLE --
-- DROP TABLE IF EXISTS public.produto CASCADE;
CREATE TABLE public.produto(
	id integer NOT NULL,
	nome varchar(255) NOT NULL,
	descricao text,
	preco numeric(12,4) NOT NULL DEFAULT 0.0,
	estoque numeric(12,4) DEFAULT 0.0,
	estoque_minimo numeric(12,4) DEFAULT 0,
	data_ultima_compra timestamp,
	id_marca integer,
	id_unidade_medida integer,
	id_grupo_produto integer
);
-- ddl-end --
ALTER TABLE public.produto OWNER TO postgres;
-- ddl-end --

-- object: marca_fk | type: CONSTRAINT --
-- ALTER TABLE public.produto DROP CONSTRAINT IF EXISTS marca_fk CASCADE;
ALTER TABLE public.produto ADD CONSTRAINT marca_fk FOREIGN KEY (id_marca)
REFERENCES public.marca (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: unidade_medida_fk | type: CONSTRAINT --
-- ALTER TABLE public.produto DROP CONSTRAINT IF EXISTS unidade_medida_fk CASCADE;
ALTER TABLE public.produto ADD CONSTRAINT unidade_medida_fk FOREIGN KEY (id_unidade_medida)
REFERENCES public.unidade_medida (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: grupo_produto_fk | type: CONSTRAINT --
-- ALTER TABLE public.produto DROP CONSTRAINT IF EXISTS grupo_produto_fk CASCADE;
ALTER TABLE public.produto ADD CONSTRAINT grupo_produto_fk FOREIGN KEY (id_grupo_produto)
REFERENCES public.grupo_produto (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_subgrupo | type: CONSTRAINT --
-- ALTER TABLE public.grupo_produto DROP CONSTRAINT IF EXISTS fk_subgrupo CASCADE;
ALTER TABLE public.grupo_produto ADD CONSTRAINT fk_subgrupo FOREIGN KEY (sub_grupo)
REFERENCES public.grupo_produto (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE NO ACTION;
-- ddl-end --



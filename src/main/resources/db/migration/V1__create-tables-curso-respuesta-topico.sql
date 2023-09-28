-- Definición de tabla para la clase 'Curso'
CREATE TABLE cursos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) not null ,
    categoria VARCHAR(255) not null ,
    PRIMARY KEY (id)
);
-- Definición de tabla para la clase 'Usuario'
CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) not null ,
    email VARCHAR(255) not null ,
    clave VARCHAR(255) not null ,
    PRIMARY KEY (id)
);
-- Definición de tabla para la clase 'Topico'
CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) not null unique,
    mensaje VARCHAR(255) not null unique,
    fecha_creacion datetime not null ,
    status VARCHAR(255) not null ,
    curso_id BIGINT not null ,
    autor_id BIGINT not null ,
    PRIMARY KEY (id),
    constraint fk_topicos_autor_id foreign key (autor_id) references usuarios(id),
    constraint fk_topicos_curso_id foreign key (curso_id) references cursos(id)

);
-- Definición de tabla para la clase 'Respuesta'
CREATE TABLE respuestas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(255) not null ,
    fecha_creacion datetime not null ,
    solucion TINYINT(1),
    topico_id BIGINT not null ,
    autor_id BIGINT not null ,
    PRIMARY KEY (id),
    constraint fk_respuestas_topico_id foreign key (topico_id) references topicos(id),
    constraint fk_respuestas_autor_id foreign key (autor_id) references usuarios(id)
);










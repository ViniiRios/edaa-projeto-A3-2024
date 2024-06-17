CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

INSERT INTO usuarios (nome) VALUES ('João'), ('Maria'), ('Carlos');
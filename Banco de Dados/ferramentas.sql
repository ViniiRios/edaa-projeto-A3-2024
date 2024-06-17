CREATE TABLE ferramentas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    disponivel BOOLEAN DEFAULT TRUE
);

INSERT INTO ferramentas (nome) VALUES ('Chave de fenda'), ('Martelo'), ('Alicate');
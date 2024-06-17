CREATE TABLE emprestimos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ferramenta_id INT,
    usuario_id INT,
    FOREIGN KEY (ferramenta_id) REFERENCES ferramentas(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
CREATE TABLE IF NOT EXISTS usuario(
    usuario_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    nascimento DATE,
    rg VARCHAR(9) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    telefone VARCHAR(12),
    celular VARCHAR(12),
    email VARCHAR(50) NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(50),
    bairro VARCHAR(50),
    localidade VARCHAR(50)
);
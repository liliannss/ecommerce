CREATE TABLE IF NOT EXISTS usuario (
    usuario_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    nascimento DATE,
    rg VARCHAR(50) NOT NULL,
    cpf VARCHAR(50) NOT NULL,
    telefone VARCHAR(50),
    celular VARCHAR(50),
    email VARCHAR(50) NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(50),
    bairro VARCHAR(50),
    localidade VARCHAR(50)
);
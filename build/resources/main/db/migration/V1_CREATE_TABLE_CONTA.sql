CREATE TABLE pagamentos (
    id SERIAL PRIMARY KEY,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE NOT NULL,,
    valor NUMERIC(10, 2) NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    situacao VARCHAR(50) NOT NULL
);
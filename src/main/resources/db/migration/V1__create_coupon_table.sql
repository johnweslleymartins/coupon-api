CREATE TABLE coupon (
    id UUID PRIMARY KEY,
    code VARCHAR(20) NOT NULL UNIQUE,
    description TEXT,
    discount_value NUMERIC(10,2) NOT NULL,
    expiration_date TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    published BOOLEAN NOT NULL DEFAULT FALSE,
    redeemed BOOLEAN NOT NULL DEFAULT FALSE
);

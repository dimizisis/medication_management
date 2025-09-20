CREATE TABLE medications
(
    id           BIGSERIAL PRIMARY KEY,
    name         TEXT NOT NULL UNIQUE,
    ingredient   TEXT NOT NULL,
    manufacturer TEXT,
    code         TEXT,
    expiry_date  DATE,
    quantity     INTEGER
);

CREATE INDEX idx_medication_name ON medications (name);
CREATE INDEX idx_medication_ingredient ON medications (ingredient);
CREATE INDEX idx_medication_manufacturer ON medications (manufacturer);
CREATE INDEX idx_medication_code ON medications (code);
CREATE TABLE sales (
  id UUID PRIMARY KEY,
  warehouse_id UUID NOT NULL REFERENCES warehouses(id),
  idempotency_key VARCHAR(120) NOT NULL UNIQUE,
  status VARCHAR(30) NOT NULL,
  total_amount NUMERIC(19,2) NOT NULL CHECK (total_amount >= 0),
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE sale_items (
  id UUID PRIMARY KEY,
  sale_id UUID NOT NULL REFERENCES sales(id),
  product_id UUID NOT NULL REFERENCES products(id),
  quantity INTEGER NOT NULL CHECK (quantity > 0),
  unit_price NUMERIC(19,2) NOT NULL CHECK (unit_price >= 0),
  line_total NUMERIC(19,2) NOT NULL CHECK (line_total >= 0)
);

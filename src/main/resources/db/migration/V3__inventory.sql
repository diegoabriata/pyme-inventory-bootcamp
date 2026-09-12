CREATE TABLE inventory_balances (
  id UUID PRIMARY KEY,
  product_id UUID NOT NULL REFERENCES products(id),
  warehouse_id UUID NOT NULL REFERENCES warehouses(id),
  quantity INTEGER NOT NULL DEFAULT 0 CHECK (quantity >= 0),
  version BIGINT NOT NULL DEFAULT 0,
  UNIQUE(product_id, warehouse_id)
);

CREATE TABLE stock_movements (
  id UUID PRIMARY KEY,
  product_id UUID NOT NULL REFERENCES products(id),
  warehouse_id UUID NOT NULL REFERENCES warehouses(id),
  movement_type VARCHAR(30) NOT NULL,
  quantity INTEGER NOT NULL CHECK (quantity > 0),
  reference_type VARCHAR(50),
  reference_id UUID,
  occurred_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
CREATE INDEX idx_stock_movements_product_warehouse ON stock_movements(product_id, warehouse_id, occurred_at DESC);

-- Função que atualiza o campo updated_at
CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = now();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Triggers para cada tabela que possui o campo updated_at
CREATE TRIGGER trg_set_updated_at_users
    BEFORE UPDATE ON users
    FOR EACH ROW
    EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_set_updated_at_farms
    BEFORE UPDATE ON farms
    FOR EACH ROW
    EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_set_updated_at_crop_types
    BEFORE UPDATE ON crop_types
    FOR EACH ROW
    EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_set_updated_at_crops
    BEFORE UPDATE ON crops
    FOR EACH ROW
    EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_set_updated_at_sensors
    BEFORE UPDATE ON sensors
    FOR EACH ROW
    EXECUTE FUNCTION set_updated_at();
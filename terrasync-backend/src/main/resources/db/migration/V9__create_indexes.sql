CREATE INDEX idx_farms_user_id ON farms(user_id);
CREATE INDEX idx_crops_farm_id ON crops(farm_id);
CREATE INDEX idx_sensors_crop_id ON sensors(crop_id);
CREATE INDEX idx_sensor_data_sensor_time ON sensor_data(sensor_id, read_at DESC);
CREATE INDEX idx_alerts_sensor_id ON alerts(sensor_id);
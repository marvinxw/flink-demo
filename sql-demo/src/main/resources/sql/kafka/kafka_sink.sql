CREATE TABLE flink_env_local_test (
  id INT,
  name STRING,
  age INT
) WITH (
  'connector.type' = 'kafka',
  'connector.version' = 'universal',
  'connector.topic' = 'local_test',
  'update-mode' = 'append',
  'connector.properties.bootstrap.servers' = 'localhost:9092',
  'connector.properties.compression.type' = 'lz4',
  'connector.properties.retries' = '3',
  'connector.properties.request.timeout.ms' = '10000',
  'connector.properties.batch.size' = '163840',
  'connector.properties.linger.ms' = '1',
  'connector.properties.buffer.memory' = '33554432',
  'format.type' = 'json'
)

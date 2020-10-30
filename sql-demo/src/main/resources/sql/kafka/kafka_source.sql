CREATE TABLE local_kafka_source (
  id INT,
  name STRING,
  age INT
) WITH (
  'connector.type' = 'kafka',
  'connector.version' = 'universal',
  'connector.topic' = 'local_test',
  'connector.properties.bootstrap.servers' = 'localhost:9092',
  'connector.properties.group.id' = 'local_test-v1',
  -- optional: valid modes are "earliest-offset", "latest-offset", "group-offsets", "specific-offsets" or "timestamp"
  'connector.startup-mode' = 'earliest-offset',
  'connector.properties.flink.partition-discovery.interval-millis' = '300000',
  'format.type' = 'json'
)

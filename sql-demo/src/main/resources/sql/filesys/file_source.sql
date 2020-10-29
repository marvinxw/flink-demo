CREATE TABLE local_filesys (
  id INT,
  name STRING,
  age INT
) WITH (
  'connector' = 'filesystem',
  'path' = 'file:///Users/marvin/Coding/marvinxw/flink-demo/docs/local_user.csv',
  'format' = 'csv'
)

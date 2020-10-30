-- https://ci.apache.org/projects/flink/flink-docs-stable/dev/table/connect.html
-- https://ci.apache.org/projects/flink/flink-docs-stable/dev/table/connect.html#update-modes

CREATE TABLE local_filesys (
  id INT,
  name STRING,
  age INT
) WITH (
  'connector.type' = 'filesystem',
  'connector.path' = 'file:///Users/marvin/Coding/marvinxw/flink-demo/docs/local_user.csv',
  'format.type' = 'csv',
  'format.field-delimiter' = ','
)

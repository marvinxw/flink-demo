-- https://ci.apache.org/projects/flink/flink-docs-stable/dev/table/connect.html#jdbc-connector

-- use test;
--
-- CREATE TABLE IF NOT EXISTS `local_user`(
--    `id` INT UNSIGNED AUTO_INCREMENT,
--    `user_id` int NOT NULL,
--    `name` VARCHAR(10) NOT NULL,
--    `age` int,
--    PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- ;

CREATE TABLE local_mysql_sink (
  user_id INT,
  name STRING,
  age INT
) WITH (
  'connector.type' = 'jdbc', -- required: specify this table type is jdbc
  'connector.url' = 'jdbc:mysql://127.0.0.1:32773/test?useSSL=false', -- required: JDBC DB url
  'connector.table' = 'local_user',  -- required: jdbc table name
  -- optional: the class name of the JDBC driver to use to connect to this URL.
  -- If not set, it will automatically be derived from the URL.
  'connector.driver' = 'com.mysql.jdbc.Driver',
  -- optional: jdbc user name and password
  'connector.username' = 'root',
  'connector.password' = '123456',
  -- **followings are scan options, optional, used when reading from a table**
  -- optional: SQL query / prepared statement.
  -- If set, this will take precedence over the 'connector.table' setting
--   'connector.read.query' = 'SELECT * FROM local_user',
  -- These options must all be specified if any of them is specified. In addition,
  -- partition.num must be specified. They describe how to partition the table when
  -- reading in parallel from multiple tasks. partition.column must be a numeric,
  -- date, or timestamp column from the table in question. Notice that lowerBound and
  -- upperBound are just used to decide the partition stride, not for filtering the
  -- rows in table. So all rows in the table will be partitioned and returned.
--   'connector.read.partition.column' = 'column_name', -- optional: the column name used for partitioning the input.
--   'connector.read.partition.num' = '50', -- optional: the number of partitions.
--   'connector.read.partition.lower-bound' = '500', -- optional: the smallest value of the first partition.
--   'connector.read.partition.upper-bound' = '1000', -- optional: the largest value of the last partition.
  -- optional, Gives the reader a hint as to the number of rows that should be fetched
  -- from the database when reading per round trip. If the value specified is zero, then
  -- the hint is ignored. The default value is zero.
--   'connector.read.fetch-size' = '100',
  -- **followings are lookup options, optional, used in temporary join**
  -- optional, max number of rows of lookup cache, over this value, the oldest rows will
  -- be eliminated. "cache.max-rows" and "cache.ttl" options must all be specified if any
  -- of them is specified. Cache is not enabled as default.
--   'connector.lookup.cache.max-rows' = '5000',
  -- optional, the max time to live for each rows in lookup cache, over this time, the oldest rows
  -- will be expired. "cache.max-rows" and "cache.ttl" options must all be specified if any of
  -- them is specified. Cache is not enabled as default.
--   'connector.lookup.cache.ttl' = '10s',
--   'connector.lookup.max-retries' = '3', -- optional, max retry times if lookup database failed
  -- **followings are sink options, optional, used when writing into table**
  -- optional, flush max size (includes all append, upsert and delete records),
  -- over this number of records, will flush data. The default value is "5000".
  'connector.write.flush.max-rows' = '1',
  -- optional, flush interval mills, over this time, asynchronous threads will flush data.
  -- The default value is "0s", which means no asynchronous flush thread will be scheduled.
  'connector.write.flush.interval' = '1s',
  -- optional, max retry times if writing records to database failed
  'connector.write.max-retries' = '3'
--   'update-mode' = 'upsert'  -- otherwise: 'retract' or 'upsert'
)

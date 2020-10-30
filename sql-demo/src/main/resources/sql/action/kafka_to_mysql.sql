insert into local_mysql_sink
select
id as user_id,
name,
age
from local_kafka_source

CREATE TABLE `klass` (
`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`leader_id` BIGINT,
`create_date` TIMESTAMP DEFAULT NOW()
)
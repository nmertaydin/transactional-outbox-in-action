CREATE USER 'maxwell'@'%' IDENTIFIED BY 'XXXXXX';
GRANT ALL ON maxwell.* TO 'maxwell'@'%';
GRANT SELECT, REPLICATION CLIENT, REPLICATION SLAVE ON *.* TO 'maxwell'@'%';
FLUSH PRIVILEGES;
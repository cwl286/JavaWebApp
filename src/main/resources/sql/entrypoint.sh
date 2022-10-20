#!/bin/bash

database=TestDB
password=Admin_password123
wait_time=2s

# wait for SQL Server to come up
echo importing data will start in $wait_time...
sleep $wait_time
echo importing data...


#Example
# /var/lib/mysql/mysql > source \home\user\Desktop\test.sql;

# run the init script to create the DB
/opt/mssql-tools/bin/sqlcmd -S 0.0.0.0 -U sa -P $password -i ./init.sql


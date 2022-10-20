#!/bin/bash

wait_time=2s

# wait for Mysql Server to come up
echo MySql will start in $wait_time...
sleep $wait_time
echo Loading MySql...


#Example
# /var/lib/mysql/mysql > source \home\user\Desktop\test.sql;
mysql -uroot --password=Password -e "CREATE DATABASE TestDB"
mysql -uroot --password=Password -e "USE TestDB"

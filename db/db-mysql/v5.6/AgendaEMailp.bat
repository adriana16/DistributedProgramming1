set MYSQL_HOME=d:\mysql-5.5.13-winx64\bin
set path=%MYSQL_HOME%;%PATH%
mysql -u root -p< CreateAgendaE.sql
mysql -u root -p< ValuesAgendaE.sql
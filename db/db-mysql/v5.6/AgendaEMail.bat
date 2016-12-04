set MYSQL_HOME=d:\mysql-5.6.10-winx64\bin
set path=%MYSQL_HOME%;%PATH%
mysql -u root < CreateAgendaE.sql
mysql -u root < ValuesAgendaE.sql
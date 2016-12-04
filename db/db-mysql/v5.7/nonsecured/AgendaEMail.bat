set MYSQL_HOME=e:\db\mysql-5.7.10-winx64\bin
set path=%MYSQL_HOME%;%PATH%
mysql -u root < CreateAgendaE.sql
mysql -u root < ValuesAgendaE.sql
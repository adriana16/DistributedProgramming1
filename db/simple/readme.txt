1. Se creaza catalogul lib
2. Se copiaza in catalogul lib driver-ul SGBD.
3. Se actualizeaza proprietatea dbms cu SGBD (DBMS) utilizat in build.xml.

In build.xml:
Pentru derby
  user=password=""
  
Pentru mysql
  user="root"
  password=""
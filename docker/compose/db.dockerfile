FROM mariadb:10.7.1

ENV MYSQL_ROOT_PASSWORD='umbeginde'
ENV MARIADB_DATABASE='Umgebindehaus'
ENV MARIADB_USER='umgebindehaus'
ENV MARIADB_PASSWORD='*insecure*'

COPY './files/db-conf/50-server.cnf' '/etc/mysql/mariadb.conf.d/50-server.cnf'

CMD ["mariadbd", "--character-set-server=utf8mb4", "--collation-server=utf8mb4_unicode_ci"]

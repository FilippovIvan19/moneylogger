FROM postgres:17-alpine

RUN mkdir /sql_initialization/

COPY initial_data.sql /sql_initialization/
COPY structure.sql /sql_initialization/
COPY init.sh /docker-entrypoint-initdb.d/

#!/bin/bash

DATABASE_NAME="money_logger_db"

echo "*** CREATING DATABASE ***"


psql -U $POSTGRES_USER -d $POSTGRES_DB -f /sql_initialization/structure.sql
psql -U $POSTGRES_USER -d $POSTGRES_DB -f /sql_initialization/initial_data.sql


echo "*** DATABASE CREATED! ***"

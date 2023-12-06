#!/bin/bash
#
echo " \
    SELECT 'CREATE DATABASE vantian' \
    WHERE NOT EXISTS ( \
        SELECT FROM pg_database \
        WHERE datname = 'vantian')\\gexec \
" | psql -U postgres

psql -U postgres -d vantian -f create_tables.sql

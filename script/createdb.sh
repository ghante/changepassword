
PGPASSWORD=postgres psql -U postgres -h localhost -c "drop database trailblazers"
PGPASSWORD=postgres psql -U postgres -h localhost -c "create database trailblazers"
PGPASSWORD=postgres psql -U postgres -h localhost -d trailblazers < SQL-README


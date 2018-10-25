# Database migration example with Flyway 
Database migration example with Flyway

## Flyway
- Migrate without maven profile
```
mvn flyway:migrate
```
- Migrate with maven profile
```
mvn flyway:migrate -P local
# mvn flyway:migrate -P <profile_name>
```

- Clean with maven profile
```
mvn flyway:clean -P local
# mvn flyway:clean -P <profile_name>
```
- Clean without maven profile
```
mvn flyway:clean
```
- Review migration status with maven profile
```
mvn flyway:info -P local
```

## Docker
### Install and run mariadb
- Download image
```
docker pull mariadb
```
- Run on the container
```
docker run -p 33066:3306 --name mariadb -e MYSQL_ROOT_HOST=% -e MYSQL_ROOT_PASSWORD=123456 -d mariadb:l
atest
```
```
Connection Information example:
MariaDB container host: 10.0.75.2
Port: 33066
```
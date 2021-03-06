## 구성 방법

1. docker-compose up -d
2. docker exec -it mysql_primary mysql -uroot -proot
3. 접속한 상태에서 mysql/main/init/init.sql 내용의 쿼리를 실행
4. !! binary_log and position 체크 (replica 연결할때 사용)
5. docker exec -it mysql_replica mysql -uroot -proot
6. 접속한 상태에서 mysql/main/init/init.sql 내용의 쿼리를 실행
7. check Slave_IO_Running: Yes, Slave_SQL_Running: Yes 
    - yes가 떠야 정상적으로 연결된 것임.
8. db client으로 main, replica 접속하여 main에서 ddl이나 cud 실행
    - replica에서 정상적으로 동기화가 된다면 정상


## Tip

### docker container name 으로 연결
```sql
-- slave db server query
CHANGE MASTER TO
    MASTER_HOST='mysql_primary',
    MASTER_USER='replica',
    MASTER_PASSWORD='1234',
    MASTER_LOG_FILE='binary_log.000003',
    MASTER_LOG_POS=848,
    GET_MASTER_PUBLIC_KEY=1;
```
`MASTER_HOST='mysql_main'` 처럼 docker container name 으로 연결하려면   
> default 망으로 연결하지 말고 새로운 network 망 하나 생성 

```yml
# docker-compose.yml

networks:
  net-mysql:
    driver: bridge
...
services:
  mysql_main:
    container_name: mysql_main
    ...
    networks:
      - net-mysql

  mysql_replica:
    container_name: mysql_replica
    ...
    networks:
      - net-mysql
```

### primary, replica query 확인

디비의 general_log 설정을 확인 후 쿼리로 어떤 서버로 쿼리가 나갔는지 확인 가능

```sql
-- my.cnf 이미 설정되어 있음. 
show variables like '%general%';
show variables like 'log_output';
set global log_output='table';

select * from general_log gl  order by event_time DESC ;
```

## TODO
1. main, replica 각각의 my.cnf 의 설정 추가

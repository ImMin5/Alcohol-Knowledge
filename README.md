# Achol-Knowledge
소비자와 함께 만들어가는 알콜 리스트


## spring boot 배포방법

- 실행
```
java –jar [name-of-jar-file].jar
```
- 백그라운드 실행 
```
nohup java –jar [name-of-jar-file].jar &

```
- 외부에서 설정파일 불러오기
- java -jar app.jar --spring.config.location=file:d:\properties\application.properties
- 실행 확인
```
ps -ef | grep java
```

- 실행중단
```
kill -9 [pid]
```

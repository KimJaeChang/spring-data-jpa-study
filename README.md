+ ### Spring Boot Thymeleaf
  + 스프링 부트 thymeleaf viewName 매핑
    + > resources:templates/{ViewName}.html

+ ### H2 데이터베이스
  + 개발이나 테스트 용도로 가볍고 편리한 DB, 웹 하면 제공
  + 링크 : https://www.h2database.com
  + 데이터베이스 파일 생성 방법
    + http://localhost:8082 접속
      + jdbc:h2:~/spring-data-jpa-test (초기 파일 생성용)
    + <U>**~/spring-data-jpa-test**</U> 파일 생성 확인
      + windows : <U>**C:\Users\user**</U>
      + mac : 정리 예정
    + 이후 부터는 <U>**jdbc:h2:tcp://localhost/~/spring-data-jpa-test**</U> 로 접속

+ ### Spring-Data-Jpa
  + Repository 생성시 JpaRepository<T, ID> 을 상속받는다.
    + 지원 메서드 :
      + findAll
      + findById
      + save
      + 등등
            
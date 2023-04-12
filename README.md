# 나만의 광고 관리 플랫폼

<p align="center">
    <img src = "https://user-images.githubusercontent.com/107471786/230712268-f93fae35-81a1-4747-af8c-3d6d35989814.jpeg" width="30%">
</p>

# 프로젝트 정보
프로젝트 목적: 11시 11분 인턴쉽 과제  
개발기간: 2023.03 ~ 2023.04

# 프로젝트 소개

광고 등록, 광고 관리 등의 기능을 가지는 간단한 광고 관리 플랫폼 만들기

# 기술 스택

- Frontend: TypeScript, ReactJS, AntDesign
- Backend: Java, SpringBoot, JPA, SpringSecurity, SpringBatch
- DB: H2

# ERD
![erd](https://user-images.githubusercontent.com/107471786/230759653-c1777ea6-5b96-470d-9509-15171cc2457b.png)

# 주요 기능

## 로그인

https://user-images.githubusercontent.com/107471786/230723392-08f85fa0-6833-4565-8f79-4fc1c0a7ba5b.mov

1. 로그인 버튼 클릭시, 전달받은 아이디와 비밀번호로 UsernamePasswordAuthenticationToken 생성
2. UsernamePasswordAuthenticationToken이 유효하면, 권한을 담은 JWT Token을 생성하여 전달  
3. JWT Token안에 있는 권한정보를 통해 권한 획득(회원의 권한에 따라 보여지는 메뉴와 접근가능한 페이지가 다름)
4. 로그인 이후의 모든 API 호출시, Axios Interceptor를 활용하여 모든 요청의 인증 관련 헤더에 전달받은 JWT 토큰을 추가
5. 직접 구현한 JWT 관련 Security Filter를 활용하여 해당 요청에 담긴 토큰이 유효한지 검사

## 일괄 처리 작업

https://user-images.githubusercontent.com/107471786/230723397-25c4e045-e32b-4e28-ab3c-c707972e9183.mov

1. 처리할 여러 작업 등록  
(날짜,직접 광고 ID,노출수,클릭수,평균 노출 순위,평균 클릭 비용,광고비 형태의 CSV 파일 업로드)
2. Quartz 스케줄러를 활용하여, 처리할 작업이 존재하면 Batch Job 실행
3. 읽은 CSV 파일이 유효하면, 작업 상태 값을 완료로 설정하고, 해당 작업의 결과를 저장
4. 읽은 CSV 파일이 유효하지 않으면, 작업 상태 값을 에러로 설정하고, 해당 작업의 결과를 저장하지 않음

# 회고

## 1. 데이터 파싱은 어디에서?
프론트단에서 파싱을 하지 않기 위해, 백단에서 전달받은 응답값을 그대로 사용할수 있도록 API를 만들었다.   
그 결과 백단에서는 불필요한 데이터를 다루게 되었고, 프론트단의 요구사항 변경시 백엔드의 데이터 구조도 변하게 되어 유지보수가 힘들어졌다.  
또한 화면에 종속적인 API를 만들다 보니, 클라이언트의 사소한 차이에 대응하는 비슷한 API를 여러개 만들게 되었다.  
백단에서는 어떠한 요청에도 유연하게 대응할 수 있는 구조로 API를 만들고, 프론트단에서 데이터 파싱을 해야겠다고 결론을 지었다.  

## 2. 리액트 컴포넌트를 어떻게 분리할까?
컴포넌트를 최대한 작게 쪼갠다면, 재사용성이 높아져 유지보수가 쉬워질 것이라 생각했다.  
그 결과  props를 통해 데이터를 전달하는 과정에서 해당 데이터가 필요하지 않은 컴포넌트에도 데이터가 전달되어 코드가 지저분해졌다.  
이 문제를 해결하기위해 useContext를 이용하여 상태 관리를 시도했다.  
하지만 Context를 사용하니, props의 변화가 없음에도 렌더링이 되어 디버깅이 힘들어 졌고, 오히려 컴포넌트의 재사용성이 떨어지게 되었다.  
적당한 컴포넌트 분리 기준을 고민하였고, 이번 프로젝트에서 컴포넌트를 아래와 같이 분리했다.

### 1) 레이아웃 관련 컴포넌트 
<img width="183" alt="스크린샷 2023-04-09 오전 12 12 19" src="https://user-images.githubusercontent.com/107471786/230728922-5fcebd74-8161-4db3-a218-30c2fcf273eb.png">

- 한 페이지(AdPage)는 AdPageHeader, AdPageBody, AdPageFooter로 이루어짐

### 2) Content Body(AdPageBody) 하위 컴포넌트
<img width="291" alt="스크린샷 2023-04-09 오전 12 17 31" src="https://user-images.githubusercontent.com/107471786/230729131-4e10452e-688d-4ab2-bd0e-a5519b310992.png">

- XXX/contentBody: XXX 페이지의 Content Body 하위 컴포넌트
- XXX/modal: XXX 페이지의 모달 관련 컴포넌트

### 3) 라우터 관련 컴포넌트
<img width="307" alt="스크린샷 2023-04-08 오후 11 59 54" src="https://user-images.githubusercontent.com/107471786/230728240-1ee8a809-7dea-4eac-a850-657a8187807a.png">

- AuthenticationNoneRequiredRouter: 인증이 필요하지 않은 라우터(예시: 로그인 페이지)
- AuthenticationRequiredRouter: 인증이 필요한 라우터
- RoleRequiredRouter: AuthenticationRequiredRouter의 하위 컴포넌트로 사용되며, 권한이 필요한 라우터

### 4) section 태그 관련 컴포넌트
<img width="189" alt="스크린샷 2023-04-08 오후 11 55 47" src="https://user-images.githubusercontent.com/107471786/230728112-21c1467d-d68a-4a36-84e5-54b0a62b5646.png">

- Content Body(AdPageBody)는 SectionHeader, SectionBody, SectionFooter로 이루어짐  

### 5) 테이블 관련 dd, dt 태그 관련 컴포넌트
<img width="144" alt="스크린샷 2023-04-08 오후 11 51 56" src="https://user-images.githubusercontent.com/107471786/230727855-6252082c-8c6e-45f0-9aa0-99dcff12b450.png">

## 3. JPA 성능 개선
JPA를 활용하여 간단한 프로젝트를 만든 경험이 있었지만, 일대일 관계같은 테이블 관계를 생각해 해본적이 없었다.  
save(), findById() 같은 쿼리 메소드로 모든것이 가능하다고 생각했고, 쿼리문을 직접 작성해 본적도 없었다.  
이번 프로젝트를 통해 데이터의 참조 무결성 때문에 외래키의 개념이 필요하다는걸 깨달았고, 쿼리 성능에 대해서도 고민하게 되었다.

### 1) 즉시 로딩, 지연 로딩, 패치 조인
내가 조회하지 않은 필드에 대한 쿼리가 생기는 것에 대해 의문이 생겼고, 그 이유를 찾다가 즉시 로딩과 지연 로딩에 대한 개념을 알게 되었다.  
연관된 모든 필드를 가져오는 즉시로딩보다는 가급적이면 필요한 필드만 가져오는 지연 로딩을 사용하는 것이 좋다는 결론을 내렸다.  
즉시 로딩은 예상하지 못한 쿼리문이 발생하며, N+1 문제를 일으키기 때문이다.  
해당 엔티티의 모든 필드가 필요한 경우에만 부분적으로 패치 조인을 사용하여 데이터를 한방 쿼리로 가져오면 된다.  

### 2) 묵시적 조인 대신 명시적 조인 사용하기
JPQL 에서는 조인문을 사용하지 않아도 되는 줄 알고 묵시적 조인만을 사용했다.  
묵시적 조인이 편하기는 하지만, 성능이나 유지보수 측면에서 명시적으로 조인문을 선언하는 것이 바람직한것을 깨달았다.  

### 3) 벌크 연산 사용하기
여러 데이터를 수정하는 경우, 반복문을 통해 save() 메소드를 호출하여 데이터를 수정했다.  
쿼리 성능 개선에 대해 찾아보다가 벌크 연산에 대한 개념을 알게 되었고, 이번 프로젝트에 적용해보았다.
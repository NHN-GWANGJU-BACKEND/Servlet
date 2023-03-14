model :  내부 비즈니스 로직을 처리하기 위한 역할, 사용자가 입력한 데이터나<br>
사용자에 출력할 데이터를 다룬다.
```
DTO : 비지니스 로직을 가지지 않은 순수한 데이터 객체<br>
 -> User, Post

Repository : DTO에 접근하기 위한 메서드들을 사용<br>-> MapUserRepository, JsonUserRepository, MapPostRepository
```


view : 최종 사용자에게 화면을 보여주는 역할, 화면에 무엇을 보여줄지에 대한 로직이 작성된다.
<br>

controller : model과 view 사이에 있는 컴포넌트이다. 데이터를 어떻게 처리할지에 대한
로직을 작성하는 역할을 한다.
<br><br>

### 과제를 하면서 어려웠던 점

1. 이미지 파일 호출했을 때 엑스박스로 나오는 문제<br>
```
tomcat 보안상의 이유로 웹브라우저에 경로입력으로 프로젝트 외부 폴더로는
접근이 안되도록 기본 설정이 되어있다. 이를 tomcat->config->server.xml에 들어가서 
<Context baseDoc="외부폴더경로" path="접근경로" reloadable=true/>
로 접근경로와 외부폴더경로를 매핑해주면 이미지 파일을 정상적으로 불러올 수 있다.
```

2. 파일 읽고 쓰기.
```
파일을 읽어오는 방법 : StreamReader or FileReader
stream 방식은 byte단위, file방식은 char단위로 읽어오는 차이점이 있다.

couter.dat파일을 읽어올 때 강사님이 수업시간에 알려주신 코드로 작성했는데 
null값이 불러와져서 DataInputStream을 원래 쓰던 BufferedReader로 변경했더니
불러와 졌다. 하지만 write할 때 문제가 생겼다 DataOutputStream을 이용해서
파일을 작성했는데 아스키코드 문자가 작성되었고 이를 BufferedReader에서 String으로
받아내는 과정에 문제가 발생되었다. 문제해결을 위해 FileWriter를 이용해 문자로 데이터를
넘겨줬더니 일단은 해결되었지만 stream관련 공부가 필요함을 느끼고있다.
```

3. ServletContext destroy 실행
```
파일 저장 로직을 이곳에서 실행시키고자 하는데 어떻게해도 적용이 되지 않았다.
혹시 로직이 문제인가 하여 filter부분에서 실행시켜봤지만 filter부분에서는 
정상적으로 실행이 됐다. 이유를 찾고싶었지만 디버그나 로그가 안되는 구간이라 
이유를 알아내지 못했다.
```


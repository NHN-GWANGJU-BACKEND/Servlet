## 최종과제 피드백 사항

post 등록시 postId 값을 ++ 해서 유지하는거 같아요.
동시성 이슈에 대해서도 고민해보세요.

* synchronized
* AtomicInteger

```java
int postId = (int) servletContext.getAttribute("postId");

post.setId(++postId);
post.setContent(content);
post.setTitle(title);
post.setWriteTime(LocalDateTime.now());
post.setWriterUserId(user.getId());

mapPostRepository.register(post);
servletContext.setAttribute("postId", postId);
```

* postId null이면 Long.parseLong(postId) 호출시 null point exception이 발생합니다.

```java
public class PostController implements Command {
    
@Override
public String execute(HttpServletRequest req,  HttpServletResponse resp) {
ServletContext servletContext = req.getServletContext();
PostRepository postRepository = (PostRepository) servletContext.getAttribute("mapPostRepository");
String postId = req.getParameter("postId");

        Post post = postRepository.getPost(Long.parseLong(postId));

        req.setAttribute("post",post);

        return "/postView.jsp";
    }
}
```

* page, size 또한 ?size=&page= 넘어온다면 null point exception발생함으로 아래 코드를 참고해주세요.
  * null이면 기본값을 세팅합니다.
  size=1
  page=1  
```java
@Component
public class PostListController implements Command {
    
@Override
public String execute(HttpServletRequest req, HttpServletResponse resp) {
//        int page = Integer.parseInt(req.getParameter("page"));
//        int size = Integer.parseInt(req.getParameter("size"));
int page = req.getParameter("page")==null ? 1 : Integer.parseInt(req.getParameter("page"));
int size = req.getParameter("size")==null ? 10 : Integer.parseInt(req.getParameter("size"));

        ServletContext servletContext = req.getServletContext();
        PostRepository mapPostRepository = (PostRepository) servletContext.getAttribute("mapPostRepository");

        List<Post> postList = mapPostRepository.getPagedPosts(page,size).getList();

        req.setAttribute("postList",postList);
        req.setAttribute("page",page);
        req.setAttribute("size",size);

        return "/postList.jsp";
    }
}
```

* spring bean 구현할 때...
  * AnnotationConfigApplicationContext 여기저기서 중복해서 Context를 생성하는거 같아요 .
  * 최초  한 번 생성하시고 전역적으로 사용할 수 있는 방법을 고민해보세요.
에를들어 singleton pattern을 이용하는 방법도 있구요 참고만 해주세요..
```java
public final class BaseContext {

    private final AnnotationConfigApplicationContext ac;
    private static final BaseContext baseContext = new BaseContext();

    private BaseContext() {
        ac = new AnnotationConfigApplicationContext("com.nhnacademy");
    }

    public static BaseContext getInstance() {
        return baseContext;
    }

    public AnnotationConfigApplicationContext getContext() {
        return ac;
    }

}
```

package book.ch5.webserver;

public abstract class AbstractController implements Controller{
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        if(request.getMethod() == HttpMethod.GET) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doGet(HttpRequest request, HttpResponse response);
    public abstract void doPost(HttpRequest request, HttpResponse response);
}

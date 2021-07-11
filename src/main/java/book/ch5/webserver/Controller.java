package book.ch5.webserver;

public interface Controller {
    void service(HttpRequest request, HttpResponse response);
}

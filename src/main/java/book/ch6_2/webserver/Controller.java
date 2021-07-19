package book.ch6_2.webserver;

public interface Controller {
    void service(HttpRequest request, HttpResponse response);
}

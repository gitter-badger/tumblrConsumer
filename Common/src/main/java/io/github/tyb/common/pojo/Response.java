package io.github.tyb.common.pojo;

public class Response<T> {
    private final T data;

    private final String status;
    private final String message;

    public Response(T data, String status, String message) {
        this.data = data;
        this.status=status;
        this.message=message;
    }

    /*public void set(T t) {
        this.t = t;
    }

     */

    public T get() {
        return data;
    }
}

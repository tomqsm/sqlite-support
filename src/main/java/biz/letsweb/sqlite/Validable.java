package biz.letsweb.sqlite;

public interface Validable <T>{

    void validate(T model) throws Exception;
}

package com.io.dexxf.common.result;

public record Result<T,E>(boolean success,T data, E errorMsg) {

    public static <T,E> Result<T,E> ok(T data) {
        return new Result<>(true,data,null);
    }

    public static <T,E> Result<T,E> ok(T data, E errorMsg) {
        return new Result<>(true,data,errorMsg);
    }

    public static <T,E> Result<T,E> fail(E errorMsg) {
        return new Result<>(false,null,errorMsg);
    }
}

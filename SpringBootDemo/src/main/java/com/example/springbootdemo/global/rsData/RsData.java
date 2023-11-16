package com.example.springbootdemo.global.rsData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class RsData<T> {
    private final String resultCode;
    private final String message;
    private T data;
}

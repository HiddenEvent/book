package com.richard.book.web;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


// 단위 테스트( Controller 관련 로직만 띄우기) - Controller, Filter, ControllerAdvice

// @ExtendWith(SpringExtension.class) jUnit5를 사용하기 때문에 Spring으로 확장시키는 어노테이션을 따로 설정하지 않아도 된다.
@WebMvcTest // Controller, Filter, ControllerAdvice 만 메모리에 띄운다 /Spring으로 확장시키는 어노테이션들고 있다.

public class BookControllerUnitTest {
}

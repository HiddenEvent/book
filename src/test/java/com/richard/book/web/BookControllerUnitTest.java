package com.richard.book.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.richard.book.domain.Book;
import com.richard.book.service.BookService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// 단위 테스트( Controller 관련 로직만 띄우기) - Controller, Filter, ControllerAdvice

// @ExtendWith(SpringExtension.class) jUnit5를 사용하기 때문에 Spring으로 확장시키는 어노테이션을 따로 설정하지 않아도 된다.


@WebMvcTest // Controller, Filter, ControllerAdvice 만 메모리에 띄운다 /Spring으로 확장시키는 어노테이션들고 있다.
public class BookControllerUnitTest {
    Logger log = (Logger) LoggerFactory.getLogger(BookControllerUnitTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Ioc환경에 bean 등록됨 => 가짜 null임...
    private BookService bookService;
    
    // BDDMockito 패턴 => given, when, then 으로 구성되어 있음
    @Test
    public void save_테스트() throws Exception {
        // given ( 테스트를 하기 위한 준비) => 받을 데이터 준비
        Book book = Book.builder().title("스프링 따라하기").author("코스").build();
        String content = new ObjectMapper().writeValueAsString(book);
        // when (실행된 다는 가정하에) then( 어떤값이 나올 것이다 정의)
        when(bookService.저장하기(book)).thenReturn( Book.builder().id(1L).title("스프링 따라하기").author("코스").build());

        // when(테스트 실행)
        ResultActions resultActions = mockMvc.perform(post("/book")
        .contentType(MediaType.APPLICATION_JSON) // contentType: 던지는 데이터 타입이 무엇이냐?
        .content(content)
        .accept(MediaType.APPLICATION_JSON)); //accept: 응답객체가 무엇이냐?

        // then(검증)
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("스프링 따라하기"))
                .andDo(MockMvcResultHandlers.print());


    }

}
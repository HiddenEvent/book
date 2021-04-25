package com.richard.book.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


/**
- 통합 테스트 (모든 Bean들을 똑같이 IOC에 올리고 테스트 하는 것)
 WebEnvironment.MOCK = 실제 톰켓을 올리는게 아니라, 다른 톰켓으로 테스트(가짜)
 WebEnvironment.RANDOM_POR = 실제 톰켓으로 테스트
 @AutoConfigureMockMvc = MockMvc를 ioc에 등록해줌.
 @Transactional = 각 각의 테스트함수가 종료될 때마다 트랜잭션을 rollback 해주는 어노테이션!
 */

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BookControllerIntegreTest {

    @Autowired
    private MockMvc mockMvc;
}

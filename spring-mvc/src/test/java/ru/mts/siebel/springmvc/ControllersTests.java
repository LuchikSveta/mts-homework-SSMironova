package ru.mts.siebel.springmvc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mts.siebel.springmvc.dao.User;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ControllersTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloController() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }

    @ParameterizedTest
    @MethodSource("fetchData")
    public void testGreetController(final String name) throws Exception {
        mockMvc.perform(post("/greet?name=" + name))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, " + name + "!"));
    }

    @ParameterizedTest
    @MethodSource("fetchUserData")
    public void testUserController(final User user) throws Exception {
        int id = user.getId();
        String name = user.getName();
        mockMvc.perform(get("/user/get/" + name + "?id=" + id))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, " + name + "! Your id is " + id));
    }

    @Test
    public void testGetUserByIdController() throws Exception {
        mockMvc.perform(get("/user/10"))
                .andExpect(status().isOk())
                .andExpect(content().string("User was not found"));
    }

    @ParameterizedTest
    @MethodSource("fetchUserData")
    public void testGetUserByIdController(final User user) throws Exception {
        mockMvc.perform(get("/user/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("User was found"));
    }

    @Test
    public void testGetUserAgentController() throws Exception {
        String expectedUserAgent = "user-agent=test";
        mockMvc.perform(MockMvcRequestBuilders.get("/user/agent").header("User-Agent", expectedUserAgent))
                .andExpect(status().isOk())
                .andExpect(content().string("User-Agent: " + expectedUserAgent));
    }

    @Test
    public void testUserExceptionController() throws Exception {
        mockMvc.perform(get("/user/exception"))
                .andExpect(status().isOk())
                .andExpect(content().string("CustomException with ResponseStatus = 404 Not Found"));
    }

    @ParameterizedTest
    @MethodSource("fetchData")
    public void testDefaultController(final String name) throws Exception {
        mockMvc.perform(get("/default"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Guest!"));
        mockMvc.perform(get("/default?name=" + name))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, " + name + "!"));
    }

    @Test
    public void testDateController() throws Exception {
        LocalDate date = LocalDate.now();
        mockMvc.perform(get("/date/" + date))
                .andExpect(status().isOk())
                .andExpect(content().string("Valid date: " + date));
        mockMvc.perform(get("/date/2024-0-0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid date format: 2024-0-0, need: yyyy-MM-dd"));
    }

    private static Stream<Arguments> fetchData() {
        return Stream.of(
                Arguments.arguments("Sveta"),
                Arguments.arguments("Stas"),
                Arguments.arguments("Gleb"),
                Arguments.arguments("Ivan"),
                Arguments.arguments("Anna")
        );
    }

    private static Stream<Arguments> fetchUserData() {
        return Stream.of(
                Arguments.arguments(new User(1, "Sveta", "sveta@mail.ru")),
                Arguments.arguments(new User(2, "Stas", "stas@mail,ru")),
                Arguments.arguments(new User(3, "Gleb", "gleb@mail,ru")),
                Arguments.arguments(new User(4, "Ivan", "ivan@mail,ru")),
                Arguments.arguments(new User(5, "Anna", "anna@mail,ru"))
        );
    }

}

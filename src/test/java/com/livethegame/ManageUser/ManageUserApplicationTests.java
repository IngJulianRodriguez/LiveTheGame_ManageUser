package com.livethegame.ManageUser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livethegame.ManageUser.common.UserResponseMapper;
import com.livethegame.ManageUser.controller.ManageUserRestController;
import com.livethegame.ManageUser.dto.UserRequest;
import com.livethegame.ManageUser.exception.UserNotFoundException;
import com.livethegame.ManageUser.repository.UserRepository;
import com.livethegame.ManageUser.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ManageUserRestController.class)
@AutoConfigureMockMvc
class ManageUserApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;
    @MockBean
    private UserResponseMapper userResponseMapper;

    private static final String PASSWORD = "admin";
    private static final String USER = "admin";

    @Test
    void testUpdateUser_Success() throws Exception {
        UserRequest request = new UserRequest();
        doNothing().when(userService).updateUserDetails(request);
        mockMvc.perform(MockMvcRequestBuilders.put("/users/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario actualizado correctamente"));
    }

    @Test
    void testUpdateUser_Business_UserNotFound() throws Exception {
//        UserRequest request = new UserRequest();
//        request.setId(1L);
//        UserService mockUserService = mock(UserService.class);
//        doThrow(UserNotFoundException.class)
//                .when(mockUserService).updateUserDetails(request);
//        mockMvc.perform(MockMvcRequestBuilders.put("/users/update")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(request)))
//                .andExpect(status().isNotFound())
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserNotFoundException));
    }
    @Test
    void testUpdateUser_SystemException() throws Exception {

    }

    @Test
    void testConfirmEmailUser_Success() throws Exception {
        Long idUser = 1L;
        doNothing().when(userService).confirmEmail(idUser);
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/confirm-email/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Email confirmado correctamente"));
    }



    @Test
    public void testWithoutAuthorization() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/update")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testUnauthorizedAccess() throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodingParaUsuarioSinPermiso = encoder.encodeToString(("usuario" + ":" + "password").getBytes());
        mockMvc.perform(MockMvcRequestBuilders.get("/users/update")
                        .header("Authorization", "Basic " + encodingParaUsuarioSinPermiso)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}

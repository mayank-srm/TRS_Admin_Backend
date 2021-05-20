package com.TokenReviewSystem.services;

import com.TokenReviewSystem.model.UserData;
import com.TokenReviewSystem.model.UserloginData;
import com.TokenReviewSystem.repository.UserDataRepository;
import com.TokenReviewSystem.repository.UserRepository;
import com.TokenReviewSystem.services.UserServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserDataRepository userDataRepository;

    @Spy
    @InjectMocks
    UserServices userServices;

    @Test
    public void loginTest1(){
        String emailID = "abc@gmail.com";
        String password = "123123";
        String customertype = "user";

        UserloginData userloginData = mock(UserloginData.class);
        Optional<UserloginData> auth = Optional.of(userloginData);
        when(userRepository.findAllByEmailIdAndPasswordAndUsertype(emailID,password,customertype))
                .thenReturn(auth);

        ResponseEntity <UserloginData> responseEntity = userServices.login(emailID,password,customertype);

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.OK, statusCode);
    }

    @Test
    public void loginTest2(){
        String emailID = "abc@gmail.com";
        String password = "123123";
        String customertype = "user";

        UserloginData userloginData = mock(UserloginData.class);
        Optional<UserloginData> auth = Optional.empty();
        when(userRepository.findAllByEmailIdAndPasswordAndUsertype(emailID,password,customertype))
                .thenReturn(auth);

        ResponseEntity <UserloginData> responseEntity = userServices.login(emailID,password,customertype);

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND, statusCode);
    }

    @Test
    public void getAllUserTest1(){
        UserloginData userloginData = mock(UserloginData.class);
        List<UserloginData> list = new ArrayList<>();
        list.add(userloginData);
        doReturn(list).when(userRepository).findAll();
        ResponseEntity <List<UserloginData>> responseEntity = userServices.getAlluaerdata();

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.OK,statusCode);
    }

    @Test
    public void getAllUserTest2(){
        List<UserloginData> list = new ArrayList<>();
        doReturn(list).when(userRepository).findAll();
        ResponseEntity <List<UserloginData>> responseEntity = userServices.getAlluaerdata();

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND,statusCode);
    }

    @Test
    public void addData1(){
        UserloginData userloginData = mock(UserloginData.class);
        UserData saved = mock(UserData.class);
        Optional<UserloginData> optional = Optional.empty();
        when(userRepository.findByEmailId(userloginData.getEmailId())).thenReturn(optional);
        when(userDataRepository.save(userloginData.getUserdata())).thenReturn(saved);

        ResponseEntity<UserloginData> responseEntity = userServices.signinuser(userloginData);

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.OK,statusCode);
    }

    @Test
    public void addData2(){
        UserloginData userloginData = mock(UserloginData.class);
        Optional<UserloginData> optional = Optional.of(userloginData);
        when(userRepository.findByEmailId(userloginData.getEmailId())).thenReturn(optional);
        ResponseEntity<UserloginData> responseEntity = userServices.signinuser(userloginData);

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND,statusCode);
    }

    @Test
    public void testValidateEmail(){
        assertTrue(UserloginData.isValidEmail("Abc123@gmail.com"));
    }

    @Test
    public void testValidateWrongEmail(){
        assertFalse(UserloginData.isValidEmail("Abc123gmail.com"));
    }

    @Test
    public void testValidatePhone(){
        assertTrue(UserData.isValidPhone("9120329400"));
    }

    @Test
    public void testValidateWrongPhone(){
        assertFalse(UserData.isValidPhone("12412312"));
    }

    @Test
    public void testValidatePin(){
        assertTrue(UserData.isValidPin("123123"));
    }

    @Test
    public void testValidateWrongPin(){
        assertFalse(UserData.isValidPin("12412"));
    }

}

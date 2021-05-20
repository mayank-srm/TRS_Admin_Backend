package com.TokenReviewSystem.services;

import com.TokenReviewSystem.model.LawyerTokenData;
import com.TokenReviewSystem.model.UserData;
import com.TokenReviewSystem.repository.LawyerTokenRepository;
import com.TokenReviewSystem.repository.UserDataRepository;
import com.TokenReviewSystem.repository.UserRepository;
import com.TokenReviewSystem.services.LawyerTokenServices;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class LawyerServiceTest {

    @Mock
    LawyerTokenRepository lawyerTokenRepository;

    @Mock
    UserRepository userRepository;

    @Spy
    @InjectMocks
    LawyerTokenServices lawyerTokenServices;

    @Mock
    UserDataRepository userDataRepository;

    @Test
    public void findData1(){
        String emailId = "abc@gmail.com";
        doReturn(1234).when(userRepository).findidandupdate(emailId);
        UserData userData = mock(UserData.class);
        Optional<UserData> optional = Optional.of(userData);
        doReturn(optional).when(userDataRepository).findById(1234);
        ResponseEntity<UserData> responseEntity = lawyerTokenServices.findpatientdata(emailId);

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.OK,statusCode);
    }

    @Test
    public void findData2(){
        String emailId = "abc@gmail.com";
        doReturn(1234).when(userRepository).findidandupdate(emailId);
        UserData userData = mock(UserData.class);
        Optional<UserData> optional = Optional.empty();
        doReturn(optional).when(userDataRepository).findById(1234);
        ResponseEntity<UserData> responseEntity = lawyerTokenServices.findpatientdata(emailId);

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND,statusCode);
    }

    @Test
    public void addData1(){
        LawyerTokenData lawyerTokenData = mock(LawyerTokenData.class);
        LawyerTokenData saved = mock(LawyerTokenData.class);
        Optional<LawyerTokenData> optional = Optional.empty();
        doReturn(optional).when(lawyerTokenRepository).findById(0);
        doReturn(saved).when(lawyerTokenRepository).save(lawyerTokenData);

        ResponseEntity<LawyerTokenData> responseEntity = lawyerTokenServices.addtoken(lawyerTokenData);

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.OK,statusCode);
    }

    @Test
    public void addData2(){
        LawyerTokenData lawyerTokenData = mock(LawyerTokenData.class);
        Optional<LawyerTokenData> optional = Optional.of(lawyerTokenData);
        doReturn(optional).when(lawyerTokenRepository).findById(0);
        ResponseEntity<LawyerTokenData> responseEntity = lawyerTokenServices.addtoken(lawyerTokenData);

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND,statusCode);
    }

    @Test
    public void getAllToken1(){
        LawyerTokenData lawyerTokenData = mock(LawyerTokenData.class);
        List<LawyerTokenData> list = new ArrayList<>();
        list.add(lawyerTokenData);
        doReturn(list).when(lawyerTokenRepository).findAll();
        ResponseEntity <List<LawyerTokenData>> responseEntity = lawyerTokenServices.getalltoken();

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.OK,statusCode);
    }

    @Test
    public void getAllToken2(){
        List<LawyerTokenData> list = new ArrayList<>();
        doReturn(list).when(lawyerTokenRepository).findAll();
        ResponseEntity <List<LawyerTokenData>> responseEntity = lawyerTokenServices.getalltoken();
        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND,statusCode);
    }

    @Test
    public void testValidateEmail(){
        assertTrue(LawyerTokenData.isValidEmail("Abc123@gmail.com"));
    }

    @Test
    public void testValidateWrongEmail(){
        assertFalse(LawyerTokenData.isValidEmail("Abc123gmail.com"));
    }

}

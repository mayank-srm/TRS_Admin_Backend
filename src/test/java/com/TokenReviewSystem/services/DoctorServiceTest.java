package com.TokenReviewSystem.services;

import com.TokenReviewSystem.model.DoctorTokenData;
import com.TokenReviewSystem.model.UserData;
import com.TokenReviewSystem.repository.DoctorTokenRepository;
import com.TokenReviewSystem.repository.UserDataRepository;
import com.TokenReviewSystem.repository.UserRepository;
import com.TokenReviewSystem.services.DoctorTokenServices;
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
public class DoctorServiceTest {

    @Mock
    DoctorTokenRepository doctorTokenRepository;

    @Mock
    UserRepository userRepository;

    @Spy
    @InjectMocks
    DoctorTokenServices doctorTokenServices;

    @Mock
    UserDataRepository userDataRepository;

    @Test
    public void findData1(){
        String emailId = "abc@gmail.com";
        doReturn(1234).when(userRepository).findidandupdate(emailId);
        UserData userData = mock(UserData.class);
        Optional<UserData> optional = Optional.of(userData);
        doReturn(optional).when(userDataRepository).findById(1234);
        ResponseEntity<UserData> responseEntity = doctorTokenServices.findpatientdata(emailId);

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
        ResponseEntity<UserData> responseEntity = doctorTokenServices.findpatientdata(emailId);

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND,statusCode);
    }

    @Test
    public void addData1(){
        DoctorTokenData doctorTokenData = mock(DoctorTokenData.class);
        DoctorTokenData saved = mock(DoctorTokenData.class);
        Optional<DoctorTokenData> optional = Optional.empty();
        doReturn(optional).when(doctorTokenRepository).findById(0);
        doReturn(saved).when(doctorTokenRepository).save(doctorTokenData);

        ResponseEntity<DoctorTokenData> responseEntity = doctorTokenServices.addtoken(doctorTokenData);

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.OK,statusCode);
    }

    @Test
    public void addData2(){
        DoctorTokenData lawyerTokenData = mock(DoctorTokenData.class);
        Optional<DoctorTokenData> optional = Optional.of(lawyerTokenData);
        doReturn(optional).when(doctorTokenRepository).findById(0);
        ResponseEntity<DoctorTokenData> responseEntity = doctorTokenServices.addtoken(lawyerTokenData);

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND,statusCode);
    }

    @Test
    public void getAllToken1(){
        DoctorTokenData lawyerTokenData = mock(DoctorTokenData.class);
        List<DoctorTokenData> list = new ArrayList<>();
        list.add(lawyerTokenData);
        doReturn(list).when(doctorTokenRepository).findAll();
        ResponseEntity <List<DoctorTokenData>> responseEntity = doctorTokenServices.getalltoken();

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.OK,statusCode);
    }

    @Test
    public void getAllToken2(){
        List<DoctorTokenData> list = new ArrayList<>();
        doReturn(list).when(doctorTokenRepository).findAll();
        ResponseEntity <List<DoctorTokenData>> responseEntity = doctorTokenServices.getalltoken();
        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND,statusCode);
    }

    @Test
    public void testValidateEmail(){
        assertTrue(DoctorTokenData.isValidEmail("Abc123@gmail.com"));
    }

    @Test
    public void testValidateWrongEmail(){
        assertFalse(DoctorTokenData.isValidEmail("Abc123gmail.com"));
    }
}

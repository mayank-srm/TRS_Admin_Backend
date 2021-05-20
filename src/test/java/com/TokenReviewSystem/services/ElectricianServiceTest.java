package com.TokenReviewSystem.services;

import com.TokenReviewSystem.model.ElectricianTokenData;
import com.TokenReviewSystem.model.UserData;
import com.TokenReviewSystem.repository.ElectricianTokenRepository;
import com.TokenReviewSystem.repository.UserDataRepository;
import com.TokenReviewSystem.repository.UserRepository;
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
public class ElectricianServiceTest {

    @Mock
    ElectricianTokenRepository electricianTokenRepository;

    @Mock
    UserRepository userRepository;

    @Spy
    @InjectMocks
    ElectricianTokenServices electricianTokenServices;

    @Mock
    UserDataRepository userDataRepository;

    @Test
    public void findData1(){
        String emailId = "abc@gmail.com";
        doReturn(1234).when(userRepository).findidandupdate(emailId);
        UserData userData = mock(UserData.class);
        Optional<UserData> optional = Optional.of(userData);
        doReturn(optional).when(userDataRepository).findById(1234);
        ResponseEntity<UserData> responseEntity = electricianTokenServices.findpatientdata(emailId);

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
        ResponseEntity<UserData> responseEntity = electricianTokenServices.findpatientdata(emailId);

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND,statusCode);
    }

    @Test
    public void addData1(){
        ElectricianTokenData electricianTokenData = mock(ElectricianTokenData.class);
        ElectricianTokenData saved = mock(ElectricianTokenData.class);
        Optional<ElectricianTokenData> optional = Optional.empty();
        doReturn(optional).when(electricianTokenRepository).findById(0);
        doReturn(saved).when(electricianTokenRepository).save(electricianTokenData);

        ResponseEntity<ElectricianTokenData> responseEntity = electricianTokenServices.addtoken(electricianTokenData);

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.OK,statusCode);
    }

    @Test
    public void addData2(){
        ElectricianTokenData electricianTokenData = mock(ElectricianTokenData.class);
        Optional<ElectricianTokenData> optional = Optional.of(electricianTokenData);
        doReturn(optional).when(electricianTokenRepository).findById(0);
        ResponseEntity<ElectricianTokenData> responseEntity = electricianTokenServices.addtoken(electricianTokenData);

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND,statusCode);
    }

    @Test
    public void getAllToken1(){
        ElectricianTokenData lawyerTokenData = mock(ElectricianTokenData.class);
        List<ElectricianTokenData> list = new ArrayList<>();
        list.add(lawyerTokenData);
        doReturn(list).when(electricianTokenRepository).findAll();
        ResponseEntity <List<ElectricianTokenData>> responseEntity = electricianTokenServices.getalltoken();

        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.OK,statusCode);
    }

    @Test
    public void getAllToken2(){
        List<ElectricianServiceTest> list = new ArrayList<>();
        doReturn(list).when(electricianTokenRepository).findAll();
        ResponseEntity <List<ElectricianTokenData>> responseEntity = electricianTokenServices.getalltoken();
        HttpStatus statusCode = responseEntity.getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND,statusCode);
    }


    @Test
    public void testValidateEmail(){
        assertTrue(ElectricianTokenData.isValidEmail("Abc123@gmail.com"));
    }

    @Test
    public void testValidateWrongEmail(){
        assertFalse(ElectricianTokenData.isValidEmail("Abc123gmail.com"));
    }

}
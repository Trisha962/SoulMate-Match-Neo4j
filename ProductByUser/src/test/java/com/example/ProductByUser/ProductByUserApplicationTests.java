//package com.example.ProductByUser;//package com.example.ProductByUser;
////
////import org.junit.jupiter.api.Test;
////import org.springframework.boot.test.context.SpringBootTest;
////
////@SpringBootTest
////class ProductByUserApplicationTests {
////
////	@Test
////	void contextLoads() {
////	}
////
////}
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import com.example.ProductByUser.exception.UserAlreadyExists;
//import com.example.ProductByUser.model.User;
//import com.example.ProductByUser.model.UserDTO;
//import com.example.ProductByUser.proxy.NeoProxy;
//import com.example.ProductByUser.proxy.UserProxy;
//import com.example.ProductByUser.repository.UserRepository;
//import com.example.ProductByUser.service.IUserService;
//import com.example.ProductByUser.service.UserServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Optional;
//
//public class ProductByUserApplicationTests {
//
//    private UserRepository userRepository = mock(UserRepository.class);
//    private UserProxy userProxy = mock(UserProxy.class);
//    private NeoProxy neoProxy = mock(NeoProxy.class);
//
//    private IUserService userService = new UserServiceImpl(userRepository, userProxy, neoProxy);
//
//    @Test
//    public void testAddUser() throws UserAlreadyExists {
//        // Prepare test data
//        User user = new User();
//        user.setEmail("test@example.com");
//        user.setPassword("password");
//
//        when(userRepository.findById(user.getEmail())).thenReturn(Optional.empty());
//        when(userRepository.save(user)).thenReturn(user);
//
//        // Perform the test
//        User response = userService.addUser(user);
//
//        // Verify the result
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(user, response.getBody());
//
//        verify(userRepository, times(1)).findById(user.getEmail());
//        verify(userRepository, times(1)).save(user);
//        verify(userProxy, times(1)).registerUser(any(UserDTO.class));
//        verify(neoProxy, times(1)).registerUser(any(UserDTO.class));
//    }
//
//    @Test
//    public void testAddUser_UserAlreadyExists() throws UserAlreadyExists {
//        // Prepare test data
//        User user = new User();
//        user.setEmail("test@example.com");
//        user.setPassword("password");
//
//        when(userRepository.findById(user.getEmail())).thenReturn(Optional.of(user));
//
//        // Perform the test
//        User response = userService.addUser(user);
//
//        // Verify the result
//        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
//        assertEquals("User already exists", response.getBody());
//
//        verify(userRepository, times(1)).findById(user.getEmail());
//        verify(userRepository, never()).save(user);
//        verify(userProxy, never()).registerUser(any(UserDTO.class));
//        verify(neoProxy, never()).registerUser(any(UserDTO.class));
//    }
//
//}

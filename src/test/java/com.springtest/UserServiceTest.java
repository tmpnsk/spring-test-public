package com.springtest;

import com.springtest.model.News;
import com.springtest.model.User;
import com.springtest.model.enums.Role;
import com.springtest.repo.NewsRepository;
import com.springtest.repo.UserRepository;
import com.springtest.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private NewsRepository newsRepository;
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@InjectMocks
	private UserServiceImpl userService;

	private static final Long USER_ID = 3L,
			USER_ADMIN_ID = 1L;
	private static final String USER_LOGIN = "nagibator3000",
			USER_NAME = "dfsda",
			CURRENT_PASSWORD = "12345678",
			NEW_PASSWORD = "666",
			INCORRECT_PASSWORD = "qwerty123",
			HASHED_PASSWORD = "$2a$10$ldNU/V54T.LIdmRVm1Aymer85G2KEz3RnrTGXJxjCi2DC7ASSl0HG";
	private static User USER,
			USER_ADMIN;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		USER = getTestUser(false);
		USER_ADMIN = getTestUser(true);

		Mockito.when(userRepository.findOne(Matchers.eq(USER_ADMIN_ID))).thenReturn(USER_ADMIN);

		Mockito.when(bCryptPasswordEncoder.matches(Matchers.eq(CURRENT_PASSWORD), Matchers.anyString())).thenReturn(true);
		Mockito.when(bCryptPasswordEncoder.matches(Matchers.eq(INCORRECT_PASSWORD), Matchers.anyString())).thenReturn(false);
		Mockito.when(bCryptPasswordEncoder.encode(Matchers.anyString())).thenReturn(HASHED_PASSWORD);
	}

	private User getTestUser(boolean isAdmin) {
		User user = new User();
		user.setId(isAdmin ? USER_ADMIN_ID : USER_ID);
		user.setLogin(USER_LOGIN);
		user.setRole(isAdmin ? Role.ROLE_ADMIN : Role.ROLE_USER);
		user.setPassword(HASHED_PASSWORD);
		user.setFirstName(USER_NAME);
		user.setSecondName(USER_NAME);
		return user;
	}

	@Test
	public void testGetOne() {
		Mockito.when(userRepository.findOne(Matchers.anyLong())).thenReturn(USER);
		assertNotNull(userService.getOne(USER_ID));
	}

	@Test
	public void testGetNewsByUser() {
		List<News> newsList = Collections.emptyList();
		Mockito.when(newsRepository.findByUser(Matchers.eq(USER_ID))).thenReturn(newsList);

		assertNotNull(userService.getNewsByUser(USER_ID));
	}

	@Test
	public void testUpdatePassword() {
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);

		Authentication userAuthentication = new UsernamePasswordAuthenticationToken(USER, USER.getPassword());
		Mockito.when(securityContext.getAuthentication()).thenReturn(userAuthentication);

		assertTrue(userService.updatePassword(NEW_PASSWORD, CURRENT_PASSWORD, USER_ID));
		assertFalse(userService.updatePassword(NEW_PASSWORD, INCORRECT_PASSWORD, USER_ID));

		Authentication adminAuthentication = new UsernamePasswordAuthenticationToken(USER_ADMIN, USER_ADMIN.getPassword());
		Mockito.when(securityContext.getAuthentication()).thenReturn(adminAuthentication);

		assertTrue(userService.updatePassword(NEW_PASSWORD, CURRENT_PASSWORD, USER_ADMIN_ID));
	}
}
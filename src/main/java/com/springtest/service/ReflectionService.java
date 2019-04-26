package com.springtest.service;

import javax.servlet.http.HttpServletRequest;

public interface ReflectionService {

	Object getObjectFromRequest(Class<?> entity, HttpServletRequest request);
}
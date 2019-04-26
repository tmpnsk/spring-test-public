package com.springtest.service.impl;

import com.springtest.model.BaseModel;
import com.springtest.service.ReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.springframework.util.StringUtils.capitalize;

@Service
public class ReflectionServiceImpl implements ReflectionService {

	@Autowired
	private ApplicationContext appContext;

	private final String REPOSITORY_POSTFIX = "Repository";
	private final String DATE_FORMAT = "yyyy-MM-dd";

	@Override
	public Object getObjectFromRequest(Class<?> entity, HttpServletRequest request) {
		Object record = createObjectByClass(entity);
		Field[] fields = entity.getDeclaredFields();
		for (Field field : fields) {
			Method method;
			if (request.getParameter(field.getName()) != null && !request.getParameter(field.getName()).isEmpty())
				try {
					method = entity.getMethod("set" + capitalize(field.getName()), field.getType());
					method.invoke(record, castToFieldType(field.getType(), request.getParameter(field.getName())));
				} catch (Exception e) {
					e.printStackTrace();
				}
		}

		return record;
	}

	private <T> T createObjectByClass(Class<T> entity) {
		try {
			return entity.getConstructor().newInstance();
		} catch (Exception e) {
			System.out.print("Failed to create new instance of " + entity);
			return null;
		}
	}

	private Object castToFieldType(Class<?> type, String value) {	// преобразуем значение из строки в тип, в котором данные должны хранится
		if (type.isAssignableFrom(String.class))
			return value;
		if (BaseModel.class.isAssignableFrom(type))
			return getBasisObject(type, Long.valueOf(value));
		if (type.isAssignableFrom(Character.class))
			return value.charAt(0);
		if (Enum.class.isAssignableFrom(type))
			return Enum.valueOf((Class<? extends Enum>) type, value);
		if (Number.class.isAssignableFrom(type)) {
			if (type.isAssignableFrom(Long.class))
				return Long.valueOf(value);
			if (type.isAssignableFrom(Integer.class))
				return Integer.valueOf(value);
			if (type.isAssignableFrom(Short.class))
				return Short.valueOf(value);
			if (type.isAssignableFrom(Byte.class))
				return Byte.valueOf(value);
			if (type.isAssignableFrom(Float.class))
				return Float.valueOf(value);
			if (type.isAssignableFrom(Double.class))
				return Double.valueOf(value);
		}
		if (Date.class.isAssignableFrom(type))
			return getDateFromString(value);
		return null;
	}

	private <T> T getBasisObject(Class<T> entity, Long id) {	// получаем наш кастомный объект из БД (через репозиторию)
		return (T) getRepositoryByClass(entity).findOne(id);
	}

	private CrudRepository getRepositoryByClass(Class<?> entity) {
		return Optional.ofNullable((CrudRepository) appContext.getBean(getCrudBeanName(entity.getSimpleName())))
				.orElseThrow(() -> new RuntimeException("No repo for " + entity.getName()));
	}

	private String getCrudBeanName(String entityName) {	// получаем имя репозитории ("ИмяКласса" + "Repository")
		return entityName.substring(0, 1).toLowerCase() + entityName.substring(1) + REPOSITORY_POSTFIX;
	}

	private Date getDateFromString(String value) {
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		try {
			return new Date((df.parse(value)).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
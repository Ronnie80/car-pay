
package com.yangyl.manage.common.dto;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.yangyl.manage.utils.CommonUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * java 8 时间默认序列化
 *
 * @author L.cm
 */
public class JavaTimeModule extends SimpleModule {

	public JavaTimeModule() {
		super(PackageVersion.VERSION);
		this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(CommonUtils.DATETIME_FORMAT));
		this.addDeserializer(LocalDate.class, new LocalDateDeserializer(CommonUtils.DATE_FORMAT));
		this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(CommonUtils.TIME_FORMAT));
		this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(CommonUtils.DATETIME_FORMAT));
		this.addSerializer(LocalDate.class, new LocalDateSerializer(CommonUtils.DATE_FORMAT));
		this.addSerializer(LocalTime.class, new LocalTimeSerializer(CommonUtils.TIME_FORMAT));
	}

}

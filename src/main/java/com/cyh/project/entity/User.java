package com.cyh.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.beetl.sql.core.annotatoin.AutoID;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class User implements Serializable {

	@AutoID
	private Integer id;
	private String name;
	private Integer age;
	@JsonFormat(pattern = "yyyy-MM-dd HH")
	private Date date;

}

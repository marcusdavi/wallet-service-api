package com.wallet.api.exceptions.handler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.http.HttpStatusCode;

@JsonInclude(Include.NON_NULL)
public class Problem {

    private Integer status;
    private OffsetDateTime dateTime;
    private String title;
    private List<Field> fields;

    public Integer getStatus() {
	return status;
    }

    public void setStatus(Integer status) {
	this.status = status;
    }

    public OffsetDateTime getDateTime() {
	return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
	this.dateTime = dateTime;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public List<Field> getFields() {
	return fields;
    }

    public void setFields(List<Field> fields) {
	this.fields = fields;
    }

    public static Problem buildProblem(String message, HttpStatusCode status) {
        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateTime(OffsetDateTime.now());
        problem.setTitle(message);
        return problem;
    }

}

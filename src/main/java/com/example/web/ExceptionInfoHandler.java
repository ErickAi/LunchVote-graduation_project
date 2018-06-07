package com.example.web;

import com.example.util.exception.DateExpiredException;
import com.example.util.exception.ErrorInfo;
import com.example.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {
    private static Logger log = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)  // 401
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ErrorInfo unauthorized(HttpServletRequest req, AuthenticationException e) {
            return logAndGetErrorInfo(req, e);
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)  // 403
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ErrorInfo accessDenied(HttpServletRequest req, AccessDeniedException e) {
        return logAndGetErrorInfo(req, e);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DateExpiredException.class)
    @ResponseBody
    public ErrorInfo dateExpired(HttpServletRequest req, DateExpiredException e) {
        return logAndGetErrorInfo(req, e);
    }
    //  http://stackoverflow.com/a/22358422/548473
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorInfo unprocessable(HttpServletRequest req, NotFoundException e) {
        return logAndGetErrorInfo(req, e);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        return logAndGetErrorInfo(req, e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e);
    }

    private static ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e) {
            log.error("error at request " + req.getRequestURL(), e);
        return new ErrorInfo(req.getRequestURL(), e);
    }}
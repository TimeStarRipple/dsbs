//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.whut.dsbs.customer.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.exception.BaseException;
import com.hand.hap.core.exception.TokenException;
import com.hand.hap.core.impl.RequestHelper;
import com.hand.hap.core.util.RequestUtil;
import com.hand.hap.core.validator.FieldErrorWithBean;
import com.hand.hap.security.DefaultConfiguration;
import com.hand.hap.security.TokenUtils;
import com.hand.hap.system.dto.BaseDTO;
import com.hand.hap.system.dto.ResponseData;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.ognl.OgnlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

@RestController
public class BaseController {
    protected static final String DEFAULT_PAGE = "1";
    protected static final String DEFAULT_PAGE_SIZE = "10";
    protected static final String SYS_VALIDATION_PREFIX = "hap.validation.";
    protected static final String DEFAULT_VIEW_HOME = "";
    @Autowired
    private DefaultConfiguration configuration;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private Validator validator;
    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    public BaseController() {
    }

    protected String getViewPath() {
        return this.configuration != null?this.configuration.getDefaultViewPath():"";
    }

    protected Validator getValidator() {
        return this.validator;
    }

    public MessageSource getMessageSource() {
        return this.messageSource;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder, HttpServletRequest request) {
        Object target = dataBinder.getTarget();
    }

    protected IRequest createRequestContext(HttpServletRequest request) {
        return RequestHelper.createServiceRequest(request);
    }

    protected void checkToken(HttpServletRequest request, Collection<? extends BaseDTO> dtos) throws TokenException {
        if(request != null) {
            this.checkToken(request.getSession(false), dtos);
        }

    }

    protected void checkToken(HttpSession session, Collection<? extends BaseDTO> dtos) throws TokenException {
        TokenUtils.checkToken(session, dtos);
    }

    protected void checkToken(HttpServletRequest request, BaseDTO baseDTO) throws TokenException {
        if(request != null) {
            this.checkToken(request.getSession(false), baseDTO);
        }

    }

    protected void checkToken(HttpSession session, BaseDTO baseDTO) throws TokenException {
        TokenUtils.checkToken(session, baseDTO);
    }

    @ExceptionHandler({Exception.class})
    public Object exceptionHandler(Exception exception, HttpServletRequest request) {
        this.logger.error(exception.getMessage(), exception);
        if(RequestUtil.isAjaxRequest(request)) {
            Throwable thr = this.getRootCause(exception);
            ResponseData res = new ResponseData(false);
            if(thr instanceof BaseException) {
                BaseException be = (BaseException)thr;
                Locale locale = RequestContextUtils.getLocale(request);
                String messageKey = be.getDescriptionKey();
                String message = this.messageSource.getMessage(messageKey, be.getParameters(), messageKey, locale);
                res.setCode(be.getCode());
                res.setMessage(message);
            } else {
                res.setMessage(thr.getMessage());
            }

            return res;
        } else {
            return new ModelAndView("500");
        }
    }

    protected String getErrorMessage(Errors errors, HttpServletRequest request) {
        Locale locale = RequestContextUtils.getLocale(request);
        String errorMessage = null;

        ObjectError error;
        for(Iterator var5 = errors.getAllErrors().iterator(); var5.hasNext(); errorMessage = error.getCode()) {
            error = (ObjectError)var5.next();
            if(error.getDefaultMessage() != null) {
                if(error instanceof FieldErrorWithBean) {
                    errorMessage = this.getStandardFieldErrorMessage((FieldErrorWithBean)error, locale);
                } else {
                    errorMessage = this.messageSource.getMessage(error.getDefaultMessage(), (Object[])null, locale);
                }
                break;
            }
        }

        return errorMessage;
    }

    protected String nls(HttpServletRequest request, String code, Object[] args) {
        Locale locale = RequestContextUtils.getLocale(request);
        return this.messageSource.getMessage(code, args, code, locale);
    }

    protected String nls(HttpServletRequest request, String code) {
        Locale locale = RequestContextUtils.getLocale(request);
        return this.messageSource.getMessage(code, (Object[])null, code, locale);
    }

    protected String getStandardFieldErrorMessage(FieldErrorWithBean fieldError, Locale locale) {
        String field = fieldError.getField();
        Class clazz = fieldError.getTargetBean().getClass();
        clazz = this.findDeclareClass(clazz, field);
        String fieldPromptMessageKey = clazz.getSimpleName() + "." + field;
        String fieldPrompt = this.messageSource.getMessage(fieldPromptMessageKey.toLowerCase(), (Object[])null, locale);
        return this.messageSource.getMessage("hap.validation." + fieldError.getCode().toLowerCase(), new Object[]{fieldPrompt}, locale);
    }

    private Class findDeclareClass(Class fromClass, String fieldName) {
        Class clazz = fromClass;

        while(clazz.getSuperclass() != null) {
            try {
                clazz.getDeclaredField(fieldName);
                return clazz;
            } catch (NoSuchFieldException var5) {
                clazz = clazz.getSuperclass();
            }
        }

        return fromClass;
    }

    private Throwable getRootCause(Throwable throwable) {
        while(throwable.getCause() != null) {
            throwable = throwable.getCause();
        }

        if(throwable instanceof OgnlException && ((OgnlException)throwable).getReason() != null) {
            return this.getRootCause(((OgnlException)throwable).getReason());
        } else {
            return throwable;
        }
    }

    protected Long getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Long)session.getAttribute("userId");
    }

    protected Long getRoleId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Long)session.getAttribute("roleId");
    }

    protected String getLanguage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String)session.getAttribute("locale");
    }
}

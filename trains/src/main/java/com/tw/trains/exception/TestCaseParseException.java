package com.tw.trains.exception;

/**
 * @author: yuanbo
 * @description: 测试用例解析异常
 * 
 */
public class TestCaseParseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TestCaseParseException() {
        super();
    }

    public TestCaseParseException(String message) {
        super(message);
    }

    public TestCaseParseException(Throwable e) {
        super(e);
    }

    public TestCaseParseException(String message, Throwable e) {
        super(message, e);
    }
}

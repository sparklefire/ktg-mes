package com.ktg.common.exception;

public class BussinessException extends RuntimeException {
    private static final long serialVersionUID = -1L;

    public BussinessException(){
        super();
    }


    public BussinessException(String msg){
        super(msg);
    }

    public BussinessException(String msg,Throwable cause){
        super(msg,cause);
    }

    public BussinessException(Throwable cause){
        super(cause);
    }

}

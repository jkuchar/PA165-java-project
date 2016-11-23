/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.exceptions;

/**
 *
 * @author charlliz
 */
public class CarParkServiceException extends RuntimeException{

	public CarParkServiceException() {
		super();
	}

	public CarParkServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CarParkServiceException(String message, Throwable cause) {
		super(message, cause);
	}
        
        public CarParkServiceException(String message) {
		super(message);
	}

	public CarParkServiceException(Throwable cause) {
		super(cause);
	}

}

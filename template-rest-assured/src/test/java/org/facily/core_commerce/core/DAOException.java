package org.facily.core_commerce.core;

@SuppressWarnings("serial")
public class DAOException extends RuntimeException {

	/**
	 * Constructor.
	 * @param messageKey
	 */
	public DAOException(String messageKey) {
        super(messageKey);
    }

	/**
	 * Constructor.
	 * @param messageKey
	 * @param cause
	 */
	public DAOException(String messageKey, Throwable cause) {
        super(messageKey, cause);
    }
}

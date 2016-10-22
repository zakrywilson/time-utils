package com.zakrywilson.time;

/**
 * The class <tt>ConversionException</tt> represent an error occurred while converting from one
 * date object to another.
 *
 * @author Zach Wilson
 */
public final class ConversionException extends Exception {

    /**
     * Constructs a new exception with <tt>null</tt> as its detail message. The cause is not
     * initialized, and may subsequently be initialized by a call to
     * {@link Throwable.initCause(java.lang.Throwable)}.
     */
    ConversionException() {}

    /**
     * Constructs a new exception with the specified detail message. The cause is not initialized,
     * and may subsequently be initialized by a call to
     * {@link Throwable.initCause(java.lang.Throwable)}.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the
     *                {@link Throwable.getMessage()} method.
     */
    ConversionException(final String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * <p>
     * Note that the detail message associated with cause is <i>not</i> automatically incorporated
     * in this exception's detail message.
     * </p>
     *
     * @param message the detail message. The detail message is saved for later retrieval by the
     *                {@link Throwable.getMessage()} method.
     * @param cause the cause (which is saved for later retrieval by the {@link Throwable.getCause()}
     *              method). (A <tt>null</tt> value is permitted, and indicates that the cause is
     *              nonexistent or unknown.)
     */
    ConversionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message of
     * {@code (cause==null ? null : cause.toString())} (which typically contains the class and
     * detail message of <tt>cause</tt>). This constructor is useful for exceptions that are little
     * more than wrappers for other throwables (for example, {@link PrivilegedActionException}).
     *
     * @param cause the cause (which is saved for later retrieval by the {@link Throwable.getCause()}
     *              method). (A <tt>null</tt> value is permitted, and indicates that the cause is
     *              nonexistent or unknown.)
     */
    ConversionException(final Throwable cause) {
        super(cause);
    }

}

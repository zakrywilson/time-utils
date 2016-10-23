package com.zakrywilson.time;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The class {@link TimeUtils} performs conversions to and from {@link Date},
 * {@link XMLGregorianCalendar}, and {@link String}. This class allows for multiple date formats
 * for parsing <tt>Strings</tt> to date/calendar objects. The first element in the array of date
 * formats is set as the <i>primary</i> date format which will be used to convert date/calendar
 * objects to their <tt>String</tt> form.
 *
 * @author Zach
 */
public final class TimeUtils {

    /**
     * The value <tt>0</tt> to indicate the primary date format in the array of date formats.
     */
    private static final int PRIMARY_FORMAT = 0;

    /**
     * All possible date formats for parsing and <tt>String</tt> conversions.
     */
    private final String[] dateFormats;

    /**
     * Returns an instance with an initialized date format. The date format is the date format by
     * which <tt>Strings</tt> will be parsed and date/calendar objects will be converted to
     * <tt>Strings</tt>.
     *
     * @param dateFormat the date format to be set
     * @throws IllegalArgumentException if the date format is <tt>null</tt>
     */
    public TimeUtils(final String dateFormat) throws IllegalArgumentException {
        if (dateFormat == null) {
            throw new IllegalArgumentException("Date format cannot be null");
        }
        this.dateFormats = new String[]{dateFormat};
    }

    /**
     * Returns an instance with an initialized array of date formats. The date formats are the
     * formats by which <tt>Strings</tt> will be parsed and date/calendar objects will be converted
     * to <tt>Strings</tt>. The date format at element <tt>0</tt> will be the primary format used
     * for converting objects to <tt>Strings</tt>.
     *
     * @param dateFormats the date formats to be set
     * @throws IllegalArgumentException if the date format is <tt>null</tt>
     */
    public TimeUtils(final String... dateFormats) throws IllegalArgumentException {
        if (dateFormats == null) {
            throw new IllegalArgumentException("Date format array cannot be null");
        }
        if (dateFormats.length == 0) {
            throw new IllegalArgumentException("Date format must contain at least one string");
        }
        this.dateFormats = dateFormats;
    }

    /**
     * Formats a {@link Date} to the provided <tt>String</tt> format.
     *
     * @param date the date to be formatted
     * @return the formatted <tt>Date</tt>
     */
    public String formatDate(final Date date) {
        return TimeUtils.formatDate(date, dateFormats[PRIMARY_FORMAT]);
    }

    /**
     * Formats the {@link XMLGregorianCalendar} to the provided <tt>String</tt> format.
     *
     * @param calendar the date to be formatted
     * @return the formatted <tt>XMLGregorianCalendar</tt>
     */
    public String formatDate(final XMLGregorianCalendar calendar) {
        return TimeUtils.formatDate(calendar, dateFormats[PRIMARY_FORMAT]);
    }

    /**
     * Parses a date <tt>String</tt> with the array of possible formats.
     *
     * @param date the <tt>String</tt> to be parsed to a <tt>Date</tt>
     * @return the <tt>Date</tt>
     * @throws ConversionException if the conversion produces an error
     * @throws ParseException if none of the parse formats match the <tt>String</tt> date
     */
    public Date parseDate(final String date) throws ConversionException, ParseException {
        return TimeUtils.parseDate(date, dateFormats);
    }

    /**
     * Parses a date <tt>String</tt> with the array of possible formats.
     *
     * @param date the <tt>String</tt> to be parsed to a <tt>XMLGregorianCalendar</tt>
     * @return the <tt>XMLGregorianCalendar</tt>
     * @throws ConversionException if the conversion produces an error
     * @throws ParseException if none of the parse formats match the <tt>String</tt> date
     */
    public XMLGregorianCalendar parseDateToXMLGregorianCalendar(final String date)
            throws ConversionException, ParseException {
        return TimeUtils.parseDateToXMLGregorianCalendar(date, dateFormats);
    }

    /**
     * Converts a {@link Date} to an {@link XMLGregorianCalendar}.
     *
     * @param date the date to be converted
     * @return the <tt>XMLGregorianCalendar</tt> equivalent of the <tt>Date</tt> object
     * @throws ConversionException if the conversion produces an error
     */
    public static XMLGregorianCalendar dateToXMLGregorianCalendar(final Date date)
            throws ConversionException {
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (final DatatypeConfigurationException e) {
            throw new ConversionException("Unable to convert Date to XMLGregorianCalendar", e);
        }
    }

    /**
     * Converts a {@link XMLGregorianCalendar} to a {@link Date}.
     *
     * @param calendar the date to be converted
     * @return the <tt>Date</tt> equivalent of the <tt>XMLGregorianCalendar</tt> object
     */
    public static Date xmlGregorianCalendarToDate(final XMLGregorianCalendar calendar) {
        return calendar.toGregorianCalendar().getTime();
    }

    /**
     * Formats a {@link Date} to the provided <tt>String</tt> format.
     *
     * @param date the date to be formatted
     * @param formatPattern the date format to which the <tt>Date</tt> object is to be formatted
     * @return the formatted <tt>Date</tt>
     */
    public static String formatDate(final Date date, final String formatPattern) {
        return new SimpleDateFormat(formatPattern).format(date);
    }

    /**
     * Formats the {@link XMLGregorianCalendar} to the provided <tt>String</tt> format.
     *
     * @param calendar the date to be formatted
     * @param formatPattern the date format to which the <tt>Date</tt> object is to be formatted
     * @return the formatted <tt>XMLGregorianCalendar</tt>
     */
    public static String formatDate(final XMLGregorianCalendar calendar,
            final String formatPattern) {
        return new SimpleDateFormat(formatPattern).format(calendar);
    }

    /**
     * Parses a date <tt>String</tt> with an array of possible formats.
     *
     * @param date the <tt>String</tt> to be parsed to a <tt>Date</tt>
     * @param parseFormats the multiple date formats
     * @return the <tt>Date</tt>
     * @throws ParseException if none of the parse formats match the <tt>String</tt> date
     * @throws ConversionException if the conversion produces an error
     */
    public static Date parseDate(final String date, final String... parseFormats)
            throws ParseException, ConversionException {
        final SimpleDateFormat parser = new SimpleDateFormat();
        for (String parseFormat : parseFormats) {
            String pattern = parseFormat;
            if (pattern.endsWith("ZZ")) {
                pattern = pattern.substring(0, pattern.length() - 1);
            }
            parser.applyPattern(pattern);
            final Date d = parser.parse(date);
            if (d != null) {
                return d;
            }
        }
        throw new ParseException("Unable to parse date '" + date + "'", 0);
    }

    /**
     * Parses a date <tt>>String</tt> with an array of possible formats.
     *
     * @param date the <tt>String</tt> to be parsed to a <tt>XMLGregorianCalendar</tt>
     * @param parseFormats the multiple date formats
     * @return the <tt>XMLGregorianCalendar</tt>
     */
    public static XMLGregorianCalendar parseDateToXMLGregorianCalendar(final String date,
            final String... parseFormats) throws ParseException, ConversionException {
        return dateToXMLGregorianCalendar(parseDate(date, parseFormats));
    }

}

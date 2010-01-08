package name.pehl.gwt.piriti.client.converter;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * Converter for float objects. Uses {@code Float.valueOf(value)} if no format
 * is specified and {@link NumberFormat#parse(String)} otherwise.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class FloatConverter extends AbstractConverter<Float>
{
    /**
     * Converts the specified value to float.
     * 
     * @param value
     *            The string to be converted
     * @param format
     *            Must be a valid number format or {@code null}
     * @return {@code null} if the value is {@code null}, empty or in the wrong
     *         format, otherwise the converted float
     * @see name.pehl.gwt.piriti.client.converter.Converter#convert(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Float convert(String value, String format)
    {
        if (isValid(value))
        {
            try
            {
                if (format == null)
                {
                    return Float.valueOf(value);
                }
                else
                {
                    return parseFloat(value, format);
                }
            }
            catch (NumberFormatException e)
            {
                return null;
            }
        }
        return null;
    }


    /**
     * Parsing happens in an extra method so it can be overwritten in unit
     * tests.
     * 
     * @param value
     * @param format
     * @return
     */
    protected Float parseFloat(String value, String format)
    {
        NumberFormat numberFormat = NumberFormat.getFormat(format);
        return new Float((float) numberFormat.parse(value));
    }
}

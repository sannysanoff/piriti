package name.pehl.gwt.piriti.rebind.json.fieldhandler;

import name.pehl.gwt.piriti.client.json.JsonReader;
import name.pehl.gwt.piriti.rebind.FieldContext;
import name.pehl.gwt.piriti.rebind.IndentedWriter;
import name.pehl.gwt.piriti.rebind.fieldhandler.AbstractRegistryFieldHandler;
import name.pehl.gwt.piriti.rebind.fieldhandler.FieldHandler;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

/**
 * {@link FieldHandler} implementation for types with an own {@link JsonReader}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class JsonRegistryFieldHandler extends AbstractRegistryFieldHandler
{
    /**
     * TODO Javadoc
     * 
     * @param writer
     * @param fieldContext
     * @throws UnableToCompleteException
     * @see name.pehl.gwt.piriti.rebind.xml.fieldhandler.DefaultFieldHandler#writeConverterCode(name.pehl.gwt.piriti.rebind.IndentedWriter,
     *      name.pehl.gwt.piriti.rebind.FieldContext)
     */
    @Override
    public void writeConverterCode(IndentedWriter writer, FieldContext fieldContext) throws UnableToCompleteException
    {
        JClassType classType = fieldContext.getClassOrInterfaceType();
        JField jsonRegistryField = findRegistryMember(fieldContext.getClassOrInterfaceType());
        writer.write("JsonReader<%1$s> %2$sReader = jsonRegistry.get(%1$s.class);", classType.getQualifiedSourceName(),
                fieldContext.getValueVariable());
        writer.write("if (%sReader != null) {", fieldContext.getValueVariable());
        writer.indent();
        writer.write("Element nestedElement = XPathUtils.getElement(%s, \"%s\");", fieldContext.getInputVariable(),
                fieldContext.getPath());
        writer.write("if (nestedElement != null) {");
        writer.indent();
        writer.write("%s = %s.%s.readSingle(nestedElement);", fieldContext.getValueVariable(), classType
                .getQualifiedSourceName(), jsonRegistryField.getName());
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("}");
    }


    @Override
    protected String getReaderClassname()
    {
        return JsonReader.class.getName();
    }
}
package test.common.util.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import test.common.util.StringHelper;
/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=BaseModelDTO.java" alt="e020378">Jerome KARMANN</a>
 *
 */
public class BaseModelDTO implements Serializable {

	private static final long serialVersionUID = -4914974329720744780L;

	private Map<String, Object> keyValue = new HashMap<String, Object>();

	/**
	 * @param map the map to set
	 */
	public void setKeyValue(Map<String, Object> kv) {
		this.keyValue = kv;
	}

	/**
	 * @return the map
	 */
	public Map<String, Object> getKeyValue() {
		return keyValue;
	}

	public void setProperty(String key, Object value){
		if(key!=null)
			this.keyValue.put(key, value);
	}
	
	public Object getProperty(String key) {
		if(key!=null)
			return this.keyValue.get(key);
		return null;
	}

	public BigDecimal getPropertyBigDecimal(String key) {
		if(key!=null){
			Object result = this.keyValue.get(key);
			if(result!=null)
				if(result instanceof BigDecimal)
					return (BigDecimal)result;
				else
					throw new ClassCastException(generateWrongPropertyCastMessage(BigDecimal.class, key));
		}
		return null;
	}

	public String getPropertyString(String key) {
		if(key!=null){
			Object result = this.keyValue.get(key);
			if(result!=null)
				if(result instanceof String)
					return (String)result;
				else
					throw new ClassCastException(generateWrongPropertyCastMessage(String.class, key));
		}
		return null;
	}

	private String generateWrongPropertyCastMessage(Class<?> clas, String property){
		return MessageFormat.format("Property [{0}] is not an instance of class [{1}]\ntoString(\n{2}\n)", property, clas.getName(), StringHelper.toString(this));
	}
}

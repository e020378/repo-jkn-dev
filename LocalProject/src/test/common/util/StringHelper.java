package test.common.util;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=StringHelper.java" alt="e020378">Jerome KARMANN</a>
 *
 */
public final class StringHelper {

	private StringHelper(){}
	
	/**
	 * Prepare x times the params for the Callable Statement
	 * @param count Number of params according to the Stored Procedure for the target Callable Statement.
	 * @return String with x time the question Mark (For instance "?, ?, ?, ...").
	 */
	public static final String csParamTimes(int count){
		if(count<1)
			return "";
		String p = "";
		for(int i=0; i<count; i++){
			p = p.concat(",?");
		}
		return p.substring(1);
	}

	/**
	 * Apply LEFT trim + RIGHT trim to remove leading + trailing space sequence.
	 * @param input
	 * @return new String trimmed
	 */
	public static String trimLR(final String input){
		if(input==null) return null;
		return input.replaceAll("^\\s*|\\s*$", "");
	}

	public static final String addChar(String input, char c, int count, boolean left){
		if(count<1) return input;
		String s = "";
		for(int i=0; i<count; i++){
			s = c + s;
		}
		if(left)
			return s + input;
		else
			return input + s;
	}

	public static final boolean isEmpty(String input){
		return input==null||"".equalsIgnoreCase(trimLR(input));
	}
	
	public static final String quote(String value){
		return "'"+value+"'";
	}

	/**
	 * Generic toString() method that will accept any Object type
	 * @param o Object to be printed as String
	 * @return String representation of the Object, according to its accessors.
	 */
	public static String toString(Object o) {
		try{
			int mx = 0;
			List<String> ln = new ArrayList<String>(),
						 lv = new ArrayList<String>();
			Method[] methods = o.getClass().getMethods();
			String atom = "\t{0} = {1}\r\n";
			for(Method m: methods ){
				String n = m.getName();
				if(n.matches("(get|is).*") && !n.matches("getClass")){
					try{
						Object res = m.invoke(o);
						if(res!=null){
							n = n.replaceFirst("^(get|is)", "");
							if(n.length()>mx)
								mx = n.length();
							ln.add(n);
							lv.add(res.toString());
						}
					}catch(Exception e){}
				}
			}
			String s = "";
			for(int i=0; i<ln.size(); i++){
				String n = ln.get(i);
				n = addChar(n, ' ', mx - n.length(), false);
				s += MessageFormat.format(atom, n, lv.get(i));
			}
			return o.getClass().getName()+".toString(){\r\n"+s+"}";
		}catch(Exception e){
			return o.toString();
		}
	}
}

package test.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author <a href="mailto:jerome.karmann-partner@arcelormittal.com?subject=FileUtil.java" alt="e020378">Jerome KARMANN</a>
 */
public final class FileUtil {

	private FileUtil(){}

	public static String readFile(File f) throws IOException{
		StringBuffer sb = new StringBuffer(0);
		BufferedReader br = new BufferedReader(new FileReader(f));
		while(br.ready()){
			sb.append(br.readLine()).append("\n");
		}
		br.close();
		return sb.toString();
	}

}
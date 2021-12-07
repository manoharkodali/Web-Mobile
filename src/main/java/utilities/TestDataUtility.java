package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class TestDataUtility {

	public static Map<String, Object> loadData(String ymlFile) throws FileNotFoundException {
		String location = System.getProperty("user.dir") + "\\src\\main\\resources\\TestData\\" + ymlFile;
		InputStream inputStream = new FileInputStream(new File(location));

		Yaml yaml = new Yaml();
		Map<String, Object> data = yaml.load(inputStream);
		System.out.println(data);
		return data;
	}

	public static void main(String... args) throws FileNotFoundException {
		
		String s = "01:03";
		System.out.println(s.replaceFirst("^0+(?!$)", ""));
		System.out.println(s.split(":")[0].replaceFirst("0", ""));
		System.out.println(s.split(":")[0].split("")[1]);
	}

}

package hashcodecontest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Start {

	private final static String BASE_FOLDER = "C:\\hashcodecontest";
	private final static String BASE_FOLDER_IN = BASE_FOLDER + "\\in\\";
	private final static String BASE_FOLDER_OUT = BASE_FOLDER + "\\out\\";

	private final static String FILE_SEPARATOR = " ";

	private final static List<String> IN_FILE = Arrays.asList("a_example.in", "b_small.in", "c_medium.in",
			"d_quite_big.in", "e_also_big.in");

	public static void main(String[] args) throws IOException {

		for (String inFile : IN_FILE) {

			List<String> linesFilesIn = Files.readAllLines(Paths.get(BASE_FOLDER_IN + inFile));

			String[] header = linesFilesIn.get(0).split(FILE_SEPARATOR);
			List<String> datas = Arrays.asList(linesFilesIn.get(1).split(FILE_SEPARATOR));

			List<String> result = new ArrayList<String>();
			
			// Start your code
			
			
			// End your code

			Files.write(Paths.get(BASE_FOLDER_OUT + inFile.replace(".in", ".out")),
					result.stream().collect(Collectors.joining(FILE_SEPARATOR)).getBytes());

		}

	}

}

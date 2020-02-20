package hashcodecontest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class Start {

	private final static String BASE_FOLDER = "C:\\hashcode";
	private final static String BASE_FOLDER_IN = BASE_FOLDER + "\\in\\";
	private final static String BASE_FOLDER_OUT = BASE_FOLDER + "\\out\\";

	private final static String FILE_SEPARATOR = " ";

	private final static List<String> IN_FILE = Arrays.asList("c_incunabula.txt");

	public static void main(String[] args) throws IOException {

		for (String inFile : IN_FILE) {

			List<String> linesFilesIn = Files.readAllLines(Paths.get(BASE_FOLDER_IN + inFile));

			String[] header = linesFilesIn.get(0).split(FILE_SEPARATOR);
			int nbTotalBook = Integer.parseInt(header[0]);
			int nbLibraries = Integer.parseInt(header[1]);
			int days = Integer.parseInt(header[2]);

			List<Library> totalLibraries = new ArrayList<>();

			List<String> bookLine = Arrays.asList(linesFilesIn.get(1).split(FILE_SEPARATOR));
			AtomicInteger index = new AtomicInteger();
			List<Book> totalBooks = bookLine.stream().map(b -> new Book(index.getAndIncrement(), Integer.parseInt(b)))
					.collect(Collectors.toList());

//			System.out.println(
//					"total livre : " + nbTotalBook + " nb libraries : " + nbLibraries + " total temps : " + days);
//			System.out.println(totalBooks.toString());

			List<String> librariesLines = linesFilesIn.stream().skip(2).collect(Collectors.toList());
			int indexLibrary = 0;
			for (int i = 0; i < librariesLines.size(); i += 2) {
				if (StringUtils.isNotBlank(librariesLines.get(i))) {

					Library lib = new Library(librariesLines.get(i).split(FILE_SEPARATOR), indexLibrary++);
					List<String> idBooks = Arrays.asList(librariesLines.get(i + 1).split(FILE_SEPARATOR));
					List<Integer> intIdBooks = idBooks.stream().map(Integer::parseInt).collect(Collectors.toList());
					lib.setBooks(totalBooks.stream().filter(b -> intIdBooks.contains(b.getId()))
							.collect(Collectors.toList()));
					totalLibraries.add(lib);
				}
			}

//			System.out.println(totalLibraries.toString());

			totalLibraries.stream().forEach(l -> l.setMaxAndProcess(days));

			totalLibraries = totalLibraries.stream().sorted(Comparator.comparing(Library::getRatio).reversed())
					.collect(Collectors.toList());

			List<Library> scannedLibraries = new ArrayList<>();
			List<Book> scannedBooks = new ArrayList<>();

			AtomicInteger timeLeft = new AtomicInteger(days);

			for (Library lib : totalLibraries) {
				if (timeLeft.get() > lib.getSignupProcess()) {
					try {
					timeLeft.addAndGet(-lib.getSignupProcess());
					scannedLibraries.add(lib);
					int toScan = timeLeft.get() * lib.getnBookPerDay();
					List<Book> notAlreadyScanned = lib.getBooks().stream().filter(b -> !scannedBooks.contains(b))
							.collect(Collectors.toList());

					List<Book> aScanner = notAlreadyScanned.subList(0,
							Math.min(toScan - 1, notAlreadyScanned.size() - 1));
					lib.getScannedBooks().addAll(aScanner);

					scannedBooks.addAll(aScanner);
					}catch(Exception e) {
						timeLeft.addAndGet(lib.getSignupProcess());
						scannedLibraries.remove(lib);
						
					}
				}

			}

//			System.out.println(totalLibraries.toString());
//			System.out.println("scannés " + scannedLibraries.toString());

			String linesresult = "";
			for (Library librarie : scannedLibraries) {
				linesresult += librarie.getId() + FILE_SEPARATOR + librarie.getScannedBooks().size() + "\n";
				for (int i = 0; i < librarie.getScannedBooks().size(); i++) {
					linesresult += librarie.getScannedBooks().get(i).getId();
					if (i < librarie.getScannedBooks().size() - 1) {
						linesresult += FILE_SEPARATOR;
					}

				}
				linesresult += "\n";

			}
			String output = "" + scannedLibraries.size() + "\n" + linesresult;

			Files.write(Paths.get(BASE_FOLDER_OUT + inFile.replace(".in", ".out")), output.getBytes());
		}

	}

}

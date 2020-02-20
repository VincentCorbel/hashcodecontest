package hashcodecontest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Library {

	private int id;
	private int nbBooks;
	private List<Book> books;
	private int signupProcess;
	private int nBookPerDay;
	private int processTime;
	private int maxScannableBooks;
	private int maxPossibleScore;
	private Double ratio;
	private List<Book> scannedBooks = new ArrayList<>();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNbBooks() {
		return nbBooks;
	}
	public void setNbBooks(int nbBooks) {
		this.nbBooks = nbBooks;
	}
	
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public int getSignupProcess() {
		return signupProcess;
	}
	public void setSignupProcess(int signupProcess) {
		this.signupProcess = signupProcess;
	}
	public int getnBookPerDay() {
		return nBookPerDay;
	}
	public void setnBookPerDay(int nBookPerDay) {
		this.nBookPerDay = nBookPerDay;
	}
	
	
	public int getProcessTime() {
		return processTime;
	}
	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}
	public int getMaxPossibleScore() {
		return maxPossibleScore;
	}
	public void setMaxPossibleScore(int maxPossibleScore) {
		this.maxPossibleScore = maxPossibleScore;
	}
	
	
	public int getMaxScannableBooks() {
		return maxScannableBooks;
	}
	public void setMaxScannableBooks(int maxScannableBooks) {
		this.maxScannableBooks = maxScannableBooks;
	}
	
	
	public Double getRatio() {
		return ratio;
	}
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
	
	public List<Book> getScannedBooks() {
		return scannedBooks;
	}
	public void setScannedBooks(List<Book> scannedBooks) {
		this.scannedBooks = scannedBooks;
	}
	public Library(int id, int nbBooks, List<Book> books, int signupProcess, int nBookPerDay) {
		super();
		this.id = id;
		this.nbBooks = nbBooks;
		this.books = books;
		this.signupProcess = signupProcess;
		this.nBookPerDay = nBookPerDay;
	}
	
	public Library (String[] line, int id) {
		this.id = id;
		this.nbBooks = Integer.parseInt(line[0]);
		this.signupProcess = Integer.parseInt(line[1]);
		this.nBookPerDay = Integer.parseInt(line[2]);
	}
	
	public void setMaxAndProcess(int days) {
		this.processTime = days-signupProcess;
		if(processTime<1) {
			this.maxScannableBooks = processTime*nBookPerDay;
			books = books.stream().sorted(Comparator.comparing(Book::getScore).reversed()).collect(Collectors.toList());
			AtomicInteger sum = new AtomicInteger();
			System.out.println("maxscan " +maxScannableBooks);
			System.out.println("size" +books.size());
			books.subList(0, Math.min(maxScannableBooks-1, books.size()-1)).stream().forEach(b -> sum.addAndGet(b.getScore()));		
			this.maxPossibleScore = sum.get();
			ratio = Double.valueOf(maxPossibleScore)/Double.valueOf(books.size());
			//ratio = Double.valueOf(maxPossibleScore)/Double.valueOf(signupProcess);	
		}
		else {
			
			ratio=Double.valueOf(-1);
		}
	}
	@Override
	public String toString() {
		return "Library [id=" + id + ", nbBooks=" + nbBooks + ", books=" + books + ", signupProcess=" + signupProcess
				+ ", nBookPerDay=" + nBookPerDay + ", processTime=" + processTime + ", maxScannableBooks="
				+ maxScannableBooks + ", maxPossibleScore=" + maxPossibleScore + ", ratio=" + ratio + ", scannedBooks="
				+ scannedBooks + "]";
	}

	
	
}

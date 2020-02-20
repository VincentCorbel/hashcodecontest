package hashcodecontest;

import java.util.List;

public class Library {

	private int id;
	private int nbBooks;
	private List<Book> books;
	private int signupProcess;
	private int nBookPerDay;
	
	
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
	@Override
	public String toString() {
		return "Library [id=" + id + ", nbBooks=" + nbBooks + ", books=" + books + ", signupProcess=" + signupProcess
				+ ", nBookPerDay=" + nBookPerDay + "]";
	}
}

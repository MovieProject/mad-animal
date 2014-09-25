package movie.business.domain;

public class Movie {
	private int movieNum;
	private String movieName;
	private String genre;
	private String releaseDate;
	private String drector;
	private String synopsis;
	private String photoDir;

	public Movie() {
	}

	public Movie(int movieNum, String movieName, String genre,
			String releaseDate, String drector, String synopsis, String photoDir) {
		super();
		this.movieNum = movieNum;
		this.movieName = movieName;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.drector = drector;
		this.synopsis = synopsis;
		this.photoDir = photoDir;
	}

	public int getMovieNum() {
		return movieNum;
	}

	public String getMovieName() {
		return movieName;
	}

	public String getGenre() {
		return genre;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getDrector() {
		return drector;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public String getPhotoDir() {
		return photoDir;
	}

	public void setMovieNum(int movieNum) {
		this.movieNum = movieNum;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setDrector(String drector) {
		this.drector = drector;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public void setPhotoDir(String photoDir) {
		this.photoDir = photoDir;
	}

	@Override
	public String toString() {
		return "Movie [movieNum=" + movieNum + ", movieName=" + movieName
				+ ", genre=" + genre + ", releaseDate=" + releaseDate
				+ ", drector=" + drector + ", synopsis=" + synopsis
				+ ", photoDir=" + photoDir + "]";
	}

}

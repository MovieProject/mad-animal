package movie.business.domain;

public class Movie {
	private int movieNum;
	private String movieName;
	private String genre;
	private String releaseDate;
	private String director;
	private String synopsis;
	private String photoDir;

	public Movie() {
	}
	/**insert용*/
	
	public Movie(String movieName, String genre, String director,
			String releaseDate,String synopsis, String photoDir) {
		super();
		this.movieName = movieName;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.director = director;
		this.synopsis = synopsis;
		this.photoDir = photoDir;
	}

	/**List 생성용*/
	public Movie(int movieNum, String movieName, String genre,
			String releaseDate, String director, String synopsis, String photoDir) {
		super();
		this.movieNum = movieNum;
		this.movieName = movieName;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.director = director;
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

	public String getdirector() {
		return director;
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

	public void setdirector(String director) {
		this.director = director;
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
				+ ", director=" + director + ", synopsis=" + synopsis
				+ ", photoDir=" + photoDir + "]";
	}

}

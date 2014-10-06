package movie.business.domain;

public class Movie {
	private int movieNum;
	private String movieName;
	private String genre;
	private String releaseDate;
	private String director;
	private String synopsis;
	private String photoDir;
	private String memberID;
	private int type;

	public int getType() {
		return type;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Movie() {
	}
	/**update용*/
	public Movie(int movieNum, String movieName, String genre,
			String releaseDate, String director, String synopsis,
			String photoDir, String memberID, int type) {
		super();
		this.movieNum = movieNum;
		this.movieName = movieName;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.director = director;
		this.synopsis = synopsis;
		this.photoDir = photoDir;
		this.memberID = memberID;
		this.type = type;
	}
	/**insert용*/
	
	public Movie(String movieName, String genre, String director,
			String releaseDate,String synopsis, String photoDir,String memberID,int type) {
		super();
		this.movieName = movieName;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.director = director;
		this.synopsis = synopsis;
		this.photoDir = photoDir;
		this.memberID = memberID;
		this.type = type;
	}

	/**List 생성용*/
	public Movie(int movieNum, String movieName,String director, String releaseDate,int type) {
		super();
		this.movieNum = movieNum;
		this.movieName = movieName;
		this.releaseDate = releaseDate;
		this.director = director;
		this.type = type;
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
				+ ", photoDir=" + photoDir +",memberID"+memberID+" type : "+ type+"]";
	}
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

}

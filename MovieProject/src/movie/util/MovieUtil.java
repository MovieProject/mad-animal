package movie.util;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;

public class MovieUtil {
	/**유효한 회원임을 나타내는 상수*/
	public static final int VALID_MEMBER = 1; 
	
	/**memberID가 존재하지 않는 회원임을 나타내는 상수*/
	public static final int INVALID_ID = 0; 
	/**PASSWORD가 일치하지 않는 회원임을 나타내는 상수*/
	public static final int INVALID_PASSWORD = -1; 
	
	public static final int GRADE_ADMIN = 2;
	public static final int GRADE_WRITER = 1;
	public static final int GRADE_GENERAL = 0;

	public final static int PAGE_LIST_SIZE = 5;
	public final static int PAGE_GROUP_SIZE = 2;

	public static int getTotalPageCount(int totalBoardCount) {
		int totalPageCount = totalBoardCount / PAGE_LIST_SIZE;
		if (totalBoardCount % PAGE_LIST_SIZE != 0) {
			totalPageCount++;
		}

		return totalPageCount;
	}

	public static int getStartPageNumber(int pageNumber) {
		return (((pageNumber - 1) / PAGE_GROUP_SIZE) * PAGE_GROUP_SIZE) + 1;
	}

	public static int getEndPageNumber(int pageNumber, int totalBoardCount) {
		int totalPageCount = getTotalPageCount(totalBoardCount);
		int startPageNumber = getStartPageNumber(pageNumber);

		int endPageNumber = (startPageNumber + PAGE_GROUP_SIZE) - 1;
		if (endPageNumber > totalPageCount) {
			endPageNumber = totalPageCount;
		}

		return endPageNumber;

	}

	public static int getStartRow(int pageNumber) {
		return (pageNumber - 1) * PAGE_LIST_SIZE + 1;
	}

	public static int getEndRow(int pageNumber) {
		return pageNumber * PAGE_LIST_SIZE;
	}
	
	public static void createThumbnail(String directory, String file, byte[] image, int width, int height) throws IOException {
		String extension = file.substring(file.lastIndexOf(".")  + 1);

		// load image (convert inputstream to bufferedImage)
		ByteArrayInputStream is = new ByteArrayInputStream(image);
		BufferedImage originImg = ImageIO.read(is);
		
		BufferedImage thumbImg = Scalr.resize(originImg, Method.QUALITY, Mode.FIT_TO_HEIGHT, width, height, Scalr.OP_ANTIALIAS);
		// write image (convert bufferedImage to outpurstream)
		//ByteArrayOutputStream os = new ByteArrayOutputStream();
		//ImageIO.write(thumbImg, extension, os);
		
		// write image to a file
		File thumbFile = new File(directory, file);
		ImageIO.write(thumbImg, extension, thumbFile);
	}
	
	public static void createThumbnail(File directory, File file, int width, int height) throws IOException {
		String name = file.getName();
		String extension = name.substring(name.lastIndexOf(".")  + 1);

		// load image (convert file to bufferedImage)
		BufferedImage originImg = ImageIO.read(file);		
		BufferedImage thumbImg = Scalr.resize(originImg, Method.QUALITY, Mode.FIT_TO_HEIGHT, width, height, Scalr.OP_ANTIALIAS);
		
		// write image to a file
		File thumbFile = new File(directory, "th_" + name);
		ImageIO.write(thumbImg, extension, thumbFile);
	}
}

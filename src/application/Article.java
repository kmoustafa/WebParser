package application;

public class Article {

	String body;
	String header;
	String imgURL;
	

	
	public Article(String body, String header, String imgURL) {
		this.body = body;
		this.header = header;
		this.imgURL = imgURL;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	
	
}

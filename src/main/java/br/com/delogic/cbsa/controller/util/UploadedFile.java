package br.com.delogic.cbsa.controller.util;

public class UploadedFile {

	private String name;
	private Long size;
	private String url;
	private String thumbnailUrl;
	private String deleteUrl;
	private String deleteType;

	public UploadedFile() {
		super();
	}

	public UploadedFile(String name, Long size, String url) {
		super();
		this.name = name;
		this.size = size;
		this.url = url;
	}

	public UploadedFile(String name, Long size, String url,
			String thumbnail_url, String delete_url, String delete_type) {
		super();
		this.name = name;
		this.size = size;
		this.url = url;
		this.thumbnailUrl = thumbnail_url;
		this.deleteUrl = delete_url;
		this.deleteType = delete_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getDeleteUrl() {
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}

	public String getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}

}
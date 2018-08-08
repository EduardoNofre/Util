package edu.coreUtil.ftp;

import java.io.File;
import java.io.InputStream;

public interface FTPConnection {

	boolean connect();

	boolean disconnect();

	boolean upload(String dir,File file);

	boolean upload(String dir,String name,File file);

	InputStream download(String filePath);

	public String getUser();

	public String getPassword();

	public Integer getPort();

	public String getHost();
}

package edu.coreUtil.sftp;

import com.jcraft.jsch.SftpProgressMonitor;

/**
 *
 * @author Eduardo Nofre
 * 
 * 
 */
public class SFTPMonitor implements SftpProgressMonitor {

	public void init(int i, String string, String string1, long l) {
		System.out.println("STARTING: " + i + " " + string + " -> " + string1 + " total: " + l);
	}

	public boolean count(long l) {
		// for(int x=0;x<l;x++) {
		System.out.println("MONITOR : COUNT #");
		// }
		return true;
	}

	public void end() {
		System.out.println("MONITOR :: END");
	}

}

package org.beardedgeeks.mergeproperties;

import java.io.File;

public class Merge {
	/**
	 * @parameter
	 * @required
	 */
	private File targetFile;

	/**
	 * @parameter
	 * @required
	 */
	private File[] files;

	public File getTargetFile() {
		return targetFile;
	}

	public File[] getFiles() {
		return files;
	}

	public String toString() {
		return "Merge [files=" + files + ", targetFile="
				+ targetFile + "]";
	}
}

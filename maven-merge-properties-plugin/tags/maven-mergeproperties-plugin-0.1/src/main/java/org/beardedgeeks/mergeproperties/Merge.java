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
	private File[] propertiesFiles;

	public File getTargetFile() {
		return targetFile;
	}

	public File[] getFiles() {
		return propertiesFiles;
	}

	public String toString() {
		return "Merge [files=" + propertiesFiles + ", targetFile="
				+ targetFile + "]";
	}
}

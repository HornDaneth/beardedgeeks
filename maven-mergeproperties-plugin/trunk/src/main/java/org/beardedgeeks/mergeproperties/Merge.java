package org.beardedgeeks.mergeproperties;

import java.io.File;
import java.util.Arrays;

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
		return propertiesFiles.clone();
	}

	@Override
	public String toString() {
		return "Merge [files=" + Arrays.asList(propertiesFiles)
				+ ", targetFile=" + targetFile + "]";
	}
}

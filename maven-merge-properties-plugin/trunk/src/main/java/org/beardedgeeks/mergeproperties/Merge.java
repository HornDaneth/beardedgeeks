package org.beardedgeeks.mergeproperties;

import java.io.File;
import java.util.Arrays;

/**
 * The POJO to hold the merging configuration.
 * 
 * @author hleinone
 */
public class Merge {
	/**
	 * The target file.
	 * 
	 * @parameter
	 * @required
	 */
	private File targetFile;

	/**
	 * The files to merge.
	 * 
	 * @parameter
	 * @required
	 */
	private File[] propertiesFiles;

	/**
	 * Returns the target file where the result of the merging should be saved.
	 * 
	 * @return The target file.
	 */
	public File getTargetFile() {
		return targetFile;
	}

	/**
	 * Returns the files that are to be merged.
	 * 
	 * @return The files to merge.
	 */
	public File[] getFiles() {
		return propertiesFiles.clone();
	}

	@Override
	public String toString() {
		return "Merge [files=" + Arrays.asList(propertiesFiles)
				+ ", targetFile=" + targetFile + "]";
	}
}

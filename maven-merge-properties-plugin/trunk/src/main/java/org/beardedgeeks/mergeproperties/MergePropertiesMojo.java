package org.beardedgeeks.mergeproperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Merges multiple properties files into one.
 * 
 * @author hleinone
 * @goal merge
 * @requiresProject
 */
public class MergePropertiesMojo extends AbstractMojo {
	/**
	 * The properties files to merge. <br>
	 * Usage:
	 * 
	 * <pre>
	 * &lt;merges&gt;
	 *    &lt;merge&gt;
	 *       &lt;targetFile&gt;${build.outputDirectory}/application.properties&lt;/targetFile&gt;
	 *       &lt;propertiesFiles&gt;
	 *          &lt;propertiesFile&gt;src/main/config/${user.name}/application.properties&lt;/propertiesFile&gt;
	 *          &lt;propertiesFile&gt;src/main/config/extended/application.properties&lt;/propertiesFile&gt;
	 *          &lt;propertiesFile&gt;src/main/config/default/application.properties&lt;/propertiesFile&gt;
	 *       &lt;/propertiesFiles&gt;
	 *    &lt;/merge&gt;
	 * &lt;/merges&gt;
	 * </pre>
	 * 
	 * @parameter
	 * @required
	 */
	private Merge[] merges;

	/**
	 * @see org.apache.maven.plugin.AbstractMojo#execute()
	 */
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		for (Merge merge : merges) {
			// merge properties files
			final List<File> propertiesFiles = Arrays.asList(merge.getFiles());
			// iterate backwards to get propertiesFiles priorities right
			Collections.reverse(propertiesFiles);
			final Properties mergedProperties = new Properties();
			for (File propertiesFile : propertiesFiles) {
				InputStream input = null;
				try {
					if (propertiesFile.isDirectory())
						throw new MojoExecutionException("File "
								+ propertiesFile.getAbsolutePath()
								+ " is directory!");
					if (!propertiesFile.exists())
						throw new MojoExecutionException("File "
								+ propertiesFile.getAbsolutePath()
								+ " does not exist!");
					input = new FileInputStream(propertiesFile);
					mergedProperties.load(input);
				} catch (FileNotFoundException e) {
					throw new MojoExecutionException("Could not find file: "
							+ propertiesFile.getAbsolutePath(), e);
				} catch (IOException e) {
					throw new MojoExecutionException(
							"Could not read from file: "
									+ propertiesFile.getAbsolutePath(), e);
				} finally {
					if (input != null) {
						try {
							input.close();
						} catch (IOException e) {
							// no can do
						}
					}
				}
			}

			// save to target file
			final File targetPropertiesFile = merge.getTargetFile();
			OutputStream output = null;
			if (targetPropertiesFile.isDirectory())
				throw new MojoExecutionException("File "
						+ targetPropertiesFile.getAbsolutePath()
						+ " is directory!");
			try {
				if (targetPropertiesFile.exists()
						&& !targetPropertiesFile.delete())
					throw new MojoExecutionException("Could not remove file: "
							+ targetPropertiesFile.getAbsolutePath());

				final File targetDirectory = targetPropertiesFile
						.getParentFile();

				if (!targetDirectory.exists() && !targetDirectory.mkdirs())
					throw new MojoExecutionException(
							"Could not create directory: "
									+ targetDirectory.getAbsolutePath());
				if (!targetDirectory.isDirectory())
					throw new MojoExecutionException("Not a directory: "
							+ targetDirectory.getAbsolutePath());
				if (!targetPropertiesFile.createNewFile())
					throw new MojoExecutionException("Could not create file: "
							+ targetPropertiesFile.getAbsolutePath());

				output = new FileOutputStream(targetPropertiesFile);
				mergedProperties.store(output, targetPropertiesFile.getName());
			} catch (FileNotFoundException e) {
				throw new MojoExecutionException("Could not find file: "
						+ targetPropertiesFile.getAbsolutePath(), e);
			} catch (IOException e) {
				throw new MojoExecutionException("Could not write to file: "
						+ targetPropertiesFile.getAbsolutePath(), e);
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
						// no can do
					}
				}
			}
		}
	}
}

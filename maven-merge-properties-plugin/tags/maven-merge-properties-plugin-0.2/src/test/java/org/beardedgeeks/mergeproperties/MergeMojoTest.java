package org.beardedgeeks.mergeproperties;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.powermock.api.easymock.PowerMock.expectLastCall;
import static org.powermock.api.easymock.PowerMock.expectNew;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Test class for {@link MergeMojo}.
 * 
 * @author hleinone
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(MergeMojo.class)
public class MergeMojoTest {
	@Test(expected = MojoExecutionException.class)
	public void testExecute_propertiesFileIsDirectory() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File fileMock = createMock(File.class);
		final File[] files = new File[] { fileMock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		expect(mergeMock.getFiles()).andReturn(files);
		expect(fileMock.isDirectory()).andReturn(true);
		expect(fileMock.getAbsolutePath()).andReturn("/test.properties");
		replay(mergeMock, fileMock);
		mojo.execute();
		verify(mergeMock, fileMock);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_propertiesFileNotExists() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File fileMock = createMock(File.class);
		final File[] files = new File[] { fileMock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		expect(mergeMock.getFiles()).andReturn(files);
		expect(fileMock.isDirectory()).andReturn(false);
		expect(fileMock.exists()).andReturn(false);
		expect(fileMock.getAbsolutePath()).andReturn("/test.properties");
		replay(mergeMock, fileMock);
		mojo.execute();
		verify(mergeMock, fileMock);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_propertiesFileNotFound() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File fileMock = createMock(File.class);
		final File[] files = new File[] { fileMock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		expect(mergeMock.getFiles()).andReturn(files);
		expect(fileMock.isDirectory()).andReturn(false);
		expect(fileMock.exists()).andReturn(true);
		expectNew(FileInputStream.class, fileMock).andThrow(
				new FileNotFoundException());
		expect(fileMock.getAbsolutePath()).andReturn("/test.properties");
		replay(mergeMock, fileMock, FileInputStream.class);
		mojo.execute();
		verify(mergeMock, fileMock, FileInputStream.class);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_propertiesFileCanNotRead() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File fileMock = createMock(File.class);
		final File[] files = new File[] { fileMock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		expect(mergeMock.getFiles()).andReturn(files);
		expect(fileMock.isDirectory()).andReturn(false);
		expect(fileMock.exists()).andReturn(true);
		expectNew(FileInputStream.class, fileMock).andThrow(new IOException());
		expect(fileMock.getAbsolutePath()).andReturn("/test.properties");
		replay(mergeMock, fileMock, FileInputStream.class);
		mojo.execute();
		verify(mergeMock, fileMock, FileInputStream.class);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_propertiesFileCanNotReadOnLoad() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File fileMock = createMock(File.class);
		final File[] files = new File[] { fileMock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		final FileInputStream fileInputStreamMock = createMock(FileInputStream.class);

		final Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(files);
		expect(fileMock.isDirectory()).andReturn(false);
		expect(fileMock.exists()).andReturn(true);
		expectNew(FileInputStream.class, fileMock).andReturn(
				fileInputStreamMock);
		propertiesMock.load(fileInputStreamMock);
		expectLastCall().andThrow(new IOException());
		expect(fileMock.getAbsolutePath()).andReturn("/test.properties");
		fileInputStreamMock.close();

		replay(mergeMock, fileMock, fileInputStreamMock, propertiesMock,
				FileInputStream.class, Properties.class);
		mojo.execute();
		verify(mergeMock, fileMock, fileInputStreamMock, propertiesMock,
				FileInputStream.class, Properties.class);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_targetFileIsDirectory() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File targetFileMock = createMock(File.class);

		final File file1Mock = createMock(File.class);
		final File file2Mock = createMock(File.class);
		final File[] files = new File[] { file1Mock, file2Mock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		final FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		final FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		final Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(files);
		expect(file2Mock.isDirectory()).andReturn(false);
		expect(file2Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file2Mock).andReturn(
				file2InputStreamMock);
		propertiesMock.load(file2InputStreamMock);
		file2InputStreamMock.close();

		expect(file1Mock.isDirectory()).andReturn(false);
		expect(file1Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file1Mock).andReturn(
				file1InputStreamMock);
		propertiesMock.load(file1InputStreamMock);
		file1InputStreamMock.close();
		expect(mergeMock.getTargetFile()).andReturn(targetFileMock);
		expect(targetFileMock.isDirectory()).andReturn(true);
		expect(targetFileMock.getAbsolutePath()).andReturn("/test.properties");

		replay(mergeMock, file2Mock, file2InputStreamMock, file1Mock,
				file1InputStreamMock, propertiesMock, targetFileMock,
				FileInputStream.class, Properties.class);
		mojo.execute();
		verify(mergeMock, file2Mock, file1Mock, file1InputStreamMock,
				file2InputStreamMock, propertiesMock, targetFileMock,
				FileInputStream.class, Properties.class);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_targetFileDeletionFails() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File targetFileMock = createMock(File.class);

		final File file1Mock = createMock(File.class);
		final File file2Mock = createMock(File.class);
		final File[] files = new File[] { file1Mock, file2Mock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		final FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		final FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		final Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(files);
		expect(file2Mock.isDirectory()).andReturn(false);
		expect(file2Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file2Mock).andReturn(
				file2InputStreamMock);
		propertiesMock.load(file2InputStreamMock);
		file2InputStreamMock.close();

		expect(file1Mock.isDirectory()).andReturn(false);
		expect(file1Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file1Mock).andReturn(
				file1InputStreamMock);
		propertiesMock.load(file1InputStreamMock);
		file1InputStreamMock.close();
		expect(mergeMock.getTargetFile()).andReturn(targetFileMock);
		expect(targetFileMock.isDirectory()).andReturn(false);
		expect(targetFileMock.exists()).andReturn(true);
		expect(targetFileMock.delete()).andReturn(false);
		expect(targetFileMock.getAbsolutePath()).andReturn("/test.properties");

		replay(mergeMock, file2Mock, file2InputStreamMock, file1Mock,
				file1InputStreamMock, propertiesMock, targetFileMock,
				FileInputStream.class, Properties.class);
		mojo.execute();
		verify(mergeMock, file2Mock, file1Mock, file1InputStreamMock,
				file2InputStreamMock, propertiesMock, targetFileMock,
				FileInputStream.class, Properties.class);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_targetDirectoriesCanNotCreate() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File targetFileParentFileMock = createMock(File.class);

		final File targetFileMock = createMock(File.class);

		final File file1Mock = createMock(File.class);
		final File file2Mock = createMock(File.class);
		final File[] files = new File[] { file1Mock, file2Mock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		final FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		final FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		final Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(files);
		expect(file2Mock.isDirectory()).andReturn(false);
		expect(file2Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file2Mock).andReturn(
				file2InputStreamMock);
		propertiesMock.load(file2InputStreamMock);
		file2InputStreamMock.close();

		expect(file1Mock.isDirectory()).andReturn(false);
		expect(file1Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file1Mock).andReturn(
				file1InputStreamMock);
		propertiesMock.load(file1InputStreamMock);
		file1InputStreamMock.close();
		expect(mergeMock.getTargetFile()).andReturn(targetFileMock);
		expect(targetFileMock.isDirectory()).andReturn(false);
		expect(targetFileMock.exists()).andReturn(true);
		expect(targetFileMock.delete()).andReturn(true);
		expect(targetFileMock.getParentFile()).andReturn(
				targetFileParentFileMock);
		expect(targetFileParentFileMock.exists()).andReturn(false);
		expect(targetFileParentFileMock.mkdirs()).andReturn(false);
		expect(targetFileParentFileMock.getAbsolutePath()).andReturn(
				"/test.properties");

		replay(mergeMock, file2Mock, file2InputStreamMock, file1Mock,
				file1InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, FileInputStream.class,
				Properties.class);
		mojo.execute();
		verify(mergeMock, file2Mock, file1Mock, file1InputStreamMock,
				file2InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, FileInputStream.class,
				Properties.class);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_targetDirectoryIsNotADirectory() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File targetFileParentFileMock = createMock(File.class);

		final File targetFileMock = createMock(File.class);

		final File file1Mock = createMock(File.class);
		final File file2Mock = createMock(File.class);
		final File[] files = new File[] { file1Mock, file2Mock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		final FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		final FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		final Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(files);
		expect(file2Mock.isDirectory()).andReturn(false);
		expect(file2Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file2Mock).andReturn(
				file2InputStreamMock);
		propertiesMock.load(file2InputStreamMock);
		file2InputStreamMock.close();

		expect(file1Mock.isDirectory()).andReturn(false);
		expect(file1Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file1Mock).andReturn(
				file1InputStreamMock);
		propertiesMock.load(file1InputStreamMock);
		file1InputStreamMock.close();
		expect(mergeMock.getTargetFile()).andReturn(targetFileMock);
		expect(targetFileMock.isDirectory()).andReturn(false);
		expect(targetFileMock.exists()).andReturn(false);
		expect(targetFileMock.getParentFile()).andReturn(
				targetFileParentFileMock);
		expect(targetFileParentFileMock.exists()).andReturn(false);
		expect(targetFileParentFileMock.mkdirs()).andReturn(true);
		expect(targetFileParentFileMock.isDirectory()).andReturn(false);
		expect(targetFileParentFileMock.getAbsolutePath()).andReturn(
				"/test.properties");

		replay(mergeMock, file2Mock, file2InputStreamMock, file1Mock,
				file1InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, FileInputStream.class,
				Properties.class);
		mojo.execute();
		verify(mergeMock, file2Mock, file1Mock, file1InputStreamMock,
				file2InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, FileInputStream.class,
				Properties.class);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_targetFileCanNotCreate() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File targetFileParentFileMock = createMock(File.class);

		final File targetFileMock = createMock(File.class);

		final File file1Mock = createMock(File.class);
		final File file2Mock = createMock(File.class);
		final File[] files = new File[] { file1Mock, file2Mock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		final FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		final FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		final Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(files);
		expect(file2Mock.isDirectory()).andReturn(false);
		expect(file2Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file2Mock).andReturn(
				file2InputStreamMock);
		propertiesMock.load(file2InputStreamMock);
		file2InputStreamMock.close();

		expect(file1Mock.isDirectory()).andReturn(false);
		expect(file1Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file1Mock).andReturn(
				file1InputStreamMock);
		propertiesMock.load(file1InputStreamMock);
		file1InputStreamMock.close();
		expect(mergeMock.getTargetFile()).andReturn(targetFileMock);
		expect(targetFileMock.isDirectory()).andReturn(false);
		expect(targetFileMock.exists()).andReturn(false);
		expect(targetFileMock.getParentFile()).andReturn(
				targetFileParentFileMock);
		expect(targetFileParentFileMock.exists()).andReturn(true);
		expect(targetFileParentFileMock.isDirectory()).andReturn(true);
		expect(targetFileMock.createNewFile()).andReturn(false);
		expect(targetFileMock.getAbsolutePath()).andReturn("/test.properties");

		replay(mergeMock, file2Mock, file2InputStreamMock, file1Mock,
				file1InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, FileInputStream.class,
				Properties.class);
		mojo.execute();
		verify(mergeMock, file2Mock, file1Mock, file1InputStreamMock,
				file2InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, FileInputStream.class,
				Properties.class);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_targetFileCreateCanNotWrite() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File targetFileParentFileMock = createMock(File.class);

		final File targetFileMock = createMock(File.class);

		final File file1Mock = createMock(File.class);
		final File file2Mock = createMock(File.class);
		final File[] files = new File[] { file1Mock, file2Mock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		final FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		final FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		final Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(files);
		expect(file2Mock.isDirectory()).andReturn(false);
		expect(file2Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file2Mock).andReturn(
				file2InputStreamMock);
		propertiesMock.load(file2InputStreamMock);
		file2InputStreamMock.close();

		expect(file1Mock.isDirectory()).andReturn(false);
		expect(file1Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file1Mock).andReturn(
				file1InputStreamMock);
		propertiesMock.load(file1InputStreamMock);
		file1InputStreamMock.close();
		expect(mergeMock.getTargetFile()).andReturn(targetFileMock);
		expect(targetFileMock.isDirectory()).andReturn(false);
		expect(targetFileMock.exists()).andReturn(false);
		expect(targetFileMock.getParentFile()).andReturn(
				targetFileParentFileMock);
		expect(targetFileParentFileMock.exists()).andReturn(true);
		expect(targetFileParentFileMock.isDirectory()).andReturn(true);
		expect(targetFileMock.createNewFile()).andThrow(new IOException());
		expect(targetFileMock.getAbsolutePath()).andReturn("/test.properties");

		replay(mergeMock, file2Mock, file2InputStreamMock, file1Mock,
				file1InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, FileInputStream.class,
				Properties.class);
		mojo.execute();
		verify(mergeMock, file2Mock, file1Mock, file1InputStreamMock,
				file2InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, FileInputStream.class,
				Properties.class);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_targetFileNotFound() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File targetFileParentFileMock = createMock(File.class);

		final File targetFileMock = createMock(File.class);

		final File file1Mock = createMock(File.class);
		final File file2Mock = createMock(File.class);
		final File[] files = new File[] { file1Mock, file2Mock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		final FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		final FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		final Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(files);
		expect(file2Mock.isDirectory()).andReturn(false);
		expect(file2Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file2Mock).andReturn(
				file2InputStreamMock);
		propertiesMock.load(file2InputStreamMock);
		file2InputStreamMock.close();

		expect(file1Mock.isDirectory()).andReturn(false);
		expect(file1Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file1Mock).andReturn(
				file1InputStreamMock);
		propertiesMock.load(file1InputStreamMock);
		file1InputStreamMock.close();
		expect(mergeMock.getTargetFile()).andReturn(targetFileMock);
		expect(targetFileMock.isDirectory()).andReturn(false);
		expect(targetFileMock.exists()).andReturn(false);
		expect(targetFileMock.getParentFile()).andReturn(
				targetFileParentFileMock);
		expect(targetFileParentFileMock.exists()).andReturn(true);
		expect(targetFileParentFileMock.isDirectory()).andReturn(true);
		expect(targetFileMock.createNewFile()).andReturn(true);
		expectNew(FileOutputStream.class, targetFileMock).andThrow(
				new FileNotFoundException());
		expect(targetFileMock.getAbsolutePath()).andReturn("/test.properties");

		replay(mergeMock, file2Mock, file2InputStreamMock, file1Mock,
				file1InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, FileInputStream.class,
				Properties.class, FileOutputStream.class);
		mojo.execute();
		verify(mergeMock, file2Mock, file1Mock, file1InputStreamMock,
				file2InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, FileInputStream.class,
				Properties.class, FileOutputStream.class);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_targetFileCanNotWrite() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File targetFileParentFileMock = createMock(File.class);

		final File targetFileMock = createMock(File.class);

		final File file1Mock = createMock(File.class);
		final File file2Mock = createMock(File.class);
		final File[] files = new File[] { file1Mock, file2Mock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		final FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		final FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		final Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(files);
		expect(file2Mock.isDirectory()).andReturn(false);
		expect(file2Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file2Mock).andReturn(
				file2InputStreamMock);
		propertiesMock.load(file2InputStreamMock);
		file2InputStreamMock.close();

		expect(file1Mock.isDirectory()).andReturn(false);
		expect(file1Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file1Mock).andReturn(
				file1InputStreamMock);
		propertiesMock.load(file1InputStreamMock);
		file1InputStreamMock.close();
		expect(mergeMock.getTargetFile()).andReturn(targetFileMock);
		expect(targetFileMock.isDirectory()).andReturn(false);
		expect(targetFileMock.exists()).andReturn(false);
		expect(targetFileMock.getParentFile()).andReturn(
				targetFileParentFileMock);
		expect(targetFileParentFileMock.exists()).andReturn(true);
		expect(targetFileParentFileMock.isDirectory()).andReturn(true);
		expect(targetFileMock.createNewFile()).andReturn(true);
		expectNew(FileOutputStream.class, targetFileMock).andThrow(
				new IOException());
		expect(targetFileMock.getAbsolutePath()).andReturn("/test.properties");

		replay(mergeMock, file2Mock, file2InputStreamMock, file1Mock,
				file1InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, FileInputStream.class,
				Properties.class, FileOutputStream.class);
		mojo.execute();
		verify(mergeMock, file2Mock, file1Mock, file1InputStreamMock,
				file2InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, FileInputStream.class,
				Properties.class, FileOutputStream.class);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_targetFileCanNotWriteOnStore() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File targetFileParentFileMock = createMock(File.class);

		final File targetFileMock = createMock(File.class);

		final File file1Mock = createMock(File.class);
		final File file2Mock = createMock(File.class);
		final File[] files = new File[] { file1Mock, file2Mock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		final FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		final FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		final Properties propertiesMock = createMock(Properties.class);

		final FileOutputStream targetFileOutputStreamMock = createMock(FileOutputStream.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(files);
		expect(file2Mock.isDirectory()).andReturn(false);
		expect(file2Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file2Mock).andReturn(
				file2InputStreamMock);
		propertiesMock.load(file2InputStreamMock);
		file2InputStreamMock.close();

		expect(file1Mock.isDirectory()).andReturn(false);
		expect(file1Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file1Mock).andReturn(
				file1InputStreamMock);
		propertiesMock.load(file1InputStreamMock);
		file1InputStreamMock.close();
		expect(mergeMock.getTargetFile()).andReturn(targetFileMock);
		expect(targetFileMock.isDirectory()).andReturn(false);
		expect(targetFileMock.exists()).andReturn(false);
		expect(targetFileMock.getParentFile()).andReturn(
				targetFileParentFileMock);
		expect(targetFileParentFileMock.exists()).andReturn(true);
		expect(targetFileParentFileMock.isDirectory()).andReturn(true);
		expect(targetFileMock.createNewFile()).andReturn(true);
		expectNew(FileOutputStream.class, targetFileMock).andReturn(
				targetFileOutputStreamMock);
		expect(targetFileMock.getName()).andReturn("test.properties");
		propertiesMock.store(targetFileOutputStreamMock, "test.properties");
		expectLastCall().andThrow(new IOException());
		expect(targetFileMock.getAbsolutePath()).andReturn("/test.properties");
		targetFileOutputStreamMock.close();

		replay(mergeMock, file2Mock, file2InputStreamMock, file1Mock,
				file1InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, targetFileOutputStreamMock,
				FileInputStream.class, Properties.class, FileOutputStream.class);
		mojo.execute();
		verify(mergeMock, file2Mock, file1Mock, file1InputStreamMock,
				file2InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, targetFileOutputStreamMock,
				FileInputStream.class, Properties.class, FileOutputStream.class);
	}

	@Test
	public void testExecute() throws Exception {
		final MergeMojo mojo = new MergeMojo();

		final Merge mergeMock = createMock(Merge.class);
		final Merge[] merges = new Merge[] { mergeMock };

		final File targetFileParentFileMock = createMock(File.class);

		final File targetFileMock = createMock(File.class);

		final File file1Mock = createMock(File.class);
		final File file2Mock = createMock(File.class);
		final File[] files = new File[] { file1Mock, file2Mock };

		final Field mergesField = MergeMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, merges);

		final FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		final FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		final Properties propertiesMock = createMock(Properties.class);

		final FileOutputStream targetFileOutputStreamMock = createMock(FileOutputStream.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(files);
		expect(file2Mock.isDirectory()).andReturn(false);
		expect(file2Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file2Mock).andReturn(
				file2InputStreamMock);
		propertiesMock.load(file2InputStreamMock);
		file2InputStreamMock.close();

		expect(file1Mock.isDirectory()).andReturn(false);
		expect(file1Mock.exists()).andReturn(true);
		expectNew(FileInputStream.class, file1Mock).andReturn(
				file1InputStreamMock);
		propertiesMock.load(file1InputStreamMock);
		file1InputStreamMock.close();
		expect(mergeMock.getTargetFile()).andReturn(targetFileMock);
		expect(targetFileMock.isDirectory()).andReturn(false);
		expect(targetFileMock.exists()).andReturn(false);
		expect(targetFileMock.getParentFile()).andReturn(
				targetFileParentFileMock);
		expect(targetFileParentFileMock.exists()).andReturn(true);
		expect(targetFileParentFileMock.isDirectory()).andReturn(true);
		expect(targetFileMock.createNewFile()).andReturn(true);
		expectNew(FileOutputStream.class, targetFileMock).andReturn(
				targetFileOutputStreamMock);
		expect(targetFileMock.getName()).andReturn("test.properties");
		propertiesMock.store(targetFileOutputStreamMock, "test.properties");
		targetFileOutputStreamMock.close();

		replay(mergeMock, file2Mock, file2InputStreamMock, file1Mock,
				file1InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, targetFileOutputStreamMock,
				FileInputStream.class, Properties.class, FileOutputStream.class);
		mojo.execute();
		verify(mergeMock, file2Mock, file1Mock, file1InputStreamMock,
				file2InputStreamMock, propertiesMock, targetFileMock,
				targetFileParentFileMock, targetFileOutputStreamMock,
				FileInputStream.class, Properties.class, FileOutputStream.class);
	}
}
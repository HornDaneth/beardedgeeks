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
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MergePropertiesMojo.class)
public class MergePropertiesMojoTest {
	@Test(expected = MojoExecutionException.class)
	public void testExecute_propertiesFileIsDirectory()
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException,
			MojoExecutionException, MojoFailureException {
		MergePropertiesMojo mojo = new MergePropertiesMojo();

		Merge mergeMock = createMock(Merge.class);
		Merge[] mergesMock = new Merge[] { mergeMock };

		File fileMock = createMock(File.class);
		File[] filesMock = new File[] { fileMock };

		Field mergesField = MergePropertiesMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, mergesMock);

		expect(mergeMock.getFiles()).andReturn(filesMock);
		expect(fileMock.isDirectory()).andReturn(true);
		expect(fileMock.getAbsolutePath()).andReturn("/test.properties");
		replay(mergeMock, fileMock);
		mojo.execute();
		verify(mergeMock, fileMock);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_propertiesFileNotExists() throws SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException, MojoExecutionException,
			MojoFailureException {
		MergePropertiesMojo mojo = new MergePropertiesMojo();

		Merge mergeMock = createMock(Merge.class);
		Merge[] mergesMock = new Merge[] { mergeMock };

		File fileMock = createMock(File.class);
		File[] filesMock = new File[] { fileMock };

		Field mergesField = MergePropertiesMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, mergesMock);

		expect(mergeMock.getFiles()).andReturn(filesMock);
		expect(fileMock.isDirectory()).andReturn(false);
		expect(fileMock.exists()).andReturn(false);
		expect(fileMock.getAbsolutePath()).andReturn("/test.properties");
		replay(mergeMock, fileMock);
		mojo.execute();
		verify(mergeMock, fileMock);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_propertiesFileNotFound() throws Exception {
		MergePropertiesMojo mojo = new MergePropertiesMojo();

		Merge mergeMock = createMock(Merge.class);
		Merge[] mergesMock = new Merge[] { mergeMock };

		File fileMock = createMock(File.class);
		File[] filesMock = new File[] { fileMock };

		Field mergesField = MergePropertiesMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, mergesMock);

		expect(mergeMock.getFiles()).andReturn(filesMock);
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
		MergePropertiesMojo mojo = new MergePropertiesMojo();

		Merge mergeMock = createMock(Merge.class);
		Merge[] mergesMock = new Merge[] { mergeMock };

		File fileMock = createMock(File.class);
		File[] filesMock = new File[] { fileMock };

		Field mergesField = MergePropertiesMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, mergesMock);

		expect(mergeMock.getFiles()).andReturn(filesMock);
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
		MergePropertiesMojo mojo = new MergePropertiesMojo();

		Merge mergeMock = createMock(Merge.class);
		Merge[] mergesMock = new Merge[] { mergeMock };

		File fileMock = createMock(File.class);
		File[] filesMock = new File[] { fileMock };

		Field mergesField = MergePropertiesMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, mergesMock);

		FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(filesMock);
		expect(fileMock.isDirectory()).andReturn(false);
		expect(fileMock.exists()).andReturn(true);
		expectNew(FileInputStream.class, fileMock).andReturn(
				file2InputStreamMock);
		propertiesMock.load(file2InputStreamMock);
		expectLastCall().andThrow(new IOException());
		expect(fileMock.getAbsolutePath()).andReturn("/test.properties");
		file2InputStreamMock.close();

		replay(mergeMock, fileMock, file2InputStreamMock, propertiesMock,
				FileInputStream.class, Properties.class);
		mojo.execute();
		verify(mergeMock, fileMock, file2InputStreamMock, propertiesMock,
				FileInputStream.class, Properties.class);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_targetFileIsDirectory() throws Exception {
		MergePropertiesMojo mojo = new MergePropertiesMojo();

		Merge mergeMock = createMock(Merge.class);
		Merge[] mergesMock = new Merge[] { mergeMock };

		File targetFileMock = createMock(File.class);

		File file1Mock = createMock(File.class);
		File file2Mock = createMock(File.class);
		File[] filesMock = new File[] { file1Mock, file2Mock };

		Field mergesField = MergePropertiesMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, mergesMock);

		FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(filesMock);
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
	public void testExecute_targetDirectoriesCanNotCreate() throws Exception {
		MergePropertiesMojo mojo = new MergePropertiesMojo();

		Merge mergeMock = createMock(Merge.class);
		Merge[] mergesMock = new Merge[] { mergeMock };

		File targetFileParentFileMock = createMock(File.class);

		File targetFileMock = createMock(File.class);

		File file1Mock = createMock(File.class);
		File file2Mock = createMock(File.class);
		File[] filesMock = new File[] { file1Mock, file2Mock };

		Field mergesField = MergePropertiesMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, mergesMock);

		FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(filesMock);
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
		expect(targetFileParentFileMock.mkdirs()).andReturn(false);
		expect(targetFileMock.getParentFile()).andReturn(
				targetFileParentFileMock);
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
		MergePropertiesMojo mojo = new MergePropertiesMojo();

		Merge mergeMock = createMock(Merge.class);
		Merge[] mergesMock = new Merge[] { mergeMock };

		File targetFileParentFileMock = createMock(File.class);

		File targetFileMock = createMock(File.class);

		File file1Mock = createMock(File.class);
		File file2Mock = createMock(File.class);
		File[] filesMock = new File[] { file1Mock, file2Mock };

		Field mergesField = MergePropertiesMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, mergesMock);

		FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(filesMock);
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
		expect(targetFileParentFileMock.mkdirs()).andReturn(true);
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
		MergePropertiesMojo mojo = new MergePropertiesMojo();

		Merge mergeMock = createMock(Merge.class);
		Merge[] mergesMock = new Merge[] { mergeMock };

		File targetFileParentFileMock = createMock(File.class);

		File targetFileMock = createMock(File.class);

		File file1Mock = createMock(File.class);
		File file2Mock = createMock(File.class);
		File[] filesMock = new File[] { file1Mock, file2Mock };

		Field mergesField = MergePropertiesMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, mergesMock);

		FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(filesMock);
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
		expect(targetFileParentFileMock.mkdirs()).andReturn(true);
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
		MergePropertiesMojo mojo = new MergePropertiesMojo();

		Merge mergeMock = createMock(Merge.class);
		Merge[] mergesMock = new Merge[] { mergeMock };

		File targetFileMock = createMock(File.class);

		File file1Mock = createMock(File.class);
		File file2Mock = createMock(File.class);
		File[] filesMock = new File[] { file1Mock, file2Mock };

		Field mergesField = MergePropertiesMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, mergesMock);

		FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(filesMock);
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
		expectNew(FileOutputStream.class, targetFileMock).andThrow(
				new FileNotFoundException());
		expect(targetFileMock.getAbsolutePath()).andReturn("/test.properties");

		replay(mergeMock, file2Mock, file2InputStreamMock, file1Mock,
				file1InputStreamMock, propertiesMock, targetFileMock,
				FileInputStream.class, Properties.class, FileOutputStream.class);
		mojo.execute();
		verify(mergeMock, file2Mock, file1Mock, file1InputStreamMock,
				file2InputStreamMock, propertiesMock, targetFileMock,
				FileInputStream.class, Properties.class, FileOutputStream.class);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_targetFileCanNotWrite() throws Exception {
		MergePropertiesMojo mojo = new MergePropertiesMojo();

		Merge mergeMock = createMock(Merge.class);
		Merge[] mergesMock = new Merge[] { mergeMock };

		File targetFileMock = createMock(File.class);

		File file1Mock = createMock(File.class);
		File file2Mock = createMock(File.class);
		File[] filesMock = new File[] { file1Mock, file2Mock };

		Field mergesField = MergePropertiesMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, mergesMock);

		FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		Properties propertiesMock = createMock(Properties.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(filesMock);
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
		expectNew(FileOutputStream.class, targetFileMock).andThrow(
				new IOException());
		expect(targetFileMock.getAbsolutePath()).andReturn("/test.properties");

		replay(mergeMock, file2Mock, file2InputStreamMock, file1Mock,
				file1InputStreamMock, propertiesMock, targetFileMock,
				FileInputStream.class, Properties.class, FileOutputStream.class);
		mojo.execute();
		verify(mergeMock, file2Mock, file1Mock, file1InputStreamMock,
				file2InputStreamMock, propertiesMock, targetFileMock,
				FileInputStream.class, Properties.class, FileOutputStream.class);
	}

	@Test(expected = MojoExecutionException.class)
	public void testExecute_targetFileCanNotWriteOnStore() throws Exception {
		MergePropertiesMojo mojo = new MergePropertiesMojo();

		Merge mergeMock = createMock(Merge.class);
		Merge[] mergesMock = new Merge[] { mergeMock };

		File targetFileMock = createMock(File.class);

		File file1Mock = createMock(File.class);
		File file2Mock = createMock(File.class);
		File[] filesMock = new File[] { file1Mock, file2Mock };

		Field mergesField = MergePropertiesMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, mergesMock);

		FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		Properties propertiesMock = createMock(Properties.class);

		FileOutputStream targetFileOutputStreamMock = createMock(FileOutputStream.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(filesMock);
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
		expectNew(FileOutputStream.class, targetFileMock).andReturn(
				targetFileOutputStreamMock);
		expect(targetFileMock.getName()).andReturn("test.properties");
		propertiesMock.store(targetFileOutputStreamMock, "test.properties");
		expectLastCall().andThrow(new IOException());
		expect(targetFileMock.getAbsolutePath()).andReturn("/test.properties");
		targetFileOutputStreamMock.close();

		replay(mergeMock, file2Mock, file2InputStreamMock, file1Mock,
				file1InputStreamMock, propertiesMock, targetFileMock,
				targetFileOutputStreamMock, FileInputStream.class,
				Properties.class, FileOutputStream.class);
		mojo.execute();
		verify(mergeMock, file2Mock, file1Mock, file1InputStreamMock,
				file2InputStreamMock, propertiesMock, targetFileMock,
				targetFileOutputStreamMock, FileInputStream.class,
				Properties.class, FileOutputStream.class);
	}

	@Test
	public void testExecute() throws Exception {
		MergePropertiesMojo mojo = new MergePropertiesMojo();

		Merge mergeMock = createMock(Merge.class);
		Merge[] mergesMock = new Merge[] { mergeMock };

		File targetFileMock = createMock(File.class);

		File file1Mock = createMock(File.class);
		File file2Mock = createMock(File.class);
		File[] filesMock = new File[] { file1Mock, file2Mock };

		Field mergesField = MergePropertiesMojo.class
				.getDeclaredField("merges");
		mergesField.setAccessible(true);
		mergesField.set(mojo, mergesMock);

		FileInputStream file1InputStreamMock = createMock(FileInputStream.class);
		FileInputStream file2InputStreamMock = createMock(FileInputStream.class);

		Properties propertiesMock = createMock(Properties.class);

		FileOutputStream targetFileOutputStreamMock = createMock(FileOutputStream.class);

		expectNew(Properties.class).andReturn(propertiesMock);
		expect(mergeMock.getFiles()).andReturn(filesMock);
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
		expectNew(FileOutputStream.class, targetFileMock).andReturn(
				targetFileOutputStreamMock);
		expect(targetFileMock.getName()).andReturn("test.properties");
		propertiesMock.store(targetFileOutputStreamMock, "test.properties");
		targetFileOutputStreamMock.close();

		replay(mergeMock, file2Mock, file2InputStreamMock, file1Mock,
				file1InputStreamMock, propertiesMock, targetFileMock,
				targetFileOutputStreamMock, FileInputStream.class,
				Properties.class, FileOutputStream.class);
		mojo.execute();
		verify(mergeMock, file2Mock, file1Mock, file1InputStreamMock,
				file2InputStreamMock, propertiesMock, targetFileMock,
				targetFileOutputStreamMock, FileInputStream.class,
				Properties.class, FileOutputStream.class);
	}
}

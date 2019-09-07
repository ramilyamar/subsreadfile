package com.github.ramilyamar.subsreadfile.file;

import io.vavr.control.Option;

public interface FileDao {
    /**
     * Creates and saves file to storage
     *
     * @param fileInfo file information
     * @return id of created file
     */
    long createFile(FileInfo fileInfo);

    /**
     *  Returns file information by id
     * @param id id of file
     * @return file information by id or {@link Option#none} if file with this id is not found
     */
    Option<FileInfo> getFileInfoById(long id);
}

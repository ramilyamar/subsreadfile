package com.github.ramilyamar.subsreadfile.file;

import io.vavr.control.Option;

import java.util.List;

public interface FileDao {
    /**
     * Creates and saves file to storage.
     *
     * @param fileInfo file information
     * @return id of created file
     */
    long createFile(FileInfo fileInfo);

    /**
     *  Returns file information by id.
     * @param id id of file
     * @return file information by id or {@link Option#none} if file with this id is not found
     */
    Option<FileInfo> getFileInfoById(long id);

    /**
     * Returns movies by user id.
     * @param userId id of user
     * @return movies of user or empty list if user is not found
     */
    List<MovieInfo> getMoviesByUserId(long userId);
}

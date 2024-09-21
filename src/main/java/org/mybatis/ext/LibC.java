package org.mybatis.ext;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Platform;

public interface LibC {

    int chdir(CharSequence path);

    long getcwd(byte[] cwd, int len);

    default String getcwd() {
        byte[] cwd = new byte[1024];
        long result = getcwd(cwd, 1024);
        if (result == -1) return null;
        int len = 0;
        for (; len < 1024; len++) if (cwd[len] == 0) break;
        return new String(cwd, 0, len);
    }

    static LibC load() {
        Platform NATIVE_PLATFORM = Platform.getNativePlatform();
        String STANDARD_C_LIBRARY_NAME = NATIVE_PLATFORM.getStandardCLibraryName();
        return LibraryLoader.create(LibC.class).load(STANDARD_C_LIBRARY_NAME);
    }
}
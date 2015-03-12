package com.pandehoz.viajary;

import java.io.File;

abstract class AlbumStorageDirFactory {
    public abstract File getAlbumStorageDir(String albumName);
}
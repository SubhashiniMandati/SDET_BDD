package XrayIntegration;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FeatureUnzipper {

    public static void unzipFeatures() throws IOException {

        File zipFile = new File("target/xray-features.zip");
        File destDir = new File("src/test/resources/features");

        try (ZipInputStream zis =
                     new ZipInputStream(new FileInputStream(zipFile))) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {

                File newFile = new File(destDir, entry.getName());
                new File(newFile.getParent()).mkdirs();

                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }
            }
        }
        System.out.println("Feature files extracted");
    }
}


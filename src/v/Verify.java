/**
 * This file is licensed to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 **/
package v;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.CodeSigner;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Verify {

  public static void verify(final String apkPath) throws Exception {
    final JarFile apk = new JarFile(apkPath, true);
    final byte[] buffer = new byte[16384]; // 16k buffer
    InputStream input = null;
    boolean allEntriesUnsigned = true;
    for (Enumeration<JarEntry> entries = apk.entries(); entries
        .hasMoreElements();) {
      final JarEntry entry = entries.nextElement();
      try {
        input = apk.getInputStream(entry);

        // Must read to make the code signers available.
        while (input.read(buffer) != -1) {
        }
      } finally {
        if (input != null) {
          input.close();
        }
      }
      CodeSigner[] codeSigners = entry.getCodeSigners();
      if (codeSigners == null) { // no signer
        continue;
      }
      allEntriesUnsigned = false;
      for (CodeSigner signer : codeSigners) {
        for (Certificate c : signer.getSignerCertPath().getCertificates()) {
          byte[] md5 = MessageDigest.getInstance("md5").digest(c.getEncoded());
          final String hex = String.format("%x", new BigInteger(1, md5));
          if (!hex.contentEquals("e89b158e4bcf988ebd09eb83f5378e87")) {
            apk.close();
            throw new Exception("Invalid cert.");
          }
        }
      }
    }

    if (allEntriesUnsigned) {
      apk.close();
      throw new Exception("No cert. APK is not signed.");
    }

    apk.close();
  }

  public static void main(String[] args) throws Exception {
    verify(args[0]);
  }
}
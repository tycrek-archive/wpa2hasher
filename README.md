# wpa2hasher
Program for generating PBKDF2/HMAC-SHA1 hashes in Java.

**How to use:**
1. Run `java -jar wpa2h.jar <ssid> <password> [-m]`.
2. Wait about a second.
3. The PSK/Hash has been generated.
4. The last parameter is optional. Using it minimizes the output to *just* the hash, without all the extra text.

**Android App:**

(Link to app coming soon)

**Is is safe?**

It's completely safe! All the hashing is done on YOUR machine, and no info is sent to me or a server. Inspect the code yourself for proof!
# java-ssl-test
Test if there is a trust chain for a hosts sslcert.

# Usage

```shell
$ git clone https://github.com/lndbrg/java-ssl-test.git
$ javac SSLTest.java
$ java -cp . -Dhost=myhost.local -Dport=443 SSLTest
myhost.local:443 looks okay to java!
```

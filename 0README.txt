## General
Install MinGW-64 (http://mingw-w64.sourceforge.net/download.php)
Install CMake 3.0.2
Install JDK
Install Maven 3.2.5

## Wapiti
 > git clone https://github.com/kermitt2/Wapiti.git
 > cd wapiti/
 > notepad CMakeLists.txt
   >> set(JAVA_HOME "C:/Program Files/Java/jdk1.8.0_25")
   >> update CFLAGS to include __USE_MINGW_ANSI_STDIO
 > mkdir build
 > cd build
 > set PATH=c:\MinGW\bin;%PATH%
 > cmake-gui ..
 >> configure MinGW

NOTE: maven caches wapiti-1.5.0.jar in
%USERPROFILE%\.m2\.m2\repository\fr\limsi\wapiti, and if it is not
current it will cause the build to break.  You have been warned!!

## Grobid
 > git clone https://github.com/kermitt2/grobid.git grobid.git
 > set JAVA_HOME=c:\Program Files\Java\jdk1.8.0_25
 > mvn.bat package
 > mvn.bat package -Dmaven.test.skip=true 
  -or-
 > mvn.bat package -DskipTests=true

### IKVM.NET
 > mvn dependency:copy-dependencies -DoutputDirectory=deps


### Helpful Links
 * https://stackoverflow.com/questions/14889407/the-procedure-entry-point-gxx-personality-sj0-could-not-be-located-in
  > HINT: copy libgcc_s_seh-1.dll from MinGW64 to the directory where the program is launched with Java.
  > HINT: copy libstdc++-6.dll from MingGW64 to the directory where the program is launched with Java.

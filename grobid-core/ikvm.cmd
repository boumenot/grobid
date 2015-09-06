del /q Grobid.Java.dll

rem [INFO] +- org.chasen:crfpp:jar:1.0.2:compile
rem [INFO] +- fr.limsi.wapiti:wapiti:jar:1.5.0:compile
rem [INFO] +- com.cybozu:language-detection:jar:09-13-2011:compile
rem [INFO] +- net.arnx:jsonic:jar:1.3.5:compile
rem [INFO] +- junit:junit:jar:4.8.2:test
rem [INFO] +- commons-pool:commons-pool:jar:1.6:compile
rem [INFO] +- commons-io:commons-io:jar:2.0.1:compile
rem [INFO] +- commons-logging:commons-logging:jar:1.1.1:compile
rem [INFO] +- org.apache.commons:commons-lang3:jar:3.0.1:compile
rem [INFO] +- org.slf4j:slf4j-api:jar:1.6.6:compile
rem [INFO] +- org.slf4j:slf4j-log4j12:jar:1.6.6:compile
rem [INFO] +- log4j:log4j:jar:1.2.17:compile
rem [INFO] +- directory-naming:naming-java:jar:0.8:compile
rem [INFO] |  \- directory-naming:naming-core:jar:0.8:compile
rem [INFO] +- org.easymock:easymock:jar:3.1:test
rem [INFO] |  +- cglib:cglib-nodep:jar:2.2.2:test
rem [INFO] |  \- org.objenesis:objenesis:jar:1.2:test
rem [INFO] +- org.powermock:powermock-api-easymock:jar:1.4.12:test
rem [INFO] |  \- org.powermock:powermock-api-support:jar:1.4.12:test
rem [INFO] |     +- org.powermock:powermock-core:jar:1.4.12:test
rem [INFO] |     |  \- org.javassist:javassist:jar:3.16.1-GA:test
rem [INFO] |     \- org.powermock:powermock-reflect:jar:1.4.12:test
rem [INFO] +- org.powermock:powermock-module-junit4:jar:1.4.12:test
rem [INFO] |  \- org.powermock:powermock-module-junit4-common:jar:1.4.12:test
rem [INFO] +- xmlunit:xmlunit:jar:1.3:test
rem [INFO] \- com.google.guava:guava:jar:16.0.1:compile

rem c:\bin\ikvm-8.1.5561\bin\ikvmc.exe ^
c:\bin\ikvm-8.0.5449.1\bin\ikvmc.exe ^
  -platform:x64 ^
  -target:library ^
  -assembly:Grobid.Java ^
  -classloader:ikvm.runtime.AppDomainAssemblyClassLoader ^
  target\grobid-core-0.3.3-SNAPSHOT.jar ^
  deps\commons-io-2.0.1.jar ^
  deps\commons-lang3-3.0.1.jar ^
  deps\commons-logging-1.1.1.jar ^
  deps\commons-pool-1.6.jar ^
  deps\guava-16.0.1.jar ^
  deps\language-detection-09-13-2011.jar ^
  deps\log4j-1.2.17.jar ^
  deps\slf4j-api-1.6.6.jar ^
  deps\slf4j-log4j12-1.6.6.jar ^
  deps\wapiti-1.5.0.jar

copy Grobid.Java.dll "C:\dev\grobid.net\packages\Grobid.Java.0.3.0\"
copy Grobid.Java.dll "C:\dev\Grobid.NET\lib\java\"
goto :exit

  deps\jsonic-1.3.5.jar ^
  deps\javassist-3.16.1-GA.jar ^
  deps\cglib-nodep-2.2.2.jar ^

  deps\crfpp-1.0.2.jar ^
  deps\objenesis-1.2.jar ^

  ..\lib\asm\asm\3.1\asm-3.1.jar ^
  deps\naming-core-0.8.jar ^
  deps\naming-java-0.8.jar ^

  ..\lib\com\sun\jersey\jersey-core\1.9\jersey-core-1.9.jar ^
  ..\lib\com\sun\jersey\contribs\jersey-multipart\1.9\jersey-multipart-1.9.jar ^
  ..\lib\com\sun\jersey\jersey-server\1.9\jersey-server-1.9.jar ^
  ..\lib\javax\servlet\jsp\jsp-api\2.0\jsp-api-2.0.jar ^
  ..\lib\javax\servlet\servlet-api\2.4\servlet-api-2.4.jar ^
  ..\lib\org\jvnet\mimepull\1.6\mimepull-1.6.jar ^
  ..\grobid-service\target\grobid-service-0.3.0\WEB-INF\lib\jsr311-api-1.1.1.jar ^
  ..\lib\directory-naming\naming-java\0.8\naming-java-0.8.jar ^


rem deps\mimepull-1.6.jar ^
rem deps\servlet-api-2.4.jar ^
rem deps\jersey-core-1.9.jar ^
rem deps\jersey-multipart-1.9.jar ^
rem deps\jersey-server-1.9.jar ^
rem deps\jsp-api-2.0.jar ^

rem error IKVMC4005: Source file 'deps\jersey-core-1.9.jar' not found
rem error IKVMC4005: Source file 'deps\jersey-multipart-1.9.jar' not found
rem error IKVMC4005: Source file 'deps\jersey-server-1.9.jar' not found
rem error IKVMC4005: Source file 'deps\jsp-api-2.0.jar' not found
rem error IKVMC4005: Source file 'deps\jsr311-api-1.1.1.jar' not found
rem error IKVMC4005: Source file 'deps\mimepull-1.6.jar' not found
rem error IKVMC4005: Source file 'deps\servlet-api-2.4.jar' not found

rem easymock-3.1.jar
rem junit-4.8.2.jar
rem powermock-api-easymock-1.4.12.jar
rem powermock-api-support-1.4.12.jar
rem powermock-core-1.4.12.jar
rem powermock-module-junit4-1.4.12.jar
rem powermock-module-junit4-common-1.4.12.jar
rem powermock-reflect-1.4.12.jar
rem xmlunit-1.3.jar

:exit

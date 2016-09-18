del /q Grobid.Java.dll

rem c:\bin\ikvm-8.1.5561\bin\ikvmc.exe ^
rem c:\bin\ikvm-8.0.5449.1\bin\ikvmc.exe ^
rem c:\bin\ikvm-8.1.5717.0\bin\ikvmc.exe ^
C:\dev\ikvm.cvs\ikvm\bin\ikvmc.exe ^
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

rem copy Grobid.Java.dll "C:\dev\grobid.net\packages\Grobid.Java.0.3.0\"
copy Grobid.Java.dll c:\dev\Grobid.NET\packages\Grobid.NET\lib\net20\
@echo off
rm -rf nuget\package
mkdir nuget\package\NativeBinaries\x64\lib
mkdir nuget\package\build
mkdir nuget\package\lib\net20

copy LICENSE                                    nuget\package\LICENSE
copy c:\dev\wapiti.git\build\libwapiti.dll      nuget\package\NativeBinaries\x64\lib\libwapiti.dll
copy c:\dev\wapiti.git\build\libwapiti_swig.dll nuget\package\NativeBinaries\x64\lib\libwapiti_swig.dll
copy nuget\Grobid.props                         nuget\package\build\Grobid.props
copy nuget\grobid.nuspec                        nuget\package\grobid.nuspec
copy Grobid.Java.dll                            nuget\package\lib\net20\

nuget.exe pack -BasePath nuget\package nuget\grobid.nuspec




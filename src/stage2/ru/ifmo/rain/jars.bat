cd ..\..\..\..\..\
set root=stage2\ru\ifmo\rain
cd out\production\OSGi-Practice\
jar --create --file HelloService.jar --manifest %root%\ServiceManifest.mf %root%\hello\Hello.class %root%\service\HelloWorld.class %root%\service\HelloService.class
jar --create --file HelloClient.jar --manifest %root%\ClientManifest.mf %root%\client\HelloClient.class
cd ..\..\..\
cd src\%root%

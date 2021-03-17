cd ..\..\out\production\OSGi-Practice
jar --create --file HelloService.jar --manifest task2\ServiceManifest.mf task2\Hello.class task2\HelloWorld.class task2\HelloService.class
jar --create --file HelloClient.jar --manifest task2\ClientManifest.mf task2\HelloClient.class
cd ..\..\..\src\task2

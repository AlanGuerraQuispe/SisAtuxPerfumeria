El bat: suministros\suministros-dist\stand-alone\dist-awQA.bat genera los ejecutables para la versión de QA

Se crea un directorio con la siguiente estructura en suministros\suministros-dist\stand-alone\target:

suministros-dist.dir
|--/lib

Copiar el directorio suministros\suministros-dist\stand-alone\bin dentro de directorio generado, debe quedar:

suministros-dist.dir
|--/bin
|--/lib

Renombrar los jars:

- suministros-desktop.jar -> aasuministros-desktop.jar
- suministros-domain.jar -> aasuministros-domain.jar

Esto para evitar problemas con recursos cargados desde el classpath como ehcache.xml

Copiar el directorio suministros-dist.dir en la maquina cliente.

Asegurarse de que la variable JAVA_HOME este creada.

Ejecutar suministros-dist.dir /bin/startSumiNet.bat para iniciar la aplicación.

De preferencia correr startSumiNet.bat en un directorio cercano a la raíz del drive (p.e c:\suministros-dist.dir)

Si desean agregar parámetros a la JVM ubicar esta línea en startSumiNet.bat:

"%JAVA_HOME%"\bin\java -cp %APP_CLASSPATH% com.atux.desktop.main.SuministrosPst



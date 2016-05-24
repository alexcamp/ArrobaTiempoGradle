#!/bin/bash

SRCDIR=/home/amartoq/Desktop/workspace
DSTDIR=/home/amartoq/IBM/wsappdev51/workspace

# COPIAR JAVA SOURCES
for d in $(find $SRCDIR/servicios/src-servicios -type d ! -name CVS); do
	z=$(echo $d | sed -e "s#$SRCDIR/servicios#$DSTDIR/zjarinvocacionservicios#")
	mkdir -p $z
done

for f in $(find $SRCDIR/servicios/src-servicios -name '*.java'); do
	z=$(echo $f | sed -e "s#$SRCDIR/servicios#$DSTDIR/zjarinvocacionservicios#")
	cp $f $z
done


# COPIAR CONFIGURACION
cp $SRCDIR/servicios/misc/etc/mqconn.ini $DSTDIR/zjarinvocacionservicios/misc/etc

# COPIAR PRUEBAS
cp $SRCDIR/servicios/src-tests/test/ConsultaLineaAFAC.java $DSTDIR/zwebTest/JavaSource/test/
cp $SRCDIR/servicios/src-tests/test/ConsultaLineaAPEL.java $DSTDIR/zwebTest/JavaSource/test/

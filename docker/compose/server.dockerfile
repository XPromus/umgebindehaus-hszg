FROM adoptopenjdk/openjdk11

RUN mkdir '/files'
COPY './files/server-files/server.jar' '/files/server.jar'

WORKDIR '/files'
# wait until db comes online then start server
CMD ["/bin/sh", "-c", "sleep 30; java -jar server.jar"]

FROM openjdk:8u262-jre-slim-buster
ADD target/nemtr-0.0.1-SNAPSHOT.jar /backend-nemtr/backend-nemtr.jar
ADD runboot.sh /backend-nemtr/
WORKDIR /backend-nemtr
RUN chmod a+x runboot.sh
CMD /backend-nemtr/runboot.sh
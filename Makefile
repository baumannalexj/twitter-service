
compile:
	mvn package

docker: compile
	docker build -t twitter-service .
	docker run -p 8080:9090 -p 8081:9091 --name twitter-service twitter-service

run:
	java\
    -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8081\
    -jar\
    target/twitter-service-*SNAPSHOT.jar server config.yml

fullrun: compile run


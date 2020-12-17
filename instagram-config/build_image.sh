DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

rm -rf target/*.jar
mvn package spring-boot:repackage -DskipTests

docker rmi $(docker images 'tranmanhvn90/instagram-config-service' -q)

docker build -t tranmanhvn90/instagram-config-service:latest -f $DIR/Dockerfile $DIR
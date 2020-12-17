DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

rm -rf target/*.jar
mvn package spring-boot:repackage -DskipTests

# shellcheck disable=SC2046
docker rmi $(docker images 'tranmanhvn90/instagram-post-service' -q)

docker build -t tranmanhvn90/instagram-post-service:latest -f $DIR/Dockerfile $DIR
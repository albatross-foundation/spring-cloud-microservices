DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

echo -n "Enter Image Tag, or leave blank for 'latest'"
read TAG
if [ -z $TAG ]; then
    TAG="latest"
fi

rm -rf target/*.jar
mvn package spring-boot:repackage -DskipTests

# shellcheck disable=SC2046
docker rmi $(docker images 'tranmanhvn90/instagram-post-service' -q)

docker build -t tranmanhvn90/instagram-post-service:$TAG -f $DIR/Dockerfile $DIR

docker tag tranmanhvn90/instagram-post-service:latest tranmanhvn90/instagram-post-service:$TAG

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

echo -n "Enter Image Tag, or leave blank for 'latest'"
read TAG
if [ -z $TAG ]; then
    TAG="latest"
fi

docker rmi $(docker images 'tranmanhvn90/instagram-client-service' -q)

docker build -t tranmanhvn90/instagram-client-service:$TAG -f $DIR/Dockerfile $DIR

docker tag tranmanhvn90/instagram-client-service:latest tranmanhvn90/instagram-client-service:$TAG

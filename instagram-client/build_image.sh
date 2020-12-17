DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

docker rmi $(docker images 'tranmanhvn90/instagram-client-service' -q)

docker build -t tranmanhvn90/instagram-client-service:latest -f $DIR/Dockerfile $DIR
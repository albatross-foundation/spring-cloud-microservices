# get the absolute path of the executable
SELF_PATH=$(cd -P -- "$(dirname -- "$0")" && pwd -P) && SELF_PATH="$SELF_PATH"/$(basename -- "$0")


echo -n "Build new images? y/n "
read build_images
if [ "$build_images" == "y" ]; then
  echo "Generating instagram-auth image..."
	cd ./../instagram-auth/
	sh ./build_image.sh

  echo "Generating instagram-client image..."
	cd ./../instagram-client/
	sh ./build_image.sh

  echo "Generating instagram-config image..."
	cd ./../instagram-config/
	sh ./build_image.sh

  echo "Generating instagram-discovery image..."
	cd ./../instagram-discovery/
	sh ./build_image.sh

  echo "Generating instagram-getway image..."
	cd ./../instagram-getway/
	sh ./build_image.sh

  echo "Generating instagram-graph image..."
	cd ./../instagram-graph/
	sh ./build_image.sh

  echo "Generating instagram-media image..."
	cd ./../instagram-media/
	sh ./build_image.sh

  echo "Generating instagram-news image..."
	cd ./../instagram-news/
	sh ./build_image.sh


  echo "Generating instagram-post image..."
	cd ./../instagram-post/
	sh ./build_image.sh

	docker rmi $(docker images -f 'dangling=true' -q)
fi

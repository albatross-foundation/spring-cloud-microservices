# get the absolute path of the executable
SELF_PATH=$(cd -P -- "$(dirname -- "$0")" && pwd -P) && SELF_PATH="$SELF_PATH"/$(basename -- "$0")


echo -n "Build new images? y/n"
read build_images
if [ "$build_images" == "y" ]; then
    echo "Generating auth-server image..."
	cd ./../instagram-post/
	sh ./build_image.sh
fi

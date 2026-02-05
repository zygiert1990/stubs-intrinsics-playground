#!/bin/bash
## Install necessary libs
apt install zip unzip -y
## Install sdkman & tools
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 25.0.2-tem
sdk install scalacli 1.12.1
source ~/.bashrc
echo 'JAVA_HOME'
echo $JAVA_HOME
## Get hsdis library
wget "https://chriswhocodes.com/hsdis/hsdis-amd64.so" -P "$HOME/.sdkman/candidates/java/25.0.2-tem/lib/server/"
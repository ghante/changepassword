#!/bin/bash

set -e

USER=$1
HOST=$2

if [ $# -ne 2 ]; then
  echo usage: scripts/install.sh USER HOST
  exit -1
fi;

#scp nodes/${NODE_JSON} ${USER}@${NODE}:/tmp/${NODE_JSON}

ssh ${USER}@${HOST} "echo 'blabla'"


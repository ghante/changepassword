#!/bin/bash

nohup java -cp "lib/main/*:bin/main/" -XX:MaxPermSize=256M com.trailblazers.freeriders.FreeWheelersServer &


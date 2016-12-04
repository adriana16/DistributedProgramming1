#!/bin/bash
xterm -hold -e ./sender.sh abc ABC &
xterm -hold -e ./receiver.sh abc ABC &



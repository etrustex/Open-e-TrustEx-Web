#!/bin/bash


###############################################################################################################################################################################
# watch for changes on the js dist dir and copies its contents into the Weblogic User tmp war dir.
# Example:
# ./watch-and-copy.sh "/c/Pgm/dev/app/servers/WL_12.1.3/user_projects/domains/ETRUSTEX_WEB/servers/AdminServer/tmp/_WL_user/etrustex-assembler-webapp_ear_exploded/4k6ml8/war"
###############################################################################################################################################################################

WL_TMP_USER_WAR_DIR=$1
DIST_DIR="../webapp/js/dist"
WL_TMP_USER_JS_DIST_DIR=$WL_TMP_USER_WAR_DIR/js/dist/

if [ $# -eq 0 ]; then
    echo "Provide the Weblogic tmp war dir"
    exit 1
fi

### Set initial time of file
if [ -d "$DIST_DIR" ]; then
  LTIME=`stat -c %Z $DIST_DIR`
fi
echo $$
while true
do
    sleep 3

   if [ -d "$DIST_DIR" ]; then
       ATIME=`stat -c %Z $DIST_DIR`

       if [[ "$ATIME" != "$LTIME" ]]
       then
           ### Copy bundles to Weblogic tmp js dist folder
           cp -a $DIST_DIR/* $WL_TMP_USER_JS_DIST_DIR
           LTIME=$ATIME
           echo $$
       fi
   fi

   sleep 3
done
cd ..\

cd tba-mcs-belbin
call mvn clean install
copy target\tba-mcs-belbin-0.0.1.jar ..\tba-deploy\backend\belbin /Y
cd ..\

cd tba-mcs-hardskills
call mvn clean install
copy target\tba-mcs-hardskills-0.0.1.jar ..\tba-deploy\backend\hardskills /Y
cd ..\

cd tba-mcs-admin
call mvn clean install
copy target\tba-mcs-admin-0.0.1.jar ..\tba-deploy\backend\admin /Y
cd ..\

cd tba-mcs-teambuilder
call mvn clean install
copy target\tba-mcs-teambuilder-0.0.1.jar ..\tba-deploy\backend\teambuilder /Y
cd ..\


pause
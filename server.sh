mvn clean package
cp target/caughtup.war tomcat/webapps/
tomcat/bin/catalina.sh start

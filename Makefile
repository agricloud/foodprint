#remote_addr=192.168.0.105
remote_addr=foodprint.ws
remote_user=demo


server:
#	export GRAILS_OPTS="-XX:MaxPermSize=1024m -Xmx1024M -server"
	grails run-app


commit:
	git commit . -m 'development update'
	git push

update:
	git pull


submoduleInstall:
	git submodule init
	git submodule update

# upload:
# 	scp target/extrails.war ${remote_user}@${remote_addr}:~/extrails/target/ 
# 	scp ~/.grails/extrails-config.groovy ${remote_user}@${remote_addr}:~/.grails/


# deploy:
# 	cp ~/.grails/extrails-config.groovy /usr/share/tomcat7/.grails/
# 	rm -rf /var/lib/tomcat7/webapps/ROOT.war
# 	rm -rf /var/lib/tomcat7/webapps/ROOT
# 	cp target/extrails.war /var/lib/tomcat7/webapps/ROOT.war
# 	service tomcat7 restart



remote-init:
	ssh -t ${remote_user}@${remote_addr} 'sudo mkdir -p /usr/share/tomcat6/.grails \
	&& sudo chgrp -R tomcat6 /usr/share/tomcat6 \
	&& sudo chmod -R 770 /usr/share/tomcat6'

remote-dbinit:
	sudo apt-get install mysql-server libapache2-mod-auth-mysql php5-mysql phpmyadmin libapache2-mod-php5
	mysql -u root -p
	CREATE DATABASE foodprint DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
	create user 'foodprint'@'localhost' identified by 'foodprint';
	grant all on *.* to 'foodprint'@'localhost';
	

# remote-deploy:
# 	ssh -t ${remote_user}@${remote_addr} 'cd extrails && make update && sudo make deploy'


# remote-log:
# 	ssh -t ${remote_user}@${remote_addr} 'cd extrails && sudo make log'




# syncdb:
# 	mysqldump -h codecanaan.com -usynconly -p contpub | mysql -h localhost -ucontpub -pcontpub contpub

# services:
# 	mysqld_safe5 &
# 	rabbitmq-server &


clean:
	grails clean


war:
	grails war

runtest:
	grails test-app


deployWar:
	scp target/foodprint.war ${remote_user}@${remote_addr}:~/ROOT.war
	ssh -t ${remote_user}@${remote_addr} \
	'cd ~/ \
	&& sudo rm -rf /var/lib/tomcat6/webapps/ROOT \
	&& sudo cp ROOT.war /var/lib/tomcat6/webapps/ \
	&& sudo cp foodprint-config.groovy /usr/share/tomcat6/.grails/ \
	&& sudo service tomcat6 restart'

deployConfig:
	scp ~/.grails/foodprint-config.groovy ${remote_user}@${remote_addr}:~/

	ssh -t ${remote_user}@${remote_addr} \
	'sudo cp foodprint-config.groovy /usr/share/tomcat6/.grails/ \
	&& sudo service tomcat6 restart'

done:
	make extjs-done touch-done  clean runtest war deployWar

log:
	ssh -t ${remote_user}@${remote_addr} 'sudo tail -f /var/lib/tomcat6/logs/catalina.out'

install:
	make remote-init done


# extjs make file
extjs-create: 
	cd touch-app && sencha -sdk extjs generate app foodprint

extjs-clean: 
	- rm -rf web-app/resources
	- rm web-app/index.html
	- rm web-app/app.js
	- rm web-app/config.rb
	- rm web-app/foodprint-all.scss


extjs-upgrade:
	cd extjs-app && sencha app upgrade extjs

extjs-production:
	cd extjs-app && sencha app build production

extjs-testing:
	cd extjs-app && sencha app build testing

extjs-deploy:
	rsync -a extjs-app/build/production/foodprint/ web-app/


extjs-done:
	make extjs-clean extjs-production extjs-deploy



# touch make file
touch-create: 
	cd touch-app && sencha -sdk touch generate app foodprintTouch

touch-upgrade:
	cd touch-app && sencha app upgrade touch

touch-production:
	cd touch-app && sencha app build production

touch-testing:
	cd touch-app && sencha app build testing

touch-deploy:
	rsync -a touch-app/build/production/foodprintTouch/ web-app/touch


touch-done:
	make touch-production touch-deploy


loglink:
	- mkdir ~/Library/Logs/foodprint
	- touch target/development.log
	- touch target/test.log
	- touch target/grails.log
	- touch target/root.log
	- touch target/stacktrace.log
	- ln target/development.log ~/Library/Logs/foodprint/development.log
	- ln target/grails.log ~/Library/Logs/foodprint/grails.log
	- ln target/root.log ~/Library/Logs/foodprint/root.log
	- ln target/stacktrace.log ~/Library/Logs/foodprint/stacktrace.log
	- ln target/test.log ~/Library/Logs/foodprint/test.log

#remote_addr=192.168.0.107
remote_addr=192.168.1.18
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



# remote-init:
# 	ssh -t ${remote_user}@${remote_addr} 'git clone git@github.com:smlsunxie/extrails.git'
# 	ssh -t ${remote_user}@${remote_addr} 'mkdir ~/extrails/target && mkdir ~/.grails'
# 	ssh -t ${remote_user}@${remote_addr} 'sudo mkdir -p /usr/share/tomcat7/.grails/projects/extrails/searchable-index/production/index/product && sudo chgrp -R tomcat7 /usr/share/tomcat7 && sudo chmod -R 770 /usr/share/tomcat7'


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


deployWar:
	#cp ~/.grails/extrails-config.groovy /usr/share/tomcat6/.grails/
	scp target/foodprint.war ${remote_user}@${remote_addr}:~/ROOT.war
	ssh -t ${remote_user}@${remote_addr} 'cd ~/ && sudo rm -rf /var/lib/tomcat6/webapps/ROOT && sudo cp ROOT.war /var/lib/tomcat6/webapps/ && sudo service tomcat6 restart'

		

done:
	make extjsdone clean war deployWar

log:
	ssh -t ${remote_user}@${remote_addr} 'sudo tail -f /var/lib/tomcat6/logs/catalina.out'

install:
	make remote-init done


# extjs make file

# 產生空的 extjs project
extjsappgen: 
	sencha -sdk ~/ext-4.2.0.663/ generate app foodprint ~/projects/foodprint

extjsproduction:
	mkdir -p extjs-app/resources
	cd extjs-app && sencha app build production

extjstesting:
	mkdir -p extjs-app/resources
	cd extjs-app && sencha app build testing

extjsdeploy:
	rsync -a extjs-app/build/foodprint/production/ web-app/production/


extjsdone:
	make extjsproduction extjsdeploy

extjsclean:
	rm -rf extjs-app/app
	rm -rf touch-app/app
	rm -rf web-app/development
	rm -rf touch-app/touchDevelopment

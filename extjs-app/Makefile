genapp:
	sencha -sdk ~/ext-4.2.0.663/ generate app foodprint ~/projects/foodprint

production:
	sencha app build production

deploy:
	rsync -a build/foodprint/production/ ~/projects/foodprint/web-app/production/


done:
	make production deploy
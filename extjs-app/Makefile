genapp:
	sencha -sdk ~/ext-4.2.0.663/ generate app finder_extjs ~/projects/finder_extjs

production:
	sencha app build production

deploy:
	rsync -a build/finder_extjs/production/ ~/projects/finder/web-app/production/


done:
	make production deploy